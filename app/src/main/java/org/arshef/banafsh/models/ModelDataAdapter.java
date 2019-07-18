package org.arshef.banafsh.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class ModelDataAdapter extends ArrayAdapter<DataModel> {
    public ModelDataAdapter(@NonNull Context context, int resource, @NonNull List<DataModel> objects) {
        super(context, resource, objects);
    }
}
