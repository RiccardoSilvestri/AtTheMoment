package it.rizzoli.atthemoment.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.atthemoment.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.rizzoli.atthemoment.API.ApiListaMezzi;
import it.rizzoli.atthemoment.controller.RicercaAroundMe;

public class LinesActivityAroundMe extends AppCompatActivity {

    private ProgressBar progressBar;
    private FindAroundMeHelper findAroundMeHelper;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);

        progressBar = findViewById(R.id.progressBar);
        Button buttonGoToMain = findViewById(R.id.button_go_to_main);
        ListView listView = findViewById(R.id.lista);
        SearchView searchView = findViewById(R.id.searchView);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        findAroundMeHelper = new FindAroundMeHelper(this);
        progressBar.setVisibility(View.VISIBLE);

        findAroundMeHelper.startLocationUpdates(new FindAroundMeHelper.LocationCallback() {
            @Override
            public void onLocationRetrieved(double latitude, double longitude) {
                executorService.execute(() -> {
                    runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        Log.d("LinesActivityAroundMe", "Latitude: " + latitude + ", Longitude: " + longitude);
                    });

                    RicercaAroundMe ricercaAroundMe = new RicercaAroundMe();
                    ricercaAroundMe.listaMezziAroundMe(latitude, longitude, mezziList -> {
                        runOnUiThread(() -> {
                            ArrayList<String> mezziArray = new ArrayList<>();
                            ArrayList<String> mezziArrayNomi = new ArrayList<>();

                            for (ApiListaMezzi mezzo : mezziList) {
                                mezziArray.add(mezzo.getCode());
                                mezziArrayNomi.add(mezzo.getLineDescription());
                            }

                            ArrayAdapter<String> listaMezziAdapter = new ArrayAdapter<>(
                                    LinesActivityAroundMe.this,
                                    android.R.layout.simple_expandable_list_item_1,
                                    mezziArrayNomi
                            );
                            listView.setAdapter(listaMezziAdapter);

                            listView.setOnItemClickListener((adapterView, view, position, id) -> {
                                String visibleName = listaMezziAdapter.getItem(position);
                                int originalIndex = mezziArrayNomi.indexOf(visibleName);
                                String selectedCode = mezziArray.get(originalIndex);

                                Log.d("LinesActivityAroundMe", "Selected item code: " + selectedCode);

                                Intent intent = new Intent(LinesActivityAroundMe.this, InfoLineActivity.class);
                                intent.putExtra("Mezzo", selectedCode);
                                startActivity(intent);
                            });

                            buttonGoToMain.setOnClickListener(v -> {
                                Intent mainIntent = new Intent(LinesActivityAroundMe.this, MainActivity.class);
                                startActivity(mainIntent);
                            });

                            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String query) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    listaMezziAdapter.getFilter().filter(newText);
                                    return true;
                                }
                            });
                        });
                    });
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LinesActivityAroundMe.this, error, Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (findAroundMeHelper != null) {
            findAroundMeHelper.stopLocationUpdates();
        }
        executorService.shutdown();
    }
}
