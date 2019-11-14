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
import com.malex.velo72.custom.views.BottomSliderView.BikeParkingObject;
import com.malex.velo72.custom.views.BottomSliderView.BottomSliderView;
import com.malex.velo72.custom.views.BottomSliderView.MainMenuObject;
import com.malex.velo72.models.BikeParkingModel;
import com.malex.velo72.models.BikeShopModel;
import com.malex.velo72.presenters.MapPresenter;
import com.malex.velo72.views.MapView;

import java.util.List;

public class MapActivity extends MvpAppCompatActivity implements MapView, OnMapReadyCallback {

    @InjectPresenter
    MapPresenter mapPresenter;

    private GoogleMap mMap;
    private BottomSliderView bottomSliderView;
    private MainMenuObject mainMenuObject;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bottomSliderView = findViewById(R.id.bottomSliderView);
        mainMenuObject = new MainMenuObject(MapActivity.this, getSupportFragmentManager(), bottomSliderView);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTag().equals(R.string.bikeparking_entity)) mapPresenter.loadEntityDescription(R.string.bikeparking_entity, marker.getSnippet());
                if (marker.getTag().equals(R.string.bikeshop_entity)) mapPresenter.loadEntityDescription(R.string.bikeshop_entity, marker.getSnippet());
                return true;
            }
        });

        mapPresenter.loadBikeways();
        mapPresenter.loadBikeShops();
        mapPresenter.loadBikeParkings();
    }

    @Override
    public void updateBikeways(List<PolylineOptions> polylines) {
        for (PolylineOptions polyline : polylines
        ) {
            mMap.addPolyline(polyline);
        }
    }

    @Override
    public void updateBikeShops(List<MarkerOptions> markers) {
        for (MarkerOptions marker : markers
        ) {
            Marker bikeShopMarker = mMap.addMarker(marker);
            bikeShopMarker.setTag(R.string.bikeshop_entity);
        }
    }

    @Override
    public void updateBikeParkings(List<MarkerOptions> markers) {
        for (MarkerOptions marker : markers
        ) {
            Marker bikeParkingMarker = mMap.addMarker(marker);
            bikeParkingMarker.setTag(R.string.bikeparking_entity);
        }
    }

    @Override
    public void updateDescriptionView(BikeShopModel description) {
        //bottomSliderView.setBottomSliderViewObject(new BikeShopObject(MapActivity.this, description), getWindowManager().getDefaultDisplay());
        bottomSliderView.setBottomSliderViewObject(mainMenuObject, getWindowManager().getDefaultDisplay());
    }

    @Override
    public void updateDescriptionView(BikeParkingModel description) {
        bottomSliderView.setBottomSliderViewObject(new BikeParkingObject(MapActivity.this, description), getWindowManager().getDefaultDisplay());
    }

    public void showBikeHirePlaces()
    {
        int a = 1;
    }
}
