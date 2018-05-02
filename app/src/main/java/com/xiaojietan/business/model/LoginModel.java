package com.xiaojietan.business.model;

import com.xiaojietan.business.model.bean.response.ConfigBean;
import com.xiaojietan.business.model.bean.response.LoginResponse;
import com.xiaojietan.net.BaseModel;

import rx.Observable;


/**
 * Created by Jake on 2017/11/03.
 * 登录
 */
public class LoginModel extends BaseModel {

    public LoginModel(){

    }

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    public Observable<LoginResponse> login(String mobile, String password){
        return mApi.login(mobile,password);
    }

    public Observable<ConfigBean> getConfig(){
        return mApi.getConfig();
    }
}
