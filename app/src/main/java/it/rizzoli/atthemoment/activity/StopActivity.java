package it.rizzoli.atthemoment.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atthemoment.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import it.rizzoli.atthemoment.API.ApiFermata;
import it.rizzoli.atthemoment.controller.RicercaInfoFermata;

public class StopActivity extends AppCompatActivity {

    private MapView map;
    private TextView descriptionTextView;
    private TextView bookInfoTextView;
    private TextView waitingMessageTextView;
    private Handler handler = new Handler();
    private Runnable updateTask;
    boolean mapUpdated = false;
    String idMezzo="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        Intent intent = getIntent();
        String input = intent.getStringExtra("Fermata");
        idMezzo = intent.getStringExtra("IdMezzo");



        updateTask = new Runnable() {
            @Override
            public void run() {
                infoFermata(input);
                handler.postDelayed(this, 10000);
                Log.d("StopActivity", "Periodic task executed");
            }
        };
        handler.post(updateTask);

        if (savedInstanceState == null) {
            FragStop fragStop = new FragStop();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_stop_view, fragStop)
                    .commit();
        }
        mappa(43,12,"","");
    }
    private void infoFermata(String input) {
        RicercaInfoFermata ricercaInfoFermata = new RicercaInfoFermata();
        ricercaInfoFermata.infoFermata(input, infofermataList -> {
            runOnUiThread(() -> {
                Bundle bundle = new Bundle();
                ApiFermata apiFermata = null;

                if (infofermataList != null && !infofermataList.isEmpty()) {
                    for (int j = 0; j < infofermataList.size(); j++){
                        if(infofermataList.get(j).getBookInfo().equals(idMezzo)){
                            apiFermata = infofermataList.get(j);
                        }
                    }

                    if (apiFermata != null) {
                        if (!mapUpdated) {
                            mappa(apiFermata.getX(), apiFermata.getY(), apiFermata.getBookInfo(), apiFermata.getDescription());
                            mapUpdated = true;
                        }

                        bundle.putString("descriptionTextView", apiFermata.getDescription());
                        bundle.putString("bookInfoTextView", apiFermata.getBookInfo());
                        bundle.putString("waitingMessageTextView", apiFermata.getWaitingMessage());
                    } else {
                        descriptionTextView.setText("Informazioni non trovate");
                        bookInfoTextView.setText("Nessun dato disponibile");
                        waitingMessageTextView.setText("Attendi...");
                    }
                } else {
                    descriptionTextView.setText("Informazioni non trovate");
                    bookInfoTextView.setText("Nessun dato disponibile");
                    waitingMessageTextView.setText("Attendi...");

                    bundle.putString("descriptionTextView", "Informazioni non trovate");
                    bundle.putString("bookInfoTextView", "Nessun dato disponibile");
                    bundle.putString("waitingMessageTextView", "Attendi...");
                }

                FragStop fragStop = new FragStop();
                fragStop.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_stop_view, fragStop)
                        .commit();
            });
        });
    }



    private void mappa(double x,double y,String numero,String description) {

        Configuration.getInstance().load(getApplicationContext(), getPreferences(MODE_PRIVATE));
        map = findViewById(R.id.mapView);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(19);
        GeoPoint milanCenter = new GeoPoint(x, y);
        mapController.setCenter(milanCenter);
        addMarker(x, y,numero, description);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTask);
    }
}
