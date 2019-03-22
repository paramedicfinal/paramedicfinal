package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class paramedics_management_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedics_management_page);
    }

    public void toAddParamedic(View view) {
        Intent intent = new Intent(paramedics_management_page.this,add_paramedic_page.class);
        startActivity(intent);
    }

    public void toDispalyParamedics(View view) {
        Intent intent = new Intent(paramedics_management_page.this,dispaly_modify_delete_paramedic.class);
        startActivity(intent);
    }


}
