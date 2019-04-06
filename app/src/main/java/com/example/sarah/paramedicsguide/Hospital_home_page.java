package com.example.sarah.paramedicsguide;

import android.content.Context;
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
    static List<Report> reportList = new ArrayList<Report>();

    static DatabaseReference reference1;
    Context context=Hospital_home_page.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospital_home_page);
        getNewCaseByQuery(context);
        CardView c2=(CardView)findViewById(R.id.cv_report);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Hospital_home_page.this,Report_list_pg.class);
                startActivity(i);
            }
        });
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
   static public  void getNewCaseByQuery(final Context con) {

        reference1 = FirebaseDatabase.getInstance().getReference().child("NewCase");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Hospital_home_page.list_newCase.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NewCase module = new NewCase();
                    module = snapshot.getValue(NewCase.class);
                    if (module.getName_hospital().equals(hospital_login_page.user.hospitalName)) {
                        reportList_fillup_paramedic_info(module,con);

                        if (module.recive == false) {
                            daialog_notify(module.getName_paramedic(),con);
                            module.recive = true;
                            reference1.child(snapshot.getKey()).setValue(module);
                        }
                        boolean noDublicat=true;
                        for(int i=0;i<list_newCase.size();i++){
                           if(module.getKey_patient()==list_newCase.get(i).getKey_patient()){
                               noDublicat=false;
                           }
                        }
                        if(noDublicat){
                        list_newCase.add(module);

                        }

                    } else {
                        module = null;
                    }


                }
            }
            public void daialog_notify(String name, Context context){
                String mas =  "مريض جديد ،تم ارسال طلب من المسعف "+ name;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(mas).setPositiveButton("قبول",null);
                builder.show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private static void reportList_fillup_paramedic_info(NewCase module,final Context con) {
        if(reportList.size()==0){
            Report r = new Report();
            r.paramedic_name=module.getName_paramedic();
            r.paramedic_center=module.getParamedic_center();
            r.key_patient=module.getKey_patient();
            reportList.add(r);
        }
        else{
            int count =0;

            for(int i=0;i<list_newCase.size();i++){

                if(module.getKey_patient()!=list_newCase.get(i).getKey_patient()){
                    count++;
                }

            }
            if(count==list_newCase.size()){
                Report r = new Report();
                r.paramedic_name=module.getName_paramedic();
                r.paramedic_center=module.getParamedic_center();
                r.key_patient=module.getKey_patient();
                reportList.add(r);
            }

        }

    }




}
