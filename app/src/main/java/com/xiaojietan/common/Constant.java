package com.xiaojietan.common;

import android.os.Environment;

import com.xiaojietan.testbase.BuildConfig;


/**
 * 常量定义
 * Created by xiaojie.tan on 2017/10/26
 */
public class Constant {
    public static final String APPLICATION_NAME = "wztc";
    public static final String APP_CACHE_BASE_DIR = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/" + APPLICATION_NAME;
    public static final String APP_VIDEO_CACHE_DIR = APP_CACHE_BASE_DIR
            + "/video/";

    public static final String SERVER_BASE_URL = BuildConfig.HTTP_SERVER_URL;;
}
