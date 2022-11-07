package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class AfterLogin extends AppCompatActivity {
    ImageButton police,fireService,trippleNine,hospital,blood,fuel,pharmacy,loc,em;

    ImageView menuIcon;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);


        police = findViewById(R.id.police);
        fireService = findViewById(R.id.AtmBooth);
        hospital = findViewById(R.id.Hospital);
        trippleNine = findViewById(R.id.trippleNine);
        blood = findViewById(R.id.blood);
        fuel = findViewById(R.id.fuelStation);
        pharmacy = findViewById(R.id.Pharmacy);

        em=findViewById(R.id.home_em);
        //loc = findViewById(R.id.location2);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        menuIcon=findViewById(R.id.menu_icon);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationDrawer ();
        navigationView= findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forPharmacy();
            }
        });

        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forFuel();
            }
        });

        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forBlood();
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

        em.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AfterLogin.this, SendLocation.class);
                startActivity(intent);
            }
        });
    }
    public void forSendingLocation() {
        final double[] L = new double[1];
        final double[] A = new double[1];
        if(ActivityCompat.checkSelfPermission(AfterLogin.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location3 = task.getResult();
                    if(location3 != null){
                        System.out.println("-----------------------ZAhid-------------------------------------");
                        Geocoder geocoder = new Geocoder(AfterLogin.this, Locale.getDefault());
                        L[0] = location3.getLatitude();
                        A[0] = location3.getAltitude();
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "I am in Danger");
                        intent.putExtra(Intent.EXTRA_TEXT,"I need your help !\n "+"I am in this location "+ " https://www.google.com/maps/search/?api=1&query="+L[0]+","+A[0]+" ");
                        startActivity(intent);
                    }
                }
            });
        }else{
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location3 = task.getResult();
                    if(location3 != null){
                        System.out.println("-----------------------ZAhid-------------------------------------");
                        Geocoder geocoder = new Geocoder(AfterLogin.this, Locale.getDefault());
                        L[0] = location3.getLatitude();
                        A[0] = location3.getLongitude();
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:"));
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "I am in Danger");
                        intent.putExtra(Intent.EXTRA_TEXT,"I need your help !\n "+"I am in this location "+ " https://www.google.com/maps/search/?api=1&query="+L[0]+","+A[0]+" ");
                        startActivity(intent);
                    }
                }
            }        );
            System.out.println("-----------------------ZAhid------222222222222-------------------------------");
            ActivityCompat.requestPermissions(AfterLogin.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }


    }
    private void forPharmacy() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=pharmacy");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forFuel() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=filling+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
          //  Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
       // }
    }

    private void forPolice() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=police+station");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
       // if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
       // }else{
            //Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
       // }
    }

    private void forBlood() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AfterLogin.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_spinner,null);
        mBuilder.setTitle("Choose Blood Group :");
        Spinner  mSpinner = mView.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AfterLogin.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.bloodtypes));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(AfterLogin.this,BloodSearchResult.class);
                    i.putExtra("type",mSpinner.getSelectedItem().toString());
                    startActivity(i);
                    dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void forHospital() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals+&+clinics");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        //if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
          //  Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }

    private void forTrippleNine() {
        Intent dialIntent = new Intent();
        dialIntent.setAction(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:999"));
        startActivity(dialIntent);
    }

    private void forFireStation() {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=atm+booth");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
       // if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        //}else{
          //  Toast.makeText(getApplicationContext(),"Google Map is not installed or disabled", Toast.LENGTH_SHORT).show();
        //}
    }


    //nav drawer
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

   // @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent ;

        if(item.getItemId()==R.id.nav_blood_bank) {
            forBlood();
        }
        else if (item.getItemId()==R.id.nav_home){
             intent = new Intent(AfterLogin.this, AfterLogin.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.nav_about) {
            // intent = new Intent(UserDashboard.this, ContactUs.class);
            //startActivity(intent);
        }
        else if(item.getItemId()==R.id.emmargency) {
             intent = new Intent(AfterLogin.this, SendLocation.class);
            startActivity(intent);
        }
       // else if (item.getItemId()==R.id.nav_profile){
            // intent = new Intent(UserDashboard.this, Profile.class);
            //startActivity(intent);
        //}

        else if (item.getItemId()==R.id.nav_logout){
             firebaseAuth.signOut();
             intent = new Intent(AfterLogin.this, LoginPage.class);
             startActivity(intent);
             finish();
        }
        return false;
    }
}