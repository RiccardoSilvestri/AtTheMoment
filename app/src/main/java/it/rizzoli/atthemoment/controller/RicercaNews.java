package it.rizzoli.atthemoment.controller;

import android.util.Log;
import it.rizzoli.atthemoment.model.principali.News;
import it.rizzoli.atthemoment.service.CallAtm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class RicercaNews {

    private final ExecutorService executorService;

    public RicercaNews() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void listaNews(Consumer<List<News>> callback) {
        executorService.execute(() -> {
            try {
                List<News> newsList = CallAtm.newsFixGzipResponse(CallAtm.news());
                if (newsList == null || newsList.isEmpty()) {
                    Log.e("RicercaNews", "Risposta vuota o malformata.");
                    callback.accept(new ArrayList<>());
                    return;
                }

                for (News news : newsList) {
                    Log.d("RicercaNews", "News trovato: " + news.getTitle());
                }
                callback.accept(newsList);

            } catch (IOException e) {
                Log.e("RicercaNews", "Errore nel recuperare le notizie.", e);
                callback.accept(new ArrayList<>());
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
        Log.d("RicercaNews", "ExecutorService terminato.");
    }
}
