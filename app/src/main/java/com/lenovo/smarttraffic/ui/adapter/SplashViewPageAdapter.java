package com.lenovo.smarttraffic.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;

import java.util.List;

import butterknife.BindView;

public class SplashViewPageAdapter extends PagerAdapter {

    private static final String TAG = "SplashViewPageAdapter";

    private List<Integer> viewImageRes = null;
    private ImageView adapterImageView;
    private Button adapterButton;
    private LinearLayout adapterLinear;

    public SplashViewPageAdapter(List<Integer> viewImage) {
        this.viewImageRes = viewImage;
    }


    @Override
    public int getCount() {
        return viewImageRes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_splash_viewpager, null);
        initView(view);
        adapterImageView.setImageResource(viewImageRes.get(position));
        Log.i(TAG, "instantiateItem: " + view.toString());
        adapterButton.setVisibility(View.GONE);
        if (position == viewImageRes.size() - 1) {
            adapterButton.setVisibility(View.VISIBLE);
        }
        container.addView(view);
        adapterButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
        //显示小红点
        for (int i = 0; i < viewImageRes.size(); i++) {
            ImageView imageView = new ImageView(context);
            if (i == position) {
                imageView.setImageResource(R.drawable.select_point);
            } else {
                imageView.setImageResource(R.drawable.select_point_no);
            }
            adapterLinear.addView(imageView);
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }

    private void initView(View view) {
        adapterImageView = (ImageView) view.findViewById(R.id.adapter_image_view);
        adapterButton = (Button) view.findViewById(R.id.adapter_button);
        adapterLinear = (LinearLayout) view.findViewById(R.id.adapter_linear);
    }
}
