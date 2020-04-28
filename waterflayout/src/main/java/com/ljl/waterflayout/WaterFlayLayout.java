package com.ljl.waterflayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljl
 * @Time: 2020/4/28 16:56
 * @Company：Haimo
 * @Description: 功能描述
 */
public class WaterFlayLayout extends ViewGroup {
    public WaterFlayLayout(Context context) {
        super(context);
    }

    public WaterFlayLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterFlayLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaterFlayLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    private List<View> linView = new ArrayList<>();
    private List<List<View>> allLinList = new ArrayList<>();
    List<Integer> lstLineHegiht = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取父类的大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //获取自身的mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //测量的宽高，最后设置的宽高
        int measureWidth = 0;
        int measureHeight = 0;

        //当前行宽高
        int currentWidth = 0;
        int currentHeight = 0;

        //各个child测量的宽高
        int childWidth = 0;
        int childHeight = 0;
        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) { //设置同父类一样大小
            measureWidth = widthSize;
            measureHeight = heightSize;
        } else {
            //获取所有的child
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                //获取每个子view
                View child = getChildAt(i);
                //子view 自己测量
                measureChild(child, widthSize, heightSize);

                //获取MARGIN 获取到当前LayoutParams
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                if (currentWidth + childWidth > widthSize) { //换行
                    measureWidth = Math.max(measureWidth, currentWidth);
                    measureHeight = currentHeight + childHeight;
                    allLinList.add(linView);
                    linView = new ArrayList<>();
                    linView.add(child);
                    currentWidth=childWidth;
                    currentHeight=childHeight;

                } else {
                    currentWidth += childWidth;
                    currentHeight = Math.max(currentHeight, childHeight);
                    linView.add(child);
                }
            }
        }
        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
