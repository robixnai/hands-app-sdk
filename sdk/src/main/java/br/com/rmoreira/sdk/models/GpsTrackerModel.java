package br.com.rmoreira.sdk.models;

import android.location.Location;

import br.com.rmoreira.sdk.services.GpsTracker;

/**
 * Created by robsonmoreira on 05/12/17.
 */

public class GpsTrackerModel {

    private GpsTracker mGpsTracker;

    public GpsTrackerModel() {
        mGpsTracker = new GpsTracker();
    }

    public void stopUsingGps() {
        mGpsTracker.stopUsingGps();
    }

    public double getLatitude() {
        return mGpsTracker.getLatitude();
    }

    public double getLongitude() {
        return mGpsTracker.getLongitude();
    }

    public boolean canGetLocation() {
        return mGpsTracker.canGetLocation();
    }

    public Location getLocation() {
        return mGpsTracker.getLocation();
    }

}
