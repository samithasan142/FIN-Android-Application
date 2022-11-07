package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class loginRegister_main extends AppCompatActivity {
    Button log,reg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginregister_main);
        log = findViewById(R.id.log);
        reg = findViewById(R.id.reg);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forLog();
            }
        });
        
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forReg();
            }
        });
    }

    private void forReg() {
        Intent i = new Intent(loginRegister_main.this, Register.class);
        startActivity(i);
    }

    private void forLog() {
        Intent i = new Intent(loginRegister_main.this, LoginPage.class);
        startActivity(i);
    }
}
