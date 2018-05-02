package com.xiaojietan.business.presenter;

import com.xiaojietan.business.contract.LoginContract;
import com.xiaojietan.business.model.LoginModel;
import com.xiaojietan.business.model.bean.response.LoginResponse;
import com.xiaojietan.common.Util;

import cn.hzxiaojietan.base.common.base.BasePresenter;
import cn.hzxiaojietan.base.net.NetBaseSubscriber;
import cn.hzxiaojietan.base.net.NetBaseSubscription;

/**
 * Created by Jake on 2017/11/03.
 * 登录
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mLoginModel = new LoginModel();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();
    }

    @Override
    public void login(String mobile,String password) {
        mView.showProgress("请稍后...",true);
        mSubscriptions.add(NetBaseSubscription.subscription(mLoginModel.login(mobile,password),
                new NetBaseSubscriber<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        super.onSuccess(response);
                        mView.hideProgress();
                        if(response.isResult()){
                            if (response!= null && response.getData().getUser().getMobile() != null) {
                                Util.setCurrentUser(response.getData().getUser());
                                if(response.getData().getToken() != null &&
                                        !response.getData().getToken().equals("")){
                                    Util.saveToken(response.getData().getToken());
                                }
                                mView.refreshView(response.getData().getUser().getAlterFlag());
                            } else {
                                mView.showToast("登录异常");
                            }
                        }else{
                            mView.showToast( response.getData().getMsg());
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        super.onFail(message);
                        mView.hideProgress();
                        mView.showToast(message);
                    }
                }));
    }
}
