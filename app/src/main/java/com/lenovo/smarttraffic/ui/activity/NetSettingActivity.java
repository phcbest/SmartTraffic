package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.util.ToastUtils;

public class NetSettingActivity extends BaseActivity {
    private EditText editServiceAddress1;
    private EditText editServiceAddress2;
    private EditText editServiceAddress3;
    private EditText editServiceAddress4;

    private EditText editServicePort;
    private TextView buttonSave;
    private TextView buttonCancel;

    private static final String TAG = "NetSettingActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_setting_net;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initTap();
    }


    private void initTap() {
        //检测输入框输入
        editServiceAddress1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                correctionCharacter(charSequence, editServiceAddress1);
            }


            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        editServiceAddress2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                correctionCharacter(charSequence, editServiceAddress2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editServiceAddress3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                correctionCharacter(charSequence, editServiceAddress3);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editServiceAddress4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                correctionCharacter(charSequence, editServiceAddress4);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editServicePort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int port = Integer.valueOf(charSequence.toString());
                if (port > 65535) {
                    ToastUtils.showToast(NetSettingActivity.this, "只能输入0-65535", Toast.LENGTH_SHORT);
                    editServicePort.setText(R.string.text_max_port);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //取消按钮
        buttonCancel.setOnClickListener(view -> {
            finish();
        });

        //确定按钮
        buttonSave.setOnClickListener(view -> {
            if (!editServiceAddress1.getText().toString().isEmpty() && !editServiceAddress2.getText().toString().isEmpty() &&
                    !editServiceAddress3.getText().toString().isEmpty() && !editServiceAddress4.getText().toString().isEmpty() &&
                    !editServicePort.getText().toString().isEmpty()) {
                String format = String.format("当前ip：%s.%s.%s.%s \n 端口:%s", editServiceAddress1.getText().toString(),
                        editServiceAddress2.getText().toString(), editServiceAddress3.getText().toString(),
                        editServiceAddress4.getText().toString(), editServicePort.getText().toString());
                Log.i(TAG, "initTap: " + format);
                ToastUtils.showToast(this, format, Toast.LENGTH_SHORT);
                finish();
            } else {
                ToastUtils.showToast(this, "参数不齐全", Toast.LENGTH_SHORT);
            }

        });

    }

    private void initData() {

    }

    private void correctionCharacter(CharSequence charSequence, EditText editText) {
        if (charSequence.length() > 3) {
            ToastUtils.showToast(this, "只能输入0-255", Toast.LENGTH_SHORT);
            editText.setText(R.string.text_max_ip);
        }
        int ipAddress = 0;
        try {
            ipAddress = Integer.valueOf(charSequence.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (ipAddress > 255) {
            ToastUtils.showToast(this, "只能输入0-255", Toast.LENGTH_SHORT);
            editText.setText(R.string.text_max_ip);
        }
    }


    private void initView() {
        editServiceAddress1 = (EditText) findViewById(R.id.edit_service_address1);
        editServiceAddress2 = (EditText) findViewById(R.id.edit_service_address2);
        editServiceAddress3 = (EditText) findViewById(R.id.edit_service_address3);
        editServiceAddress4 = (EditText) findViewById(R.id.edit_service_address4);

        editServicePort = (EditText) findViewById(R.id.edit_service_port);
        buttonSave = (TextView) findViewById(R.id.button_save);
        buttonCancel = (TextView) findViewById(R.id.button_cancel);

    }
}
