package com.malex.velo72.custom_views.BottomSliderView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BottomSliderView extends LinearLayout {

    private int height = 0;
    private float startY = 0;
    private FrameLayout.LayoutParams params;
    private LinearLayout llShortDescription;
    private LinearLayout llLongDescription;

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
            case MotionEvent.ACTION_DOWN: // нажатие
                params = (FrameLayout.LayoutParams) this.getLayoutParams();
                height = params.height;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE: // движение
                params.height = height + (int) (startY - y);
                this.setLayoutParams(params);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void mainViewsInitialize()
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

    }

    public void setBottomSliderViewObject(BottomSliderViewObject object)
    {
        mainViewsInitialize();
        llShortDescription.addView(object.getShortDescriptionView());
        llLongDescription.addView(object.getLongDescriptionView());
        this.addView(llShortDescription);
        this.addView(llLongDescription);
        invalidate();
    }
}

