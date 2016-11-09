package com.google.maps.android.utils.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.maps.android.utils.demo.model.Row;
import java.util.List;

public class MySimpleArrayAdapter extends ArrayAdapter<Row> {
  private final Context context;

  public MySimpleArrayAdapter(Context context, List<Row> rows) {
    super(context, -1, rows);
    this.context = context;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.item_list, parent, false);
    TextView idView = (TextView) rowView.findViewById(R.id.idView);
    TextView nameView = (TextView) rowView.findViewById(R.id.nameView);
    TextView descriptionView = (TextView) rowView.findViewById(R.id.descriptionView);
    TextView latLngView = (TextView) rowView.findViewById(R.id.latLngView);
    idView.setText(getItem(position).getDoc().getId());
    nameView.setText(getItem(position).getDoc().getName());
    descriptionView.setText(getItem(position).getDoc().getDescription());
    latLngView.setText(getItem(position).getDoc().getPoint().getCoordinates().get(0)+","
        + ""+getItem(position).getDoc().getPoint().getCoordinates().get(1));
    return rowView;
  }
}