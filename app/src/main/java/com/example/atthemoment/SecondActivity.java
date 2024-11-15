package com.example.atthemoment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
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
                connection.setRequestProperty("Accept", "application/json, text/plain, */*");
                connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br, zstd");
                connection.setRequestProperty("Accept-Language", "en-US,en;q=0.9,it-IT;q=0.8,it;q=0.7");
                connection.setRequestProperty("Referer", "https://giromilano.atm.it/");
                connection.setRequestProperty("Sec-CH-UA", "\"Chromium\";v=\"130\", \"Google Chrome\";v=\"130\", \"Not?A_Brand\";v=\"99\"");
                connection.setRequestProperty("Sec-CH-UA-Mobile", "?0");
                connection.setRequestProperty("Sec-CH-UA-Platform", "\"Windows\"");
                connection.setRequestProperty("Sec-Fetch-Dest", "empty");
                connection.setRequestProperty("Sec-Fetch-Mode", "cors");
                connection.setRequestProperty("Sec-Fetch-Site", "same-origin");
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

                int responseCode = connection.getResponseCode();
                try (InputStream inputStream = new GZIPInputStream(connection.getInputStream());
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                    String response = reader.lines().collect(Collectors.joining("\n"));

                    Gson gson = new Gson();
                    Mezzo myResponseObject = gson.fromJson(response, Mezzo.class);
                    Log.d("Response", myResponseObject.toString());
                }

            } catch (Exception e) {
                Log.e("Error", "Errore");
            }

        }).start();
    }
}