package org.arshef.banafsh.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.models.ModelDataAdapter;
import org.arshef.banafsh.models.ResultDataAdapter;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        List<DataModel> list = ModelDataAdapter.Models;
        ResultDataAdapter adapter = new ResultDataAdapter(this, list);
        ListView listView = findViewById(R.id.resultListView);
        listView.setAdapter(adapter);
    }
}
