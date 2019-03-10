package com.sample.android.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sample.android.R;

import java.util.Iterator;
import java.util.List;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:菜单工具
 * 作者:杨松松
 * 创建时间:2019/1/15 23:58
 */
public class MGPopupMenu {
    private static final String TAG = MGPopupMenu.class.getSimpleName();
    public static int TEXT_COLOR_DEFAULT = 0;
    private PopupWindow mPopupWindow;
    private LinearLayout mContainer;
    private Context mContext;
    private MGPopupMenu.OnItemOnclickListener onItemOnclickListener;
    private List<MenuItem> menuItems;
    private int mWidth;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (MGPopupMenu.this.onItemOnclickListener != null) {
                MGPopupMenu.this.onItemOnclickListener.onClickListener((MenuItem) v.getTag());
            }
        }
    };

    public MGPopupMenu(Context context, List<MenuItem> items, int width, int height) {
        this.mContext = context;
        this.menuItems = items;
        this.mWidth = width;
        this.mContainer = (LinearLayout) View.inflate(context, R.layout.popup_menu_item, (ViewGroup) null);
        this.initView(context, items, width);
        this.mPopupWindow = new PopupWindow(this.mContainer, width, height);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setTouchable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initView(Context context, List<MenuItem> items, int width) {
        if (null != items && !items.isEmpty()) {
            LayoutParams itemsLayoutParms = new LayoutParams(-1, -2);
            Iterator var1 = items.iterator();
            while (var1.hasNext()) {
                MenuItem item = (MenuItem) var1.next();
                ViewGroup itemView = null;
                if (item.imgDrawable == null && item.imageId == 0) {
                    itemView = (ViewGroup) View.inflate(context, R.layout.popup_menu_item, (ViewGroup) null);
                } else {
                    itemView = (ViewGroup) View.inflate(context, R.layout.popup_menu_icon_item, (ViewGroup) null);
                    this.setItemImage(itemView, item);
                }
                ((TextView) itemView.findViewById(R.id.tv_menu_item_text)).setText(item.text);
                itemView.setTag(item);
                itemView.setOnClickListener(this.onClickListener);
                this.mContainer.addView(itemView, itemsLayoutParms);
            }
            this.mContainer.getChildAt(this.mContainer.getChildCount()).findViewById(0).setVisibility(View.GONE);
        }
    }

    private void setItemImage(ViewGroup itemView, MenuItem item) {
        if (item.imgDrawable != null) {
            ((ImageView) itemView.findViewById(R.id.iv_menu_item_icon)).setImageDrawable(item.imgDrawable);
        } else {
            ((ImageView) itemView.findViewById(R.id.iv_menu_item_icon)).setImageDrawable(this.getDrawable(this.mContext, item.imageId));
        }
    }

    private Drawable getDrawable(Context mContext, int imageId) {
        return mContext.getResources().getDrawable(imageId, null);
    }

    public void dismiss() {
        this.mPopupWindow.dismiss();
    }


    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public interface OnItemOnclickListener {
        void onClickListener(MenuItem item);
    }
}
