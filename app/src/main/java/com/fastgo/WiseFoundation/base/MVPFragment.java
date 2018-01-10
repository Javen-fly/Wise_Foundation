package com.fastgo.WiseFoundation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fastgo.WiseFoundation.common.APP;
import com.fastgo.WiseFoundation.di.component.DaggerFragmentComponent;
import com.fastgo.WiseFoundation.di.component.FragmentComponent;
import com.fastgo.WiseFoundation.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * MVP Fragment基类
 */
public abstract class MVPFragment<T extends BasePresenter> extends SimpleFragment{

    private boolean isInjected;

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(APP.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(!isInjected){
            initInject();
            isInjected = true;
        }
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    protected abstract void initInject();
}