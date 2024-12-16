package it.rizzoli.atthemoment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atthemoment.R;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

import it.rizzoli.atthemoment.model.principali.News;
import it.rizzoli.atthemoment.service.CallAtm;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable updateTask;
    private TextView subHeaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_tab_bar, new FragTabBar())
                    .commit();
        }

        Button button = findViewById(R.id.button_go_to_second);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                intent.putExtra("Bottone", Integer.parseInt(v.getTag().toString()));
                startActivity(intent);
            }
        };

        LinearLayout buttonGoTram = findViewById(R.id.button_go_tram);
        buttonGoTram.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoMetro = findViewById(R.id.button_go_metro);
        buttonGoMetro.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoBus = findViewById(R.id.button_go_autobus);
        buttonGoBus.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoTreno = findViewById(R.id.button_go_treno);
        buttonGoTreno.setOnClickListener(buttonClickListener);

        handler = new Handler();

        subHeaderText = findViewById(R.id.subHeaderText);
        new Thread(() -> {
            try {
                stampaNews();
            } catch (IOException e) {
                Log.e("MainActivity", "Errore durante la fetch delle news", e);
            }
        }).start();

    }
    static String test = "";
    public void startUpdating() {
        if (updateTask == null) {
            updateTask = new Runnable() {
                @Override
                public void run() {
                    if (test.length() > 0) {
                        test = test.substring(1);
                        subHeaderText.setText(test);
                    } else {
                        Log.d("PeriodicUpdater", "Stringa vuota, nessun carattere da rimuovere.");
                    }
                    handler.postDelayed(this, 500);
                }
            };

            handler.post(updateTask);
        }
    }

    public static void stampaNews() throws IOException {
        List<News> newsList = CallAtm.newsFixGzipResponse(CallAtm.news());
        for (News news : newsList) {
            System.out.println("Titolo: " + news.getTitle());
            System.out.println("Pubblicazione: " + news.getPublication());
            System.out.println("Scadenza: " + news.getExpiration());
            System.out.println("Corpo del messaggio: " + news.getBody());
            System.out.println("Linee: " + String.join(", ", news.getLines()));
            System.out.println("GUID: " + news.getGuid());
            String plainText = Jsoup.parse(news.getBody()).text();
            test = test + plainText;
        }

    }
    public void stopUpdating() {
        if (handler != null && updateTask != null) {
            handler.removeCallbacks(updateTask);
            updateTask = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startUpdating();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUpdating();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
}
