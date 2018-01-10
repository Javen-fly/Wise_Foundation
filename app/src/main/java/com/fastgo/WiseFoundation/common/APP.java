package com.fastgo.WiseFoundation.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.fastgo.WiseFoundation.di.component.AppComponent;
import com.fastgo.WiseFoundation.di.component.DaggerAppComponent;
import com.fastgo.WiseFoundation.di.module.AppModule;
import com.fastgo.WiseFoundation.di.module.HttpModule;
import com.kehua.utils.tools.KHUtils;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Application
 */
public class APP extends Application {
    public static AppComponent appComponent;

    private Reference<Activity> _currentActivity;

    protected static APP _INSTANCE;

    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    public static APP getInstance(){
        if(_INSTANCE == null){
            synchronized(APP.class){
                if(_INSTANCE == null)
                    _INSTANCE = new APP();
            }
        }
        return _INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!shouldInit())
            return;
        _INSTANCE = this;
        //初始化
        KHUtils.init(this);
    }

    /**
     * 设置当前Activity
     * @param activity
     */
    public void setCurrentActivity(Activity activity) {
        if (_currentActivity != null)
            _currentActivity.clear();
        if(activity==null)
            _currentActivity = null;
        else
            _currentActivity = new WeakReference<>(activity);
    }

    /**
     * 获取当前Activity
     * @return
     */
    public Activity getCurrentActivity() {
        if (_currentActivity != null) {
            Activity ref = _currentActivity.get();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                if (ref==null||ref.isDestroyed())
                    return null;
                else
                    return ref;
            }else{
                if (ref==null||ref.isFinishing())
                    return null;
                else
                    return ref;
            }
        }
        return null;
    }


    /**
     * 是否需要初始化
     * @return
     */
    public boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
