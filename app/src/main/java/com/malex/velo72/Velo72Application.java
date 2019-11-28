package com.malex.velo72;

import android.app.Application;
import android.util.DisplayMetrics;

import io.realm.Realm;

public class Velo72Application extends Application {

    private int screenHeight;
    private float screenDensity;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        setDisplayMetrics();
    }

    private void setDisplayMetrics()
    {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenDensity = dm.density;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }

    public float getScreenDensity()
    {
        return screenDensity;
    }
}
