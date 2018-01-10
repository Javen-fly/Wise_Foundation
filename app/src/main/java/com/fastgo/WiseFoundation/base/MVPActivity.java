package com.fastgo.WiseFoundation.base;

import com.fastgo.WiseFoundation.common.APP;
import com.fastgo.WiseFoundation.di.component.ActivityComponent;
import com.fastgo.WiseFoundation.di.component.DaggerActivityComponent;
import com.fastgo.WiseFoundation.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * MVP activity基类
 */
public abstract class MVPActivity<T extends BasePresenter> extends SimpleActivity{

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .appComponent(APP.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


    protected abstract void initInject();
}