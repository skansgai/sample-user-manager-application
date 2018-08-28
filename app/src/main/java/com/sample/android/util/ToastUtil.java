package com.sample.android.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.android.R;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.util
 * 功能描述:Toast工具类
 * 作者:杨松松
 * 创建时间:2018/8/24 22:49
 */

public class ToastUtil {
    /**
     * Toast显示时长
     */
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    /**
     * 自定义Toast View
     */
    private static View toastView;

    /**
     * 屏幕窗口管理器
     */
    private WindowManager mWindowManager;
    private static int mDuration;
    private final int WHAT = 100;
    private static View oldView;
    private static Toast toast;
    private static CharSequence oldText;
    private static CharSequence currentText;
    private static ToastUtil instance = null;
    private static TextView textView;
    private static boolean isShow = true;
    private static ImageView mImgIcon;
    private static TextView mTxtMsg;
    private static LinearLayout mLytShow;

    /**
     * 显示消息(这个是把文本颜色和背景色没有提出来的写法)
     *
     * @param context 上下文
     * @param message 消息内容
     */
    public static void showToast(Context context, String message) {
        if (isShow) {
            toast = new Toast(context);
            // 消息内容
            mTxtMsg = new TextView(context);
            LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mTxtMsg.setTextColor(Color.parseColor("#000000"));
            mTxtMsg.setTextSize(DensityUtils.dp2px(context, 12));
            mTxtMsg.setPadding(10, 5, 10, 5);

            mTxtMsg.setLayoutParams(lParams1);

            mLytShow = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            mLytShow.setOrientation(LinearLayout.HORIZONTAL);
            mLytShow.setLayoutParams(params);

            int roundRadius = 100; // 8dp 圆角半径
            int fillColor = Color.parseColor("#F89009");// 内部填充颜色
            GradientDrawable gd = new GradientDrawable();// 创建drawable
            gd.setCornerRadius(roundRadius);
            gd.setColor(fillColor);

            mLytShow.setBackgroundDrawable(gd);
            mLytShow.addView(mTxtMsg);

            if (message != null) {
                mTxtMsg.setText(message);
            }
            toast.setView(mLytShow);
            toast.setGravity(Gravity.BOTTOM, 0,
                    DensityUtils.dp2px(context, 100));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 显示消息（这个是把文本颜色和toast背景颜色提出来的写法）
     *
     * @param context 上下文
     * @param message 消息内容
     */
    public static void showToast(Context context, String message,
                                 String textColor, String bgColor) {
        if (isShow) {
            toast = new Toast(context);
            // 消息内容
            mTxtMsg = new TextView(context);
            LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mTxtMsg.setTextColor(Color.parseColor(textColor));
            mTxtMsg.setTextSize(DensityUtils.dp2px(context, 12));
            mTxtMsg.setPadding(10, 5, 10, 5);

            mTxtMsg.setLayoutParams(lParams1);

            mLytShow = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            mLytShow.setOrientation(LinearLayout.HORIZONTAL);
            mLytShow.setLayoutParams(params);

            int roundRadius = 100; // 8dp 圆角半径
            int fillColor = Color.parseColor(bgColor);// 内部填充颜色
            GradientDrawable gd = new GradientDrawable();// 创建drawable
            gd.setCornerRadius(roundRadius);
            gd.setColor(fillColor);

            mLytShow.setBackgroundDrawable(gd);
            mLytShow.addView(mTxtMsg);

            if (message != null) {
                mTxtMsg.setText(message);
            }
            toast.setView(mLytShow);
            toast.setGravity(Gravity.BOTTOM, 0,
                    DensityUtils.dp2px(context, 100));
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param message 信息内容
     */
    public static void showIconToast(Context context, String message, int resId) {
        if (isShow) {
            toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.toast_layout, null);
            TextView tv = (TextView) view.findViewById(R.id.toast_tv);
            ImageView mImgShow = (ImageView) view.findViewById(R.id.img_toast);
            if (message != null) {
                tv.setText(message);
            }
            mImgShow.setImageResource(resId);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * 显示消息内容（带图标）
     *
     * @param context   上下文
     * @param message   消息内容
     * @param resId     图片资源id
     * @param textColor 文本颜色
     * @param textSize  文本大小
     * @param bgColor   背景颜色
     */
    public static void showIconToastMessage(Context context, String message,
                                            int resId, String textColor, float textSize, String bgColor) {
        if (isShow) {
            toast = new Toast(context);
            // 图片
            mImgIcon = new ImageView(context);
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    DensityUtils.dp2px(context, 48), DensityUtils.dp2px(
                    context, 48));
            mImgIcon.setImageResource(resId);
            lParams.gravity = Gravity.CENTER_HORIZONTAL
                    | Gravity.CENTER_VERTICAL;
            lParams.setMargins(5, 5, 5, 5);
            mImgIcon.setLayoutParams(lParams);

            // 消息内容
            mTxtMsg = new TextView(context);
            LinearLayout.LayoutParams lParams1 = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mTxtMsg.setTextColor(Color.parseColor(textColor));
            mTxtMsg.setTextSize(DensityUtils.dp2px(context, textSize));
            mTxtMsg.setLayoutParams(lParams1);

            mLytShow = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            mLytShow.setOrientation(LinearLayout.HORIZONTAL);
            mLytShow.setLayoutParams(params);

            int roundRadius = 100; // 8dp 圆角半径
            // int strokeColor = Color.parseColor("#2E3135");// 边框颜色
            int fillColor = Color.parseColor(bgColor);// 内部填充颜色
            GradientDrawable gd = new GradientDrawable();// 创建drawable
            gd.setColor(fillColor);
            gd.setCornerRadius(roundRadius);
            // gd.setStroke(0, strokeColor);

            mLytShow.setBackgroundDrawable(gd);
            mLytShow.addView(mImgIcon);
            mLytShow.addView(mTxtMsg);

            if (message != null) {
                mTxtMsg.setText(message);
            }
            toast.setView(mLytShow);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
