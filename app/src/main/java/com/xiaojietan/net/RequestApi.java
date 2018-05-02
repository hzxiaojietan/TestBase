package com.xiaojietan.net;

import com.xiaojietan.business.model.bean.response.ConfigBean;
import com.xiaojietan.business.model.bean.response.LoginResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xiaojie.tan on 2017/10/26
 * 服务器接口
 */

public interface RequestApi {

    /**
     * 登录
     * @param mobile
     * @param password
     * @return
     */
    @POST("p/login")
    Observable<LoginResponse> login(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 获取配置信息
     * @return
     */
    @POST("config/getConfig")
    Observable<ConfigBean> getConfig();
}
