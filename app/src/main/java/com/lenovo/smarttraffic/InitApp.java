package com.lenovo.smarttraffic;

import android.app.Activity;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 * 功能:初始化
 */
public class InitApp extends MultiDexApplication {

    private static Handler mainHandler;
    //    private static Context AppContext;
    private static InitApp instance;
    //Set集合，基础自Collection。特征是插入无序，不可指定位置访问。
    private Set<Activity> allActivities;

    public static synchronized InitApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
//        AppContext = this;
        instance = this;
        mainHandler = new Handler();

    }

    //    public static Context getContext(){
//        return AppContext;
//    }
    public static Handler getHandler() {
        return mainHandler;
    }

    public void addActivity(Activity act) {
        //判set为空，进行实例化
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        //一定是非空状态进行添加
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
