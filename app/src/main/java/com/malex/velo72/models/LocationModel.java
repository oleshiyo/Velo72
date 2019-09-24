package com.malex.velo72.models;

import com.google.android.gms.maps.model.LatLng;

public class LocationModel {

    private LatLng coordinates;

    public LocationModel(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}
