package com.sample.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sample.android.R;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： 主页activity
 * @作者： 杨松松
 * @创建时间： 2017/11/19 16:26
 */

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
    }
}
