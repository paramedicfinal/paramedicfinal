package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class by_voice_Arabic extends AppCompatActivity {
    private TextView txvResult;
    String text="";
    VitalSigns VitalSigns;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_voice);
        txvResult = (TextView) findViewById(R.id.txvResult);
        Bundle b = getIntent().getExtras();
       // key=b.getString("PatientID");
    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-SA");



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
        alertDlg.setMessage("لإرسال النص اختر تأكيد");
        alertDlg.setCancelable(false);
        alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                by_voice_Arabic.super.onBackPressed();

            }
        });

        alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDlg.create().show();   }

    public void buttonSend(View view) {
        createDialog();
;
       VitalSigns signs = new VitalSigns(text,new_case.patient.key);
        myRef.push().setValue(signs);



    }
    public void buttonDeleat(View view) {
        createDialogDeleat();
    }
    private void createDialogDeleat() {

        if (txvResult != null) {
            AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
            alertDlg.setMessage("لحذف النص اختر تأكيد ");
            alertDlg.setCancelable(false);
            alertDlg.setPositiveButton("تأكيد", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txvResult.setText("");
                    text="";
                }
            });

            alertDlg.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDlg.create().show();
        }
        else{}
    }
}
