package it.rizzoli.atthemoment.controller;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import it.rizzoli.atthemoment.API.ApiFermata;
import it.rizzoli.atthemoment.API.ApiListaMezzi;
import it.rizzoli.atthemoment.model.JourneyPatterns;
import it.rizzoli.atthemoment.model.LineInfo;
import it.rizzoli.atthemoment.model.principali.Fermata;
import it.rizzoli.atthemoment.model.principali.ListaMezzi;
import it.rizzoli.atthemoment.service.CallAtm;

public class RicercaAroundMe {
    private final ExecutorService executorService;

    public RicercaAroundMe() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void listaMezziAroundMe(double y, double x, Consumer<List<ApiListaMezzi>> callback) {
        executorService.execute(() -> {
            try {
                JourneyPatterns journeyPatterns = CallAtm.fixGzipResponse(CallAtm.infoAroundMe(y,x),JourneyPatterns.class);
                if (journeyPatterns == null || journeyPatterns.getJourneyPatterns() == null) {
                    Log.e("RicercaMezzi", "Risposta vuota o malformata.");
                    callback.accept(new ArrayList<>());
                    return;
                }

                List<ListaMezzi> listaMezzi = journeyPatterns.getJourneyPatterns();
                List<ApiListaMezzi> apiListaMezzi = new ArrayList<>();
                for (ListaMezzi mezzo : listaMezzi) {
                    String code = mezzo.getCode();
                    int tipologia = mezzo.getLine().getTransportMode();
                    String lineDescription = mezzo.getLine().getLineDescription();
                    String direction = mezzo.getDirection();
                    apiListaMezzi.add(new ApiListaMezzi(code, direction, lineDescription, tipologia));
                }

                for (ApiListaMezzi mezzo : apiListaMezzi) {
                    Log.d("RicercaMezzi", "Mezzo trovato: " + mezzo);
                }
                callback.accept(apiListaMezzi);

            } catch (Exception e) {
                Log.e("RicercaMezzi", "Errore nel recuperare la lista dei mezzi.", e);
                callback.accept(new ArrayList<>());
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        Log.d("RicercaInfoFermata", "ExecutorService terminato.");
    }
}