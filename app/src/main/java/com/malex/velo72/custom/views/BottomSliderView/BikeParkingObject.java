package com.malex.velo72.custom.views.BottomSliderView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malex.velo72.R;
import com.malex.velo72.models.BikeParkingModel;

public class BikeParkingObject implements BottomSliderViewObject {

    private BikeParkingModel bikeParking;
    private Context context;
    private LayoutInflater layoutInflater;


    public BikeParkingObject(Context context, BikeParkingModel bikeParking) {
        this.context = context;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bikeParking = bikeParking;
    }

    @Override
    public View getView() {

        View bikeParkingLayout = layoutInflater.inflate(R.layout.short_description_view, null);
        TextView tv1 = bikeParkingLayout.findViewById(R.id.locationName);
        TextView tv2 = bikeParkingLayout.findViewById(R.id.text2);
        tv1.setText(bikeParking.getName());
        tv2.setText(String.valueOf(bikeParking.getSpacesNumber()));
        return bikeParkingLayout;
    }
}
