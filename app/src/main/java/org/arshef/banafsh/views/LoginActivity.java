package org.arshef.banafsh.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.arshef.banafsh.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = findViewById(R.id.usrTxt);
                EditText password = findViewById(R.id.passTxt);
                if (username.getText().toString().equals("a") && password.getText().toString().equals("a"))
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
}
