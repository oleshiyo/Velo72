package com.malex.velo72.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.malex.velo72.R;

public class BikeParkingFragment  extends Fragment {

    View view;
    public BikeParkingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getView() != null)
            view = getView();
        else
            view = inflater.inflate(R.layout.bikeparking_fragment, container, false);
        return view;
    }
}