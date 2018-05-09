package com.bwei.jiangbikuan.day0509;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String url = "http://120.27.23.105/user/login";
    /**
     * 请输入手机号
     */
    private EditText mEtMobile;
    /**
     * 请输入密码
     */
    private EditText mEtPassword;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 注册
     */
    private Button mBtnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mEtMobile = (EditText) findViewById(R.id.et_mobile);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnReg = (Button) findViewById(R.id.btn_reg);
        mBtnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                RequestParams params = new RequestParams(url);
                params.addBodyParameter("mobile",mEtMobile.getText().toString());
                params.addParameter("password",mEtPassword.getText().toString());
                //params.addHeader("head","android"); //为当前请求添加一个头
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //解析result
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        if("0".equals(loginBean.getCode())){
                            Intent intent = new Intent(MainActivity.this,LoginSuccessActivity.class);
                            startActivity(intent);
                        }else if("1".equals(loginBean.getCode())){
                            Toast.makeText(MainActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
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
            case R.id.btn_reg:
                Intent intent = new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);
                break;
        }
    }
}
