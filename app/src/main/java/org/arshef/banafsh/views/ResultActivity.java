package org.arshef.banafsh.views;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.services.ItemMoveCallback;
import org.arshef.banafsh.services.ModelDataAdapter;
import org.arshef.banafsh.services.PDFHandler;
import org.arshef.banafsh.services.RecyclerViewAdapter;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    public static String name;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button button = findViewById(R.id.submitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog, null);
                builder.setCancelable(false);
                builder.setView(dialogView);
                Button btn_positive = dialogView.findViewById(R.id.okBtn);
                Button btn_negative = dialogView.findViewById(R.id.cancelBtn);
                final EditText nameTxt = dialogView.findViewById(R.id.nameInput);
                final AlertDialog dialog = builder.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = nameTxt.getText().toString();
                        if (!name.equals("")) {
                            try {
                                PDFHandler.createTable(name, ModelDataAdapter.Models);
                                Toast.makeText(ResultActivity.this, String.format("%s %s/pdfs/", "Saved in ", Environment.getExternalStorageDirectory().getAbsolutePath()), Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Log.wtf("**", e.getMessage());
                                Toast.makeText(ResultActivity.this, "Error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
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
