package com.malex.velo72.models;

import com.google.android.gms.maps.model.LatLng;

public class BikeParkingModel extends LocationModel {

    private long id;
    private String name;
    private int spacesNumber;

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public int getSpacesNumber() {
        return spacesNumber;
    }

    public BikeParkingModel(long id, LatLng coordinates, String name, int spacesNumber) {
        super(coordinates);
        this.id = id;
        this.name = name;
        this.spacesNumber = spacesNumber;
    }

}
