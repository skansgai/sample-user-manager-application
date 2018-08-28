package com.sample.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.sample.android.R;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:自定义XListView，实现上滑加载更多，下拉刷新数据
 * 作者:杨松松
 * 创建时间:2018/8/18 22:33
 */
public class XListView extends ListView implements AbsListView.OnScrollListener {

    private final static String TAG = XListView.class.getName();

    /**
     * 保存下拉位置Y坐标
     */
    private float mLastY = -1;

    /**
     * Scroller
     */
    private Scroller mScroller;

    /**
     * 滑动监听
     */
    private OnScrollListener mScrollListener;

    /**
     * XListView监听
     */
    private IXListViewListener mListViewListener;

    /**
     * 下拉头部
     */
    private XListViewHeader mHeaderView;

    /**
     * 下拉主体，用于计算头部高度
     */
    private RelativeLayout mHeaderViewContent;

    /**
     * 下拉箭头
     */
    private TextView mHeaderTimeView;

    /**
     * 下拉头部高度
     */
    private int mHeaderViewHeight;

    /**
     * 能否下拉刷新
     */
    private boolean mEnablePullRefresh = true;

    /**
     * 是否正在刷新
     */
    private boolean mPullRefreshing = false;


    /**
     * 滑动速度追踪类
     */
    private VelocityTracker mVelocityTracker;

    /**
     * ACTION_DOWN的坐标
     */
    private float xDown;
    private float yDown;

    /**
     * 判断横滑、竖滑的最小值
     */
    private int MAX_Y = 5;
    private int MAX_X = 3;

    /**
     * 当前点击的position
     */
    private int mTouchPosition;

    /**
     * 当前点击的item View
     */
    private XListViewItem mTouchView;

    /**
     * 当前触摸状态
     */
    private int mTouchState = TOUCH_STATE_NONE;

    /**
     * ACTION_DOWN时设置的状态
     */
    private static final int TOUCH_STATE_NONE = 0;

    /**
     * 横滑
     */
    private static final int TOUCH_STATE_X = 1;

    /**
     * 竖滑
     */
    private static final int TOUCH_STATE_Y = 2;


    /**
     * footer view
     */
    private XListViewFooter mFooterView;

    private boolean mEnablePullLoad;
    private boolean mPullLoading;
    private boolean mIsFooterReady = false;

    /**
     * item个数
     */
    private int mTotalItemCount;

    private int mScrollBack;

    /**
     * 头部滑动返回
     */
    private final static int SCROLLBACK_HEADER = 0;
    /**
     * footer滑动返回
     */
    private final static int SCROLLBACK_FOOTER = 1;

    private final static int SCROLL_DURATION = 400;
    private final static int PULL_LOAD_MORE_DELTA = 50;
    private final static float OFFSET_RADIO = 1.8f; // support iOS like pull

