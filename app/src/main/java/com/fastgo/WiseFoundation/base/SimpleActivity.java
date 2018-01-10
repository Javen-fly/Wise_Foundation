package com.fastgo.WiseFoundation.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.fastgo.WiseFoundation.R;
import com.fastgo.WiseFoundation.common.APP;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 无MVP的Activity基类
 */
public abstract class SimpleActivity extends SupportActivity implements BaseView{

    protected Activity mContext;

    private Unbinder mUnBinder;

    private boolean mViewCreated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        APP.getInstance().setCurrentActivity(this);
        init();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if(!mViewCreated){
            onViewCreated();
            initUI();
            mViewCreated = true;
        }
        return super.onCreateView(name, context, attrs);
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        APP.getInstance().setCurrentActivity(null);
        mUnBinder.unbind();
    }

    /**
     * 统一动画弹出Activity
     * @param intent
     */
    public void startActivityEx(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.window_left_in,
                R.anim.window_left_out);
    }

    /**
     * 统一动画结束Activity
     */
    public void finishEx() {
        finish();
        overridePendingTransition(R.anim.window_right_in,
                R.anim.window_right_out);
    }

    /**
     * 底部弹出Activity
     * @param intent
     */
    public void startActivityFB(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.move_in_from_bottom,
                R.anim.motionless_anim);
    }

    /**
     * 从底部结束Activity
     */
    public void finishFB() {
        finish();
        overridePendingTransition(R.anim.motionless_anim,
                R.anim.move_out_from_bottom);
    }

    /**
     * 弹出键盘
     *
     * @param editText
     */
    public void showKeyBoard(final EditText editText) {
        editText.setFocusable(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(editText, 0);
                           }

                       },
                200);
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard() {
        //关闭键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 是否前台
     * @return
     */
    private boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        String packageName = getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
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
