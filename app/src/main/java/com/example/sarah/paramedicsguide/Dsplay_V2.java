package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dsplay_V2 extends AppCompatActivity {
    Medications medications ;
    TextView t;
    String s;
    ImageView imageView11,imageView12,imageView13,imageView14,imageView_track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplay__v2);

        Hospital_home_page.getNewCaseByQuery(Dsplay_V2.this);

        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        imageView13=(ImageView)findViewById(R.id.imageView13);
        imageView14=(ImageView)findViewById(R.id.imageView14);
        imageView_track=(ImageView)findViewById(R.id.imageView_track);

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V2.this,Dsplay_V1.class);
                startActivity(i);
            }
        });
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V2.this,Dsplay_V3.class);
                startActivity(i);
            }
        });
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V2.this,Dsplay_V4.class);
                startActivity(i);
            }
        });
        imageView_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Dsplay_V2.this,MapsActivity2.class);
                startActivity(i);
            }
        });

        Query query2 = FirebaseDatabase.getInstance().getReference().child("Medications")
                .orderByChild("patient_key")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    medications = new Medications();
                    medications=snapshot.getValue(Medications.class);
                }
                if(medications==null)
                    Toast.makeText(Dsplay_V2.this,"لم يتم ارسال الأدوية المستخدمة حتى الآن ", Toast.LENGTH_SHORT).show();

                else {
                     s=dsplay_medications(medications);
                    t.setText(s.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
         t = (TextView) findViewById(R.id.textView_sened_medications);

    }

     public String dsplay_medications(Medications medications){
        String s= "";
        if(medications.Atropine==true){s+="\n\n"+"الأتروبين";}
        if(medications.Aspirin==true){s+="\n\n"+"الأسبرين";}
        if(medications.Salbutamol==true){s+="\n\n"+"سالبوتامول";}
        if(medications.Adrenaline==true){s+="\n\n"+"الأدرينالين ( الإيبينفرين )";}
        if(medications.Nitroglycerin==true){s+="\n\n"+"النتروغليسيرين";}
        if(medications.Dextrose==true){s+="\n\n"+"الدكستروز 50%";}
        if(medications.Gelatin==true){s+="\n\n"+"جيلاتين سكر تحت اللسان";}
        reportList_fillup_patient_info();
        return s;


    }

    public void go_montor(){
        Intent i = new Intent(Dsplay_V2.this,MapsActivity2.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Dsplay_V2.this,MapsActivity2.class);
        startActivity(i);
    }

    private  void reportList_fillup_patient_info() {
        Toast.makeText(Dsplay_V2.this,"هنا  ", Toast.LENGTH_SHORT).show();


        for(int i=0;i<Hospital_home_page.reportList.size();i++){
            Toast.makeText(Dsplay_V2.this,Hospital_the_cases.selected_patient.key+" "+Hospital_home_page.reportList.get(i).key_patient, Toast.LENGTH_SHORT).show();

            if(Hospital_the_cases.selected_patient.key==Hospital_home_page.reportList.get(i).key_patient){
                Report r = new Report();
                r.paramedic_center=Hospital_home_page.reportList.get(i).paramedic_center;
                r.paramedic_name=Hospital_home_page.reportList.get(i).paramedic_name;
                r.key_patient=Hospital_home_page.reportList.get(i).key_patient;

                r.patient_name=Hospital_home_page.reportList.get(i).patient_name;
                r.patient_medical_case=Hospital_home_page.reportList.get(i).patient_medical_case;

                r.medicine=s;
                Hospital_home_page.reportList.set(i,r);
                Toast.makeText(Dsplay_V2.this,"تم تعديل التقرير  ", Toast.LENGTH_SHORT).show();
                break;
            }

        }

    }
}
