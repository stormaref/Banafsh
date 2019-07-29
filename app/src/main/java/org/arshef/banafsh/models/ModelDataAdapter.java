package org.arshef.banafsh.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.arshef.banafsh.R;

import java.util.ArrayList;
import java.util.List;

public class ModelDataAdapter extends ArrayAdapter<DataModel> {
    public static List<DataModel> Models;

    public ModelDataAdapter(@NonNull Context context, @NonNull List<DataModel> objects) {
        super(context, R.layout.list_item_layout, objects);
        Models = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
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
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!((CheckBox) v).isChecked())
                    Models.remove(item);
                else Models.add(item);
            }
        });
        return convertView;
    }

    private void addItem(DataModel item) {
        if (!checkList(item))
            Models.add(item);
    }

    private boolean checkList(DataModel item) {
        for (DataModel model :
                Models) {
            if (model.Code == item.Code)
                return true;
        }
        return false;
    }
}
