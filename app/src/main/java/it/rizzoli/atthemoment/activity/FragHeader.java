package it.rizzoli.atthemoment.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.atthemoment.R;


public class FragHeader extends Fragment {
    public FragHeader() {}

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


        return rootView;
    }

}
