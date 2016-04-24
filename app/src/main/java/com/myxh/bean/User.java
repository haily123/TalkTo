package com.myxh.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by myxh on 2016/4/24.
 */
public class User extends BmobUser {

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
