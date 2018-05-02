package com.xiaojietan.business.model.bean.response;

import com.xiaojietan.business.model.bean.ConfigDataBean;

import java.io.Serializable;

/**
 * 配置项或者
 * Created by xiaojie.tan on 2017/10/26
 */
public class ConfigBean implements Serializable {
    private boolean result;
    private ConfigDataBean data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ConfigDataBean getData() {
        return data;
    }

    public void setData(ConfigDataBean data) {
        this.data = data;
    }
}
