package com.xiaojietan.net;

import com.xiaojietan.common.Constant;
import com.xiaojietan.common.Util;

import cn.hzxiaojietan.base.net.NetManager;
import cn.hzxiaojietan.base.net.RetrofitNullOnEmptyConvertFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiaojie.tan on 2017/10/26
 * BaseModel
 */
public class BaseModel {

    //自己服务器api
    protected RequestApi mApi;

    public BaseModel() {
        mApi =  getApi();
    }

    /**
     * 获取请求api
     *
     * @return
     */
    private RequestApi getApi() {
        if (mApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(new NetManager().getOkHttpBuilder(Util.getToken()).build())
                    .baseUrl(Constant.SERVER_BASE_URL)
                    .addConverterFactory(new RetrofitNullOnEmptyConvertFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            mApi = retrofit.create(RequestApi.class);
        }
        return mApi;
    }
}
