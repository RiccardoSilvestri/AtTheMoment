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

        Button button = findViewById(R.id.button_go_to_news);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(intent);
        });

        Button buttonFindArroundMe = findViewById(R.id.button_find_around_me);
        buttonFindArroundMe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LinesActivityAroundMe.class);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
}
