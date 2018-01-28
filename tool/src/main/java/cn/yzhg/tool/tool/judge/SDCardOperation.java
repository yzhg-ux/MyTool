package cn.yzhg.tool.tool.judge;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

import cn.yzhg.tool.tool.log.LogTool;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class SDCardOperation {

    private SDCardOperation() {
    }

    public static final int B = 0;  //byte
    public static final int K = 1;  //kb
    public static final int M = 2;  //mb


    /**
     * 判断当前目录是否存在,如果不存在就创建目录
     *
     * @param path
     * @return
     */
    public static File ExistSDCardMkdirs(String path,int SdSize) {
        /*判断当前SD卡是否存在,以及SD容量是否大于30M*/
        if (!ExistSDCard() || getSDFreeSize(M) < SdSize) return null;
        File file = new File(Environment.getExternalStorageDirectory(), path);
        /*判断当前目录是否存在,如果不存在,就创建目录*/
        if (!file.exists()) {
            LogTool.d("ExistSDCardMkdirs: 创建目录");
            file.mkdirs();
        }
        return file;
    }

    /**
     * 判断SD卡是否存在
     */
    public static boolean ExistSDCard() {
        return Environment.getExternalStorageState().equals(Environment
                .MEDIA_MOUNTED);
    }

    /**
     * 判断SD卡剩余空间
     */
    public static long getSDFreeSize(int type) {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
        if (SDCardOperation.M == type) {
            return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
        } else if (SDCardOperation.K == type) {
            return (freeBlocks * blockSize) / 1024;   //单位KB
        } else if (SDCardOperation.B == type) {
            return freeBlocks * blockSize;  //单位Byte
        } else {
            return freeBlocks * blockSize;  //单位Byte
        }
    }

    /**
     * 判断SD卡容量
     */
    public long getSDAllSize(int type) {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //获取所有数据块数
        long allBlocks = sf.getBlockCount();
        if (SDCardOperation.M == type) {
            return (allBlocks * blockSize) / 1024 / 1024; //单位MB
        } else if (SDCardOperation.K == type) {
            return (allBlocks * blockSize) / 1024; //单位KB
        } else if (SDCardOperation.B == type) {
            return allBlocks * blockSize; //单位Byte
        } else {
            return allBlocks * blockSize; //单位Byte
        }
    }
}
