package com.malex.velo72.custom.views.BottomSliderView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.malex.velo72.Velo72Application;

public class BottomSliderView extends LinearLayout {

    private int sliderHeight = 0;
    private int halfHeight = 0;
    private int maxHeight = 0;
    private int screenHeight = 0;
    private float screenDensity = 0;
    private float startY = 0;
    private FrameLayout.LayoutParams params;
    private LinearLayout llContainer;
    private ValueAnimator animator;

    public BottomSliderView(Context context) {
        super(context);
        setDisplayMetrics();
    }

    public BottomSliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setDisplayMetrics();
    }

    public BottomSliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDisplayMetrics();
    }

    public BottomSliderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setDisplayMetrics();
    }

    private void setDisplayMetrics()
    {
        Velo72Application app = (Velo72Application)getContext().getApplicationContext();
        screenHeight = app.getScreenHeight();
        screenDensity = app.getScreenDensity();
        halfHeight = screenHeight/2;
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
                int newHeight = sliderHeight + (int) (startY - y);
                if (newHeight < maxHeight)
                    params.height = newHeight;
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
        if (params.height < maxHeight & params.height > halfHeight)
        {
            if (sliderHeight == maxHeight) return halfHeight;
            if (sliderHeight == halfHeight) return maxHeight;
        }
        return 0;
    }

    private void mainViewsInitialize()
    {
        if (llContainer != null) {
            this.removeView(llContainer);
            llContainer = null;
        }
        llContainer = new LinearLayout(this.getContext());
        llContainer.setLayoutParams(new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

        params = (FrameLayout.LayoutParams) this.getLayoutParams();
    }

    public void setBottomSliderViewObject(BottomSliderViewObject object)
    {
        mainViewsInitialize();

        View view = object.getView();
        if (view != null)
        {
            llContainer.addView(object.getView());
            this.addView(llContainer);
        }

        invalidate();

        maxHeight = (int)(screenHeight - Math.ceil(25 * screenDensity));
        if (params.height == 0) executeAnimation(0, halfHeight);
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

