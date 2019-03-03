package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class drugs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs2);
    }

    public void sendDrugs(View view) {
        Intent intent = new Intent(drugs.this,vitalAndDrugs.class);
        startActivity(intent);
    }
}
