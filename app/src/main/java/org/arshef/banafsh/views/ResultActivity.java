package org.arshef.banafsh.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.models.ModelDataAdapter;
import org.arshef.banafsh.models.ResultDataAdapter;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.super.onBackPressed();
            }
        });
        List<DataModel> list = ModelDataAdapter.Models;
        ResultDataAdapter adapter = new ResultDataAdapter(this, list);
        ListView listView = findViewById(R.id.resultListView);
        listView.setAdapter(adapter);
    }
}
