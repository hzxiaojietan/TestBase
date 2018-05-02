package cn.hzxiaojietan.base.application;

import android.content.Context;
import android.os.Build;

import org.litepal.LitePalApplication;

import cn.hzxiaojietan.base.business.common.AppStatusConstant;
import cn.hzxiaojietan.base.business.common.AppStatusManager;

/**
 * Created by xiaojie.tan on 2017/10/27
 */
public class BaseApplication extends LitePalApplication {
    private static final String TAG = "BaseApplication";
    private static Context context;
    public static String MOBILE_TYPE;
    public static String OS_VERSION;

    public void onCreate() {
        super.onCreate();
        //进入应用初始化设置成正常状态
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);
        BaseApplication.context = getApplicationContext();
        MOBILE_TYPE = Build.MODEL;
        OS_VERSION = Build.VERSION.RELEASE;

        // 检查内存泄漏问题
//        LeakCanary.install(this);

//        AppLog.init(BuildConfig.DEBUG);
    }

    public static Context getAppContext() {
        return BaseApplication.context;
    }

    public static String channelIdString;

    public static String getChannelIdString() {
        return channelIdString;
    }

    public static void setChannelIdString(String channelIdString) {
        BaseApplication.channelIdString = channelIdString;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //加这段是因为在4.x版本的手机上，photoview会出现: java.lang.NoClassDefFoundError: uk.co.senab.photoview.PhotoViewAttacher 这个bug，导致查看图片失败.
        //refer to : https://github.com/chrisbanes/PhotoView/issues/336
//        MultiDex.install(this);
    }
}
