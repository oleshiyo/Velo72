package com.malex.velo72.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.malex.velo72.ModelConverter;
import com.malex.velo72.models.BikeParkingModel;
import com.malex.velo72.models.BikeShopModel;
import com.malex.velo72.models.BikewayModel;
import com.malex.velo72.providers.BikeStuffProvider;
import com.malex.velo72.views.MapView;

import java.util.ArrayList;
import java.util.List;


@InjectViewState
public class MapPresenter extends MvpPresenter<MapView> {

    private MapView mapView = getViewState();
    private BikeStuffProvider bsProvider = new BikeStuffProvider(this);

    public void loadBikeways() {
        bsProvider.getAllBikeways();
    }

    public void loadBikeShops() {
        bsProvider.getAllBikeShops();
    }

    public void loadBikeParkings() { bsProvider.getAllBikeParkings(); }

    public void loadEntityDescription(int entityType, String id) {
        bsProvider.loadEntityDescription(entityType, Long.parseLong(id));
        return;
    }

    public void bikewaysLoaded(List<BikewayModel> ways) {
        List<PolylineOptions> polylines = new ArrayList<>();
        for (BikewayModel way : ways
        ) {
            polylines.add(ModelConverter.convertToPolyline(way));
        }
        mapView.updateBikeways(polylines);
    }

    public void bikeShopsLoaded(List<BikeShopModel> shops) {
        List<MarkerOptions> markers = new ArrayList<>();
        for (BikeShopModel shop : shops
        ) {
            markers.add(ModelConverter.convertBikeShopToMarker(shop));
        }
        mapView.updateBikeShops(markers);
    }

    public void bikeParkingsLoaded(List<BikeParkingModel> bikeParkings) {
        List<MarkerOptions> markers = new ArrayList<>();
        for (BikeParkingModel bikeParking : bikeParkings
        ) {
            markers.add(ModelConverter.convertBikeParkingToMarker(bikeParking));
        }
        mapView.updateBikeParkings(markers);
    }


    public void bikeShopDescriptionLoaded(BikeShopModel bikeShop) {
        mapView.updateDescriptionView(bikeShop);
    }

    public void bikeParkingDescriptionLoaded(BikeParkingModel bikeParking) {
        mapView.updateDescriptionView(bikeParking);
    }
}
