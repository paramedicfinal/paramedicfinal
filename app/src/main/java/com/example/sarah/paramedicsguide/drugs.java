package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class drugs extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;

    private CheckBox ch1,ch2,ch3,ch4,ch5,ch6,ch7;
    private Button bt;
    private ArrayList<String> mediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs2);

         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Drugs");




              /*  Medications drugs=new Medications( c1, c2, c3, c4, c5, c6, c7);
                String idChild=myRef.push().getKey();
                myRef.child(idChild).setValue(drugs);
                Toast.makeText(getApplicationContext(),"تم ارسال الادويه",Toast.LENGTH_SHORT).show();
*/







    }

   /* public void sendDrugs(View view) {



























        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);
        ch4=(CheckBox)findViewById(R.id.checkBox4);
        ch5=(CheckBox)findViewById(R.id.checkBox5);
        ch6=(CheckBox)findViewById(R.id.checkBox6);
        ch7=(CheckBox)findViewById(R.id.checkBox7);

        String c1=ch1.getText().toString().trim();
        String c2=ch2.getText().toString().trim();
        String c3=ch3.getText().toString().trim();
        String c4=ch4.getText().toString().trim();
        String c5=ch5.getText().toString().trim();
        String c6=ch6.getText().toString().trim();
        String c7=ch7.getText().toString().trim();

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
        });*/



    public void sendDrugs(View view) {

        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3=(CheckBox)findViewById(R.id.checkBox3);
        ch4=(CheckBox)findViewById(R.id.checkBox4);
        ch5=(CheckBox)findViewById(R.id.checkBox5);
        ch6=(CheckBox)findViewById(R.id.checkBox6);
        ch7=(CheckBox)findViewById(R.id.checkBox7);

        bt=(Button)findViewById(R.id.pg4_1_buttonsend);

        String c1=ch1.getText().toString().trim();
        String c2=ch2.getText().toString().trim();
        String c3=ch3.getText().toString().trim();
        String c4=ch4.getText().toString().trim();
        String c5=ch5.getText().toString().trim();
        String c6=ch6.getText().toString().trim();
        String c7=ch7.getText().toString().trim();
        String idChild;

        if(ch1.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c1);
        }

        if(ch2.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c2);
        }
        if(ch3.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c3);
        }
        if(ch4.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c4);
        }
        if(ch5.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c5);
        }
        if(ch6.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c6);
        }
        if(ch7.isChecked()){

            idChild=myRef.push().getKey();
            myRef.child(idChild).setValue(c7);
        }

        Intent in = new Intent(this, vitalAndDrugs.class);
        startActivity(in);

    }
}

