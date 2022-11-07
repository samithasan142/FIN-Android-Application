package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

import android.os.Bundle;

public class SendLocation extends AppCompatActivity {

    private static  final int REQUEST_LOCATION=1;
    private static  final int REQUEST_CALL=1;
    private static final int RESULT_PICK_CONTACT =1;
    EditText phone;
    ImageView select;

    //ImageView getlocationBtn;
    ImageView sendMail;
    FusedLocationProviderClient fusedLocationProviderClient;
    Button getlocationBtn;
    TextView showLocationTxt;
    String phoneNumber;
    LocationManager locationManager;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        showLocationTxt=findViewById(R.id.show_location);
        getlocationBtn=findViewById(R.id.getLocation);
        getlocationBtn.setBackgroundColor(getResources().getColor(R.color.light_white));
        phone= (EditText) findViewById(R.id.emergencyPhoneNo);
        select=(ImageView) findViewById(R.id.chooseContact);
        phoneNumber=phone.getText().toString();
        sendMail= findViewById(R.id.location2);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forSendingLocation();
            }
        });

        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                }
                else {
                    getLocation();
                }
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });


    }


    public void forSendingLocation() {
        final double[] L = new double[1];
        final double[] A = new double[1];
        if(ActivityCompat.checkSelfPermission(SendLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location3 = task.getResult();
                    if(location3 != null){
                        System.out.println("-----------------------ZAhid-------------------------------------");
                        Geocoder geocoder = new Geocoder(SendLocation.this, Locale.getDefault());
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
                        Geocoder geocoder = new Geocoder(SendLocation.this, Locale.getDefault());
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
            ActivityCompat.requestPermissions(SendLocation.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            phone.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }


    private void getLocation(){
        //Check Permissions again
        if (ActivityCompat.checkSelfPermission(com.example.myapplication2.SendLocation.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(com.example.myapplication2.SendLocation.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        sendSMS(phoneNumber,latitude,longitude);

                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        sendSMS(phoneNumber,latitude,longitude);

                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){
                        sendSMS(phoneNumber,latitude,longitude);

                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void sendSMS(String phoneNumber,String latitude,String longitude){
        try{
            String number=phone.getText().toString();
            SmsManager smsManager= SmsManager.getDefault();
            StringBuffer smsBody = new StringBuffer();
            //smsBody.append("I need your help !\\n \"+\"I am in this location \n");
            smsBody.append("http://maps.google.com?q=");
            smsBody.append(latitude);
            smsBody.append(",");
            smsBody.append(longitude);
            if(number.trim().length() <= 0) Toast.makeText(com.example.myapplication2.SendLocation.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
            else {
                smsManager.sendTextMessage(number, null, smsBody.toString(), null, null);
                Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Failed to send message.",Toast.LENGTH_SHORT).show();
        }
    }




}