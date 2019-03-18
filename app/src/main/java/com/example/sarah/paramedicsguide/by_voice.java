package com.example.sarah.paramedicsguide;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class by_voice extends AppCompatActivity {

    private TextView txvResult;
    String text="",key,NID,Pname,PSex,PBedType,PMedicalState;
    Patient Patient;
    VitalSigns VitalSigns;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_voice);
        txvResult = (TextView) findViewById(R.id.txvResult);
        Bundle b = getIntent().getExtras();
        key=b.getString("PatientID");
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, Locale.ENGLISH);


        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    text+=" "+result.get(0);

                    txvResult.setText(text);
                }
                break;
        }
    }


    private void createDialog() {
        AlertDialog.Builder alertDlg =new   AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to send the voice");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("Patient").child(key);

                by_voice.super.onBackPressed();

            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }

    public void buttonSend(View view) {
        createDialog();

        Bundle b= getIntent().getExtras();
        key=b.getString("PatientID");
        NID=b.getString("PatientNatioalID");
        Pname=b.getString("PatientName");
        PSex=b.getString("PatientSex");
        PBedType=b.getString("PatientBedType");
        PMedicalState=b.getString("PatientMedicalState");
        VitalSigns = new VitalSigns(text);
        Patient = new Patient(NID, Pname, PSex,  PMedicalState,PBedType ,VitalSigns);
/*
        Patient = new Patient(new_case.newCase.getPatient().nationalId
                             , new_case.newCase.getPatient().name
                             , new_case.newCase.getPatient().sex
                             ,  new_case.newCase.getPatient().medicalState
                             , new_case.newCase.getPatient().bedType
                             ,VitalSigns);


*/
        myRef.child(key).setValue(Patient);

    }
}