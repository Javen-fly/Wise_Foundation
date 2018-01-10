package com.fastgo.WiseFoundation.base;

/**
 * View基类
 */
public interface BaseView {
    /**
     * 提示框
     * @param msg
     */
    void showTipDialog(int opsStatus, String msg);

    /**
     * 提示框
     * @param msg
     */
    void showTipDialog(String msg);
    /**
     * 加载框
     * @param msg
     */
    void statusLoading(String msg);

    /**
     * 吐司
     * @param opsStatus
     * @param msg
     */
    void showToast(int opsStatus, String msg);

    /**
     * 吐司
     * @param msg
     */
    void showToast(String msg);
}
