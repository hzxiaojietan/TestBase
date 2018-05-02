package com.xiaojietan.business.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xiaojietan.business.presenter.LoginPresenter;
import com.xiaojietan.business.view.LoginFragment;
import cn.hzxiaojietan.base.common.base.BaseFragmentActivity;


/**
 * 登录
 * Created by Jake on 2017/11/03.
 */
public class LoginActivity extends BaseFragmentActivity<LoginFragment> {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImgTitleLeft.setVisibility(View.GONE);
        mTvTitle.setText("登录");
        //Presenter和View互相绑定
        new LoginPresenter(mFragment);
    }

    @Override
    protected LoginFragment createFragment() {
        return new LoginFragment();
    }
}
