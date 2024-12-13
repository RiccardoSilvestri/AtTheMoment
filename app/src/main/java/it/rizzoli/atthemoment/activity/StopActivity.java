package it.rizzoli.atthemoment.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.atthemoment.R;
import it.rizzoli.atthemoment.API.ApiFermata;
import it.rizzoli.atthemoment.controller.RicercaInfoFermata;

import android.os.Handler;

public class StopActivity extends Activity {

    private TextView descriptionTextView;
    private TextView bookInfoTextView;
    private TextView waitingMessageTextView;
    private Handler handler = new Handler();
    private Runnable updateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        descriptionTextView = findViewById(R.id.description);
        bookInfoTextView = findViewById(R.id.bookinfo);
        waitingMessageTextView = findViewById(R.id.waitingmessage);

        Intent intent = getIntent();
        String input = intent.getStringExtra("Fermata");

        updateTask = new Runnable() {
            @Override
            public void run() {
                infoFermata(input);
                handler.postDelayed(this, 10000);
                Log.d("StopActivity", "Task periodico eseguito");
            }
        };
        handler.post(updateTask);
    }

    private void infoFermata(String input) {
        RicercaInfoFermata ricercaInfoFermata = new RicercaInfoFermata();

        ricercaInfoFermata.infoFermata(input, infofermataList -> {
            runOnUiThread(() -> {
                if (infofermataList != null && !infofermataList.isEmpty()) {
                    ApiFermata apiFermata = infofermataList.get(0);

                    descriptionTextView.setText(apiFermata.getDescription());
                    bookInfoTextView.setText(apiFermata.getBookInfo());
                    waitingMessageTextView.setText(apiFermata.getWaitingMessage());
                } else {
                    descriptionTextView.setText("Informazioni non trovate");
                    bookInfoTextView.setText("Nessun dato disponibile");
                    waitingMessageTextView.setText("Attendi...");
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTask);
    }
}
