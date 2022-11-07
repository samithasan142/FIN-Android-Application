package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import pl.droidsonroids.gif.GifImageButton;

public class Login_Emergency extends AppCompatActivity {
    GifImageButton emer;
    GifImageButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_emergency);
        emer = findViewById(R.id.emerge);
        login = findViewById(R.id.log);
        emer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foremer();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forlogin();
            }
        });
    }

    public void foremer()
    {
        Intent i = new Intent(Login_Emergency.this,EmergencyLogin.class);
        startActivity(i);
    }
    public void forlogin()
    {
        Intent i = new Intent(Login_Emergency.this,loginRegister_main.class);
        startActivity(i);
    }

}