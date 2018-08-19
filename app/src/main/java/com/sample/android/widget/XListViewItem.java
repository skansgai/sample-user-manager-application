package com.sample.android.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述: 自定义XListView的item，实现左滑删除功能
 * 作者:杨松松
 * 创建时间:2018/8/19 21:31
 */
public class XListViewItem extends HorizontalScrollView {

    private final static String TAG = XListViewItem.class.getName();

    private final static int SHOW = 0;
    private final static int HIDE = 1;

    private Scroller scroller;
    /**
     * 开始滑动位置
     */
    private int start;

    /**
     * 结束滑动位置
     */
    private int end;


    private VelocityTracker velocityTracker; // 触摸助手

    public XListViewItem(Context context) {
        super(context);
    }

    public XListViewItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XListViewItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public XListViewItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start = (int) event.getX();
                if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                velocityTracker.addMovement(event);
                break;
            default:
                // 默认执行MotionEvent.ACTION_UP
                end = (int) event.getX();
                int width = 140;

                if (start > end) { //如果手指按下的X坐标大于手指抬起的X坐标
                    if (getScaleX() > width / 2
                            || velocityTracker.getXVelocity() > 500) { //获得横向滑动的速
                        smoothScrollTo(width, 0);
                    } else {
                        smoothScrollTo(0, 0);
                    }
                }

                if (start < end) {
                    if (getScaleX() < width / 2) {
                        smoothScrollTo(width, 0);
                    } else {
                        smoothScrollTo(0, 0);
                    }
                }
                velocityTracker.clear();
                break;
        }
        return super.onTouchEvent(event);
    }
}
