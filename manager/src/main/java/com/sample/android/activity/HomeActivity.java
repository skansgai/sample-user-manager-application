package com.sample.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.android.R;
import com.sample.android.util.LogUtil;
import com.sample.android.util.PublicTools;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： 主页activity
 * @作者： 杨松松
 * @创建时间： 2017/11/19 16:26
 */

public class HomeActivity extends Activity implements View.OnClickListener {


    /**
     * 日志打印标签
     */
    private final static String TAG = HomeActivity.class.getName();

    /**
     * app图标
     */
    private ImageView appIcon;

    /**
     * 用户名输入框
     */
    private EditText nameEdit;

    /**
     * 密码输入框
     */
    private EditText passwordEdit;

    /**
     * 登录按钮
     */
    private TextView loginTv;

    /**
     * 注册按钮
     */
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        PublicTools.setStatusBar(this);
        initView();
        setButtomLine(login);
        initListener();
    }

    /**
     * UI控件初始化
     */
    private void initView() {
        LogUtil.i(TAG, "");

        appIcon = (ImageView) findViewById(R.id.app_icon_img);
        nameEdit = (EditText) findViewById(R.id.edit_name_et);
        passwordEdit = (EditText) findViewById(R.id.edit_pwd_et);
        loginTv = (TextView) findViewById(R.id.login_tv);
        login = (TextView) findViewById(R.id.log_in_tv);
    }

    private void initListener() {
        loginTv.setOnClickListener(this);
    }

    /**
     * 实现点击监听
     *
     * @param v view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_tv:
                login();
                break;

        }
    }

    /**
     * 登录方法
     */
    private void login() {
        Intent intent = new Intent(HomeActivity.this, UserInformationActivity.class);
        startActivity(intent);
    }

    /**
     * 设置TextView文字下划线
     *
     * @param textView
     */
    private void setButtomLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }
}
