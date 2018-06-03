package com.example.yongbeom.tutoavr;

import android.app.Application;

/**
 * Created by yongbeom on 2018. 6. 3..
 */

public class AVRApplicaton extends Application {

    String gStr = "글로벌 스트링입니다";
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public String getgStr() {
        return gStr;
    }
}
