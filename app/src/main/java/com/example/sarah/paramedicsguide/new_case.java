package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_case extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Patient");
    String sex="";
    String medicalState="";
    String bedType ="";
    EditText name ;
    String name2="";
    EditText NID;
    Button button;
    String NID2;
    Patient Patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_case);
    }


    public void addnewcase(View view) {
        name= (EditText) findViewById(R.id.pg2_2_name);
        name2 = name.getText().toString();
        NID= (EditText) findViewById(R.id.pg2_2_id);
        button = (Button) findViewById(R.id.pg2_2button);
         NID2=NID.getText().toString();

        RadioButton male =(RadioButton)findViewById(R.id.pg2_2_male);
        if(male.isChecked()){sex="male";}
        else{sex="female";}

        RadioButton danger= (RadioButton) findViewById(R.id.pg2_2_danger);
        if(danger.isChecked()){medicalState="danger";}
        else{medicalState="notdanger";}
         Patient = new Patient(NID2, name2, sex,  medicalState);
        Spinner spinner = (Spinner) findViewById(R.id.pg2_2_bed_spinner);
//Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arr_tybed, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        Toast.makeText(getApplicationContext(),"تم اضافةالمريض بنجاح",Toast.LENGTH_SHORT).show();
        Intent in =new Intent(this,vitalAndDrugs.class);
        startActivity(in);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
// An item was selected. You can retrieve the selected item using
        bedType=  parent.getItemAtPosition(position).toString();
        Patient.setBedType(bedType);
        myRef.push().setValue(Patient);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
