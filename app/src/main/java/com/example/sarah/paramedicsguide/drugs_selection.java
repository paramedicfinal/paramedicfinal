package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class drugs_selection extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    DatabaseReference myRef = database.getReference("Medications");

    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private Button bt;
    Medications medications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs2);

    }

    public void sendDrugs(View view) {

        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);
        ch4=(CheckBox)findViewById(R.id.checkBox4);
        ch5=(CheckBox)findViewById(R.id.checkBox5);
        ch6=(CheckBox)findViewById(R.id.checkBox6);
        ch7=(CheckBox)findViewById(R.id.checkBox7);

        bt=(Button)findViewById(R.id.pg4_1_buttonsend);
         medications = new Medications();
        medications.setPatient_key(new_case.patient.key);



        if(ch1.isChecked()){
            medications.Atropine =true;
            Log.v("mmm","c1");
        }
        if(ch2.isChecked()){
            medications.Aspirin=true;
            Log.v("mmm","c2");

        }
        if(ch3.isChecked()){
            medications.Gelatin=true;
            Log.v("mmm","c3");

        }
        if(ch4.isChecked()){
            medications.Dextrose=true;
            Log.v("mmm","c4");

        }
        if(ch5.isChecked()){
            medications.Nitroglycerin=true;
            Log.v("mmm","c5");

        }
        if(ch6.isChecked()){
            medications.Adrenaline=true;
            Log.v("mmm","c6");

        }
        if(ch7.isChecked()){
            medications.Salbutamol=true;
            Log.v("mmm","c7");

        }


        myRef.push().setValue(medications);
        Toast.makeText(getApplicationContext(),"تم ارسال الادويه",Toast.LENGTH_SHORT).show();

        Intent in = new Intent(this, vitalAndDrugs.class);
        startActivity(in);
    }


}

