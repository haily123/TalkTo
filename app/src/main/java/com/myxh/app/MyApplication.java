package com.myxh.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
	
	public static MyApplication app;
	
	@Override
	public void onCreate() {
		initBmob();
		app = this;
		super.onCreate();
	}

	private void initBmob() {
		Bmob.initialize(getApplicationContext(),"b91a795196f6f4bf04786dbca88739c3");
	}

}
