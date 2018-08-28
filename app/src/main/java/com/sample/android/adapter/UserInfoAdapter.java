package com.sample.android.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.android.R;
import com.sample.android.model.entity.UserEntity;
import com.sample.android.util.ToastUtil;
import com.sample.android.widget.XListViewItem;

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

    /**
     * 数据源
     */
    private List<UserEntity> resurce;

    /**
     * 上下文对象
     */
    private Context mContext;

    public UserInfoAdapter(@Nullable Context context, @Nullable List<UserEntity> resurce) {
        this.resurce = resurce;
        this.mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.swiplist_item, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.profile_image);
            holder.groupName = (TextView) convertView.findViewById(R.id.group_name);
            holder.content = (TextView) convertView.findViewById(R.id.qq_content);
            holder.toTop = (TextView) convertView.findViewById(R.id.to_top);
            holder.hadRead = (TextView) convertView.findViewById(R.id.had_read);
            holder.delete = (TextView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserEntity entity = (UserEntity) getItem(position);

        holder.groupName.setText(entity.getZhName());
        holder.content.setText(entity.getAddress());

        final XListViewItem finalContentView = (XListViewItem) convertView;
        holder.toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "置顶");
                finalContentView.smoothCloseMenu();
            }
        });
        holder.hadRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext, "已阅读");
                finalContentView.smoothCloseMenu();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resurce.remove(position);
                finalContentView.smoothCloseMenu();
                notifyDataSetChanged();
                ToastUtil.showToast(mContext, "已删除");
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView groupName;
        TextView content;
        TextView toTop;
        TextView hadRead;
        TextView delete;

    }
}
