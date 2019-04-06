package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class hospitals_management_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_management_page);
    }

    public void toAddHospital(View view) {
        Intent intent = new Intent(hospitals_management_page.this,add_hospitals_page.class);
        startActivity(intent);
    }

    public void toDisplayHospitals(View view) {
      //  Intent intent = new Intent(hospitals_management_page.this,display_modify_delete_hospital.class);
       // startActivity(intent);
        Intent i =new Intent(hospitals_management_page.this, activity_display_modify_delete_hospital2.class);
        startActivity(i);
    }
}
