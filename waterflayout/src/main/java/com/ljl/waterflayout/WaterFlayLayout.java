package com.ljl.waterflayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

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
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    public LayoutParams getLayoutParams() {
        return super.getLayoutParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         // 1 先完成自己的宽高测试
         // 需要得到mode进行判断我的显示模式是什么？

        //得到父类宽高
       int widthSize= MeasureSpec.getSize(widthMeasureSpec);
       int heightSize=MeasureSpec.getSize(heightMeasureSpec);

       //得到自己的测量模式
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        //当前控件自己的宽高
        int measureWidth=0;
        int measureHeight=0;
        //layout_width=match_parent layout_height=match_parent
          if (widthMode==MeasureSpec.EXACTLY&&heightMode==MeasureSpec.EXACTLY){
              measureWidth=widthSize;
              measureHeight=heightSize;
          }else {
             // 获取所有的child 的总和
              int childCount = getChildCount();
              //当前行的高宽
              int iChildWidth=0;
              int iChildHeight=0;

              //当前行的宽高
              int CurrWidth=0;
              int CurrHeight=0;
              for (int i = 0; i < childCount; i++) {
                  //获取每一个child
                     View child =getChildAt(i);
                     //获取当前child 的了LayoutParams
                  MarginLayoutParams layoutParams = (MarginLayoutParams)child.getLayoutParams();
                     //child 先自己测量自己
                     measureChild(child,widthMeasureSpec,heightMeasureSpec);
                     iChildWidth =child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                     iChildHeight=child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                     if (iChildWidth+CurrWidth>widthSize){ //换行
                          //保存当前行信息
                         measureWidth =Math.max(measureWidth,CurrWidth);
                         measureHeight+=CurrHeight;
                          CurrWidth =iChildWidth;
                          CurrHeight= iChildHeight;
                     }else {
                           CurrWidth+=iChildWidth;
                           CurrHeight=Math.max(CurrHeight,iChildHeight);
                     }
              }
          }
          setMeasuredDimension(measureWidth,measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
