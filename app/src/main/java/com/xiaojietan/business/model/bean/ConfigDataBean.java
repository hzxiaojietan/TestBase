package com.xiaojietan.business.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class ConfigDataBean extends DataSupport implements Serializable {
    private long userId; // 用户id
    private String mobile; // 最后登录用户手机号，登录页面需要展示
    private String department;  //最后登录用户所属部门，登录页面需要展示
    private String withdraw_time; // 可撤销时间  单位：分钟

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWithdraw_time() {
        return withdraw_time;
    }

    public void setWithdraw_time(String withdraw_time) {
        this.withdraw_time = withdraw_time;
    }
}
