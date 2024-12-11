package it.rizzoli.atthemoment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.atthemoment.R;

import java.util.ArrayList;

import it.rizzoli.atthemoment.controller.RicercaInfoMezzo;
import it.rizzoli.atthemoment.API.ApiMezzo;
import it.rizzoli.atthemoment.model.Stop;

public class InfoLineActivity extends Activity {
    RadioGroup andataRitornoRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        andataRitornoRadioGroup = findViewById(R.id.andataRitornoRadioGroup);
        listaFermate();
        andataRitornoRadioGroup.setOnCheckedChangeListener((x,y)-> listaFermate());
        //FA CAGARE MA FUNZIONA
    }
    private void listaFermate(){
        int andataRitornoSwitch = 0;
        int selectedId = andataRitornoRadioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.radioAndata) {
            andataRitornoSwitch = 0;
        } else if (selectedId == R.id.radioRitorno) {
            andataRitornoSwitch = 1;
        }

        Intent intent = getIntent();
        String idMezzo = intent.getStringExtra("Mezzo");

        ListView listViewStops = findViewById(R.id.lista);

        RicercaInfoMezzo ricercaInfoMezzo = new RicercaInfoMezzo();

        ricercaInfoMezzo.infoMezzo(idMezzo,andataRitornoSwitch, infoMezzoList -> {
            runOnUiThread(() -> {
                ArrayList<String> fermateArray = new ArrayList<>();
                ArrayList<String> fermateArrayCodice = new ArrayList<>();

                ApiMezzo apiMezzo = infoMezzoList.get(0);

                for (Stop stop : apiMezzo.getStops()) {
                    fermateArray.add(stop.getDescription());
                    fermateArrayCodice.add(stop.getCode());
                }

                ArrayAdapter<String> fermateAdapter = new ArrayAdapter<>(
                        InfoLineActivity.this,
                        android.R.layout.simple_list_item_1,
                        fermateArray
                );

                listViewStops.setAdapter(fermateAdapter);
                listViewStops.setOnItemClickListener((adapterView, view, position, id) -> {
                    String clickedItem = fermateArrayCodice.get(position);
                    System.out.println("Clicked item: " + clickedItem);

                    Intent intent2 = new Intent(InfoLineActivity.this, StopActivity.class);
                    intent2.putExtra("Fermata", (clickedItem));
                    startActivity(intent2);
                });
            });
        });

        Log.d("InfoLineActivity", "Mezzo selezionato: " + idMezzo);
    }
}
