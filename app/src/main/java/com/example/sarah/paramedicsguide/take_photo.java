package com.example.sarah.paramedicsguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.IOException;

public class take_photo extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    DatabaseReference myRef = database.getReference("Patient");
    private StorageReference mStorageRef;
    Button mUploadBtn;
    Button bsend;
    ImageView mImageView;
    private static final int CAMERA_REQUEST_CODE = 1;
    private ProgressDialog progressDialog;
    Uri uri;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mUploadBtn = (Button)findViewById(R.id.btnImage);
        bsend=(Button)findViewById(R.id.sendImage);
        mImageView = (ImageView)findViewById(R.id.imageView2);
        progressDialog=new ProgressDialog(this);

        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK)
        {
            uri =data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                mImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bsend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progressDialog.setMessage("يتم ارسال الصورة");
                    progressDialog.show();



                    final StorageReference filepath = mStorageRef.child("Photos").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(take_photo.this,"تم ارسال الصورة بنجاح ",Toast.LENGTH_LONG).show();

                            new_case.patient.setImageUri(filepath);

                        }
                    });
                }
            });


        }
    }
    public void buttonSend(View view) {

    }
}

