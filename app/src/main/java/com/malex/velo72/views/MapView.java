package com.malex.velo72.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.malex.velo72.models.BikeParkingModel;
import com.malex.velo72.models.BikeShopModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MapView extends MvpView {

    void updateBikeways(List<PolylineOptions> polylines);

    void updateBikeShops(List<MarkerOptions> markers);

    void updateBikeParkings(List<MarkerOptions> markers);

    void updateDescriptionView(BikeShopModel description);

    void updateDescriptionView(BikeParkingModel description);

    //void showBikeHirePlaces(List<BikeShopModel> bikeHirePlaces);

}
