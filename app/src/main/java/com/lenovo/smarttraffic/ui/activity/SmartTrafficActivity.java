package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.lenovo.smarttraffic.Interface.ICallBack;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.adapter.SmartTrafficRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SmartTrafficActivity extends BaseActivity implements ICallBack {

    private static final String TAG = "SmartTrafficActivity";

    private RecyclerView smartTrafficRecyclerView;
    private ArrayList<List> itemData;

    @Override
    protected int getLayout() {
        return R.layout.activity_smart_traffic;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        itemData = new ArrayList<>();

        ArrayList<Object> list = new ArrayList<>();
        list.add(R.mipmap.icon_bad);
        list.add("环境指标");
        itemData.add(list);

        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(R.mipmap.icon_box);
        list2.add("手动控制");
        itemData.add(list2);

        ArrayList<Object> list3 = new ArrayList<>();
        list3.add(R.mipmap.icon_commputer);
        list3.add("系统设置");
        itemData.add(list3);

        ArrayList<Object> list4 = new ArrayList<>();
        list4.add(R.mipmap.icon_credit_card);
        list4.add("农信贷");
        itemData.add(list4);

        ArrayList<Object> list5 = new ArrayList<>();
        list5.add(R.mipmap.icon_monny);
        list5.add("农电微商");
        itemData.add(list5);

        ArrayList<Object> list6 = new ArrayList<>();
        list6.add(R.mipmap.icon_phone);
        list6.add("资讯通");
        itemData.add(list6);

        ArrayList<Object> list7 = new ArrayList<>();
        list7.add(R.mipmap.icon_compass);
        list7.add("创意");
        itemData.add(list7);

        Log.i(TAG, "initData: "+itemData.toString());

        SmartTrafficRecyclerViewAdapter sv = new SmartTrafficRecyclerViewAdapter(itemData);
        smartTrafficRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        smartTrafficRecyclerView.setAdapter(sv);


    }

    private void initEvent() {
    }

    private void initView() {
        smartTrafficRecyclerView = (RecyclerView) findViewById(R.id.smart_traffic_recycler_view);

    }


    @Override
    public void tapItem(View item, String title) {
        item.setOnClickListener(v -> {
            Log.i(TAG, "tapItem: "+title);
            startActivity(new Intent(SmartTrafficActivity.this,STActivityChild.class));
        });
    }
}
