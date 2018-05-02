package com.xiaojietan.business.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xiaojietan.business.activity.MainActivity;
import com.xiaojietan.business.contract.LoginContract;
import com.xiaojietan.business.model.bean.LoginBean;
import com.xiaojietan.business.model.bean.User;
import com.xiaojietan.common.Util;
import com.xiaojietan.testbase.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.hzxiaojietan.base.common.base.BaseFragment;
import cn.hzxiaojietan.base.common.utils.DialogUtils;
import cn.hzxiaojietan.base.common.utils.StringUtils;

/**
 * Created by Jake on 2017/11/03.
 * 登录
 */
public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View{
    @BindView(R.id.email)
    EditText mEtAccount;
    @BindView(R.id.password)
    EditText mEtPassword;
    @BindView(R.id.email_sign_in_button)
    Button mBt;

    private View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout llLogin = (LinearLayout)inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, llLogin);
//        mBt = (Button) llLogin.findViewById(R.id.email_sign_in_button);
//        mEtAccount = (EditText) llLogin.findViewById(R.id.email);
//        mEtPassword = (EditText) llLogin.findViewById(R.id.password);

//        EventBus.getDefault().register(this);
        // 获取上次登录用户信息
       /* User user = Util.getCurrentUser();
        if(user != null && user.getMobile() != null){
            mEtAccount.setText(user.getMobile());
            mEtAccount.setSelection(user.getMobile().length());
        }*/

        return llLogin;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        User currentUser = Util.getCurrentUser();
        if (currentUser != null) {
            goMainActivity();
            return new View(getContext());
        }

        View view = initView(inflater, container, savedInstanceState);
        return view;
    }

    private LoginBean getLoginBeanToSubmit() {
        LoginBean bean = new LoginBean();

        String account = mEtAccount.getText().toString();
        if(StringUtils.isEmpty(account)) {
            showToast("请填写账号");
            return null;
        }

        String password = mEtPassword.getText().toString();
        if(StringUtils.isEmpty(password) || password.length() < 6) {
            showToast("请填写密码(最少6位)");
            return null;
        }

        // 违法地点
        bean.setAccount(account);
        // 违法车辆号码
        bean.setPassword(password);
        return bean;
    }

    @OnClick(R.id.email_sign_in_button)
    public void login(){
        LoginBean bean = getLoginBeanToSubmit();
        if(bean != null){
            mPresenter.login(mEtAccount.getText().toString(),mEtPassword.getText().toString());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        DialogUtils.dismissHUD();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void refreshView(String isResetPassword) {
        goMainActivity();
    }

    private void goMainActivity(){
        Intent intent = MainActivity.createIntent(getContext());
        startActivity(intent);
    }
}
