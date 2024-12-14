package it.rizzoli.atthemoment.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.atthemoment.R;

public class FragStop extends Fragment {

    private TextView descriptionTextView;
    private TextView bookInfoTextView;
    private TextView waitingMessageTextView;

    public FragStop() {}

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listview_lines, container, false);

        descriptionTextView = rootView.findViewById(R.id.description);
        bookInfoTextView = rootView.findViewById(R.id.bookinfo);
        waitingMessageTextView = rootView.findViewById(R.id.waitingmessage);

        Bundle b = getArguments();
        if (b != null) {
            String desc = b.getString("descriptionTextView");
            String bookInfo = b.getString("bookInfoTextView");
            String waitingMessage = b.getString("waitingMessageTextView");

            descriptionTextView.setText(desc);
            bookInfoTextView.setText(bookInfo);
            waitingMessageTextView.setText(waitingMessage);
        }

        return rootView;
    }
}
