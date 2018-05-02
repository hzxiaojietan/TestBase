package com.xiaojietan.business.model.bean.response;


import com.xiaojietan.business.model.bean.User;

import java.io.Serializable;


/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class LoginData implements Serializable {
    private String token;
    private String msg;
    private User loginUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return loginUser;
    }

    public void setUser(User user) {
        this.loginUser = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
