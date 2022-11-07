package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class EmergencyLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton police,fireService,trippleNine,hospital,fuel,atm;
    ImageView menuIcon;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency);
        
        police = findViewById(R.id.police);
        fireService = findViewById(R.id.AtmBooth);
        hospital = findViewById(R.id.Hospital);
        trippleNine = findViewById(R.id.trippleNine);
        fuel = findViewById(R.id.fuelStation);
        atm = findViewById(R.id.atmBooth);

        menuIcon=findViewById(R.id.menu_icon);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationDrawer ();
        navigationView= findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);


        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foratm();
            }
        });

        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forfuel();
            }
        });
        
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forPolice();
            }
        });
        
        fireService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forFireStation();
            }
        });
        
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forHospital();
            }
        });
        
        trippleNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forTrippleNine();
            }
        });
    }

    private void foratm() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=nearest+atm+booth");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forfuel() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=nearest+filling+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forTrippleNine() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:999"));

        if (ActivityCompat.checkSelfPermission(EmergencyLogin.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }

    private void forHospital() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=nearest+hospital");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forFireStation() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=nearest+pharmacy");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forPolice() {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=nearest+police+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
       // }
    }

    //Nav T
    private void navigationDrawer() {
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        if(item.getItemId()==R.id.nav_blood_bank) {
            intent = new Intent(EmergencyLogin.this, LoginPage.class);
            Toast.makeText(EmergencyLogin.this, "You have to login first", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.nav_home){
            intent = new Intent(EmergencyLogin.this, EmergencyLogin.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.nav_about) {
           // intent = new Intent(UserDashboard.this, ContactUs.class);
           // startActivity(intent);
        }
        else if(item.getItemId()==R.id.emmargency) {
           intent = new Intent(EmergencyLogin.this, SendLocation.class);
            startActivity(intent);
        }
       // else if (item.getItemId()==R.id.nav_profile){
           // intent = new Intent(UserDashboard.this, Profile.class);
           // startActivity(intent);
        //}

        else if (item.getItemId()==R.id.nav_logout){
            item.setTitle("Login");
            Toast.makeText(EmergencyLogin.this, "You are not logged in", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}