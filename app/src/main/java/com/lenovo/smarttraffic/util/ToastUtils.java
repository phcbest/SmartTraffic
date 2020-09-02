package com.lenovo.smarttraffic.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast = null;

    //显示toast
    public static void showToast(Context context, String text, int duration) {
        //如果，没有toast创建一个，有的话修改内容和时间后显示
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();

    }
}
