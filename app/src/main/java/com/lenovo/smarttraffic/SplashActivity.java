package com.lenovo.smarttraffic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lenovo.smarttraffic.ui.adapter.SplashViewPageAdapter;

import java.util.ArrayList;

/**
 * @author Amoly
 * @date 2019/4/11.
 * description：
 */
public class SplashActivity extends AppCompatActivity {


    private ImageView splashImage;
    private ViewPager splashViewpager;
    private ArrayList<Integer> viewImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消了在清单文件中使用的闪屏样式，在这里进行逻辑处理
        setContentView(R.layout.activity_splash);

        initView();

        initLayout();

        initEvent();


    }

    private void initLayout() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);

        //判断是否是第一次进入
        String firstEnterKey = "firstEnter";
        SharedPreferences firstEnter = getSharedPreferences(firstEnterKey, MODE_PRIVATE);
        String firstEnterValue = firstEnter.getString(firstEnterKey, null);
        //!=null就是不是第一次进入，直接进入新页面
        if (firstEnterValue != null) {
            startActivity(intent);
            finish();
        } else {
            //显示引导图片
            splashImage.setVisibility(View.VISIBLE);
            splashViewpager.setVisibility(View.GONE);
            //==null就写入点参数
            firstEnter.edit().putString(firstEnterKey, "notFirstEnter").apply();
            //延时跳转引导界面
            InitApp.getHandler().postDelayed(this::showViewPager, 1000);
        }
    }

    private void initEvent() {


    }


    private void showViewPager() {
        //1s后将该页面的图片隐藏起来，然后显示引导的布局
        splashImage.setVisibility(View.GONE);
        splashViewpager.setVisibility(View.VISIBLE);
        viewImage = new ArrayList<>();
        viewImage.add(R.drawable.splash);
        viewImage.add(R.drawable.splash);
        viewImage.add(R.drawable.splash);
        splashViewpager.setAdapter(new SplashViewPageAdapter(viewImage));

        splashViewpager.setCurrentItem(0);
    }

    private void initView() {
        splashImage = (ImageView) findViewById(R.id.splash_image);
        splashViewpager = (ViewPager) findViewById(R.id.splash_viewpager);
    }
}
