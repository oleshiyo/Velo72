package com.malex.velo72;

import android.app.Application;

import io.realm.Realm;

public class Velo72Application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }
}
