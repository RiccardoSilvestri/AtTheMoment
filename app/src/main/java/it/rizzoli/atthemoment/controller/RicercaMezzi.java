package it.rizzoli.atthemoment.controller;
import android.util.Log;
import it.rizzoli.atthemoment.API.ApiListaMezzi;
import it.rizzoli.atthemoment.model.JourneyPatterns;
import it.rizzoli.atthemoment.model.ListaMezzi;
import it.rizzoli.atthemoment.service.CallAtm;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class RicercaMezzi {

    private final ExecutorService executorService;

    public RicercaMezzi() {
        this.executorService = Executors.newSingleThreadExecutor();
    }


    public void listaMezzi(int input, Consumer<List<ApiListaMezzi>> callback) {
        executorService.execute(() -> {
            try {
                Log.d("RicercaMezzi", "Input ricevuto: " + input);
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
        Log.d("RicercaMezzi", "ExecutorService terminato.");
    }
}
