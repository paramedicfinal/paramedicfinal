package com.example.sarah.paramedicsguide;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Filtering extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    DatabaseReference myRef = database.getReference("Medications");

    List<Hospital> hospitals ;
    Hospital hospital;
    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private Button bt;
    RadioGroup rg;
    RadioButton rb;
    String rbValue;

    //fierbase***
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        hospitals= new ArrayList<Hospital>();
        //getMultyData();
        Button but = (Button) findViewById(R.id.next);
        ch1=(CheckBox)findViewById(R.id.brain_check_box);
        ch2=(CheckBox)findViewById(R.id.accidents_check_box);
        ch3=(CheckBox)findViewById(R.id.bones_check_box);
        ch4=(CheckBox)findViewById(R.id.birth_check_box);
        ch5=(CheckBox)findViewById(R.id.other_check_box);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  getCategotyByQuery();

            }
        });

    }


    //******
  /*  public  void getCategotyByQuery(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Hospital");
        if(rbValue.equals("غير ذلك")){rbValue="عام";}
        Query query = reference.orderByChild("category").equalTo(rbValue);
        Log.v("lll22",rbValue);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Ways_find_hospital.hospitalList.clear();

                //  if(dataSnapshot.exists()){
                //forloop for add
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Hospital module = snapshot.getValue(Hospital.class);
                    Ways_find_hospital.hospitalList.add(module) ;
                }
                //forloop for get data
                for(int i=0;i<Ways_find_hospital.hospitalList.size();i++){
                    String name=Ways_find_hospital.hospitalList.get(i).hospitalName;
                    Log.v("mariaxxx", name);
                }
                Integer size=Ways_find_hospital.hospitalList.size();
                String sb = size.toString();
                Log.v("ixvi13",sb);

                //address for the next page will move to
                Intent i = new Intent(Filtering.this, MapsActivity.class);
                i.putExtra("category" , rb.getText());

                startActivity(i);
                //   }
            }
            @Override
            public  void onCancelled(DatabaseError databaseError) {
            }
        });
    }*/
/*
    public  void get_database(){
        FirebaseDatabase.getInstance().getReference().child("Hospital").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Hospital module = snapshot.getValue(Hospital.class);
                    hospitals.add(module) ;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/
  /*  public String filter(){
        if(ch1.isChecked()){ hospital.brainAndNerves=true;}
        if(ch2.isChecked()){hospital.accidents=true;}
        if(ch3.isChecked()){hospital.bones=true;}
        if(ch4.isChecked()){hospital.birth=true;}
        if(ch5.isChecked()){hospital.other=true;}



        String s= "";
        int size = hospitals.size();
        for(int i=0;i<size;i++){
            if()
        }

        return s;

    }*/
}
