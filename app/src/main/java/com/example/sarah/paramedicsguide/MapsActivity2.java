package com.example.sarah.paramedicsguide;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    TextView textView_name_dsplay2;
    Button button_vitalsigns_dsplay;
    ImageView imageView_done;
    private GoogleMap mMap;
    boolean contnueLookForLocation=true;
    private LatLng destinationLatLng, pickupLatLng;
    Marker pickupMarker;
    private DatabaseReference assignedCustomerPickupLocationRef;
    private ValueEventListener assignedCustomerPickupLocationRefListener;
    Location mLastLocation;
    NewCase getNewCase;
    String newCase_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        textView_name_dsplay2=(TextView)findViewById(R.id.textView_name_dsplay2);
        button_vitalsigns_dsplay=(Button)findViewById(R.id.button_vitalsigns_dsplay);
        imageView_done=(ImageView)findViewById(R.id.imageView_done);

        //BOTTON
        button_vitalsigns_dsplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { vitalSignClicked();}});

        //PARAMEDIC LOCATION

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


        //TEXT VIEW
        String name =Hospital_the_cases.selected_patient.name;
        textView_name_dsplay2.setText(name);

        //IMAGE VIEW
        imageView_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  }});

        //LOCATION

        getAssignedCustomerPickupLocation();





}



    public void vitalSignClicked(){
        contnueLookForLocation=false;
        Intent i = new Intent(MapsActivity2.this,Dsplay_V1.class);
        startActivity(i);
    }

    public void paramedicLocation(DataSnapshot snapshot){
        // Add a marker in Sydney and move the camera



    }

    @Override
    public void onBackPressed() {
        Dsplay_V1.vitalSigns=null;
        Intent i= new Intent(MapsActivity2.this,Hospital_home_page.class);
        startActivity(i);
    }

    private void getAssignedCustomerPickupLocation(){
        assignedCustomerPickupLocationRef = FirebaseDatabase.getInstance().getReference().child("GPS").child(Hospital_the_cases.selected_patient.key).child("l");
        assignedCustomerPickupLocationRefListener = assignedCustomerPickupLocationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && !Hospital_the_cases.selected_patient.key.equals("")){
                    List<Object> map = (List<Object>) dataSnapshot.getValue();
                    double locationLat = 0;
                    double locationLng = 0;
                    if(map.get(0) != null){
                        locationLat = Double.parseDouble(map.get(0).toString());
                    }
                    if(map.get(1) != null){
                        locationLng = Double.parseDouble(map.get(1).toString());
                    }
                    addMarkerLocationOfParamedic(locationLat,locationLng);


                   // getRouteToMarker(pickupLatLng);
                }
            }

            public void addMarkerLocationOfParamedic(double locationLat, double locationLng){
                mMap.clear();
                // Add a marker in Sydney and move the camera
                double lx=hospital_login_page.user.locationX;
                double ly=hospital_login_page.user.locationY;

                LatLng posetion = new LatLng(lx, ly);
                mMap.addMarker(new MarkerOptions().position(posetion).title("المشفى"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(posetion));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
                //
                pickupLatLng = new LatLng(locationLat,locationLng);
                pickupMarker = mMap.addMarker(new MarkerOptions().position(pickupLatLng).title("pickup location").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pickup)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }




}
