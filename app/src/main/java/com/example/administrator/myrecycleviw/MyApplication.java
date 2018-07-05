package com.example.administrator.myrecycleviw;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2018/5/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
