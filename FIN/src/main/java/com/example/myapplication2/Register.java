package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    Button submit;
    EditText name,email,contact,blood,location,password;
    String Name,Email,Contact,Blood,Location,Password;
    Spinner mSpinner;
    CheckBox Tick;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit = findViewById(R.id.button);
        name = findViewById(R.id.enterName);
        email = findViewById(R.id.locationtext);
        contact = findViewById(R.id.contact);
        location = findViewById(R.id.location);
        password = findViewById(R.id.password);
        //blood = findViewById(R.id.blood);
        Tick = findViewById(R.id.tick);
        mSpinner = findViewById(R.id.spinnerR);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.bloodtypes));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForSubmit();
            }
        });
    }
    public void ForSubmit()
    {
            Name = name.getText().toString().trim();
            Email = email.getText().toString().trim();
            Contact = contact.getText().toString().trim();
            Location = location.getText().toString().trim();
            Blood = convert(mSpinner.getSelectedItem().toString());
            Password = password.getText().toString().trim();


            if(valid()) {
                //////////////for email and password////////////////
                mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        holder h1 = new holder(Name,Email,Location,Contact,Blood);
                        Firebase(h1);
                        Toast.makeText(Register.this, "Successfully Authenticated ", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Register.this, LoginPage.class);
                        startActivity(i);
                        finish();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Register.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

            }
        }

    private void Firebase(holder h1) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference blood = database.getReference(Blood);

        blood.child(Contact).setValue(h1);
        Toast.makeText(Register.this, "Successfully Created", Toast.LENGTH_SHORT).show();
    }

    boolean valid()
    {
        if(Name.length() == 0 || Email.length()==0 || Contact.length()==0 || Password.length()==0 || Location.length()==0)
        {
            Toast.makeText(Register.this, "Do not keep any field empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Tick.isChecked())
        {
            Toast.makeText(this, "PLease check the agreement", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private String convert(String s) {
        if(s.equals("A(ve+)")) return "a+";
        else if(s.equals("A(ve-)")) return "a-";
        else if(s.equals("B(ve+)")) return "b+";
        else if(s.equals("B(ve-)")) return "b-";
        else if(s.equals("AB(ve+)")) return "ab+";
        else if(s.equals("AB(ve-)")) return "ab-";
        else if(s.equals("O(ve+)")) return "o+";
        else return "o-";
    }
}