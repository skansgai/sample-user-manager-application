package com.sample.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import com.sample.android.R;

/**
 * version v1.0
 * 项目:sample-user-manager-application
 * 包名:com.sample.android.widget
 * 功能描述:自定义RadioGroup，实现动态设置行数和列数
 * 作者:杨松松
 * 创建时间:2018/9/6 1:31
 */
public class GridRadioGroupView extends RadioGroup {

    private static final String TAG = "GridRadioGroupView";

    public static final int VERTICAL_SPACING_DPI = 15;

    public static final int HORIZONTAL_SPACING_DPI = 10;

    public float verticalSpacing = 20;

    public float horizontalSpacing = 12;

    public int numColmuns = 3;

    public GridRadioGroupView(Context context) {
        super(context, null);
    }

    public GridRadioGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GridRadioGroupView);
        numColmuns = typedArray.getInt(R.styleable.GridRadioGroupView_numColumns, numColmuns);
        int tempVerSpacing = (int) (VERTICAL_SPACING_DPI * context.getResources().getDisplayMetrics().density);
        int tempHorSpacing = (int) (HORIZONTAL_SPACING_DPI * context.getResources().getDisplayMetrics().density);
        verticalSpacing = typedArray.getDimension(R.styleable.GridRadioGroupView_verticalspacing, tempVerSpacing);
        horizontalSpacing = typedArray.getDimension(R.styleable.GridRadioGroupView_horizontalspacing, tempHorSpacing);

        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int contentWidth = right - left - getPaddingRight() - getPaddingLeft();
        int itemWidth = (int) (contentWidth - horizontalSpacing * (numColmuns - 1)) / numColmuns;

        int x = getPaddingLeft();
        int y = 0;
        int rows = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int height = view.getMeasuredHeight();
            x += itemWidth;

            if (i % numColmuns == 0) {
                x = getPaddingLeft() + itemWidth;
                rows++;
            }

            y = rows * height + (rows - 1) * (int) verticalSpacing + getPaddingTop();
            view.layout(x - itemWidth, y - height, x, y);
            x += horizontalSpacing;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int contentWidth = specWidth - getPaddingRight() - getPaddingLeft();
        int itemWidth = (int) (contentWidth - horizontalSpacing * (numColmuns - 1)) / numColmuns;
        int y = 0;
        int rows = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.measure(MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY), MeasureSpec.UNSPECIFIED);
            int height = child.getMeasuredHeight();

            if (i % numColmuns == 0) {
                rows++;
            }

            y = rows * height + (rows - 1) * (int) verticalSpacing + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(specWidth, y);
    }
}
