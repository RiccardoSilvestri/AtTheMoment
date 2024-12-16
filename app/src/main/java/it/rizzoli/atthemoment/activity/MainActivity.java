package it.rizzoli.atthemoment.activity;

import static java.lang.Integer.parseInt;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.atthemoment.R;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container_tab_bar, new FragTabBar()).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.container_header, new FragHeader()).commit();
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

        //OLD CODE
        /*
        LinearLayout buttonGoTram = findViewById(R.id.button_go_tram);
        buttonGoTram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                intent.putExtra("Bottone",parseInt(buttonGoTram.getTag().toString()) );
                startActivity(intent);
            }
        });
        LinearLayout buttonGoMetro = findViewById(R.id.button_go_metro);
        buttonGoMetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                intent.putExtra("Bottone",parseInt(buttonGoMetro.getTag().toString()) );
                startActivity(intent);
            }
        });
        LinearLayout buttonGoAutobus = findViewById(R.id.button_go_autobus);
        buttonGoAutobus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                intent.putExtra("Bottone",parseInt(buttonGoAutobus.getTag().toString()) );
                startActivity(intent);
            }
        });
        LinearLayout buttonGoTreno = findViewById(R.id.button_go_treno);
        buttonGoTreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinesActivity.class);
                intent.putExtra("Bottone",parseInt(buttonGoTreno.getTag().toString()) );
                startActivity(intent);
            }
        });

         */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }
}
