package org.arshef.banafsh.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.arshef.banafsh.R;

public class LoginActivity extends AppCompatActivity {
    final String Username = "a";
    final String Password = "a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permission();
        setContentView(R.layout.activity_login);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.usrTxt);
                EditText password = findViewById(R.id.passTxt);
                if (username.getText().toString().equals(Username) && password.getText().toString().equals(Password))
                    Login();
                else
                    Toast.makeText(LoginActivity.this, "ورود ممکن نیست", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Login() {
        Toast.makeText(LoginActivity.this, "خوش آمدید", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, ChooseActivity.class));
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(LoginActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        }
    }
}
