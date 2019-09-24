package com.malex.velo72.models;

import com.google.android.gms.maps.model.LatLng;

public class BikeShopModel extends LocationModel {

    private String name;
    private String description;
    private String phone;
    private String url;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }

    public BikeShopModel(LatLng coordinates, String name, String description, String phone, String url) {
        super(coordinates);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.url = url;
    }
}
