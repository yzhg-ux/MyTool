package cn.yzhg.tool.tool.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * author yzhg , time 2018/1/28.
 * <p>
 * aphorism:True hero is brave and useless, good scenery always in danger peak.
 * summarize(Please describe it in one sentence.)
 */

public class ThreadUtils {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private static ExecutorService sExecutors = Executors.newSingleThreadExecutor();

    /**
     * 在子线程中运行一段逻辑
     * @param runnable
     */
    public static void runOnSubThread(Runnable runnable){
        sExecutors.execute(runnable);
    }

    /**
     * 在主线程中运行一段逻辑
     * @param runnable
     */
    public static void runOnMainThread(Runnable runnable){
        sHandler.post(runnable);
    }

}
