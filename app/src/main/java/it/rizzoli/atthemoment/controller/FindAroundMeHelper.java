package it.rizzoli.atthemoment.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class FindAroundMeHelper implements LocationListener {

    private final Context context;
    private final LocationManager locationManager;
    private LocationCallback callback;

    public FindAroundMeHelper(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public interface LocationCallback {
        void onLocationRetrieved(double latitude, double longitude);
        void onError(String error);
    }

    public void startLocationUpdates(LocationCallback callback) {
        this.callback = callback;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            callback.onError("Location permission not granted");
            return;
        }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 5, this);
        }


    public void stopLocationUpdates() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (callback != null) {
            callback.onLocationRetrieved(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {}

    @Override
    public void onProviderDisabled(@NonNull String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, android.os.Bundle extras) {}
}
