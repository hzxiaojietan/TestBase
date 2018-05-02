package com.xiaojietan.business.model.bean.request;

import com.xiaojietan.business.model.bean.User;

import java.io.Serializable;

/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class LoginDTO implements Serializable {
    private User user;
    private String newPwd;
    private String oldPwd;


    public LoginDTO() {
    }

    public LoginDTO(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
