package com.malex.velo72.custom.views.BottomSliderView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BottomSliderView extends LinearLayout {

    private int sliderHeight = 0;
    private int minHeight = 0;
    private int maxHeight = 0;
    private float startY = 0;
    private FrameLayout.LayoutParams params;
    private LinearLayout llShortDescription;
    private LinearLayout llLongDescription;
    private ValueAnimator animator;

    public BottomSliderView(Context context) {
        super(context);
    }

    public BottomSliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomSliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomSliderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float y = motionEvent.getRawY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                params = (FrameLayout.LayoutParams) this.getLayoutParams();
                sliderHeight = params.height;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                params.height = sliderHeight + (int) (startY - y);
                this.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                executeAnimation(params.height, calculateDescriptionHeight());
                break;
        }
        return true;
    }

    private int calculateDescriptionHeight()
    {
        if (params.height > maxHeight) return maxHeight;
        if (params.height < maxHeight & params.height > minHeight)
        {
            if (sliderHeight == maxHeight) return minHeight;
            if (sliderHeight == minHeight) return maxHeight;
        }
        return 0;
    }

    private void mainViewsInitialize()
    {
        if (llShortDescription != null) {
            this.removeView(llShortDescription);
            llShortDescription = null;
        }
            llShortDescription = new LinearLayout(this.getContext());
            llShortDescription.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

        if (llLongDescription != null) {
            this.removeView(llLongDescription);
            llLongDescription = null;
        }
            llLongDescription = new LinearLayout(this.getContext());
            llLongDescription.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
        params = (FrameLayout.LayoutParams) this.getLayoutParams();
    }

    public void setBottomSliderViewObject(BottomSliderViewObject object, final Display display)
    {
        mainViewsInitialize();

        View shortDesc = object.getShortDescriptionView();
        if (shortDesc != null)
        {
            llShortDescription.addView(object.getShortDescriptionView());
            this.addView(llShortDescription);
        }

        View longDesc = object.getLongDescriptionView();
        if (longDesc != null)
        {
            llLongDescription.addView(object.getLongDescriptionView());
            this.addView(llLongDescription);
        }

        invalidate();
        llShortDescription.measure(0, 0);
        llLongDescription.measure(0, 0);

        minHeight = llShortDescription.getMeasuredHeight();
        if (minHeight < object.getViewHeight()) minHeight += object.getViewHeight();
        maxHeight = minHeight + llLongDescription.getMeasuredHeight();
        if (params.height == 0) executeAnimation(0, minHeight);
    }

    private void executeAnimation(int height1, int height2)
    {
        animator = ValueAnimator.ofInt(height1, height2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                params.height = (Integer) valueAnimator.getAnimatedValue();
                BottomSliderView.this.setLayoutParams(params);
            }
        });
        animator.setDuration(300);
        animator.start();
    }
}

