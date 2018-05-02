package com.xiaojietan.business.model.bean;

import cn.hzxiaojietan.base.common.base.BaseBean;

/**
 * Created by xiaojie.tan on 2017/11/03
 */

public class LoginBean implements BaseBean {
    private String account;    // 登录账号
    private String password;    // 登录密码

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
