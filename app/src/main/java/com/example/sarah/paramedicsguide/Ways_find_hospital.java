package com.example.sarah.paramedicsguide;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ways_find_hospital extends AppCompatActivity {
    static List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways_find_hospital);
        hospitalList= new ArrayList<Hospital>();
//CAER 1 DISPLAY
        LinearLayout l1 = (LinearLayout)findViewById(R.id.layout_desplay);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Filtering.class);
                startActivity(i);
            }
        });
/*
        ImageView m1 = (ImageView) findViewById(R.id.imageView_dsplay_ic);
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Filtering.class);
                startActivity(i);
            }
        });

        TextView t1 = (TextView) findViewById(R.id.textView_desplay);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Filtering.class);
                startActivity(i);
            }
        });*/

//CARD 2 SEARCH
        LinearLayout l2 = (LinearLayout)findViewById(R.id.layout_search);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Search.class);
                startActivity(i);
            }
        });

  /*      ImageView m2 = (ImageView) findViewById(R.id.imageView_search_ic);
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Search.class);
                startActivity(i);
            }
        });

        TextView t2 = (TextView) findViewById(R.id.textView_search);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ways_find_hospital.this,Search.class);
                startActivity(i);
            }
        });*/
    }
    //***********
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
    }
}
