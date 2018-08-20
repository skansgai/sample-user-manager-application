package com.sample.android.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:
 * 作者:杨松松
 * 创建时间:2018/8/20 21:29
 */
public class SwipeListLayout extends FrameLayout {

    private static final String TAG = SwipeListLayout.class.getName();

    private View hiddenView;
    private View itemView;
    private int hiddenViewWidth;
    private int hiddenViewHeight;
    private int itemWidth;
    private int itemHeight;

    private ViewDragHelper mDrageHelper;
    private OnSwipeStatusListener listener;

    private Status status = Status.Close;
    private boolean smooth = true;

    public enum Status {
        Open, Close
    }


    public SwipeListLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwipeListLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDrageHelper = ViewDragHelper.create(this, callback);
    }

    public SwipeListLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwipeListLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 设置策划状态
     *
     * @param status
     * @param smooth
     */
    public void setStatus(@Nullable Status status, boolean smooth) {
        this.status = status;
        if (status == Status.Open) {
            open(smooth);
        } else {
            close(smooth);
        }
    }

    public void setOnSwipeStatusListener(@Nullable OnSwipeStatusListener listener) {
        this.listener = listener;
    }

    public void setSmooth(@Nullable boolean smooth) {
        this.smooth = smooth;
    }

    public interface OnSwipeStatusListener {

        void onStatusChanged(Status status);

        void onStatusCloseAnimation();

        void onStatusOpenAnimation();

    }


    Callback callback = new Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return super.clampViewPositionHorizontal(child, left, dx);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    };

    private Status preStatus = Status.Close;

    private void close(boolean smooth) {
        preStatus = status;
        status = Status.Close;
        if (smooth) {
            if (mDrageHelper.smoothSlideViewTo(itemView, 0, 0)) {
                if (listener != null) {
                    listener.onStatusCloseAnimation();
                }
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            layout(status);
        }
        if (listener != null && preStatus == Status.Open) {
            listener.onStatusChanged(status);
        }
    }

    private void layout(Status status) {
        if (status == Status.Close) {
            hiddenView.layout(itemWidth, 0, itemWidth + hiddenViewWidth, itemHeight);
            itemView.layout(0, 0, itemWidth, itemHeight);
        } else {
            hiddenView.layout(itemWidth - hiddenViewWidth, 0, itemWidth, itemHeight);
            itemView.layout(-hiddenViewWidth, 0, itemWidth - hiddenViewWidth, itemHeight);
        }
    }

    private void open(boolean smooth) {
        preStatus = status;
        status = Status.Open;
        if (smooth) {
            if (mDrageHelper.smoothSlideViewTo(itemView, -hiddenViewWidth, 0)) {
                if (listener != null) {
                    listener.onStatusOpenAnimation();
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else {
            layout(status);
        }

        if (listener != null && preStatus == Status.Open) {
            listener.onStatusChanged(status);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDrageHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL) {
            mDrageHelper.cancel();
            return false;
        }
        return mDrageHelper.shouldInterceptTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        mDrageHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        hiddenView = getChildAt(0);
        itemView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        itemWidth = itemView.getMeasuredWidth();
        itemHeight = itemView.getMeasuredHeight();

        hiddenViewWidth = hiddenView.getMeasuredWidth();
        hiddenViewHeight = hiddenView.getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        layout(Status.Close);
    }
}
