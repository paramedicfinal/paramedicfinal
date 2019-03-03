package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class paramedic_home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedic_home_page);
    }

    public void startNewCase(View view) {

        Intent intent2 = new Intent(paramedic_home_page.this,new_case.class);
        startActivity(intent2);
    }
}
