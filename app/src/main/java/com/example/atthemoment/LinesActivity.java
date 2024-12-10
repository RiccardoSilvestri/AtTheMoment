package com.example.atthemoment;

import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.atthemoment.controller.RicercaMezzi;

import java.util.ArrayList;

public class LinesActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lines);

        Intent intent = getIntent();
        int bottone = intent.getIntExtra("bottone", -1);

        RicercaMezzi ricercaMezzi = new RicercaMezzi();
        ricercaMezzi.listaMezzi(bottone);

        Log.d("ciao",ricercaMezzi.toString());

        ArrayList<String> asd = new ArrayList<>();
        asd.add(ricercaMezzi.toString().toString());
        
        ArrayAdapter<String>listaMezzi=new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, asd);
    Button button = findViewById(R.id.button_go_to_main);
    ListView lv = findViewById(R.id.listaMezzi);
    lv.setAdapter(listaMezzi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LinesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
