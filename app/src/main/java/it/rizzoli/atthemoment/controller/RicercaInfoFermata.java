package it.rizzoli.atthemoment.controller;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import it.rizzoli.atthemoment.API.ApiFermata;
import it.rizzoli.atthemoment.model.LineInfo;
import it.rizzoli.atthemoment.model.principali.Fermata;
import it.rizzoli.atthemoment.service.CallAtm;

public class RicercaInfoFermata {
    private final ExecutorService executorService;

    public RicercaInfoFermata() {
        this.executorService = Executors.newSingleThreadExecutor();
    }
    public void infoFermata(String input, Consumer<List<ApiFermata>> callback) {
        executorService.execute(() -> {
            try {
                Log.d("RicercaInfoMezzo", "Input ricevuto: " + input);

                Fermata fermata = CallAtm.fixGzipResponse(CallAtm.infoFermata(input), Fermata.class);
                List<ApiFermata> apiFermata = new ArrayList<>();
                for (LineInfo lineinfo :fermata.getLines()){
                    String waitingMessage= lineinfo.getWaitMessage();
                    String bookInfo= lineinfo.getBookletUrl2();
                    apiFermata.add(new ApiFermata(fermata.getDescription(),bookInfo,waitingMessage,fermata.getLocation().getX(),fermata.getLocation().getY()));
                }
                callback.accept(apiFermata);

            } catch (IOException e) {
                Log.e("RicercaInfoFermata", "Errore nel recuperare le informazioni della fermata.", e);
                callback.accept(new ArrayList<>());
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        Log.d("RicercaInfoFermata", "ExecutorService terminato.");
    }
}