    public XListView(Context context) {
        super(context);
        initWithContext(context);
    }


    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initWithContext(context);
    }


    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);

        // 初始化下拉头
        mHeaderView = new XListViewHeader(context);
        mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.xlistview_header_content);
        mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.xlistview_header_time);
        addHeaderView(mHeaderView);

        // 初始化底部
        mFooterView = new XListViewFooter(context);
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mHeaderViewHeight = mHeaderViewContent.getHeight(); // 获得下拉头高度
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

        MAX_X = dp2px(MAX_X);
        MAX_Y = dp2px(MAX_Y);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        /*
         * 将上拉加载更多footer加入listview的底部
         * 并且保证只加入一次
         */
        if (mIsFooterReady == false) {
            mIsFooterReady = true;
            addFooterView(mFooterView);//listview原生方法
        }

        super.setAdapter(adapter);
    }

    /**
     * 设置下拉刷新功能是否开启
     *
     * @param enable
     */
    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;
        if (!mEnablePullRefresh) {//不开启，隐藏头部
            mHeaderViewContent.setVisibility(View.INVISIBLE);
        } else {
            mHeaderViewContent.setVisibility(View.VISIBLE);
        }
    }


    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
        if (!mEnablePullLoad) {
            mFooterView.hide();
            mFooterView.setOnClickListener(null); //取消监听
            setFooterDividersEnabled(false);
        } else {
            mPullLoading = false;
            mFooterView.show();
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
            setFooterDividersEnabled(true);
            mFooterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLoadMore();
                }
            });
        }
    }

    /**
     * 停止刷新
     */
    public void stopRefresh() {
        if (mPullRefreshing == true) {
            mPullRefreshing = false;
            resetHeaderHeight();
        }
    }

    /**
     * stop load ic_more, reset footer view.
     * 停止加载更多，重置footer
     */
    public void stopLoadMore() {
        if (mPullLoading == true) {
            mPullLoading = false;
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
        }
    }

    /**
     * set last refresh time
     * 设置最后一次下来刷新时间
     *
     * @param time
     */
    public void setRefreshTime(String time) {
        mHeaderTimeView.setText(time);
    }


    /**
     * 创建VelocityTracker对象，并将触摸事件加入到VelocityTracker当中
     *
     * @param event
     */
    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 获取手指在滑动的速度
     *
     * @return 滑动速度，以每秒钟移动了多少像素值为单位
     */
    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    /**
     * 回收VelocityTracker对象
     */
    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 更新头部高度
     *
     * @param delta
     */
    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
        if (mEnablePullRefresh && !mPullRefreshing) {//未处于刷新状态，更新箭头
            if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                mHeaderView.setState(XListViewHeader.STATE_READY);
            } else {
                mHeaderView.setState(XListViewHeader.STATE_NORMAL);
            }
        }
        //滑动到头部
        setSelection(0); // scroll to top each time
    }

    /**
     * 重置头部高度
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) {
            return;
        }
        if (mPullRefreshing && height <= mHeaderViewHeight) {
            return;
        }

        int finalHeight = 0;
        if (mPullRefreshing && height > mHeaderViewHeight) {
            finalHeight = mHeaderViewHeight;
        }

        mScrollBack = SCROLLBACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        invalidate();
    }

    /**
     * 更新footer离底部的距离
     *
     * @param delta
     */
    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load上拉足够高才去加载
                // ic_more.
                mFooterView.setState(XListViewFooter.STATE_READY);
            } else {
                mFooterView.setState(XListViewFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);
    }

    /**
     * 重置footer位置
     */
    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
                    SCROLL_DURATION);
            invalidate();
        }
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }

    private void startLoadMore() {
        mPullLoading = true;
        mFooterView.setState(XListViewFooter.STATE_LOADING);
        if (mListViewListener != null) {
            mListViewListener.onLoadMore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() != MotionEvent.ACTION_DOWN && mTouchView == null) {
            return super.onTouchEvent(ev);
        }

        // 加入触摸跟踪类
        createVelocityTracker(ev);
        float moveX;
        float moveY;

        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();

                int prevPosition = mTouchPosition;
                xDown = ev.getX();
                yDown = ev.getY();
                mTouchState = TOUCH_STATE_NONE;
                mTouchPosition = pointToPosition((int) xDown, (int) yDown);
                // 当前点击的Item正好是已经显示Menu的Item
                if (prevPosition == mTouchPosition && mTouchView != null && mTouchView.isMenuOpen()) {
                    mTouchState = TOUCH_STATE_X;
                    return true; // 返回true表示接受了ACTION_DOWN，那么后面的事件依然会分发给MyListView
                }
                View view = getChildAt(mTouchPosition - getFirstVisiblePosition());
                // 点击的Item不是正在显示Menu的Item，则直接关闭Menu
                if (mTouchView != null && mTouchView.isMenuOpen()) {
                    mTouchView.smoothCloseMenu();
                    mTouchView = null;
                    return false; // 返回false，那么后面的事件全部会接收不到
                }
                if (view instanceof XListViewItem) {
                    mTouchView = (XListViewItem) view;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY; // 下拉或者上拉了多少offset
                mLastY = ev.getRawY();
                if (getFirstVisiblePosition() == 0
                        && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1
                        && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                    updateFooterHeight(-deltaY / OFFSET_RADIO);
                }

                moveX = ev.getX() - xDown;
                moveY = ev.getY() - yDown;

                if (mTouchState == TOUCH_STATE_X) {
                    // 如果是横滑，则设置leftMargin
                    if (!mTouchView.isMenuOpen()) {
                        mTouchView.setLeftMargin((int) moveX);
                    } else {
                        mTouchView.setLeftMargin((int) (moveX - mTouchView.getMenuWidth()));
                    }
                    return true;
                } else if (mTouchState == TOUCH_STATE_NONE) {
                    // 设置横滑还是竖滑
                    if (Math.abs(moveY) > MAX_Y) {
                        mTouchState = TOUCH_STATE_Y;
                    } else if (Math.abs(moveX) > MAX_X) {
                        mTouchState = TOUCH_STATE_X;
                    }
                }

                break;
            default:
                mLastY = -1;
                if (getFirstVisiblePosition() == 0) {
                    if (mEnablePullRefresh && mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                        mPullRefreshing = true;
                        mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
                        if (mListViewListener != null) {
                            mListViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1) {
                    if (mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA
                            && !mPullLoading) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }

                moveX = ev.getX() - xDown;
                if (mTouchState == TOUCH_STATE_X) {
                    // 若滑动的距离是Menu宽度的一半，或者左滑速度大于200,
                    if (-moveX > mTouchView.getMenuWidth() / 2 || (moveX < 0 && getScrollVelocity() > 200)) {
                        // 若Menu是关闭的
                        if (!mTouchView.isMenuOpen()) {
                            // 滑动打开Menu
                            mTouchView.smoothOpenMenu();
                        }
                    } else {
                        // 滑动关闭Menu
                        mTouchView.smoothCloseMenu();
                        mTouchView = null;
                        mTouchPosition = -1;
                    }
                    recycleVelocityTracker();
                    return true;
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            } else {
                mFooterView.setBottomMargin(mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void setXListViewListener(IXListViewListener l) {
        mListViewListener = l;
    }


    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onXScrolling when header/footer scroll ic_back.
     */
    public interface OnXScrollListener extends OnScrollListener {
        public void onXScrolling(View view);
    }

    /**
     * implements this interface to get refresh/load ic_more event.
     * 下拉和上拉监听器
     */
    public interface IXListViewListener {
        public void onRefresh();

        public void onLoadMore();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }
}
