package com.example.sarah.paramedicsguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplay__v2);



        Query query2 = FirebaseDatabase.getInstance().getReference().child("Medications")
                .orderByChild("patient_key")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    medications = new Medications();
                    medications=snapshot.getValue(Medications.class);
                    Log.v("mmmmm",medications.patient_key);
                }
                if(medications==null)
                    Toast.makeText(Dsplay_V2.this,"لم يتم ارسال الأدوية المستخدمة حتى الآن ", Toast.LENGTH_SHORT).show();

                else {
                    String s=dsplay_medications(medications);
                    t.setText(s.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
         t = (TextView) findViewById(R.id.textView_sened_medications);

    }

    static public String dsplay_medications(Medications medications){
        String s= "";
        if(medications.Atropine==true){s+="\n\n"+"الأتروبين";}
        if(medications.Aspirin==true){s+="\n\n"+"الأسبرين";}
        if(medications.Salbutamol==true){s+="\n\n"+"سالبوتامول";}
        if(medications.Adrenaline==true){s+="\n\n"+"الأدرينالين ( الإيبينفرين )";}
        if(medications.Nitroglycerin==true){s+="\n\n"+"النتروغليسيرين";}
        if(medications.Dextrose==true){s+="\n\n"+"الدكستروز 50%";}
        if(medications.Gelatin==true){s+="\n\n"+"جيلاتين سكر تحت اللسان";}
        return s;

    }
}
