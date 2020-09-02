package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lenovo.smarttraffic.InitApp;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */

public abstract class BaseActivity extends SupportActivity{

    private static final String TAG = "BaseActivity";
    //黄油刀的资源释放对象
    private Unbinder unbind;

    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进行view视图绑定
        setContentView(getLayout());
        //绑定黄油刀
        unbind = ButterKnife.bind(this);
        //以单例模式实例化initApp，调用其添加活动方法
        InitApp.getInstance().addActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressedSupport();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressedSupport() {
        //fragment逐个退出
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0){
            super.onBackPressedSupport();
        }else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InitApp.getInstance().removeActivity(this);
        unbind.unbind();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected abstract int getLayout();
}
