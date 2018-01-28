package cn.yzhg.tool.tool.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class Md5Utils {

    /**
     * 密码的md5加密
     * @param password
     * @return
     *
     */
    public static String encode(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : result){
                int number = b&0xff;
                String str = Integer.toHexString(number);
                if(str.length()==1){
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
