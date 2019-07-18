package org.arshef.banafsh.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.arshef.banafsh.R;

import java.util.List;

public class ResultDataAdapter extends ArrayAdapter<DataModel> {
    public ResultDataAdapter(@NonNull Context context,@NonNull List<DataModel> objects) {
        super(context, R.layout.result_item_layout, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        final DataModel item = getItem(position);
        return convertView;
    }
}
