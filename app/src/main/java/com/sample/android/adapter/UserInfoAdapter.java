package com.sample.android.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sample.android.R;

import java.util.List;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.adapter
 * 功能描述:自定义Adapter，显示用户信息
 * 作者:杨松松
 * 创建时间:2018/8/19 22:01
 */
public class UserInfoAdapter extends BaseAdapter {

    private final static String TAG = UserInfoAdapter.class.getName();

    private Context mContext;
    private LayoutInflater inflater;
    private List<String> resurce;
    private Handler mHandler;

    public UserInfoAdapter(@Nullable Context context, @Nullable Handler activityHandler, @Nullable List<String> resurce) {
        this.mContext = context;
        this.mHandler = activityHandler;
        this.resurce = resurce;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resurce.size();
    }

    @Override
    public Object getItem(int position) {
        return resurce.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.xlistview_item, null);
            viewHolder.contentTv = convertView.findViewById(R.id.tv_text);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.contentTv.setText(resurce.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView contentTv;
    }
}
