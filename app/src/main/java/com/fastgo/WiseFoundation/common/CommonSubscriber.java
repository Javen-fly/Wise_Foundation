package com.fastgo.WiseFoundation.common;

import com.fastgo.WiseFoundation.base.DataStatusView;
import com.fastgo.WiseFoundation.base.StatusActivity;
import com.fastgo.WiseFoundation.model.http.exception.ApiException;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 *
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private DataStatusView mView;
    private boolean enableErrorToast;
    private boolean enableErrorTip = true;
    private boolean enableStatusLoadingInLayout;
    private boolean enableStatusLoading = true;

    protected CommonSubscriber(DataStatusView view){
        this.mView = view;
    }

    protected CommonSubscriber(DataStatusView view,
                               boolean enableErrorTip,
                               boolean enableErrorToast,
                               boolean enableStatusLoading,
                               boolean enableStatusLoadingInLayout){
        this.mView = view;
        this.enableErrorToast = enableErrorToast;
        this.enableErrorTip = enableErrorTip;
        this.enableStatusLoading = enableStatusLoading;
        this.enableStatusLoadingInLayout = enableStatusLoadingInLayout;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mView == null) {
            return;
        }
        if(enableStatusLoading)
            mView.statusLoading(null);
        else if(enableStatusLoadingInLayout)
            mView.statusLoadingInLayout(null);
    }

    @Override
    public void onComplete() {
        super.onStart();
        if (mView == null) {
            return;
        }
        mView.statusSuccess();
    }


    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (e instanceof ApiException) {
            if(enableErrorTip)
                mView.showTipDialog(StatusActivity.STATUS_ERROR,e.toString());
            else if(enableErrorToast)
                mView.showToast(StatusActivity.STATUS_ERROR,e.toString());
        } else if (e instanceof HttpException) {
            if(enableErrorTip)
                mView.showTipDialog(StatusActivity.STATUS_ERROR,"数据加载失败");
            else if(enableErrorToast)
                mView.showToast(StatusActivity.STATUS_ERROR,"数据加载失败");
        } else {
            if(enableErrorTip)
                mView.showTipDialog(StatusActivity.STATUS_ERROR,"未知错误");
            else if(enableErrorToast)
                mView.showToast(StatusActivity.STATUS_ERROR,"未知错误");
        }

        mView.statusError();
    }
}
