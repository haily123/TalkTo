package com.myxh.base;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class BaseActivity extends Activity {

	public void showDialog(String title,String msg,String btn1,String btn2,int iconId,OnClickListener btn1Listener,OnClickListener btn2Listener)
	{
		Builder builder = new Builder(this);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setIcon(iconId);
		builder.setPositiveButton(btn1, btn1Listener);
		builder.setNegativeButton(btn2, btn2Listener);
		builder.create().show();
	}

	public void showToast(String msg,int gravity,int duration)
	{
		Toast toast = Toast.makeText(getApplicationContext(),msg,duration);
		toast.setGravity(gravity,0,0);
		toast.show();
	}

	public void showToast(String msg)
	{
		Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
	}
	
}
