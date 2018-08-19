package com.sample.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sample.android.R;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:XListView头布局
 * 作者:杨松松
 * 创建时间:2018/8/18 18:49
 */
public class XListViewHeader extends LinearLayout {

    private final static String TAG = XListViewHeader.class.getName();


    /**
     * 下拉布局主体
     */
    private LinearLayout mContainer;
    /**
     * 下拉箭头
     */
    private ImageView mArrowImageView;
    /**
     * 环形进度条
     */
    private ProgressBar mProgressBar;
    /**
     * 提示文本
     */
    private TextView mHintTextView;
    private int mState = STATE_NORMAL;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    /**
     * 动画时间
     */
    private final int ROTATE_ANIM_DURATION = 180;

    public final static int STATE_NORMAL = 0;//普通状态
    public final static int STATE_READY = 1;//下拉准备刷新
    public final static int STATE_REFRESHING = 2;//正在加载

    public XListViewHeader(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public XListViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        // 初始情况，设置下拉刷新view高度为0
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.FILL_PARENT, 0);
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.xlistview_header, null);
        addView(mContainer, lp);
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView)findViewById(R.id.xlistview_header_arrow);
        mHintTextView = (TextView)findViewById(R.id.xlistview_header_hint_textview);
        mProgressBar = (ProgressBar)findViewById(R.id.xlistview_header_progressbar);
        //旋转动画
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        //设置动画时间
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        //动画终止时停留在最后一帧,也就是保留动画以后的状态
        mRotateUpAnim.setFillAfter(true);
        //旋转动画
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }

    public void setState(int state) {
        if (state == mState) return ;

        if (state == STATE_REFRESHING) {// 显示进度
            mArrowImageView.clearAnimation();
            mArrowImageView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {// 显示箭头图片
            mArrowImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.INVISIBLE);
        }

        switch(state){
            case STATE_NORMAL:
                if (mState == STATE_READY) {
                    mArrowImageView.startAnimation(mRotateDownAnim);
                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView.clearAnimation();
                }
                mHintTextView.setText(R.string.xlistview_header_hint_normal);
                break;
            case STATE_READY:
                if (mState != STATE_READY) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mHintTextView.setText(R.string.xlistview_header_hint_ready);
                }
                break;
            case STATE_REFRESHING:
                mHintTextView.setText(R.string.xlistview_header_hint_loading);
                break;
            default:
        }

        mState = state;
    }

    /**
     * 设置下拉头有效高度
     * @param height
     */
    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    /**
     * 获得下拉头有效高度
     * @return
     */
    public int getVisiableHeight() {
        return mContainer.getLayoutParams().height;
    }
}
