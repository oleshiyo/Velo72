package com.malex.velo72.providers;

import com.google.android.gms.maps.model.LatLng;
import com.malex.velo72.R;
import com.malex.velo72.models.BikeParkingModel;
import com.malex.velo72.models.BikeShopModel;
import com.malex.velo72.models.BikewayModel;
import com.malex.velo72.models.LocationModel;
import com.malex.velo72.models.PathPointModel;
import com.malex.velo72.presenters.MapPresenter;

import java.util.ArrayList;
import java.util.List;

public class BikeStuffProvider {

    private MapPresenter mapPresenter;
    private List<BikeParkingModel> bikeParkings;
    private List<BikeShopModel> bikeShops;
    private List<BikewayModel> bikeWays;

    public BikeStuffProvider(MapPresenter mapPresenter) {
        this.mapPresenter = mapPresenter;
    }

    public void getAllBikeways() {
        getBikewaysTest();
    }

    public void getAllBikeShops() {
        getBikeShopsTest();
    }

    public void getAllBikeParkings() {
        getBikeParkingsTest();
    }

    public void loadEntityDescription(int entityType, long id) {
        switch(entityType){
            case R.string.bikeparking_entity:
                BikeParkingModel bikeParking = getModelById(BikeParkingModel.class, id);
                mapPresenter.bikeParkingDescriptionLoaded(bikeParking);
                break;

            case R.string.bikeshop_entity:
                BikeShopModel bikeShop = getModelById(BikeShopModel.class, id);
                mapPresenter.bikeShopDescriptionLoaded(bikeShop);
                break;

            default:
                break;
        }
    }

    private <T extends LocationModel> T getModelById(Class<T> type, long id) {

        if (type.getName().equals(BikeParkingModel.class.getName()))
        {
            for (BikeParkingModel bikeParking:bikeParkings
                 ) {
                if (bikeParking.getId() == id) return (T)bikeParking;
            }
        }
        if (type.getName().equals(BikeShopModel.class.getName()))
        {
            for (BikeShopModel bikeShop: bikeShops
            ) {
                if (bikeShop.getId() == id) return (T)bikeShop;
            }
        }
        return null;
    }

    private void getBikewaysTest() {
        bikeWays = new ArrayList<>();
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

        bikeWays.add(way1);

        mapPresenter.bikewaysLoaded(bikeWays);
    }

    private void getBikeShopsTest() {
        bikeShops = new ArrayList<>();

        bikeShops.add(new BikeShopModel(1, new LatLng(30, 25), "FreeRide", "Best bike shop", "8-800-555-35-35", "freeride.ru"));
        bikeShops.add(new BikeShopModel(2, new LatLng(20, 25), "Спортмастер", "Ашанбайки и прочее", "8-800-535-35-35", "freeride.ru"));

        mapPresenter.bikeShopsLoaded(bikeShops);
    }

    private void getBikeParkingsTest() {
        bikeParkings = new ArrayList<>();

        bikeParkings.add(new BikeParkingModel(1, new LatLng(35, 25), "Парковка возле ТЦ Кристалл", 10));
        bikeParkings.add(new BikeParkingModel(2, new LatLng(25, 25), "Парковка возле больницы", 5));

        mapPresenter.bikeParkingsLoaded(bikeParkings);
    }
}
