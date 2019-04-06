package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
    @Override
    public void onBackPressed() {
        paramedic_login_page.user=null;
        Intent i = new Intent(paramedic_home_page.this,paramedic_login_page.class);
        startActivity(i);
    }
}
