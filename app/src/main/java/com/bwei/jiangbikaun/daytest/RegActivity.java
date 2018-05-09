package com.bwei.jiangbikaun.daytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.jiangbikaun.daytest.beans.LoginBean;
import com.bwei.jiangbikaun.daytest.beans.RegBean;
import com.bwei.jiangbikaun.daytest.utils.Api;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 请输入手机号
     */
    private EditText mRegMobile;
    /**
     * 请输入密码
     */
    private EditText mRegPassword;
    /**
     * 注册
     */
    private Button mBtnRegSuc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        mRegMobile = (EditText) findViewById(R.id.reg_mobile);
        mRegPassword = (EditText) findViewById(R.id.reg_password);
        mBtnRegSuc = (Button) findViewById(R.id.btn_regSuc);
        mBtnRegSuc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_regSuc:
                RequestParams params = new RequestParams(Api.REG_URL);
                params.addBodyParameter("mobile",mRegMobile.getText().toString());
                params.addParameter("password",mRegPassword.getText().toString());
                // params.addHeader("head","android"); //为当前请求添加一个头
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //解析result
                        RegBean regBean = new Gson().fromJson(result, RegBean.class);
                        if ("0".equals(regBean.getCode())){
                            Toast.makeText(RegActivity.this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else if ("1".equals(regBean.getCode())){
                            Toast.makeText(RegActivity.this,regBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }
                    @Override
                    public void onCancelled(CancelledException cex) {
                    }
                    @Override
                    public void onFinished() {
                    }
                });
                break;
        }
    }
}
