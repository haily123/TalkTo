package com.myxh.talkto;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.myxh.base.BaseActivity;
import com.myxh.bean.User;
import com.myxh.utils.ProgressGenerator;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by myxh on 2016/4/23.
 */
public class RegisterActivity extends BaseActivity implements View.OnFocusChangeListener, RadioGroup.OnCheckedChangeListener, ProgressGenerator.OnCompleteListener {

    public static final String TAG = "RegisterActivity";
    private EditText usrname,password,repassword,email;
    private RadioGroup sexGroup;
    private ActionProcessButton btnRegister;
    private String sex_content = "男";
    public static final int RESULT_CODE = 0x12;
    private ProgressGenerator progressGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
    }

    private void initViews() {
        usrname = (EditText) findViewById(R.id.et_usrname_register);
        password = (EditText) findViewById(R.id.et_password_register);
        repassword = (EditText) findViewById(R.id.et_repassword_register);
        email = (EditText) findViewById(R.id.et_email_register);
        sexGroup = (RadioGroup) findViewById(R.id.rg_sex_register);
        btnRegister = (ActionProcessButton) findViewById(R.id.btn_register);
        btnRegister.setMode(ActionProcessButton.Mode.ENDLESS);

        repassword.setOnFocusChangeListener(this);
        sexGroup.setOnCheckedChangeListener(this);
        progressGenerator = new ProgressGenerator(this);
    }

    public void register_btn_click(View v)
    {
        final String name_content = usrname.getText().toString().trim();
        String password_content = password.getText().toString().trim();
        String email_content = email.getText().toString().trim();

        if (name_content == null || name_content.equals("")) {
            showToast("用户名不能为空！");
            return;
        }
        if (password_content == null || password_content.equals("")) {
            showToast("密码不能为空！");
            return;
        }else if (password_content.length() < 8) {
            showToast("密码长度至少8位！");
            return;
        }
        if (email_content == null || email_content.equals("")) {
            showToast("邮箱不能为空");
            return;
        }else if (!email_content.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
            showToast("邮箱格式不正确");
            return;
        }

        usrname.setEnabled(false);
        password.setEnabled(false);
        repassword.setEnabled(false);
        email.setEnabled(false);
        sexGroup.setEnabled(false);

        User user = new User();
        user.setUsername(name_content);
        user.setPassword(password_content);
        user.setEmail(email_content);
        user.setSex(sex_content);
        user.signUp(RegisterActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                progressGenerator.start(btnRegister);
                Intent intent = new Intent();
                intent.putExtra(TAG,name_content);
                setResult(RESULT_CODE,intent);
            }

            @Override
            public void onFailure(int code, String msg) {
                showToast("注册失败:"+msg);
            }
        });

    }

    //******************************************************************************************
    /**
     * 焦点监听，repassword失去焦点时比对两次输入密码是否一致
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus)
        {
            String password_content = password.getText().toString().trim();
            String repassword_content = repassword.getText().toString().trim();
            if (!password_content.equals(repassword_content))
            {
                showToast("两次输入密码不一致", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        }
    }

    /**
     * 监听性别选择
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.rb_male_register:
                sex_content = "男";
                break;
            case R.id.rb_female_register:
                sex_content = "女";
                break;
            default:
                break;
        }
    }

    /**
     * 登陆完成销毁当前Activity
     */
    @Override
    public void onComplete() {
        showToast("注册成功");
        RegisterActivity.this.finish();
    }
    //******************************************************************************************
}
