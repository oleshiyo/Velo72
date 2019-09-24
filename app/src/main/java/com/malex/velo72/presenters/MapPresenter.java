package com.malex.velo72.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
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

    public void loadBikeways(){
        bsProvider.getAllBikeways();
    }

    public void loadBikeShops(){
        bsProvider.getAllBikeShops();
    }

    public void bikewaysLoaded(List<BikewayModel> ways){
        List<PolylineOptions> polylines = new ArrayList<>();
        for (BikewayModel way: ways
        ) {
            polylines.add(convertToPolylineOptions(way));
        }
        mapView.updateBikeways(polylines);
    }

    public void bikeShopsLoaded(List<BikeShopModel> shops){
        List<MarkerOptions> markers = new ArrayList<>();
        for (BikeShopModel shop: shops
        ) {
            markers.add(convertToMarkerOptions(shop));
        }
        mapView.updateBikeShops(markers);
    }

    private PolylineOptions convertToPolylineOptions(BikewayModel way)
    {
        List<LatLng> points = way.getCoordinates();
        return new PolylineOptions().addAll(points);
    }

    private MarkerOptions convertToMarkerOptions(BikeShopModel shop)
    {
        MarkerOptions marker = new MarkerOptions();
        marker.position(shop.getCoordinates());
        marker.title(shop.getName());
        marker.snippet(shop.getDescription());
        return marker;
    }
}
