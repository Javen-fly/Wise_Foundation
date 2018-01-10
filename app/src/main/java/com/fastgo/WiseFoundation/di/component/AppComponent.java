package com.fastgo.WiseFoundation.di.component;


import com.fastgo.WiseFoundation.common.APP;
import com.fastgo.WiseFoundation.di.module.AppModule;
import com.fastgo.WiseFoundation.di.module.HttpModule;
import com.fastgo.WiseFoundation.model.DataManager;
import com.fastgo.WiseFoundation.model.db.GreenDaoProvider;
import com.fastgo.WiseFoundation.model.http.RetrofitProvider;
import com.fastgo.WiseFoundation.model.prefs.SPProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    APP getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitProvider getRetrofitProvider();  //提供http的帮助类

    GreenDaoProvider getDBProvider();    //提供数据库帮助类

    SPProvider getSPProvider(); //提供sp帮助类
}
