package it.rizzoli.atthemoment.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atthemoment.R;

import java.util.ArrayList;

import it.rizzoli.atthemoment.model.Stop;

public class StopAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Stop> stops;

    public StopAdapter(Context context, ArrayList<Stop> stops) {
        this.context = context;
        this.stops = stops;
    }

    @Override
    public int getCount() {
        return stops.size();
    }

    @Override
    public Object getItem(int position) {
        return stops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.fragment_list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.item_description);
        textView.setText(stops.get(position).getDescription());

        return convertView;
    }
}
