package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    Bundle b;
    List <LatLng> position;
    String medicalState;
    Hospital hospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       // b = getIntent().getExtras();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
         medicalState=new_case.patient.getMedicalState();
        if(medicalState.equals(" حرجة")){}
       else {
            position = new ArrayList<LatLng>();

            Integer size = Ways_find_hospital.hospitalList.size();
            String sb = size.toString();
            Log.v("ixvi12", sb);

            for (int i = 0; i < size; i++) {

                Hospital hospital = Ways_find_hospital.hospitalList.get(i);
                double locationX, locationY;
                locationX = hospital.locationX;
                locationY = hospital.locationY;
                LatLng location = new LatLng(locationX, locationY);
                // Add a marker in Sydney and move the camera
                position.add(location);
            }

            for (int i = 0; i < size; i++) {
                Hospital hospital = Ways_find_hospital.hospitalList.get(i);
                mMap.addMarker(new MarkerOptions().position(position.get(i)).title(hospital.hospitalName));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position.get(0), 10F));
            mMap.setOnMarkerClickListener(MapsActivity.this);
        }
    }

    //*******
    public boolean onMarkerClick(Marker marker) {
        final AlertDialog d;
        AlertDialog.Builder w = new AlertDialog.Builder(MapsActivity.this) ;
        View view = getLayoutInflater().inflate(R.layout.hospital_info_box,null);
        RelativeLayout hos_info_box_layout = (RelativeLayout) view.findViewById(R.id.box);
        TextView t = (TextView) view.findViewById(R.id.info_text2);
        TextView t2 = (TextView) view.findViewById(R.id.info_text4);
        ImageButton close = (ImageButton) view.findViewById(R.id.imageButton_close);
        Button but = (Button) view.findViewById(R.id.send_case);

        hospital = getHospital(marker);

        t.setText(hospital.hospitalName);
        t2.setText(hospital.hospitalEmail);
        w.setView(view);
        d= w.create();
        d.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name and id of paramedic
                new_case.newCase.setName_paramedic(paramedic_login_page.user.paramedicName);
                new_case.newCase.setId_paramedic(paramedic_login_page.user.paramedicID);
                new_case.newCase.setParamedic_center(paramedic_login_page.user.paramedicCenter);
                //name of hospital
                new_case.newCase.setName_hospital(hospital.hospitalName);
                FirebaseDatabase fb=FirebaseDatabase.getInstance();
                DatabaseReference myRef = fb.getReference("NewCase");
                myRef.push().setValue(new_case.newCase);

                Toast.makeText(getApplicationContext(),"تم ارسال الطلب",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MapsActivity.this,vitalAndDrugs.class);
                startActivity(i);
            }
        });



        return true;
    }

    //********
    public Hospital getHospital(Marker marker){
        int size=Ways_find_hospital.hospitalList.size();
        Hospital hospital = null;
        for(int i=0;i<size;i++){
            //hospital list
            double x=Ways_find_hospital.hospitalList.get(i).locationX;
            double y=Ways_find_hospital.hospitalList.get(i).locationY;
            //marker
            double x2=marker.getPosition().latitude;
            double y2=marker.getPosition().longitude;

            if(x==x2 && y==y2){
                hospital = Ways_find_hospital.hospitalList.get(i);
            }

        }

        return hospital;
    }
    //***********

    @Override
    public void onBackPressed() {

        if(medicalState.equals(" حرجة")){
            Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i= new Intent(MapsActivity.this,Ways_find_hospital.class);
            startActivity(i);
        }

    }
}
