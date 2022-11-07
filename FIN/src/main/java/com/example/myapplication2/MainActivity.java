package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    TextView t1 ;
    ImageButton b1;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        //t1 = (TextView) findViewById(R.id.textView);
        //b1 = (ImageButton) findViewById(R.id.imageButton5);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Login_Emergency.class);
                startActivity(i);
                finish();
            }
        }, 5000);


    }

    public void newPage()
    {
        Intent i = new Intent(MainActivity.this, Login_Emergency.class);
        startActivity(i);
    }



}