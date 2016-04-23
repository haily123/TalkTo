package com.myxh.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;

public class BaseActivity extends Activity {

	public void dialog(String title,String msg,String btn1,String btn2,int iconId,OnClickListener btn1Listener,OnClickListener btn2Listener)
	{
		Builder builder = new Builder(this);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setIcon(iconId);
		builder.setPositiveButton(btn1, btn1Listener);
		builder.setNegativeButton(btn2, btn2Listener);
		builder.create().show();
	}
	
}
