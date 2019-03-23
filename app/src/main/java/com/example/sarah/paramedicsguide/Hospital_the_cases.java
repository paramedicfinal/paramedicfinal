package com.example.sarah.paramedicsguide;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Hospital_the_cases extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapor_the_cases adapor_the_cases;
    DatabaseReference database;
    static Patient selected_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_the_cases);
        recyclerView = (RecyclerView) findViewById(R.id.rv_the_cases);



       int size = Hospital_home_page.list_newCase.size();
        for(int i  =0 ;i<size;i++) {
            NewCase newCase= Hospital_home_page.list_newCase.get(i);

            Query query2 = FirebaseDatabase.getInstance().getReference().child("Patient")
                    .orderByChild("key")
                    .equalTo(newCase.getKey_patient());
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Hospital_home_page.list_patient.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Patient p = new Patient();
                        p = snapshot.getValue(Patient.class);
                        Hospital_home_page.list_patient.add(p);
                        Log.v("nnnn",p.name);

                    }
                    display();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
    }

public void display(){
    adapor_the_cases= new Adapor_the_cases(this,Hospital_home_page.list_patient);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapor_the_cases);


}

    @Override
    public void onBackPressed() {
        Dsplay_V1.vitalSigns=null;
        Intent i= new Intent(Hospital_the_cases.this,Hospital_home_page.class);
        startActivity(i);


    }
}
