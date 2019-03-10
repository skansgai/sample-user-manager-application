package com.sample.android.widget;

import android.graphics.drawable.Drawable;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:PopupMenu的item对象
 * 作者:杨松松
 * 创建时间:2019/1/17 0:58
 */
public class MenuItem {
    public String text;
    public int textColor;
    public int imageId;
    public Drawable imgDrawable;
    public Object obj;
    public int arg;

    public MenuItem (String text, int textColor) {
        this(text, textColor, (Object)null);
    }


    public MenuItem(String text, int textColor, Object obj) {
        this(text, textColor, obj, (Drawable)null);
    }


    public MenuItem(String text, int textColor, Object obj, Drawable imgDrawable) {
        this.text = text;
        this.imageId = 0;
        this.textColor = MGPopupMenu.TEXT_COLOR_DEFAULT;
        this.textColor = textColor;
        this.imgDrawable = imgDrawable;
        this.obj = obj;
    }
}
