package org.arshef.banafsh.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.main_listview);
        List<DataModel> list;
        switch (ChooseActivity.choice) {
            case 0: {
                list = getRList();
            }
            case 1: {
                list = gerTList();
            }
            case 2: {
                list = getEList();
            }
        }
//        ModelDataAdapter adapter = new ModelDataAdapter();
//        listView.setAdapter(adapter);
    }

    private List<DataModel> gerTList() {
        return null;
    }

    private List<DataModel> getEList() {
        return null;
    }

    private List<DataModel> getRList() {
        return null;
    }
}