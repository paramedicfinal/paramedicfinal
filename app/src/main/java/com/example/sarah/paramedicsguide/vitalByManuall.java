package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class vitalByManuall extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("VitalSigns");


    EditText pluseRate;
    EditText bloodPressure;
    EditText respRate;
    EditText GCS;
    EditText bloodGlucose;
    EditText  BloodOxygen;
    EditText tempreture;
    int numpluseRate;
    int numbloodPressure;
    int numrespRate;
    int numGCS;
    int numbloodGlucose;
    int  numBloodOxygen;
    int numtempreture;

    String pluseRate2;
    String bloodPressure2;
    String respRate2;
    String GCS2;
    String bloodGlucose2;
    String  BloodOxygen2;
    String tempreture2;
    Date date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_by_manuall);
    }

    public void buttonSend(View view) {

        pluseRate=(EditText)findViewById(R.id.idpluseRate);
        pluseRate2=pluseRate.getText().toString();
        numpluseRate=Integer.parseInt(pluseRate2);

        bloodPressure=(EditText)findViewById(R.id.idbloodPressure);
        bloodPressure2=bloodPressure.getText().toString();
        numbloodPressure=Integer.parseInt(bloodPressure2);

        respRate=(EditText)findViewById(R.id.idrespRate);
        respRate2=respRate.getText().toString();
        numrespRate=Integer.parseInt(respRate2);

        GCS=(EditText) findViewById(R.id.idGCS);
        GCS2=GCS.getText().toString();
        numGCS=Integer.parseInt(GCS2);

        bloodGlucose=(EditText)findViewById(R.id.idbloodGlucose);
        bloodGlucose2=bloodGlucose.getText().toString();
        numbloodGlucose=Integer.parseInt(bloodGlucose2);

        BloodOxygen=(EditText)findViewById(R.id.idBloodOxygen);
        BloodOxygen2=BloodOxygen.getText().toString();
        numBloodOxygen=Integer.parseInt(BloodOxygen2);

        tempreture=(EditText)findViewById(R.id.idtempreture);
        tempreture2=tempreture.getText().toString();
        numtempreture=Integer.parseInt(tempreture2);

        VitalSigns signs = new VitalSigns(numpluseRate, numbloodPressure, numrespRate, numGCS, numbloodGlucose, numBloodOxygen, numtempreture);
        myRef.push().setValue(signs);
        Intent in = new Intent(this, vitalAndDrugs.class);
        startActivity(in);

    }
}
