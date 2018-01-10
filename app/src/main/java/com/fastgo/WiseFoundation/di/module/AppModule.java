package com.fastgo.WiseFoundation.di.module;


import com.fastgo.WiseFoundation.common.APP;
import com.fastgo.WiseFoundation.model.DataManager;
import com.fastgo.WiseFoundation.model.db.GreenDaoProvider;
import com.fastgo.WiseFoundation.model.db.IDBProvider;
import com.fastgo.WiseFoundation.model.http.IHttpProvider;
import com.fastgo.WiseFoundation.model.http.RetrofitProvider;
import com.fastgo.WiseFoundation.model.prefs.ISPProvider;
import com.fastgo.WiseFoundation.model.prefs.SPProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {
    private final APP application;

    public AppModule(APP application) {
        this.application = application;
    }

    @Provides
    @Singleton
    APP provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    IHttpProvider provideRetrofitProvider(RetrofitProvider provider) {
        return provider;
    }

    @Provides
    @Singleton
    IDBProvider provideDBProvider(GreenDaoProvider provider) {
        return provider;
    }

    @Provides
    @Singleton
    ISPProvider provideSPProvider(SPProvider provider) {
        return provider;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(IHttpProvider retrofitProvider, IDBProvider dbProvider, ISPProvider spProvider) {
        return new DataManager(retrofitProvider, dbProvider, spProvider);
    }
}
