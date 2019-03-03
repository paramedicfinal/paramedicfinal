package com.example.sarah.paramedicsguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.content.Intent;
import android.view.View;


public class sendVs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_vs);
    }
    public void ways(View view) {
        RadioButton wayM=(RadioButton)findViewById(R.id.manule);
        RadioButton wayV=(RadioButton)findViewById(R.id.voice);
        if(wayM.isChecked()){
            Intent in= new Intent(this,vitalByManuall.class);
            startActivity(in);

        }
        if(wayV.isChecked()){
            Intent in= new Intent(this,by_voice.class);
            startActivity(in);

        }
    }
}
