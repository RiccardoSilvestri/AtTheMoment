package it.rizzoli.atthemoment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.atthemoment.R;

public class FragTabBar extends Fragment {

    public FragTabBar() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_bar, container, false);

        // Initialize your buttons here
        Button buttonGoTram = rootView.findViewById(R.id.navTram);
        Button buttonGoMetro = rootView.findViewById(R.id.navMetro);
        Button buttonGoBus = rootView.findViewById(R.id.navBus);
        Button buttonGoTreno = rootView.findViewById(R.id.navTrain);

        // Set click listeners
        View.OnClickListener buttonClickListener = v -> {
            Intent intent = new Intent(getActivity(), LinesActivity.class); // Use getActivity() for context
            intent.putExtra("Bottone", Integer.parseInt(v.getTag().toString()));
            startActivity(intent);
        };

        buttonGoTram.setOnClickListener(buttonClickListener);
        buttonGoMetro.setOnClickListener(buttonClickListener);
        buttonGoBus.setOnClickListener(buttonClickListener);
        buttonGoTreno.setOnClickListener(buttonClickListener);

        return rootView; // Return the inflated view
    }
}
