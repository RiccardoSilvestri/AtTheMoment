package it.rizzoli.atthemoment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atthemoment.R;
import it.rizzoli.atthemoment.API.ApiFermata;
import it.rizzoli.atthemoment.controller.RicercaInfoFermata;

public class StopActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private TextView bookInfoTextView;
    private TextView waitingMessageTextView;
    private Handler handler = new Handler();
    private Runnable updateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        Intent intent = getIntent();
        String input = intent.getStringExtra("Fermata");

        updateTask = new Runnable() {
            @Override
            public void run() {
                infoFermata(input);
                handler.postDelayed(this, 10000);
                Log.d("StopActivity", "Periodic task executed");
            }
        };
        handler.post(updateTask);

        if (savedInstanceState == null) {
            FragStop fragStop = new FragStop();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_stop_view, fragStop)
                    .commit();
        }
    }

    private void infoFermata(String input) {
        RicercaInfoFermata ricercaInfoFermata = new RicercaInfoFermata();

        ricercaInfoFermata.infoFermata(input, infofermataList -> {
            runOnUiThread(() -> {
                Bundle bundle = new Bundle();
                if (infofermataList != null && !infofermataList.isEmpty()) {
                    ApiFermata apiFermata = infofermataList.get(0);


                    bundle.putString("descriptionTextView", apiFermata.getDescription());
                    bundle.putString("bookInfoTextView", apiFermata.getBookInfo());
                    bundle.putString("waitingMessageTextView", apiFermata.getWaitingMessage());

                } else {
                   descriptionTextView.setText("Informazioni non trovate");
                    bookInfoTextView.setText("Nessun dato disponibile");
                    waitingMessageTextView.setText("Attendi...");

                    bundle.putString("descriptionTextView", "Informazioni non trovate");
                    bundle.putString("bookInfoTextView", "Nessun dato disponibile");
                    bundle.putString("waitingMessageTextView", "Attendi...");

                }
                FragStop fragStop = new FragStop();
                fragStop.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_stop_view, fragStop)
                        .commit();
            });
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTask);
    }
}
