package com.example.atthemoment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.atthemoment.controller.RicercaMezzi;
import com.example.atthemoment.API.ApiListaMezzi;

import java.util.ArrayList;
import java.util.List;

public class LinesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lines);

        Intent intent = getIntent();
        int bottone = intent.getIntExtra("Bottone", -1);
        Button buttonGoToMain = findViewById(R.id.button_go_to_main);
        ListView listView = findViewById(R.id.listaMezzi);

        RicercaMezzi ricercaMezzi = new RicercaMezzi();

        ricercaMezzi.listaMezzi(bottone, mezziList -> {
            runOnUiThread(() -> {
                ArrayList<String> mezziArray = new ArrayList<>();
                for (ApiListaMezzi mezzo : mezziList) {
                    mezziArray.add(mezzo.getLineDescription());
                }

                ArrayAdapter<String> listaMezziAdapter = new ArrayAdapter<>(
                        LinesActivity.this,
                        android.R.layout.simple_expandable_list_item_1,
                        mezziArray
                );
                listView.setAdapter(listaMezziAdapter);
            });
        });

        buttonGoToMain.setOnClickListener(v -> {
            Intent mainIntent = new Intent(LinesActivity.this, MainActivity.class);
            startActivity(mainIntent);
        });
    }
}
