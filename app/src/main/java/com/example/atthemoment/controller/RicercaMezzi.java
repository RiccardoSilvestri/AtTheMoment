package com.example.atthemoment.controller;

import android.util.Log;

import com.example.atthemoment.API.ApiListaMezzi;
import com.example.atthemoment.model.JourneyPatterns;
import com.example.atthemoment.model.ListaMezzi;
import com.example.atthemoment.service.CallAtm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RicercaMezzi {

    public RicercaMezzi() {
    }

    public List<ApiListaMezzi> listaMezzi(int input) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Future<List<ApiListaMezzi>> future = executorService.submit(() -> {
                String printata = String.valueOf(input);
                Log.d("l'input è", printata);
                try {
                    JourneyPatterns journeyPatterns = CallAtm.fixGzipResponse(
                            CallAtm.listaMezzi(),
                            JourneyPatterns.class
                    );

                    List<ListaMezzi> listaMezzi = journeyPatterns.getJourneyPatterns();
                    List<ApiListaMezzi> apiListaMezzi = new ArrayList<>();
                    for (ListaMezzi mezzo : listaMezzi) {
                        String code = mezzo.getCode();
                        int tipologia = mezzo.getLine().getTransportMode();
                        String lineDescription = mezzo.getLine().getLineDescription();
                        String direction = mezzo.getDirection();
                        if (tipologia == input) {
                            apiListaMezzi.add(new ApiListaMezzi(code, direction, lineDescription, tipologia));
                        }
                    }
                    return apiListaMezzi;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Errore nel recuperare la lista dei mezzi.", e);
                }
            });

            List<ApiListaMezzi> result = future.get();
            // Log each item in the list for better debugging
            for (ApiListaMezzi mezzo : result) {
                Log.d("Mezzo Dettaglio", mezzo.toString());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Errore nel recuperare la lista dei mezzi.", e);
        } finally {
            executorService.shutdown();
        }
    }
}
