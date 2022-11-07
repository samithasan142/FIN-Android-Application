package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPass extends AppCompatActivity {
    private Button forgetbtn;
    private EditText txtemail;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        auth = FirebaseAuth.getInstance();
        txtemail = findViewById(R.id.forgetemail);
        forgetbtn =findViewById(R.id.forgetbtn);
        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }

            private void validateData() {
                email = txtemail.getText().toString();
                if(email.isEmpty()){
                    txtemail.setError("Required");
                } else
                    forgetpass();
            }

            private void forgetpass() {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgetPass.this, "Check your mail",Toast.LENGTH_SHORT);
                            finish();
                        } else {
                            //hoynai  Toast.makeText(ForgetPass.this,"Error" +task.getException().getMessage())
                        }
                    }
                });
            }
        });
    }
}