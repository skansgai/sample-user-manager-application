package com.sample.android.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

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

    public MGPopupMenu(Context context,List<MenuItem> items,int width,int height){
        this.mContext = context;
        this.menuItems = items;
        this.mWidth = width;
        this.mContainer = (LinearLayout) View.inflate(context, R.layout.popup_menu_item,(ViewGroup)null);
        this.initView(context,items,width);
        this.mPopupWindow = new PopupWindow(this.mContainer,width,height);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setTouchable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    private void initView(Context context, List<MenuItem> items, int width) {
        if (null != items && !items.isEmpty()) {
            LayoutParams itemsLayoutParms = new LayoutParams(-1,-2);
            Iterator var1 = items.iterator();
            while(var1.hasNext()) {
                MenuItem item = (MenuItem) var1.next();
                ViewGroup itemView = null;
            }
        }
     }

    public interface OnItemOnclickListener {
        void onClickListener(MenuItem item);
    }
}
