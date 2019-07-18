package org.arshef.banafsh.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.arshef.banafsh.R;

public class ChooseActivity extends AppCompatActivity {
    public static int choice = -1;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Button button = findViewById(R.id.firstBtn);
        Button button1 = findViewById(R.id.secBtn);
        Button button2 = findViewById(R.id.thirdBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 0;
                Do();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 1;
                Do();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 2;
                Do();
            }
        });
    }

    private void Do() {
        startActivity(new Intent(ChooseActivity.this, MainActivity.class));
    }
}
