package it.rizzoli.atthemoment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.atthemoment.R;
import it.rizzoli.atthemoment.controller.RicercaMezzi;
import it.rizzoli.atthemoment.API.ApiListaMezzi;

import java.util.ArrayList;

public class LinesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);

        Intent intent = getIntent();
        int bottone = intent.getIntExtra("Bottone", -1);
        Button buttonGoToMain = findViewById(R.id.button_go_to_main);
        ListView listView = findViewById(R.id.listaMezzi);

        RicercaMezzi ricercaMezzi = new RicercaMezzi();

        ricercaMezzi.listaMezzi(bottone, mezziList -> {
            runOnUiThread(() -> {
                ArrayList<String> mezziArray = new ArrayList<>();
                for (ApiListaMezzi mezzo : mezziList) {
                    mezziArray.add(mezzo.getCode());
                }

                ArrayAdapter<String> listaMezziAdapter = new ArrayAdapter<>(
                        LinesActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        mezziArray
                );
                listView.setAdapter(listaMezziAdapter);
                listView.setOnItemClickListener((adapterView, view, position, id) -> {
                    System.out.println(view.getId()); //non funziona corretamente TODO: rivedere la logica
                });
            });

            buttonGoToMain.setOnClickListener(v -> {
                Intent mainIntent = new Intent(LinesActivity.this, MainActivity.class);
                startActivity(mainIntent);
            });
        });
    }
}