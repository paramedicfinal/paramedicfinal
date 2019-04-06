package com.example.sarah.paramedicsguide;



import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class take_photo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        btnpic = (ImageView) findViewById(R.id.button);
        imgTakenPic = (ImageView)findViewById(R.id.imageView);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
        mstorage= FirebaseStorage.getInstance().getReference();
        imageView_send_photo=(ImageView)findViewById(R.id.imageView_send_photo);
    }
    ImageView btnpic;
    ImageView imageView_send_photo;
    ImageView imgTakenPic;
    private static final int CAM_REQUEST=1313;
    StorageReference mstorage;
    Uri uri;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            uri= data.getData();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakenPic.setImageBitmap(bitmap);


            uploadePhoto();
        }
    }

    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }

    public void uploadePhoto(){
        imageView_send_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageReference filepath = mstorage.child("Photos").child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(take_photo.this,"uploading finsh ",Toast.LENGTH_LONG).show();
                        }
                });
            }
        });



    }

}
