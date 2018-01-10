package com.fastgo.WiseFoundation.common;

import android.os.Environment;

import java.io.File;

/**
 * 常量类
 */
public class Constants {

    //=============================EventCode=============================

    //=============================IntentKey=============================

    //================================SP=================================

    //============================== PATH ===============================

    public static final String PATH_DATA = APP.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "APP";

    //=============================== URL ================================

    public static final String URL_KH_API = "";
}
