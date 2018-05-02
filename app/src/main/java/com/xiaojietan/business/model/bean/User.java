package com.xiaojietan.business.model.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class User extends DataSupport implements Serializable {
    @SerializedName("id")
    private long userId;
    private String mobile;
    private String password;
    private String alterFlag;   // 用户是否修改过密码 1修改过，0未修改过
    private long lastLoginTime;
    private String department;  //所属部门
    private String remarks;     // 备注
    private String telephone;   // 咨询电话,座机

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlterFlag() {
        return alterFlag;
    }

    public void setAlterFlag(String alterFlag) {
        this.alterFlag = alterFlag;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
