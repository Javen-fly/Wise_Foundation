package com.fastgo.WiseFoundation.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastgo.WiseFoundation.R;
import com.kehua.ui.LoadingView.KHLoadingView;

/**
 *带有加载状态的Fragment基类
 */
public abstract class StatusFragment<T extends BasePresenter> extends MVPFragment<T> implements DataStatusView{

    public static final int STATUS_SUCCESS = 0x00;
    public static final int STATUS_LOADING = 0x01;
    public static final int STATUS_ERROR = 0x02;

    private KHLoadingView mLoadingView;
    private View viewError;
    private View viewLoading;
    private View viewMain;
    private ViewGroup mParent;

    private int mErrorResource = R.layout.view_error;

    private int currentSTATUS = STATUS_SUCCESS;
    private boolean isErrorViewAdded = false;

    /**
     * 获取主布局Id
     * @return
     */
    protected abstract int getMainViewId();
    
    @Override
    protected void initUI() {
        if (getView() == null)
            return;
        viewMain = getView().findViewById(getMainViewId());
        if (viewMain == null) {
            throw new IllegalStateException(
                    "必须提供有效的主布局ID");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "'主布局'的父布局必须是ViewGroup");
        }
        mParent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_progress, mParent);
        viewLoading = mParent.findViewById(R.id.view_loading);
        mLoadingView = (KHLoadingView) viewLoading.findViewById(R.id.loading);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    /**
     * 重新加载
     */
    protected void reTry(){

    }

    @Override
    public void statusError() {
        errorOrEmpty(false);
    }

    @Override
    public void statusEmpty() {
        errorOrEmpty(true);
    }

    private void errorOrEmpty(boolean empty){
        if (currentSTATUS == STATUS_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, mErrorResource, mParent);
            viewError = mParent.findViewById(R.id.view_error);
            if (viewError == null) {
                throw new IllegalStateException(
                        " 'view_error' 布局错误");
            }
        }
        hideCurrentView();
        currentSTATUS = STATUS_ERROR;
        ImageView errorIcon = (ImageView) viewError.findViewById(R.id.error_icon);
        TextView errorMsg = (TextView) viewError.findViewById(R.id.error_message);
        if(empty){
            errorIcon.setImageResource(R.drawable.icon_empty);
            errorMsg.setText("没有任何数据！\n点击屏幕 重新加载");
        }else {
            errorIcon.setImageResource(R.drawable.icon_error);
            errorMsg.setText("加载失败！\n点击屏幕 重新加载");
        }
        viewError.setVisibility(View.VISIBLE);
        viewError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reTry();
            }
        });
    }

    @Override
    public void statusLoadingInLayout(String msg) {
        if (currentSTATUS == STATUS_LOADING)
            return;
        hideCurrentView();
        currentSTATUS = STATUS_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        mLoadingView.start();
    }

    @Override
    public void statusSuccess() {
        if (currentSTATUS == STATUS_SUCCESS)
            return;
        hideCurrentView();
        currentSTATUS = STATUS_SUCCESS;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentSTATUS) {
            case STATUS_SUCCESS:
                viewMain.setVisibility(View.GONE);
                break;
            case STATUS_LOADING:
                mLoadingView.stop();
                viewLoading.setVisibility(View.GONE);
                break;
            case STATUS_ERROR:
                if (viewError != null) {
                    viewError.setVisibility(View.GONE);
                }
                break;
        }
    }
}
