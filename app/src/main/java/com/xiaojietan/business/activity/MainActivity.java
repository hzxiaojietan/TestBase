package com.xiaojietan.business.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xiaojietan.business.model.LoginModel;
import com.xiaojietan.business.model.bean.response.ConfigBean;
import com.xiaojietan.testbase.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hzxiaojietan.base.business.common.AppStatusConstant;
import cn.hzxiaojietan.base.business.common.AppStatusManager;
import cn.hzxiaojietan.base.common.base.BaseActivity;
import cn.hzxiaojietan.base.common.utils.DialogUtils;
import cn.hzxiaojietan.base.common.utils.ToastUtils;
import cn.hzxiaojietan.base.net.NetBaseSubscriber;
import cn.hzxiaojietan.base.net.NetBaseSubscription;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends BaseActivity {

    public  static Intent createIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //进入应用初始化设置成正常状态
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void myClick() {
        ToastUtils.show(this,"基础项目移植成功！");

        getConfig();
    }

    public void getConfig() {
        DialogUtils.showHUD(this,"请稍后...",true);
        CompositeSubscription mSubscriptions = new CompositeSubscription();
        LoginModel mLoginModel = new LoginModel();
        mSubscriptions.add(NetBaseSubscription.subscription(mLoginModel.getConfig(),
                new NetBaseSubscriber<ConfigBean>() {
                    @Override
                    public void onSuccess(ConfigBean response) {
                        super.onSuccess(response);
                        DialogUtils.dismissHUD();
                        if (response!= null) {
                            if(response.isResult()){
                                // 成功
                            }
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        super.onFail(message);
                    }
                }));
    }
}
