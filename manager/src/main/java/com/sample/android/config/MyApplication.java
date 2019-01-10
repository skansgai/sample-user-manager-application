package com.sample.android.config;

import android.app.Application;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： Application配置
 * @作者： 杨松松
 * @创建时间： 2017/11/19 20:11
 */
public class MyApplication extends Application {

    private static MyApplication application;

    public static Application getInsistence() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
