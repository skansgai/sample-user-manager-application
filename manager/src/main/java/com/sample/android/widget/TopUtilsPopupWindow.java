package com.sample.android.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.sample.android.R;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:自定义PopupWindow
 * 作者:杨松松
 * 创建时间:2018/9/3 21:20
 */
public class TopUtilsPopupWindow extends PopupWindow implements View.OnClickListener {

    /**
     * 日志打印标签
     */
    private final static String TAG = TopUtilsPopupWindow.class.getSimpleName();

    /**
     * 自定义PopupWindow试图
     */
    private View coentxtView;

    /**
     * PopupWindow的宽
     */
    private int width;

    /**
     * PopupWindow的高
     */
    private int height;

    private Activity context;

    /**
     * PopupWindow类型
     */
    private int type;

    /**
     * PopupWindow监听
     */
    private PopupWindowOnclickListener popupWindowOnclickListener;

    public TopUtilsPopupWindow(Activity context, int type) {
        this.context = context;
        this.type = type;
        initView();
        initViewStyle();
        initListener();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        coentxtView = inflater.inflate(R.layout.popup_window_category, null);
        this.setContentView(coentxtView);

    }

    private void initViewStyle() {
        /** 设置PopupWindow的宽和高 */
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.update();
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        /** 设置半透明背景 */
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.popupwindow_anim);
    }


    private void backgroundAlpha(float b) {
        WindowManager.LayoutParams layoutParams = context.getWindow().getAttributes();
        layoutParams.alpha = b;
        context.getWindow().setAttributes(layoutParams);
    }

    private void initListener() {
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
                if (stackTraceElements.length >= 2 && "dispathchKeyEvent".equals(stackTraceElements[1].getMethodName())) {
                }
                backgroundAlpha(1f);
            }
        });
    }

    public enum FileType {
        PDF,
        IMAGE,
        ZIP,
        OTHERS
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                break;
        }
    }

    public void setPopupWindowOnclickListener(PopupWindowOnclickListener popupWindowOnclickListener) {
        popupWindowOnclickListener.popupWindowOnClick(coentxtView);
    }

    public interface PopupWindowOnclickListener {
        void popupWindowOnClick(View view);
    }

    @SuppressLint("ResourceAsColor")
    public void showPopupWindowAsButton(View parent) {
        parent.setAlpha(1f);
        this.showAtLocation(parent, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);

    }

    public void showPopupWindowAsDropDown(View parent) {
        this.showAsDropDown(parent);
    }

    public void dismissPopupWindow(View parent) {
        if (this.isShowing()) {
            parent.setAlpha(1f);
            this.dismiss();
        }
    }

}