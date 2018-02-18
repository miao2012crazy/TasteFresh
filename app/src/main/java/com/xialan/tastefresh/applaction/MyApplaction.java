package com.xialan.tastefresh.applaction;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.igexin.sdk.PushManager;
import com.xialan.tastefresh.bean.UserInfoBean;
import com.xialan.tastefresh.commonutil.ImageLoaderManager;
import com.xialan.tastefresh.service.CustomIntentService;
import com.xialan.tastefresh.service.CustomPushService;

/**
 * Created by ${苗} on 2017/11/8.
 */

public class MyApplaction extends Application {
    private static Context context;
    private static Handler handler;
    private static Thread mainThread;
    private static int mainThreadId;
    private static MyApplaction app;
    private static UserInfoBean userInfoBean;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //在此方法中需要获取handler对象,上下文环境
        context = getApplicationContext();
        //准备handler对象
        handler = new Handler();
        //hanlder应用场景(子线程往主线程中发送message)

        //获取主线程的对象,WLBApplication的onCreate运行在主线程中的代码
        mainThread = Thread.currentThread();
        //获取主线程(当前线程)id方法
        mainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();
        //让ImageLoader进行初始化
        ImageLoaderManager.initImageLoader(getApplicationContext());

    }

    /**
     *  获取上下文对象
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler对象
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread() {
        return mainThread;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    private static Handler mMainThreadHandler = null;

    /**
     * 获取主线程handler对象
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取applaction实例
     * @return
     */
    public static MyApplaction getInstance() {
        return app;
    }


    /**
     * 获取applaction实例
     * @return
     */
    public static UserInfoBean getUserBean() {
        return userInfoBean;
    }

    /**
     * 获取applaction实例
     * @return
     */
    public static void setUserBean(UserInfoBean userBean) {
        userInfoBean=userBean;
    }


}
