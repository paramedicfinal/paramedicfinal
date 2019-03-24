package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hospital_home_page extends AppCompatActivity {
    static List<NewCase> list_newCase = new ArrayList<NewCase>();
    static List<Patient> list_patient = new ArrayList<Patient>();
    DatabaseReference reference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_home_page);
        Log.v("lll22",hospital_login_page.user.hospitalName);
        getNewCaseByQuery();
        CardView c1= (CardView)findViewById(R.id.cv_bell);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Hospital_home_page.this,Hospital_the_cases.class);
                startActivity(i);
            }
        });


    }
    //******
    public  void getNewCaseByQuery(){

        reference1 = FirebaseDatabase.getInstance().getReference().child("NewCase");
        Query query1 = reference1.orderByChild("name_hospital").equalTo(hospital_login_page.user.hospitalName);
        Log.v("lll22",hospital_login_page.user.hospitalName);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_newCase.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NewCase module =  snapshot.getValue(NewCase.class);


                    if(module.recive == false){
                        daialog_notify(module.getName_paramedic());
                        module.recive=true;
                        reference1.child(snapshot.getKey()).setValue(module);

                    }
                    list_newCase.add(module);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void daialog_notify(String name){
        String mas =  "تم ارسال طلب من المسعف "+ name;
        AlertDialog.Builder builder = new AlertDialog.Builder(Hospital_home_page.this);
        builder.setTitle("مريض جديد").setMessage(mas).setPositiveButton("قبول",null);
        builder.show();
    }


}
