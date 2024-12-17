package it.rizzoli.atthemoment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.atthemoment.R;

import it.rizzoli.atthemoment.controller.RicercaAroundMe;
import it.rizzoli.atthemoment.controller.RicercaMezzi;
import it.rizzoli.atthemoment.API.ApiListaMezzi;
import android.text.TextUtils;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LinesActivityAroundMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);


        Intent intent = getIntent();
        double x = intent.getDoubleExtra("latitude", -1);
        double y = intent.getDoubleExtra("longitude", -1);




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

        RicercaAroundMe ricercaAroundMe = new RicercaAroundMe();
        ricercaAroundMe.listaMezziAroundMe(x,y, mezziList -> {
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

                    System.out.println("Clicked item code: " + selectedCode);

                    Intent intent2 = new Intent(LinesActivityAroundMe.this, InfoLineActivity.class);
                    intent2.putExtra("Mezzo", selectedCode);
                    startActivity(intent2);
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
    }
}
