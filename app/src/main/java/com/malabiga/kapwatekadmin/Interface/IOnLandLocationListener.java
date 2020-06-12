package com.malabiga.kapwatekadmin.Interface;

import com.malabiga.kapwatekadmin.Model.MyLatLng;

import java.util.List;

public interface IOnLandLocationListener {

    void onLandLocationSuccess(List<MyLatLng> latLngs);
    void onLandLocationFailed(String message);
}
