package com.example.sarah.paramedicsguide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Hospital_home_page extends AppCompatActivity {
static List<NewCase> list_newCase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_home_page);
        Log.v("lll22",hospital_login_page.user.name);
        getNewCaseByQuery();


    }
    //******
    public  void getNewCaseByQuery(){

        reference = FirebaseDatabase.getInstance().getReference().child("NewCase");

        Query query = reference.orderByChild("name_hospital").equalTo(hospital_login_page.user.name);
        Log.v("lll22",hospital_login_page.user.name);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    NewCase module = snapshot.getValue(NewCase.class);
                    if(module.recive==false){
                    list_newCase.add(module) ;
                    Log.v("lll",module.getPatient().name);
                    module.recive=true;
                    reference.child(snapshot.getKey()).setValue(module);
                    String mas = module.getName_paramedic()+"تم ارسال طلب من المسعف ";
                    AlertDialog.Builder builder = new AlertDialog.Builder(Hospital_home_page.this);
                    builder.setTitle("مريض جديد").setMessage(mas).setPositiveButton("حسنا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });




                    }
                }


                //address for the next page will move to
              //  Intent i = new Intent(Filtering.this, MapsActivity.class);
              //  i.putExtra("category" , rb.getText());

               // startActivity(i);
                //   }
            }
            @Override
            public  void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
