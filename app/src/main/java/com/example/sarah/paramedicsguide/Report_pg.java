package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Report_pg extends AppCompatActivity {

    TextView textView_report_name_pateint,textView_report_medicay_case,textView_report_paramedic_nam,textView_report_center,textView_report_medicin;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_pg);
        textView_report_name_pateint=(TextView)findViewById(R.id.textView_report_name_pateint);
        textView_report_medicay_case=(TextView)findViewById(R.id.textView_report_medicay_case);
        textView_report_paramedic_nam=(TextView)findViewById(R.id.textView_report_paramedic_nam);
        textView_report_center=(TextView)findViewById(R.id.textView_report_center);
        textView_report_medicin=(TextView)findViewById(R.id.textView_report_medicin);

        close=(Button)findViewById(R.id.image_viwe_close);

        Report report=Report_list_pg.selected_report;

        textView_report_name_pateint.setText(report.patient_name);
        textView_report_medicay_case.setText(report.patient_medical_case);
        textView_report_paramedic_nam.setText(report.paramedic_name);
        textView_report_center.setText(report.paramedic_center);
        textView_report_medicin.setText(report.medicine);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Report_pg.this, Hospital_home_page.class);
                startActivity(i);
            }
        });



    }
}
