package com.example.sarah.paramedicsguide;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, com.google.android.gms.location.LocationListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {


    private GoogleMap mMap;
    static Location location;
    LocationManager locationManager;
    GoogleApiClient googleApiClient;
    Location mLastlocation;
    LocationRequest locationRequest;

    ImageView imageView_send;
    ImageView imageView_arrow;
    TextView textView_name_box;
    TextView textView_email_box;
    TextView textView_time_box;
    LinearLayout layout_box_name_email_time;
    Hospital hospital;
    boolean isCanAccess = true;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imageView_send = (ImageView) findViewById(R.id.imageView_send);
        imageView_arrow = (ImageView) findViewById(R.id.imageView_arrow);
        textView_name_box = (TextView) findViewById(R.id.textView_name_dsplay2);
        textView_email_box = (TextView) findViewById(R.id.textView_email_box);
        textView_time_box = (TextView) findViewById(R.id.textView_tima_dsplay2);
        layout_box_name_email_time = (LinearLayout) findViewById(R.id.layout_box_name_email_time);

        imageView_send.setVisibility(View.INVISIBLE);
        imageView_arrow.setVisibility(View.INVISIBLE);
        layout_box_name_email_time.setVisibility(View.INVISIBLE);

        imageView_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        imageView_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next_int();
            }
        });

    }
    private void createDialog() {
        if(mLastlocation==null){
            openLocationGpsDaialog();
        }
        else {
            android.support.v7.app.AlertDialog.Builder alertDlg = new android.support.v7.app.AlertDialog.Builder(this);
            alertDlg.setMessage("لإرسال الطلب اختر تاكيد ");
            alertDlg.setCancelable(false);
            alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    send();

                }
            });

            alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
        }
    }
    public void openLocationGpsDaialog(){
        android.support.v7.app.AlertDialog.Builder alertDlg = new android.support.v7.app.AlertDialog.Builder(this);
        alertDlg.setMessage("قم بتشغيل نظام تحديد المواقع من الاعدادات ");
        alertDlg.create().show();
    }

    public void next_int() {
        Intent i = new Intent(MapsActivity3.this, vitalAndDrugs.class);
        startActivity(i);
    }
    public void send() {

        //name and id of paramedic
        new_case.newCase.setName_paramedic(paramedic_login_page.user.paramedicName);
        new_case.newCase.setId_paramedic(paramedic_login_page.user.paramedicID);
        new_case.newCase.setParamedic_center(paramedic_login_page.user.paramedicCenter);
        //name of hospital
        new_case.newCase.setName_hospital(hospital.hospitalName);
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = fb.getReference("NewCase");
        myRef.push().setValue(new_case.newCase);

        Toast.makeText(getApplicationContext(), "تم ارسال الطلب", Toast.LENGTH_SHORT).show();

        imageView_send.setVisibility(View.INVISIBLE);
        layout_box_name_email_time.setVisibility(View.INVISIBLE);
        imageView_arrow.setVisibility(View.VISIBLE);
        isCanAccess = false;


    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buldGooglApiClient();
        mMap.setMyLocationEnabled(true);

    }

    private synchronized void buldGooglApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(MapsActivity3.this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

        mLastlocation=location;
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("GPS");
        GeoFire geoFire = new GeoFire(ref);
        geoFire.setLocation(new_case.newCase.getKey_patient(),new GeoLocation(location.getLatitude(),location.getLongitude()));

        getClosestHospitals();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public boolean onMarkerClick(Marker marker) {
        if (isCanAccess) {
            hospital = getHospital(marker);

            imageView_send.setVisibility(View.VISIBLE);
            layout_box_name_email_time.setVisibility(View.VISIBLE);

            textView_name_box.setText(hospital.hospitalName);
            textView_email_box.setText(hospital.hospitalEmail);
            Date date = new Date();
            String convert = "" + date.getTime();
            textView_time_box.setText(convert);
        }
        return true;
    }

    //********
    public Hospital getHospital(Marker marker) {
        int size = Ways_find_hospital.hospitalList.size();
        Hospital hospital = null;
        for (int i = 0; i < size; i++) {
            //hospital list
            double x = Ways_find_hospital.hospitalList.get(i).locationX;
            double y = Ways_find_hospital.hospitalList.get(i).locationY;
            //marker
            double x2 = marker.getPosition().latitude;
            double y2 = marker.getPosition().longitude;

            if (x == x2 && y == y2) {
                hospital = Ways_find_hospital.hospitalList.get(i);
            }

        }

        return hospital;
    }

    private int radius = 1000;
    private Boolean hospitalsFound = false;
    private String hospitalFoundID;
    GeoQuery geoQuery;
    private void getClosestHospitals(){
        DatabaseReference ref_GpsHospital = FirebaseDatabase.getInstance().getReference().child("GPS_Hospitals");
        GeoFire geoFire = new GeoFire(ref_GpsHospital);
        geoQuery = geoFire.queryAtLocation(new GeoLocation(latLng.latitude, latLng.longitude), radius);

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {

                if (!hospitalsFound)
                {
                    hospitalsFound=true;
                    hospitalFoundID=key;
                  //  pickupLatLng = new LatLng(locationLat,locationLng);
                 //   pickupMarker = mMap.addMarker(new MarkerOptions().position(pickupLatLng).title("pickup location").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_pickup)));

                } }
            @Override
            public void onKeyExited(String key) {

            }
            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }
            @Override
            public void onGeoQueryReady() {
                if (!hospitalsFound)
                {
                    radius++;
                    getClosestHospitals();
                }
            }
            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "لا يمكن العوده ، يجب انهاء الحالة", Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        // startActivity(i);
    }
}
