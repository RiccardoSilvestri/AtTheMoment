package it.rizzoli.atthemoment.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
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
    private static TextView subHeaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_tab_bar, new FragTabBar())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_header, new FragHeader())
                    .commit();
        }

        /*
        TextView subText = findViewById(R.id.subHeaderText);
        subText.post(() -> {
            subText.setTranslationX(getResources().getDisplayMetrics().widthPixels);
            ObjectAnimator animator = ObjectAnimator.ofFloat(subText, "translationX", -(getResources().getDisplayMetrics().widthPixels));
            animator.setDuration(8000);
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
            animator.setRepeatCount(ValueAnimator.INFINITE);
        });
        */



        Button button = findViewById(R.id.button_go_to_second);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });

        View.OnClickListener buttonClickListener = v -> {
            Intent intent = new Intent(MainActivity.this, LinesActivity.class);
            intent.putExtra("Bottone", Integer.parseInt(v.getTag().toString()));
            startActivity(intent);
        };

        LinearLayout buttonGoTram = findViewById(R.id.button_go_tram);
        buttonGoTram.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoMetro = findViewById(R.id.button_go_metro);
        buttonGoMetro.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoBus = findViewById(R.id.button_go_autobus);
        buttonGoBus.setOnClickListener(buttonClickListener);

        LinearLayout buttonGoTreno = findViewById(R.id.button_go_treno);
        buttonGoTreno.setOnClickListener(buttonClickListener);
/*
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
    public static void stampaNews() throws IOException {
        List<News> newsList = CallAtm.newsFixGzipResponse(CallAtm.news());

        if (newsList != null && !newsList.isEmpty()) {
            News firstNews = newsList.get(0);
            String plainText = Jsoup.parse(firstNews.getBody()).text();
            subHeaderText.post(() -> subHeaderText.setText(plainText));
            System.out.println("Titolo: " + firstNews.getTitle());
            System.out.println("Pubblicazione: " + firstNews.getPublication());
            System.out.println("Scadenza: " + firstNews.getExpiration());
            System.out.println("Corpo del messaggio: " + firstNews.getBody());
            System.out.println("Linee: " + String.join(", ", firstNews.getLines()));
            System.out.println("GUID: " + firstNews.getGuid());
        } else {
            System.out.println("Nessuna notizia disponibile.");
        }

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
}
