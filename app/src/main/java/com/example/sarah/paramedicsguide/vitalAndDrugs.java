package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class vitalAndDrugs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_and_drugs);
    }


    public void tovitalPage(View view) {
        Intent in=new Intent(this,sendVs.class);
        startActivity(in);

    }

    public void buttonDone(View view) {
    }


    public void todrugPage(View view) {
        Intent in=new Intent(this,drugs.class);
        startActivity(in);

    }
    //***********
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
    }

}
