package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class selection_paramedic_hospital_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_paramedic_hospital_page);
    }

    public void toHospitalsPage(View view) {
        Intent intent = new Intent(selection_paramedic_hospital_page.this,hospitals_management_page.class);
        startActivity(intent);
    }

    public void toParamedicsPage(View view) {
        Intent intent = new Intent(selection_paramedic_hospital_page.this,paramedics_management_page.class);
        startActivity(intent);
    }


}
