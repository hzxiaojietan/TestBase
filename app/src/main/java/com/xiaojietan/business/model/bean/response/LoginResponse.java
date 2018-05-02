package com.xiaojietan.business.model.bean.response;

import java.io.Serializable;

/**
 * Created by xiaojie.tan on 2017/10/26
 */
public class LoginResponse implements Serializable {
    private boolean result;
    private LoginData data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
