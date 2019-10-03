package com.malex.velo72;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.malex.velo72.models.BikeParkingModel;
import com.malex.velo72.models.BikeShopModel;
import com.malex.velo72.models.BikewayModel;

import java.util.List;

public class ModelConverter {
    public static PolylineOptions convertToPolyline(BikewayModel way) {
        List<LatLng> points = way.getCoordinates();
        return new PolylineOptions().addAll(points);
    }

    public static MarkerOptions convertBikeShopToMarker(BikeShopModel shop) {
        MarkerOptions marker = new MarkerOptions();
        marker.position(shop.getCoordinates());
        marker.title(shop.getName());
        marker.snippet(String.valueOf(shop.getId()));
        return marker;
    }

    public static MarkerOptions convertBikeParkingToMarker(BikeParkingModel bikeParking) {
        MarkerOptions marker = new MarkerOptions();
        marker.position(bikeParking.getCoordinates());
        marker.title(bikeParking.getName());
        marker.snippet(String.valueOf(bikeParking.getId()));
        return marker;
    }
}
