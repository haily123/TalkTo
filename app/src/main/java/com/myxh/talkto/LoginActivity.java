package com.myxh.talkto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.myxh.base.BaseActivity;
import com.myxh.bean.User;
import com.myxh.utils.ProgressGenerator;

import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity implements ProgressGenerator.OnCompleteListener {

    private EditText usrname,password;
    private ActionProcessButton btnLogin;
    public static final int REQUEST_CODE = 0x11;
    private ProgressGenerator progressGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    private void initViews() {
        usrname = (EditText) findViewById(R.id.et_username_login);
        password = (EditText) findViewById(R.id.et_password_login);
        btnLogin = (ActionProcessButton) findViewById(R.id.btn_login);
        btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
        progressGenerator = new ProgressGenerator(this);
    }

    public void login_click(View v)
    {
        String name_content = usrname.getText().toString().trim();
        String password_content = password.getText().toString().trim();
        if (name_content == null || name_content.equals(""))
        {
            showToast("用户名不能为空");
            return;
        }
        if (password_content == null || password_content.equals(""))
        {
            showToast("密码不能为空");
            return;
        }
        User user = new User();
        user.setUsername(name_content);
        user.setPassword(password_content);
        user.login(this, new SaveListener() {
            @Override
            public void onSuccess() {
                progressGenerator.start(btnLogin);
            }

            @Override
            public void onFailure(int code, String msg) {
                showToast("登陆失败"+msg);
            }
        });
    }

    public void register_click(View v)
    {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && requestCode == RegisterActivity.RESULT_CODE)
        {
            String name_content = data.getStringExtra(RegisterActivity.TAG);
            usrname.setText(name_content);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onComplete() {
        showToast("登陆成功");
        Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(homeIntent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        LoginActivity.this.finish();
    }
}
