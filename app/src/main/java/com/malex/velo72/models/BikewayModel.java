package com.malex.velo72.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class BikewayModel {
    List<PathPointModel> pathPoints;

    public List<PathPointModel> getPathPoints() {
        return pathPoints;
    }

    public BikewayModel(List<PathPointModel> pathPoints) {
        this.pathPoints = pathPoints;
    }

    public List<LatLng> getCoordinates()
    {
        List<LatLng> points = new ArrayList<>();
        for (PathPointModel point: pathPoints
             ) {
            points.add(point.getCoordinates());
        }
        return points;
    }
}
