package com.example.sarah.paramedicsguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_hospitals_page extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    DatabaseReference databasehospital;

    EditText editTextName;
    EditText editTextID;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRepeatedPassword;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospitals_page);
        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextID = (EditText)findViewById(R.id.editTextID);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextRepeatedPassword = (EditText)findViewById(R.id.editTextRepeatedPassword);
        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        databasehospital = FirebaseDatabase.getInstance().getReference("Hospital");
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
    }

    private void registerHospital(){
        String name = editTextName.getText().toString().trim();
        String id = editTextID.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String repeatedPassword = editTextRepeatedPassword.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("إملأ خانة الاسم");
            editTextName.requestFocus();
            return;
        }

        if (id.isEmpty()) {
            editTextID.setError("إملأ خانة الرقم التعريفي");
            editTextID.requestFocus();
            return;
        }

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

        if (repeatedPassword.isEmpty()) {
            editTextRepeatedPassword.setError("إملأ خانة البريد الإلكتروني");
            editTextRepeatedPassword.requestFocus();
            return;
        }

        if (!name.matches("[ا-ي]+")) {
            editTextName.setError("يجب أن يكون الإسم باللغة العربية بلا رموز او أرقام ");
            editTextName.requestFocus();
            return;
        }

        if (!id.matches("[0-9]+")) {
            editTextID.setError("يجب أن يكون الرقم التعريفي مكون من أرقام فقط");
            editTextID.requestFocus();
            return;
        }

        if (id.length() != 7) {
            editTextID.setError("يجب أن يكون الرقم التعريفي مكون من 7 خانات");
            editTextID.requestFocus();
            return;
        }

        if (!password.equals(repeatedPassword)) {
            editTextPassword.setError("كلمة المرور غير متطابقة");
            editTextPassword.requestFocus();
            return;
        }

//------------------------------------------------------------------------------

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

//--------------------------------------------------------------------------------
        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"تم التسجيل بنجاح",Toast.LENGTH_SHORT).show();

                }else{
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"هذا الحساب مسجل من قبل",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Hospital hospital = new Hospital(name, id, email, password);
        databasehospital.push().setValue(hospital);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerHospital();
                break;



        }

    }
}
