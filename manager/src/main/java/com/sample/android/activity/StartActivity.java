package com.sample.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.sample.android.R;
import com.sample.android.util.LogUtil;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.activity
 * 功能描述:app的启动页面
 * 作者:杨松松
 * 创建时间:2018/7/11 20:38
 */
public class StartActivity extends Activity {
    private static final String TAG = StartActivity.class.getName();
    private Handler mHandler = new Handler();
    private final static int START_TIME = 2000;

    /**
     * onCreate()生命周期
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLogin();
            }
        }, START_TIME);
    }


    /**
     * 登录到主页的方法
     */
    private void gotoLogin() {
        LogUtil.i(TAG, "gotoLogin");
        Intent intent = new Intent(StartActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
        // 取消页面跳转时的动画，使启动的Logo图片与首页logo完美键衔接
        overridePendingTransition(0, 0);
    }

    /**
     * 屏蔽物理返回按键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.i(TAG, "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * onDestroy()生命周期
     */
    @Override
    protected void onDestroy() {
        LogUtil.i(TAG, "onDestroy");
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }
}
