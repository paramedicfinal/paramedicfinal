package com.example.sarah.paramedicsguide;

import android.location.Location;
import android.os.ParcelUuid;
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

import java.security.PublicKey;
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
    DatabaseReference reference;
    DatabaseReference refRemoveNewCase;
    NewCase remove_case;
    Medications m;

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
        imageView_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //PARAMEDIC LOCATION

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //TEXT VIEW
        String name =Hospital_the_cases.selected_patient.name;
        textView_name_dsplay2.setText(name);

        //IMAGE VIEW
        imageView_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  inClickDoneMark();}});

        //LOCATION
        double lx=hospital_login_page.user.locationX;
        double ly=hospital_login_page.user.locationY;
        LatLng posetion = new LatLng(lx, ly);
        mMap.addMarker(new MarkerOptions().position(posetion).title("المشفى"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posetion));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

        getAssignedCustomerPickupLocation();

}




    public void vitalSignClicked(){
        contnueLookForLocation=false;
        Intent i = new Intent(MapsActivity2.this,Dsplay_V1.class);
        startActivity(i);
    }

    public void inClickDoneMark(){

        reference = FirebaseDatabase.getInstance().getReference().child("NewCase");
        Query query1 = reference.orderByChild("key_patient").equalTo(Hospital_the_cases.selected_patient.key);//find the payient by key
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    remove_case =  snapshot.getValue(NewCase.class); }
                    if(remove_case==null){ Toast.makeText(MapsActivity2.this," لم يتم العثور على الحالة ",Toast.LENGTH_SHORT).show(); }
                    else{
                    Query queryMedications = FirebaseDatabase.getInstance().getReference().child("Medications")
                                    .orderByChild("patient_key")
                                    .equalTo(Hospital_the_cases.selected_patient.key);
                    queryMedications.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                m = new Medications();
                                m=snapshot.getValue(Medications.class); }

                                String medications_string=Dsplay_V2.dsplay_medications(m);
                            if(medications_string.equals("")||medications_string==null){
                                Toast.makeText(MapsActivity2.this,"لا توجد ادوية ",Toast.LENGTH_SHORT).show();
                                medications_string="لا توجد ادوية مستخدمة"; }
                                send_report(medications_string); }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }}); }}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }}); }

    public void send_report(String medications_string){
        Report report=new Report(Hospital_the_cases.selected_patient.name,
                Hospital_the_cases.selected_patient.medicalState,
                "2.36 ",
                remove_case.getName_paramedic(),
                remove_case.getParamedic_center(),
                medications_string);
        Toast.makeText(MapsActivity2.this,"تم انشاء التقرير ",Toast.LENGTH_SHORT).show();
        DatabaseReference refReport = FirebaseDatabase.getInstance().getReference().child("Report");
        refReport.push().setValue(report);
        remove_from_list();
    }

    public void remove_from_list (){
        int size =Hospital_home_page.list_newCase.size();
        for(int i = 0 ; i<size ;i++){
            if(Hospital_home_page.list_newCase.get(i).equals(remove_case)){
                Hospital_home_page.list_newCase.remove(i); } }
                refRemoveNewCase = FirebaseDatabase.getInstance().getReference().child("NewCase");
                Query queryRemoveNewCase = refRemoveNewCase.orderByChild("key_patient").equalTo(Hospital_the_cases.selected_patient.key);//find the payient by key
                queryRemoveNewCase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    refRemoveNewCase.child(snapshot.getKey()).removeValue(); } }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Intent i = new Intent(MapsActivity2.this,Hospital_home_page.class);
        startActivity(i);
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
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

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
