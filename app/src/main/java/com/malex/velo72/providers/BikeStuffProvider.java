package com.malex.velo72.providers;

import com.google.android.gms.maps.model.LatLng;
import com.malex.velo72.models.BikeShopModel;
import com.malex.velo72.models.BikewayModel;
import com.malex.velo72.models.PathPointModel;
import com.malex.velo72.presenters.MapPresenter;

import java.util.ArrayList;
import java.util.List;

public class BikeStuffProvider {

    private MapPresenter mapPresenter;

    public BikeStuffProvider(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }

    public void getAllBikeways()
    {
        getBikewaysTest();
    }

    private void getBikewaysTest()
    {
        List<BikewayModel> ways = new ArrayList<>();
        List<PathPointModel> points = new ArrayList<>();

        PathPointModel point1 = new PathPointModel(new LatLng(30.02, 20.05));
        PathPointModel point2 = new PathPointModel(new LatLng(16.02, 10.05));
        PathPointModel point3 = new PathPointModel(new LatLng(5.02, 20.05));
        PathPointModel point4 = new PathPointModel(new LatLng(4.02, 10.05));

        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);

        BikewayModel way1 = new BikewayModel(points);

        ways.add(way1);

        mapPresenter.bikewaysLoaded(ways);
    }

    public void getAllBikeShops() { getBikeShopsTest(); }

    private void getBikeShopsTest()
    {
        List<BikeShopModel> shops = new ArrayList<>();

        shops.add(new BikeShopModel(new LatLng(30, 25), "FreeRide", "Best bike shop", "8-800-555-35-35", "freeride.ru"));
        shops.add(new BikeShopModel(new LatLng(20, 25), "Спортмастер", "Ашанбайки и прочее", "8-800-535-35-35", "freeride.ru"));

        mapPresenter.bikeShopsLoaded(shops);
    }
}
