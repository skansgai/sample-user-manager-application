package com.sample.android.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sample.android.R;
import com.sample.android.util.LogUtil;
import com.sample.android.widget.XListView;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.activity
 * 功能描述:用户信息列表
 * 作者:杨松松
 * 创建时间:2018/8/18 16:08
 */
public class UserInformationActivity extends Activity implements View.OnClickListener{

    private final static String TAG = UserInformationActivity.class.getName();

    private TextView backTv;
    private TextView moreTv;
    private String[]             mStrings  = { "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee",
            "Ffffff", "Gggggg", "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
            "Nnnnnn",                     };
    private LinkedList<String> listItems = null;
    private XListView listView  = null;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_activity);
        initView();
        initListener();
    }

    /**
     * UI空件初始化
     */
    private void initView() {
        LogUtil.i(TAG, "");
        listView = (XListView)findViewById(R.id.mlist);
        backTv = (TextView) findViewById(R.id.tile_back_tv);
        moreTv = (TextView) findViewById(R.id.tile_more_tv);

        listView.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {
                new GetDataTask(true).execute();
            }

            @Override
            public void onLoadMore() {
                new GetDataTask(false).execute();
            }
        });
        listItems = new LinkedList<String>();
        listItems.addAll(Arrays.asList(mStrings));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        backTv.setOnClickListener(this);
        moreTv.setOnClickListener(this);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
    }

    /**
     * 实现点击事件方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tile_back_tv:

                break;
            case R.id.tile_more_tv:

                break;
        }
    }


    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown){
            this.isDropDown = isDropDown;
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ;
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {

            if (isDropDown) {
                listItems.addFirst("Added after drop down");
                adapter.notifyDataSetChanged();

                listView.stopRefresh();
            } else {
                listItems.add("Added after on bottom");
                adapter.notifyDataSetChanged();
                listView.stopLoadMore();
            }

            super.onPostExecute(result);
        }
    }
}
