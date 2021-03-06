package com.google.googleplaystore.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DH on 2017/7/8.
 */

public class MyApplication extends Application {

    private static Handler mMainThreadHandler;
    private static Context mContext;
    private static int mMainThreadId;

    //内存缓存的集合
    private Map<String, String> mMemProtocolCacheMap = new HashMap<>();
    public Map<String, String> getMemProtocolCacheMap() {
        return mMemProtocolCacheMap;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //得到上下文
        mContext = getApplicationContext();
        //获取主线程Handler
        mMainThreadHandler = new Handler();
        //获取主线程id
        mMainThreadId = Process.myTid();
    }

    /**
     * @return 得到上下文
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * @return 获取主线程Handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * @return 获取主线程id
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
}
