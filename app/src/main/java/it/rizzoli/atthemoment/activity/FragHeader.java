package it.rizzoli.atthemoment.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import org.jsoup.Jsoup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.atthemoment.R;

import java.io.IOException;
import java.util.List;

import it.rizzoli.atthemoment.model.principali.News;
import it.rizzoli.atthemoment.service.CallAtm;


public class FragHeader extends Fragment {
    public FragHeader() {
    }

    private static TextView subHeaderText;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(com.example.atthemoment.R.layout.fragment_header, container, false);

        //Animazione
        //FrameLayout containerHeader = findViewById(R.id.container_header);
        TextView subText = rootView.findViewById(R.id.subHeaderText);
        subText.post(() -> {

            subText.setTranslationX(getResources().getDisplayMetrics().widthPixels);
            ObjectAnimator animator = ObjectAnimator.ofFloat(subText, "translationX", -(getResources().getDisplayMetrics().widthPixels));

            animator.setDuration(8000);
            animator.setInterpolator(new LinearInterpolator());

            animator.start();
            animator.setRepeatCount(ValueAnimator.INFINITE);
        });


        //subHeaderText = findViewById(R.id.subHeaderText);
        subHeaderText = rootView.findViewById(R.id.subHeaderText);

        new Thread(() -> {
            try {
                stampaNews();
            } catch (IOException e) {
                Log.e("MainActivity", "Errore durante la fetch delle news", e);
            }
        }).start();

        return rootView;

    }

    static String test = "";

    public static void stampaNews() throws IOException {
        List<News> newsList = CallAtm.newsFixGzipResponse(CallAtm.news());

        if (newsList != null && !newsList.isEmpty()) {
            News firstNews = newsList.get(0);
            String plainText = Jsoup.parse(firstNews.getTitle()).text();
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
    }
}
