package com.xiaojietan.common;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.xiaojietan.business.model.bean.Tag;
import com.xiaojietan.business.model.bean.User;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 用户信息管理工具类
 * Created by xiaojie.tan on 2017/10/26
 */
public class Util {
    private static final String TAG = "Util";
    protected static final String PREFS_FILE = "device_id";
    protected static final String PREFS_DEVICE_ID = "device_id";
    private static String uuid;
    private static final String TOKEN_KEY = "test_base_token";
    private static final String USERID_KEY = "test_base_user_id";
    private static final String CONFIG_FILE = "config_file";
    private static final String ORDER_NO = "order_no";
    private static String token;
    private static User currentUser;
    private static Map<Integer, List<Tag>> tagMap = new HashMap<>();
    private static int mOrderNo = -1;    //当前罚单编号

    public static String getAndroidId() {
        /*
            Reference: http://www.devwiki.net/2015/11/18/Android-Device-Code/
         */
        Context context = TestBaseApplication.getAppContext();

        if(uuid == null) {
            final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
            final String id = prefs.getString(PREFS_DEVICE_ID, null );

            if (id != null) {
                // Use the ids previously computed and stored in the prefs file
                uuid = id;
            } else {
                final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

                // Use the Android ID unless it's broken, in which case fallback on deviceId,
                // unless it's not available, then fallback on a random number which we store
                // to a prefs file
                try {
                    if (!"9774d56d682e549c".equals(androidId)) {
                        uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
                    } else {
                        final String deviceId = ((TelephonyManager) context.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
                        if (deviceId != null) {
                            uuid = UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString();
                        } else {
                            uuid = UUID.randomUUID().toString();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                // Write the value out to the prefs file
                prefs.edit().putString(PREFS_DEVICE_ID, uuid).commit();
            }
        }

        return uuid;
    }

    public static void saveToken(String token) {
        Context context = TestBaseApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.commit();
        Util.token = token;
    }

    public static String getToken() {
        if (token == null) {
            Context context = TestBaseApplication.getAppContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
            token = sharedPreferences.getString(TOKEN_KEY, null);
        }
        return token;
    }

    public static void removeToken() {
        Context context = TestBaseApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.remove(USERID_KEY);
        editor.commit();
        token = null;
        currentUser = null;
    }

    /**
     * 这里不用手机号，因为第三方登录，手机号不一定存在
     */
    public static void setCurrentUser(User user) {
        Context context = TestBaseApplication.getAppContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERID_KEY, String.valueOf(user.getUserId()));
        editor.commit();

        List<User> users = DataSupport.where("userId = ?", String.valueOf(user.getUserId())).find(User.class);

        if (users.size() > 0) {
            //更新用户信息
            user.updateAll("userId = ?", String.valueOf(user.getUserId()));
        } else {
            //保存用户信息
            user.save();
        }
        //这里需要再查询一次，是因为现在修改完用户信息后，返回的用户对象中，只有修改的那个属性和userId有值，其他都是null，否则直接这样返回去，界面显示就有问题了
        List<User> userList = DataSupport.where("userId = ?", String.valueOf(user.getUserId())).find(User.class);

        currentUser = userList.get(0);
    }

    public static User getCurrentUser() {
        if (currentUser == null) {
            Context context = TestBaseApplication.getAppContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
            String serverId = sharedPreferences.getString(USERID_KEY, null);
            if (serverId != null) {
                List<User> users = DataSupport.where("userId = ?", serverId).find(User.class);
                if (users.size() > 0) {
                    currentUser = users.get(0);
                }
            }
        }
        return currentUser;
    }

    public static List<Tag> getTag(int tagType) {
        if (tagMap.size() == 0) {
            initTagData();
        }
        List<Tag> tagList = tagMap.get(tagType);

        return tagList;
    }

    public static void cleanTag() {
        DataSupport.deleteAll(Tag.class);
    }

    private static void initTagData() {
        List<Tag> tagList = DataSupport.findAll(Tag.class);

        for (Tag item : tagList) {
            int tagType = item.getType();
            List<Tag> typeTagList = tagMap.get(tagType);
            if (typeTagList == null) {
                typeTagList = new ArrayList<>();
                tagMap.put(tagType, typeTagList);
            }
            typeTagList.add(item);
        }
    }

    public static boolean hasPermission(String permission) {
        switch (permission) {
            case Manifest.permission.CAMERA:
                try {
                //实际测试说明如果关闭了相机权限，在camera.open的时候会返回RuntimeException
                    Camera camera = Camera.open();
                    camera.release();
                    return true;
                } catch (RuntimeException e) {
                    return false;
                }
            case Manifest.permission.RECORD_AUDIO:
                MediaRecorder recorder = new MediaRecorder();
                if (recorder == null) {
                    return false;
                }
                recorder.reset();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + generateFileName();
                recorder.setOutputFile(path);
                try {
                    recorder.prepare();
                    recorder.start();
                    File soundFile = new File(path);
                    //实际测试说明如果关闭了录音权限，因为无法得到数据流，就不会建立对应的输出文件
                    return soundFile.exists();
                } catch (IllegalStateException | IOException e) {
                    return false;
                } finally {
                    try {
                        recorder.stop();
                        recorder.release();
                    }catch (Exception e) {

                    }
                }
            default:
                return TestBaseApplication.getAppContext().getPackageManager().checkPermission(permission,
                        TestBaseApplication.getAppContext().getPackageName()) == PackageManager.PERMISSION_GRANTED;
        }
    }
    private static String generateFileName(){
        String fileName= UUID.randomUUID().toString()+".amr";
        return fileName;
    }
}
