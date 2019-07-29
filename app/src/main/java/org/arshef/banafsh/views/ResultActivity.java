package org.arshef.banafsh.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.services.ItemMoveCallback;
import org.arshef.banafsh.services.ModelDataAdapter;
import org.arshef.banafsh.services.RecyclerViewAdapter;

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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<DataModel> list = ModelDataAdapter.Models;
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(list);
        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
    }
}
