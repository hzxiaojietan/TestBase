package com.xiaojietan.business.contract;

import cn.hzxiaojietan.base.common.base.IBasePresenter;
import cn.hzxiaojietan.base.common.base.IBaseView;


/**
 * Created by Jake on 2017/11/03.
 * 违法停车
 */
public interface LoginContract {

    interface View extends IBaseView<Presenter> {
        // 刷新页面
        void refreshView(String isResetPassword);
    }

    interface Presenter extends IBasePresenter {
        /**
         * 登录
         */
        void login(String mobile,String password);
    }
}
