package com.lenovo.smarttraffic.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.SmartTrafficActivity;

import java.util.List;

public class SmartTrafficRecyclerViewAdapter extends RecyclerView.Adapter<SmartTrafficRecyclerViewAdapter.InitView> {

    private List<List> itemData = null;
    private View view = null;


    public SmartTrafficRecyclerViewAdapter(List<List> itemData) {
        this.itemData = itemData;
    }

    @NonNull
    @Override
    public InitView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_smart_traffic_main, parent, false);
        return new InitView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InitView holder, int position) {
        //数据绑定
        List list = itemData.get(position);
        holder.adapterStImageView.setImageResource((Integer) list.get(0));
        holder.adapterStTextView.setText(String.valueOf(list.get(1)));
        //接口回调
        new SmartTrafficActivity().tapItem(view,(String) list.get(1));
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }


    public class InitView extends RecyclerView.ViewHolder {
        ImageView adapterStImageView;
        TextView adapterStTextView;

        public InitView(View itemView) {
            super(itemView);
            adapterStImageView = (ImageView) itemView.findViewById(R.id.adapter_st_image_view);
            adapterStTextView = (TextView) itemView.findViewById(R.id.adapter_st_text_view);
        }
    }
}
