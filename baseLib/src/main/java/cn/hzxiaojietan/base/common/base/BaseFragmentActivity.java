package cn.hzxiaojietan.base.common.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import cn.hzxiaojietan.base.common.utils.ActivityUtils;
import cn.hzxiaojietan.base.common.utils.AppLog;
import cn.hzxiaojietan.base.common.utils.CheckUtils;


/**
 * Created by xiaojie.tan on 2017/10/26
 * 只是添加一个fragment的Activity
 */
public abstract class BaseFragmentActivity<T extends Fragment> extends BaseActivity {

    protected T mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.hzxiaojietan.base.R.layout.activity_base_fragment);
        initData();
        addFragment();
    }

    protected void initData() {

    }

    protected void addFragment() {
        mFragment = (T) getSupportFragmentManager().findFragmentById(cn.hzxiaojietan.base.R.id.layout_content);

        if (mFragment != null) {
            AppLog.e(getClass().getSimpleName(), "mFragment is: " + mFragment.getClass().getSimpleName());
        }
        if (mFragment == null) {
            mFragment = createFragment();
            CheckUtils.checkNotNull(mFragment, "fragment is NULL");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFragment, cn.hzxiaojietan.base.R.id.layout_content);
        }
    }

    protected abstract T createFragment();
}
