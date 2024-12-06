package com.example.atthemoment;
import android.os.Bundle;
import android.preference.PreferenceManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;


public class MapActivity extends  android.app.Activity {
    private MapView mapView = null;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        //TODO check permissions
        setContentView(R.layout.map_activity);
        mapView = findViewById(R.id.mapView);
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }
}