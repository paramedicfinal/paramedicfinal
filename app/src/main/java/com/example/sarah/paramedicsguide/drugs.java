package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class drugs extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Drugs");

    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private Button bt;
    private ArrayList<String> mediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs2);

    }

    public void sendDrugs(View view) {
        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);
        ch4=(CheckBox)findViewById(R.id.checkBox4);
        ch5=(CheckBox)findViewById(R.id.checkBox5);
        ch6=(CheckBox)findViewById(R.id.checkBox6);
        ch7=(CheckBox)findViewById(R.id.checkBox7);

        mediction=new ArrayList<>();
        bt=(Button)findViewById(R.id.pg4_1_buttonsend);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch1.isChecked()){
                  //  mediction.add("الأتروبين");
                myRef.push().setValue("الأتروبين");
                }

            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch2.isChecked()){
                    //mediction.add("الأسبرين");
                    myRef.push().setValue("الأسبرين");
                }
            }
        });
        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch3.isChecked()){
                    //mediction.add("جيلاتين سكر تحت اللسان");
                    myRef.push().setValue("جيلاتين سكر تحت اللسان");
                }
            }
        });
        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch4.isChecked()){
                    mediction.add("الدكستروز 50%");
                    myRef.push().setValue("الدكستروز 50%");
                }

            }
        });
        ch5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch5.isChecked()){
                    //mediction.add("النتروغليسيرين");
                    myRef.push().setValue("النتروغليسيرين");

                }

            }
        });

        ch6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch6.isChecked()){
                   // mediction.add("الأدرينالين");
                    myRef.push().setValue("الأدرينالين");

                }
            }
        });

        ch7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ch7.isChecked()){
                   // mediction.add("سالبوتامول");
                    myRef.push().setValue("سالبوتامول");

                }
            }
        });

        Intent intent = new Intent(drugs.this,vitalAndDrugs.class);
        startActivity(intent);
    }
}
