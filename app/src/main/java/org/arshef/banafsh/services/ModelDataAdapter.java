package org.arshef.banafsh.services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ModelDataAdapter extends ArrayAdapter<DataModel> {
    public static List<DataModel> Models;
    public static List<Boolean> checkedState;
    static ModelDataAdapter instance;

    public ModelDataAdapter(@NonNull Context context, @NonNull List<DataModel> objects) {
        super(context, R.layout.list_item_layout, objects);
        Models = new ArrayList<>();
        checkedState = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            checkedState.add(false);
        }
        instance = this;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        final DataModel item = getItem(position);
        TextView posTxt = convertView.findViewById(R.id.posTxt);
        posTxt.setText(String.format(".%s", String.valueOf(item.Position)));
        TextView titleTxt = convertView.findViewById(R.id.titleTxt);
        titleTxt.setText(item.Title);
        TextView uniTxt = convertView.findViewById(R.id.uniTxt);
        uniTxt.setText(item.University);
        CheckBox checkbox = convertView.findViewById(R.id.checkbox);
        checkbox.setChecked(checkedState.get(position));
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    checkedState.set(position, true);
                    Models.add(item);
                    Correction();
                } else {
                    checkedState.set(position, false);
                    Models.remove(item);
                    Correction();
                }
            }
        });
        checkbox.setOnCheckedChangeListener(null);
        return convertView;
    }
    public static ModelDataAdapter getInstance() {
        return instance;
    }
    public void refresh() {
        notifyDataSetChanged();
    }

    private void Correction() {
        for (int i = 0; i < Models.size(); i++) {
            Models.get(i).Position = i+1;
        }
    }
}
