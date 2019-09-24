package com.malex.velo72.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.malex.velo72.R;
import com.malex.velo72.presenters.MapPresenter;
import com.malex.velo72.views.MapView;

import java.util.List;

public class MapActivity extends MvpAppCompatActivity implements MapView, OnMapReadyCallback {

    @InjectPresenter
    MapPresenter mapPresenter;

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mapPresenter.loadBikeways();
        mapPresenter.loadBikeShops();
    }

    @Override
    public void updateBikeways(List<PolylineOptions> polylines) {
        for (PolylineOptions polyline:polylines
             ) {
            mMap.addPolyline(polyline);
        }
    }

    @Override
    public void updateBikeShops(List<MarkerOptions> markers) {
        for (MarkerOptions marker:markers
        ) {
            mMap.addMarker(marker);
        }
    }
}
