package cn.hzxiaojietan.base.common.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class DialogUtils {
    private static final String TAG = "DialogUtils";
    private static KProgressHUD hud;

    public static void showHUD(Context context, String message, boolean isCancel) {
        hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(message)
                .setCancellable(isCancel)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f).show();
    }

    public static void showHUD(Context context) {
        showHUD(context, "请求数据中...",true);
    }

    public static void dismissHUD() {
        if (hud != null) {
            hud.dismiss();
        }
    }

    public static void setCancellableDialog(boolean isCancel) {
        if (hud != null) {
            hud.setCancellable(isCancel);
        }
    }
}
