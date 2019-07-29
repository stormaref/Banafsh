package org.arshef.banafsh.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;

import java.util.List;

public class ResultDataAdapter extends ArrayAdapter<DataModel> {
    public ResultDataAdapter(@NonNull Context context,@NonNull List<DataModel> objects) {
        super(context, R.layout.result_item_layout, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_item_layout, parent, false);
        final DataModel item = getItem(position);
        TextView posTxt = convertView.findViewById(R.id.posLabel);
        posTxt.setText(String.format(".%s", String.valueOf(item.Position)));
        TextView titleTxt = convertView.findViewById(R.id.titleLabel);
        titleTxt.setText(item.Title);
        TextView uniTxt = convertView.findViewById(R.id.uniLabel);
        uniTxt.setText(item.University);
        return convertView;
    }
}
