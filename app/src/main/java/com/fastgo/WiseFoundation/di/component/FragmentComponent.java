package com.fastgo.WiseFoundation.di.component;

import android.app.Activity;

import com.fastgo.WiseFoundation.di.module.FragmentModule;
import com.fastgo.WiseFoundation.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

//    void inject(DailyFragment dailyFragment);
}
