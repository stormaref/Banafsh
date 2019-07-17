package org.arshef.banafsh.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.ModelDataAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.main_listview);
        switch (ChooseActivity.choice) {
            case 0: {

            }
            case 1: {

            }
            case 2: {

            }
        }
//        ModelDataAdapter adapter = new ModelDataAdapter();
//        listView.setAdapter(adapter);
    }
}