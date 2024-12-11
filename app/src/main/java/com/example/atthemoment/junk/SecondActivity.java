package com.example.atthemoment.junk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.atthemoment.R;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button button = findViewById(R.id.GET);
        button.setOnClickListener((v) -> getQuestionsUsingHttpClient());
    }

    public void getQuestionsUsingHttpClient() {
        new Thread(() -> {
            HttpURLConnection connection = null;

            try {
                URL url = new URL("https://giromilano.atm.it/proxy.tpportal/api/tpportal/tpl/journeyPatterns/93%7C0?alternativeRoutesMode=false");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.setRequestProperty("accept", "application/json, text/plain, */*");
                connection.setRequestProperty("accept-encoding", "gzip, deflate, br, zstd");
                connection.setRequestProperty("accept-language", "en-US,en;q=0.9,it-IT;q=0.8,it;q=0.7");
                connection.setRequestProperty("referer", "https://giromilano.atm.it/");
                connection.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"");
                connection.setRequestProperty("sec-ch-ua-mobile", "?0");
                connection.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
                connection.setRequestProperty("sec-fetch-dest", "empty");
                connection.setRequestProperty("sec-fetch-mode", "cors");
                connection.setRequestProperty("sec-fetch-site", "same-origin");
                connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

                InputStream inputStream = new GZIPInputStream(connection.getInputStream()); //decoprimo in Gzip
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8"); // converto da byte in caratteri
                Gson gson = new Gson();
                Mezzo myResponseObject = gson.fromJson(reader, Mezzo.class); //converte da json a oggetto java

                Log.d("Response", myResponseObject.toString());
                Log.d("ID", myResponseObject.getId());

                TextView idMezzo = (TextView)findViewById(R.id.idMezzo);
                idMezzo.setText(myResponseObject.getId());

                TextView descrptionMezzo = (TextView)findViewById(R.id.descrptionMezzo);
                descrptionMezzo.setText(myResponseObject.line.lineDescription);

                Log.d("Description", myResponseObject.line.lineDescription);

                for (int i = 0; i < myResponseObject.stops.size(); i++) {
                    Log.d("Stop","ID: "+myResponseObject.stops.get(i).code);
                    Log.d("Stop","Fermata: "+myResponseObject.stops.get(i).description);
                    Log.d("Stop", String.valueOf(myResponseObject.stops.get(i).location.x));
                    Log.d("Stop", String.valueOf(myResponseObject.stops.get(i).location.y));
                    Log.d("Stop", "---------");
                }


            } catch (Exception e) {
                Log.e("Error", "Errore: " + e.getMessage(), e);
            }
        }).start();
    }

}
