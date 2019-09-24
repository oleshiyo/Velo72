package com.malex.velo72.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MapView extends MvpView {

    void updateBikeways(List<PolylineOptions> polylines);
    void updateBikeShops(List<MarkerOptions> markers);
}
