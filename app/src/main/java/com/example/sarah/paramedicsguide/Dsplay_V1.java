package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Dsplay_V1 extends AppCompatActivity {
    static VitalSigns vitalSigns;
    TextView r1_1,r1_2,r1_3;
    TextView r2_1,r2_2,r2_3;
    TextView r3_1,r3_2,r3_3;
    TextView r4_1,r4_2,r4_3;
    TextView r5_1,r5_2,r5_3;
    TextView r6_1,r6_2,r6_3;
    TextView r7_1,r7_2,r7_3;
    TextView r8_1,r8_2,r8_3;
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsplay__v1);
//time
         r1_1=(TextView)findViewById(R.id.textView_time_1);
         r1_2=(TextView)findViewById(R.id.textView_time_2);
         r1_3=(TextView)findViewById(R.id.textView_time_3);


//Pulse rate
         r2_1=(TextView)findViewById(R.id.textViewn_pulse_rate_1);
         r2_2=(TextView)findViewById(R.id.textViewn_pulse_rate_1);
         r2_3=(TextView)findViewById(R.id.textViewn_pulse_rate_1);
//blood pressure
         r3_1=(TextView)findViewById(R.id.textView_blood_pressure_1);
         r3_2=(TextView)findViewById(R.id.textView_blood_pressure_1);
         r3_3=(TextView)findViewById(R.id.textView_blood_pressure_1);
//Respiratory rate
         r4_1=(TextView)findViewById(R.id.textView_respiratory_rate_1);
         r4_2=(TextView)findViewById(R.id.textView_respiratory_rate_2);
         r4_3=(TextView)findViewById(R.id.textView_respiratory_rate_3);
// Glasgow Comic Scale
         r5_1=(TextView)findViewById(R.id.textView_glasgow_comic_scale_1);
         r5_2=(TextView)findViewById(R.id.textView_glasgow_comic_scale_2);
         r5_3=(TextView)findViewById(R.id.textView_glasgow_comic_scale_3);
// Blood sugar
         r6_1=(TextView)findViewById(R.id.textView_blood_sugar_1);
         r6_2=(TextView)findViewById(R.id.textView_blood_sugar_2);
         r6_3=(TextView)findViewById(R.id.textView_blood_sugar_3);
//Serve oxygen in the blood
         r7_1=(TextView)findViewById(R.id.oxygen_in_blood_1);
         r7_2=(TextView)findViewById(R.id.oxygen_in_blood_2);
         r7_3=(TextView)findViewById(R.id.oxygen_in_blood_3);
//temperature
         r8_1=(TextView)findViewById(R.id.textView_temperature_1);
         r8_2=(TextView)findViewById(R.id.textView_temperature_2);
         r8_3=(TextView)findViewById(R.id.textView_temperature_3);



        Query query2 = FirebaseDatabase.getInstance().getReference().child("VitalSigns")
                .orderByChild("patient_key")
                .equalTo(Hospital_the_cases.selected_patient.key);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("mmmmm","onDataChange");

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    vitalSigns=new VitalSigns();
                    vitalSigns=snapshot.getValue(VitalSigns.class);
                    Log.v("mmmmm",vitalSigns.patient_key);
                }
                if(vitalSigns==null)
                    Toast.makeText(Dsplay_V1.this,"لم يتم ارسال العلامات الحيوية حتى الآن ", Toast.LENGTH_SHORT).show();

                else
                    column_1(vitalSigns);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        button=(Button)findViewById(R.id.button_next_dsplay_v1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dsplay_V1.this, Dsplay_V2.class);
                startActivity(i);

            }
        });
    }
    @Override
    public void onBackPressed() {

        vitalSigns=null;
        Intent i= new Intent(Dsplay_V1.this,MapsActivity2.class);
        startActivity(i);


    }
    public void column_1 (VitalSigns vitalSigns){
        String convertto_string;
        //r1_1.setText(vitalSigns.);

        convertto_string=vitalSigns.pluseRate+"";
        r2_1.setText(convertto_string);

        convertto_string=vitalSigns.bloodPressure+"";
        r3_1.setText(convertto_string);

        convertto_string=vitalSigns.respRate+"";
        r4_1.setText(convertto_string);

        convertto_string=vitalSigns.GCS+"";
        r5_1.setText(convertto_string);

        convertto_string=vitalSigns.bloodGlucose+"";
        r6_1.setText(convertto_string);

        convertto_string=vitalSigns.BloodOxygen+"";
        r7_1.setText(convertto_string);

        convertto_string=vitalSigns.tempreture+"";
        r8_1.setText(convertto_string);


    }
    public void column_2 (VitalSigns vitalSigns){
        String info_v=vitalSigns.bloodGlucose+"\n "
                +vitalSigns.BloodOxygen+" \n "
                +vitalSigns.bloodPressure+" \n " +
                +vitalSigns.GCS+"\n "
                +vitalSigns.pluseRate+"\n "
                +vitalSigns.respRate+"\n "
                +vitalSigns.tempreture;

    }
    public void column_3 (VitalSigns vitalSigns){
        String info_v=vitalSigns.bloodGlucose+"\n "
                +vitalSigns.BloodOxygen+" \n "
                +vitalSigns.bloodPressure+" \n " +
                +vitalSigns.GCS+"\n "
                +vitalSigns.pluseRate+"\n "
                +vitalSigns.respRate+"\n "
                +vitalSigns.tempreture;

    }
}