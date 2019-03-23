package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button button_vitalSigns;
    TextView textView;
    ImageView imageView_done;
    DatabaseReference reference1;
    DatabaseReference reference2;
    DatabaseReference reference3;


    NewCase remove_case = new NewCase();
    Paramedic paramedic;
    Medications m = new Medications();
    static List<Report> list_report = new ArrayList<Report>();
    String medications_string;
    Date date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        String name =Hospital_the_cases.selected_patient.name;
        textView = (TextView)findViewById(R.id.textView_name_dsplay2);
        textView.setText(name);

        button_vitalSigns = (Button) findViewById(R.id.button_vitalsigns_dsplay);
       // button_vitalSigns.setEnabled(vitalSigns!=null);
        button_vitalSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MapsActivity2.this,Dsplay_V1.class);
                startActivity(i);
            }
        });

        imageView_done=(ImageView)findViewById(R.id.imageView_done);
        imageView_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference1 = FirebaseDatabase.getInstance().getReference().child("NewCase");
                Query query1 = reference1.orderByChild("key_patient").equalTo(Hospital_the_cases.selected_patient.key);//find the payient by key
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.v("3333","did1");

                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            remove_case =  snapshot.getValue(NewCase.class);

                            Log.v("3333","did");


                        }
                        if(remove_case==null){
                            Toast.makeText(MapsActivity2.this," لم يتم العثور على الحالة ",Toast.LENGTH_SHORT).show();

                        }
                        else{

                            Query query2 = FirebaseDatabase.getInstance().getReference().child("Medications")
                                    .orderByChild("patient_key")
                                    .equalTo(Hospital_the_cases.selected_patient.key);
                            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        Log.v("33333","loool");
                                        m = new Medications();
                                        m=snapshot.getValue(Medications.class);
                                        Log.v("oooon",remove_case.getKey_patient());


                                    }

                                    medications_string=Dsplay_V2.dsplay_medications(m);
                                    if(medications_string.equals("")||medications_string==null){
                                        Toast.makeText(MapsActivity2.this,"لا توجد ادوية ",Toast.LENGTH_SHORT).show();
                                        medications_string="لا توجد ادوية مستخدمة";
                                    }
                                    send_report();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }



    public  void query1(){



    }

    public  void query2(){

}

public void send_report(){
    Report report=new Report(Hospital_the_cases.selected_patient.name,
            Hospital_the_cases.selected_patient.medicalState,
            "2.36 ",
            remove_case.getName_paramedic(),
            remove_case.getParamedic_center(),
            medications_string
    );

    Toast.makeText(MapsActivity2.this,"تم انشاء التقرير ",Toast.LENGTH_SHORT).show();

    list_report.add(report);
    reference1 = FirebaseDatabase.getInstance().getReference().child("Report");
    reference1.push().setValue(report);
    remove_from_list();

}
public void remove_from_list (){

    int size =Hospital_home_page.list_newCase.size();
    for(int i = 0 ; i<size ;i++){
        if(Hospital_home_page.list_newCase.get(i).equals(remove_case)){
            Hospital_home_page.list_newCase.remove(i);

        }

    }


    reference3 = FirebaseDatabase.getInstance().getReference().child("NewCase");
    Query query3 = reference3.orderByChild("key_patient").equalTo(Hospital_the_cases.selected_patient.key);//find the payient by key
    query3.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Log.v("3333","did1");

            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                reference3.child(snapshot.getKey()).removeValue();
              Log.v("3333","deleted");
 }

                Toast.makeText(MapsActivity2.this," تم انهاء الحالة ",Toast.LENGTH_SHORT).show();




        }

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

}

