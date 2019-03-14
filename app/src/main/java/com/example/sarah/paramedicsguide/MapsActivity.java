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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    Bundle b;
    List <LatLng> position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        b = getIntent().getExtras();
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
        position= new ArrayList<LatLng>();

        Integer size=Ways_find_hospital.hospitalList.size();
        String sb = size.toString();
        Log.v("ixvi12",sb);

        for(int i=0;i<size;i++) {

            Hospital hospital= Ways_find_hospital.hospitalList.get(i);
            Log.v("dududu",hospital.name);
            double locationX , locationY;
            locationX=hospital.locationX;
            locationY=hospital.locationY;
            LatLng location = new LatLng(locationX, locationY);
            // Add a marker in Sydney and move the camera
            position.add(location) ;
        }

        for(int i=0;i<size;i++) {
            Hospital hospital= Ways_find_hospital.hospitalList.get(i);
            Log.v("dududu2",hospital.name);
            mMap.addMarker(new MarkerOptions().position(position.get(i)).title(hospital.name));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position.get(0) ,10F));
        mMap.setOnMarkerClickListener( MapsActivity.this);
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
        Button but_close = (Button) view.findViewById(R.id.close_button);
        Button but = (Button) view.findViewById(R.id.send_case);

        Hospital hospital = getHospital(marker);

        String phoneNum;
        if(hospital.phone>100000000){phoneNum="0"+hospital.phone;}
        else{phoneNum="012"+hospital.phone;}

        t.setText(hospital.name);
        t2.setText(phoneNum+" ");
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
                Intent i = new Intent(MapsActivity.this,Ways_find_hospital.class);
                startActivity(i);
            }
        });

        but_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
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
        super.onBackPressed();
        Intent i= new Intent(MapsActivity.this,Ways_find_hospital.class);
        startActivity(i);
    }
}
