package com.malex.velo72.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.malex.velo72.R;
import com.malex.velo72.activities.MapActivity;

public class BikeHireFragment extends Fragment {

    View view;
    public BikeHireFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getView() != null)
            view = getView();
        else
            view = inflater.inflate(R.layout.bikehire_fragment, container, false);
        return view;
    }
}

