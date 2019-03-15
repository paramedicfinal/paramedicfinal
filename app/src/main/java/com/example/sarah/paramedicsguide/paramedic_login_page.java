package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class paramedic_login_page extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    static Paramedic user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paramedic_login_page);


        findViewById(R.id.textViewForgotPassword).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
    }
///////////////////////
    public void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if (email.isEmpty()) {
            editTextEmail.setError("إملأ خانة البريد الإلكتروني");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("إملأ خانة البريد الإلكتروني");
            editTextPassword.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("أدخل بريد إلكتروني صالح");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("يجب أن تكون كلمة المرور مكونة من 6 خانات فأكثر");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    getParamedicByQuery(email);
                }else if(task.getException().getMessage().equals("There is no user record corresponding to this identifier. The user may have been deleted.")){
                    Toast.makeText(getApplicationContext(),"هذا المستخدم غير مسجل",Toast.LENGTH_SHORT).show();
                }else if(task.getException().getMessage().equals("The password is invalid or the user does not have a password.")){
                    Toast.makeText(getApplicationContext(),"كلمة المرور غير صحيحة",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //////////////////////////////////
    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.textViewForgotPassword:
                Intent intent2 = new Intent(paramedic_login_page.this,forgot_password_page.class);
                startActivity(intent2);
                break;

            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
/////////////////////////////////
    public  void getParamedicByQuery(String email){
        Query query = FirebaseDatabase.getInstance().getReference("Paramedic")
                .orderByChild("paramedicEmail").equalTo(email);
        Log.v("lll22",email);
        query.addListenerForSingleValueEvent(valueEventListener);
        Toast.makeText(getApplicationContext(),"تم تسجيل الدخول بنجاح",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(paramedic_login_page.this,paramedic_home_page.class);
        startActivity(intent);
    }
//////////////////////////
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    user = snapshot.getValue(Paramedic.class);
                    Log.v("lll22",user.paramedicName);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}

