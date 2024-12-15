package it.rizzoli.atthemoment.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.atthemoment.R;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {
    private MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(),
                getPreferences(MODE_PRIVATE));
        setContentView(R.layout.activity_map);
        map = findViewById(com.example.atthemoment.R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(12.0);

        GeoPoint milanCenter = new GeoPoint(45.4642, 9.1900);
        mapController.setCenter(milanCenter);
        addMarker(45.4642, 9.1900, "Milano", "Centro città");

    }

    public void addMarker(double latitude, double longitude, String title, String snippet) {
        GeoPoint point = new GeoPoint(latitude, longitude);
        Marker marker = new Marker(map);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);
        marker.setSnippet(snippet);

        map.getOverlays().add(marker);
        map.invalidate();
    }

    public void addMarkerWithIcon(double latitude, double longitude, String title,
                                  String snippet, int iconResourceId) {
        GeoPoint point = new GeoPoint(latitude, longitude);
        Marker marker = new Marker(map);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(title);
        marker.setSnippet(snippet);

        Drawable icon = getResources().getDrawable(iconResourceId);
        marker.setIcon(icon);

        map.getOverlays().add(marker);
        map.invalidate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}