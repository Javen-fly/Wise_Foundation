package com.fastgo.WiseFoundation.di.component;

import android.app.Activity;

import com.fastgo.WiseFoundation.di.module.ActivityModule;
import com.fastgo.WiseFoundation.di.scope.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

//    void inject(WelcomeActivity welcomeActivity);

}
