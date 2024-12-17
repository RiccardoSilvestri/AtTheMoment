package it.rizzoli.atthemoment.controller;

import android.util.Log;
import it.rizzoli.atthemoment.API.ApiMezzo;
import it.rizzoli.atthemoment.model.Stop;
import it.rizzoli.atthemoment.model.principali.Mezzo;
import it.rizzoli.atthemoment.service.CallAtm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import android.location.Location;

public class RicercaInfoMezzo {

    private final ExecutorService executorService;

    public RicercaInfoMezzo() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void infoMezzo(String input, int direzione, Consumer<List<ApiMezzo>> callback) {
        executorService.execute(() -> {
            try {
                Log.d("RicercaInfoMezzo", "Input ricevuto: " + input);

                Mezzo mezzo = CallAtm.fixGzipResponse(CallAtm.infoMezzo(input, direzione), Mezzo.class);
                if (mezzo == null || mezzo.getStops() == null) {
                    Log.e("RicercaInfoMezzo", "Risposta vuota o malformata.");
                    callback.accept(new ArrayList<>());
                    return;
                }

                List<Stop> stops = mezzo.getStops();
                ApiMezzo apiMezzo = new ApiMezzo(mezzo.getCode(), mezzo.getLine().getLineDescription(), mezzo.getLine().getTransportMode(), stops);

                List<ApiMezzo> apiMezzi = new ArrayList<>();
                apiMezzi.add(apiMezzo);

                callback.accept(apiMezzi);

            } catch (IOException e) {
                Log.e("RicercaInfoMezzo", "Errore nel recuperare le informazioni del mezzo.", e);
                callback.accept(new ArrayList<>());
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        Log.d("RicercaInfoMezzo", "ExecutorService terminato.");
    }
}
