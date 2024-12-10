package com.example.atthemoment;

import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.atthemoment.controller.RicercaMezzi;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_go_to_second);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout buttonGoTram = findViewById(R.id.button_go_tram);
        buttonGoTram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaMezzi ricercaMezzi = new RicercaMezzi();
                ricercaMezzi.listaMezzi(parseInt(buttonGoTram.getTag().toString()));
                Log.d("ciao",ricercaMezzi.toString());
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout buttonGoMeto = findViewById(R.id.button_go_metro);
        buttonGoMeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaMezzi ricercaMezzi = new RicercaMezzi();
                ricercaMezzi.listaMezzi(parseInt(buttonGoMeto.getTag().toString()));
                Log.d("ciao",ricercaMezzi.toString());
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout buttonGoAutobus = findViewById(R.id.button_go_autobus);
        buttonGoAutobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaMezzi ricercaMezzi = new RicercaMezzi();
                ricercaMezzi.listaMezzi(parseInt(buttonGoAutobus.getTag().toString()));
                Log.d("ciao",ricercaMezzi.toString());
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout buttonGoTreno = findViewById(R.id.button_go_treno);
        buttonGoTreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RicercaMezzi ricercaMezzi = new RicercaMezzi();
                ricercaMezzi.listaMezzi(parseInt(buttonGoTreno.getTag().toString()));
                Log.d("ciao",ricercaMezzi.toString());
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
}
