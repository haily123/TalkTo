package com.myxh.app;

import android.app.Application;

public class MyApplication extends Application {
	
	public static MyApplication app;
	
	@Override
	public void onCreate() {
		app = this;
		super.onCreate();
	}

}
