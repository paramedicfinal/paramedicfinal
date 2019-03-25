package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class select_voice_language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_voice_language);
    }
    public void ways(View view) {
        RadioButton wayA=(RadioButton)findViewById(R.id.arabic);
        RadioButton wayE=(RadioButton)findViewById(R.id.english);
        if(wayA.isChecked()){
            Intent in= new Intent(this,by_voice_Arabic.class);
            startActivity(in);

        }
        if(wayE.isChecked()){
            Intent in= new Intent(this,by_voice.class);
            startActivity(in);

        }
    }}
