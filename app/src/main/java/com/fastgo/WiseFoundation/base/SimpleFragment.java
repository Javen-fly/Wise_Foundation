package com.fastgo.WiseFoundation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 无MVP的Fragment基类
 */
public abstract class SimpleFragment extends SupportFragment implements BaseView {

    protected View mView;
    protected SimpleActivity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInit = false;

    @Override
    public void onAttach(Context context) {
        if(context instanceof SimpleActivity){
            mActivity = (SimpleActivity) context;
            mContext = context;
        }else {
            throw new IllegalStateException("SimpleFragment 必须 依赖于 SimpleActivity");
        }

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if(!isInit){
            init();
            initUI();
            isInit = true;
        }
    }

    @Override
    public void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }

    /**
     * 提示框
     * @param msg
     */
    public void showTipDialog(int opsStatus,String msg){

    };

    /**
     * 提示框
     * @param msg
     */
    public void showTipDialog(String msg){

    };
    /**
     * 加载框
     * @param msg
     */
    public void statusLoading(String msg){

    };

    /**
     * 吐司
     * @param opsStatus
     * @param msg
     */
    public void showToast(int opsStatus,String msg){

    };

    /**
     * 吐司
     * @param msg
     */
    public void showToast(String msg){

    };

    /**
     * 布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化
     */
    protected void init(){

    };

    /**
     * 初始化UI
     */
    protected void initUI(){

    };
}
