package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class vitalAndDrugs extends AppCompatActivity {
static int count_v=0,count_m=0;
TextView textView_count_v;
TextView textView_count_m;
Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vital_and_drugs);
         done = (Button)findViewById(R.id.button_done);


    }


    public void tovitalPage(View view) {
        if(3>count_v){
        Intent in=new Intent(this,sendVs.class);
        startActivity(in);}
        else {
            Toast.makeText(getApplicationContext(),"لا يمكن ارسال علامات حيويه ",Toast.LENGTH_SHORT).show();

        }

    }

    public void buttonDone(View view) {finish(); }

    public void cameClicked(View view) {
        Intent i = new Intent(vitalAndDrugs.this,take_photo.class);
        startActivity(i);
    }


    public void todrugPage(View view) {
        if(1>count_m){
        Intent in=new Intent(this,drugs_selection.class);
        startActivity(in);}
        else {
            Toast.makeText(getApplicationContext(),"لقد قمت بارسلت العلامات الحيويه فعلا",Toast.LENGTH_SHORT).show();

        }

    }

    public void finish(){
        count_v=0;
        count_m=0;
        new_case.newCase=null;
        new_case.patient=null;
        Toast.makeText(getApplicationContext(),"يتم انهاء الحالة",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(vitalAndDrugs.this,paramedic_home_page.class);
        startActivity(i);
    }
    //***********
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"لا يمكن العوده ، يجب انهاء الحالة",Toast.LENGTH_SHORT).show();
    }

}
