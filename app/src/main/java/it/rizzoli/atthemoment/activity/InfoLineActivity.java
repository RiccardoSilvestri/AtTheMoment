package it.rizzoli.atthemoment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atthemoment.R;

import java.util.ArrayList;

import it.rizzoli.atthemoment.controller.RicercaInfoMezzo;
import it.rizzoli.atthemoment.API.ApiMezzo;
import it.rizzoli.atthemoment.model.Stop;

public class InfoLineActivity extends AppCompatActivity {
    RadioGroup andataRitornoRadioGroup;
    SearchView searchView;
    ArrayAdapter<String> fermateAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        progressBar = findViewById(R.id.progressBar);

        andataRitornoRadioGroup = findViewById(R.id.andataRitornoRadioGroup);
        searchView = findViewById(R.id.searchView);
        listaFermate();
        andataRitornoRadioGroup.setOnCheckedChangeListener((x, y) -> listaFermate());
        Button buttonGoBack = findViewById(R.id.button_go_back);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_header, new FragHeader())
                    .commit();
        }
        buttonGoBack.setOnClickListener(v -> finish());
    }

    private void listaFermate() {
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
        tentaCaricamentoFermate(listViewStops, idMezzo, andataRitornoSwitch);
    }

    private void tentaCaricamentoFermate(ListView listViewStops, String idMezzo, int andataRitornoSwitch) {
        RicercaInfoMezzo ricercaInfoMezzo = new RicercaInfoMezzo();
        progressBar.setVisibility(View.VISIBLE);

        ricercaInfoMezzo.infoMezzo(idMezzo, andataRitornoSwitch, infoMezzoList -> {
            runOnUiThread(() -> {
                if (infoMezzoList == null || infoMezzoList.isEmpty()) {
                    Log.e("InfoLineActivity", "Lista fermate vuota o null. Riprovo...");
                    listViewStops.postDelayed(() -> tentaCaricamentoFermate(listViewStops, idMezzo, andataRitornoSwitch), 2000);
                    return;
                }

                ArrayList<String> fermateArray = new ArrayList<>();
                ArrayList<String> fermateArrayCodice = new ArrayList<>();

                ApiMezzo apiMezzo = infoMezzoList.get(0);

                for (Stop stop : apiMezzo.getStops()) {
                    fermateArray.add(stop.getDescription());
                    fermateArrayCodice.add(stop.getCode());
                }

                fermateAdapter = new ArrayAdapter<>(
                        InfoLineActivity.this,
                        android.R.layout.simple_list_item_1,
                        fermateArray
                );
                progressBar.setVisibility(View.GONE);

                listViewStops.setAdapter(fermateAdapter);
                listViewStops.setOnItemClickListener((adapterView, view, position, id) -> {
                    String visibleName = fermateAdapter.getItem(position);
                    int originalIndex = fermateArray.indexOf(visibleName);
                    String selectedCode = fermateArrayCodice.get(originalIndex);

                    System.out.println("Clicked item: " + selectedCode);

                    Intent intent2 = new Intent(InfoLineActivity.this, StopActivity.class);
                    intent2.putExtra("Fermata", selectedCode);
                    intent2.putExtra("IdMezzo",idMezzo);
                    startActivity(intent2);
                });

                Log.i("InfoLineActivity", "Fermate caricate con successo.");
            });


            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (fermateAdapter != null) {
                        fermateAdapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        });
    }
}