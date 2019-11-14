package com.malex.velo72.custom.views.BottomSliderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malex.velo72.R;
import com.malex.velo72.models.BikeShopModel;

public class BikeShopObject implements BottomSliderViewObject {

    private BikeShopModel bikeShop;
    private Context context;
    private LayoutInflater layoutInflater;

    public BikeShopObject(Context context, BikeShopModel bikeShop) {
        this.context = context;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bikeShop = bikeShop;
    }

    @Override
    public View getShortDescriptionView() {
        View bikeShopLayout = layoutInflater.inflate(R.layout.short_description_view, null);
        TextView tv1 = bikeShopLayout.findViewById(R.id.locationName);
        TextView tv2 = bikeShopLayout.findViewById(R.id.text2);
        tv1.setText(bikeShop.getName());
        tv2.setText(String.valueOf(bikeShop.getDescription()));
        return bikeShopLayout;
    }

    @Override
    public View getLongDescriptionView() {
        View bikeShopLayout = layoutInflater.inflate(R.layout.short_description_view, null);
        TextView tv1 = bikeShopLayout.findViewById(R.id.locationName);
        TextView tv2 = bikeShopLayout.findViewById(R.id.text2);
        tv1.setText(bikeShop.getName());
        tv2.setText(String.valueOf(bikeShop.getDescription()));
        return bikeShopLayout;
    }

    @Override
    public int getViewHeight() {
        return 0;
    }
}
