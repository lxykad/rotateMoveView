package com.lxy.test.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
