package com.sample.android.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.android.R;
import com.sample.android.adapter.UserInfoAdapter;
import com.sample.android.model.entity.UserEntity;
import com.sample.android.util.LogUtil;
import com.sample.android.util.PublicTools;
import com.sample.android.widget.TopUtilsPopupWindow;
import com.sample.android.widget.XListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.activity
 * 功能描述:用户信息列表
 * 作者:杨松松
 * 创建时间:2018/8/18 16:08
 * @author 杨松松
 */
public class UserInformationActivity extends Activity implements View.OnClickListener {

    private final static String TAG = UserInformationActivity.class.getName();

    /**
     * 返回按钮
     */
    private TextView backTv;

    /**
     * 更多按钮
     */
    private TextView moreTv;

    /**
     * 数据集合
     */
    private LinkedList<String> listItems = null;

    /**
     * 列表对象
     */
    private XListView listView = null;

    /**
     * 适配器对象
     */
    private UserInfoAdapter adapter;

    /**
     * 用户信息集合
     */
    private List<UserEntity> recurse;

    /**
     * 添加文件
     */
    private TextView addUser;

    /**
     * 标题
     */
    private RelativeLayout rlTitle;

    /**
     * 数据源
     */
    private String[] mStrings = {"Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee", "Ffffff", "Gggggg",
            "Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm", "Nnnnnn",};

    private TopUtilsPopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_activity);
        PublicTools.setStatusBar(this);
        initView();
        initDate();
        initListener();
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        recurse = new ArrayList<>();
        for (int i = 0; i < mStrings.length; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setZhName(mStrings[i]);
            userEntity.setAddress(mStrings[i] + i);
            recurse.add(userEntity);
        }
    }

    /**
     * UI空件初始化
     */
    private void initView() {
        LogUtil.i(TAG, "initView");
        rlTitle = (RelativeLayout) findViewById(R.id.tile_rl);
        backTv = (TextView) findViewById(R.id.tile_back_tv);
        moreTv = (TextView) findViewById(R.id.tile_more_tv);
        addUser = (TextView) findViewById(R.id.add);
        listView = (XListView) findViewById(R.id.my_list);
        popupWindow = new TopUtilsPopupWindow(UserInformationActivity.this, 1);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        backTv.setOnClickListener(this);
        moreTv.setOnClickListener(this);
        addUser.setOnClickListener(this);
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

        // 建立Adapter并且绑定数据源
        listItems = new LinkedList<String>();
        listItems.addAll(Arrays.asList(mStrings));
        adapter = new UserInfoAdapter(this, recurse);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
    }

    /**
     * 实现点击事件方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tile_back_tv:
                LogUtil.i(TAG, "back");
                break;
            case R.id.tile_more_tv:
                LogUtil.i(TAG, "more");
                showPopupMenu();
                break;
            case R.id.add:
                LogUtil.i(TAG, "add");
                if (!popupWindow.isShowing()) {
                    popupWindow.showPopupWindowAsButton(addUser);
                } else {
                    popupWindow.dismiss();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 回调监听
     */
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        private boolean isDropDown;

        public GetDataTask(boolean isDropDown) {
            this.isDropDown = isDropDown;
        }

        @Override
        protected String[] doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
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

    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, moreTv);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_1:
                        Toast.makeText(UserInformationActivity.this, "Option 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_2:
                        Toast.makeText(UserInformationActivity.this, "Option 2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_3:
                        Toast.makeText(UserInformationActivity.this, "Option 3", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }


    @SuppressLint("ResourceType")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.add) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.layout.popup_window_category, menu);
            menu.setHeaderTitle("菜单");
            registerForContextMenu(addUser);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

}
