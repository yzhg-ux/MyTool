package cn.yzhg.tool.tool.premission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class PermissionUtils {
    public static final int ALL_PERMISSION_CODE = 10000;
    public static final int READ_CONTACTS_CODE = 10001;
    public static final int CALL_PHONE_CODE = 10002;
    public static final int READ_CALENDAR_CODE = 10003;
    public static final int CAMERA_CODE = 10004;
    public static final int BODY_SENSORS_CODE = 10005;
    public static final int ACCESS_FINE_LOCATION_CODE = 10006;
    public static final int WRITE_EXTERNAL_STORAGE_CODE = 10007;
    public static final int RECORD_AUDIO_CODE = 10008;
    public static final int SEND_SMS_CODE = 10009;
    public static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;  //允许程序访问联系人通讯录信息
    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;  //允许程序从非系统拨号器里拨打电话
    public static final String READ_CALENDAR = Manifest.permission.READ_CALENDAR;  //允许程序读取用户的日志信息
    public static final String CAMERA = Manifest.permission.CAMERA;  //允许程序访问摄像头进行拍照
    public static final String BODY_SENSORS = Manifest.permission.BODY_SENSORS;  //感应器权限
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;// 允许程序通过GPS芯片接收卫星的定位信息
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE; //SD卡读写权限
    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO; // 允许程序录制声音通过手机或耳机的麦克
    public static final String SEND_SMS = Manifest.permission.SEND_SMS;   //允许程序发送短信

    /*权限数组,申请全部权限时使用*/
    static String[] permission = {
            READ_CONTACTS,  //允许程序访问联系人通讯录信息
            CALL_PHONE,     //允许程序从非系统拨号器里拨打电话
            READ_CALENDAR,   //允许程序读取用户的日志信息
            CAMERA,   //允许程序访问摄像头进行拍照
            BODY_SENSORS,  //感应器权限
            ACCESS_FINE_LOCATION,    // 允许程序通过GPS芯片接收卫星的定位信息
            WRITE_EXTERNAL_STORAGE,        //SD卡读写权限
            RECORD_AUDIO,      // 允许程序录制声音通过手机或耳机的麦克
            SEND_SMS //允许程序发送短信
    };

    static int[] permissionCode = {
            READ_CONTACTS_CODE,
            CALL_PHONE_CODE,
            READ_CALENDAR_CODE,
            CAMERA_CODE,
            BODY_SENSORS_CODE,
            ACCESS_FINE_LOCATION_CODE,
            WRITE_EXTERNAL_STORAGE_CODE,
            RECORD_AUDIO_CODE,
            SEND_SMS_CODE
    };

    public static class Builder {  //返回他自己
        private String title = "权限申请";
        private String message = "您还没有申请该权限";
        private String negative = "取消";
        private String positive = "确定";
        private Fragment fragment;
        private String type = "activity";
        private PermissionListener pListener;

        /*设置弹出框标题*/
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /*设置弹出框显示的信息*/
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /*设置弹出框,确定按钮显示的字体,默认为去设置*/
        public Builder setNegative(String negative) {
            this.negative = negative;
            return this;
        }

        /*设置弹出框取消字体显示,默认为取消*/
        public Builder setPositive(String positive) {
            this.positive = positive;
            return this;
        }

        /*
        * 标注是在Activity中申请权限还是在Fragment 中
        *
        * 如果在Fragment中申请权限
        *   ActivityCompat.requestPermissions(context, new String[]{permission}, RequestCode);
        *   无法获取到回调
        * */
        public Builder setFragment(Fragment fragment, String type) {
            this.fragment = fragment;
            this.type = type;
            return this;
        }

        /*申请单个权限 去检查单个权限是否已经申请*/
        public void examinePermission(Activity context, String permission, int ResultCode) {
            int resultCode = ContextCompat.checkSelfPermission(context, permission);
            if (resultCode == PackageManager.PERMISSION_DENIED) {  /*如果该权限还没有申请就返回-1*/
                if (rejectPermission(context, permission)) {
                    /*用户拒绝过此权限*/
                    showPermissionDialog(context, permission, ResultCode, title, message,
                            negative, positive);
                } else {
                    /*用户没有拒绝过此权限*/
                    applyPermission(context, permission, ResultCode);
                }
            } else if (resultCode == PackageManager.PERMISSION_GRANTED) {
                /*已经申请过该权限*/
                if (pListener != null) {
                    pListener.possessPermission();
                } else {
                    throw new NullPointerException("pListener is Null,Please check if the " +
                            "pListener is empty.");
                }
            }
        }

        /*申请多个权限调用的方法,这里只在开屏页申请一次,不检查是否点击过拒绝*/
        public void morePermission(Activity context, String[] permissions) {
            List<String> mPermissionList = new ArrayList<>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager
                        .PERMISSION_DENIED) {
                    mPermissionList.add(permission);
                }
            }
            if (mPermissionList.isEmpty()) {
                /*都已经授权了,通过接口返回*/
                if (pListener != null) {
                    pListener.possessPermission();
                }
            } else {
                String[] noPermission = mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions(context, noPermission, ALL_PERMISSION_CODE);
            }
        }


        /*检查单个权限是否被用户拒绝过,返回true说明用户拒绝过此权限*/
        private boolean rejectPermission(Activity context, String permission) {
            return ActivityCompat.shouldShowRequestPermissionRationale(context, permission);
        }

        /*去申请单个权限*/
        private void applyPermission(Activity context, String permission, int RequestCode) {
            if ("activity".equals(type)) {
                ActivityCompat.requestPermissions(context, new String[]{permission}, RequestCode);
            } else if ("fragment".equals(type)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    fragment.requestPermissions(new String[]{permission}, RequestCode);
            } else {
                throw new IllegalStateException("There is only activity or fragment, Each word is" +
                        " made up of lowercase letters.");
            }
        }

        /*设计Dialog弹出框给用户解释为什么需要这个权限*/
        private void showPermissionDialog(
                Activity context, String permission, int ResultCode,
                String title, String message, String negative, String positive) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton(negative, (dialog, which) -> {
                 /*用户点击了取消按钮,*/
                if (pListener != null) pListener.cancelPermission();
            });
            builder.setPositiveButton(positive, (dialog, which) -> {
                 /*用户点击了确定按钮,再次去申请权限*/
                applyPermission(context, permission, ResultCode);
            });
            builder.create();
            builder.show();
        }

        /*用户点击了不再询问后,需要用户跳转到手机设置页面,手动打开权限*/
        public void settingDialog(Activity context) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton(negative, (dialog, which) -> {
            /*用户点击了取消按钮,*/
                if (pListener != null) pListener.cancelPermission();
            });
            builder.setPositiveButton(positive, (dialog, which) -> {
            /*用户点击了确定按钮,再次去申请权限*/
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getApplicationContext().getPackageName
                        (), null);
                intent.setData(uri);
                context.startActivity(intent);
            });
            builder.create();
            builder.show();

        }

        public Builder setPermissionListener(PermissionListener pListener) {
            this.pListener = pListener;
            return this;
        }

        /*使用接口回调的方式,调用取消事件*/
        public interface PermissionListener {
            void possessPermission();  //已经有权限了,可以去执行逻辑

            void cancelPermission();  //给用户解释权限的时候,用户点击了取消按钮

        }
    }
}
