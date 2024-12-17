package it.rizzoli.atthemoment.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atthemoment.R;

import java.io.IOException;

public class FindAroundMeActivity extends AppCompatActivity {

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around);

        test = findViewById(R.id.TEST);

        new Thread(() -> {
            try {
                double[] coordinates = stampaXY();
                runOnUiThread(() -> {
                    test.setText("Latitude: " + coordinates[0] + ", Longitude: " + coordinates[1]);
                });
            } catch (IOException e) {
                Log.e("FindAroundMeActivity", "Errore durante la fetch delle coordinate", e);
            }
        }).start();
    }

    public static double[] stampaXY() throws IOException {
        Log.e("FindMe", "Fetch iniziato");

        double latitude = 40.7128;
        double longitude = -74.0060;

        return new double[]{latitude, longitude};
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
