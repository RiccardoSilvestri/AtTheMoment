package it.rizzoli.atthemoment.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.atthemoment.R;
import org.jsoup.Jsoup;
import java.util.ArrayList;
import java.util.List;
import it.rizzoli.atthemoment.controller.RicercaNews;
import it.rizzoli.atthemoment.model.principali.News;

public class NewsActivity extends AppCompatActivity {

    private ListView listView;
    private RicercaNews ricercaNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listView = findViewById(R.id.lista);
        ricercaNews = new RicercaNews();

        fetchNews();
    }

    private void fetchNews() {
        ricercaNews.listaNews(newsList -> {
            runOnUiThread(() -> onNewsFetched(newsList));
        });
    }

    private void onNewsFetched(List<News> newsList) {
        if (newsList != null && !newsList.isEmpty()) {
            ArrayList<String> newsTitles = new ArrayList<>();
            for (News news : newsList) {
                newsTitles.add(news.getTitle());
            }

            ArrayAdapter<String> newsAdapter = new ArrayAdapter<>(NewsActivity.this,
                    android.R.layout.simple_list_item_1, newsTitles);
            listView.setAdapter(newsAdapter);

            listView.setOnItemClickListener((adapterView, view, position, id) -> {
                News selectedNews = newsList.get(position);
                String bodyText = Jsoup.parse(selectedNews.getBody()).text();
                System.out.println("Titolo: " + selectedNews.getTitle());
                System.out.println("Pubblicazione: " + selectedNews.getPublication());
                System.out.println("Scadenza: " + selectedNews.getExpiration());
                System.out.println("Corpo del messaggio: " + bodyText);
                System.out.println("Linee: " + String.join(", ", selectedNews.getLines()));
                System.out.println("GUID: " + selectedNews.getGuid());
            });
        } else {
            Toast.makeText(NewsActivity.this, "No news available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ricercaNews != null) {
            ricercaNews.shutdown();
        }
    }
}
