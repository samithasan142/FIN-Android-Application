package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    Button log;
    TextView reg,openForgetPass;
    EditText email,password;
    private FirebaseAuth mAuth;
    String E,P;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log = findViewById(R.id.log);
        reg = findViewById(R.id.register);
        email = findViewById(R.id.login1);
        password = findViewById(R.id.password1);
        openForgetPass = findViewById(R.id.openForgetPass);
        mAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForLogin();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForReg();
            }
        });
        openForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,ForgetPass.class));
            }
        });
    }
    public void ForLogin()
    {
        E = email.getText().toString().trim();
        P = password.getText().toString().trim();
        if(valid()) {
            mAuth.signInWithEmailAndPassword(E, P)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginPage.this, "Welcome", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginPage.this, AfterLogin.class);
                            startActivity(i);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginPage.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
    boolean valid()
    {
        if( E.length()==0 || P.length()==0)
        {
            Toast.makeText(LoginPage.this, "Do not keep any field empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public void ForReg()
    {
        Intent i = new Intent(LoginPage.this,Register.class);
        startActivity(i);
        finish();
    }
    //safin

}