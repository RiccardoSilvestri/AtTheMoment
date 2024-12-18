package it.rizzoli.atthemoment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.atthemoment.R;
import it.rizzoli.atthemoment.controller.RicercaMezzi;
import it.rizzoli.atthemoment.API.ApiListaMezzi;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LinesActivity extends AppCompatActivity {

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);

        progressBar = findViewById(R.id.progressBar);


        Intent intent = getIntent();
        int bottone = intent.getIntExtra("Bottone", -1);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_header, new FragHeader())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_tab_bar, new FragTabBar())
                    .commit();
        }

        Button buttonGoToMain = findViewById(R.id.button_go_to_main);
        ListView listView = findViewById(R.id.lista);
        SearchView searchView = findViewById(R.id.searchView);

        RicercaMezzi ricercaMezzi = new RicercaMezzi();
        progressBar.setVisibility(View.VISIBLE);

        ricercaMezzi.listaMezzi(bottone, mezziList -> {

            runOnUiThread(() -> {

                ArrayList<String> mezziArray = new ArrayList<>();
                ArrayList<String> mezziArrayNomi = new ArrayList<>();

                for (ApiListaMezzi mezzo : mezziList) {
                    if (mezzo.getDirection().equals("0")) {
                        mezziArray.add(mezzo.getCode());
                        mezziArrayNomi.add(mezzo.getLineDescription());
                    }
                }
                progressBar.setVisibility(View.GONE);

                ArrayAdapter<String> listaMezziAdapter = new ArrayAdapter<>(
                        LinesActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        mezziArrayNomi
                );
                listView.setAdapter(listaMezziAdapter);
                listView.setOnItemClickListener((adapterView, view, position, id) -> {
                    String visibleName = listaMezziAdapter.getItem(position);
                    int originalIndex = mezziArrayNomi.indexOf(visibleName);
                    String selectedCode = mezziArray.get(originalIndex);

                    System.out.println("Clicked item code: " + selectedCode);


                    Intent intent2 = new Intent(LinesActivity.this, InfoLineActivity.class);
                    intent2.putExtra("Mezzo", selectedCode);
                    startActivity(intent2);

                });

                buttonGoToMain.setOnClickListener(v -> {
                    Intent mainIntent = new Intent(LinesActivity.this, MainActivity.class);
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
    }
}
