package com.myxh.talkto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.myxh.base.BaseActivity;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener,DialogInterface.OnClickListener {

    private ImageView logo;
    private AlphaAnimation alphaAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
    }
    private void initViews() {
        logo = (ImageView) findViewById(R.id.splash_logo);
        alphaAnim = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.anim_splash_logo);
        logo.setAnimation(alphaAnim);
        alphaAnim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (!isNetConnected()) {
            showDialog("提示", "未连接网络，请前往设置", "去设置", "不了", android.R.drawable.ic_dialog_info, this, this);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub
        Intent mainIntent = new Intent(this,LoginActivity.class);
        startActivity(mainIntent);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    //检测设备是否联网
    private boolean isNetConnected()
    {
        ConnectivityManager cManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo == null) {
            return false;
        }
        else {
            return nInfo.isAvailable();
        }
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                this.finish();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                this.finish();
                break;
            default:
                break;
        }
    }

}