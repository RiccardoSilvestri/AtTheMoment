package it.rizzoli.atthemoment.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.atthemoment.R;


public class FragTabBar extends Fragment {
    public FragTabBar() { }
    @Override public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_bar, container, false);
        return rootView;
    }
}