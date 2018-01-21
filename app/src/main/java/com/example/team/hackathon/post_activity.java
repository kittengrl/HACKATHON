package com.example.team.hackathon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class post_activity extends AppCompatActivity {
    EditText mTittle, mDesc;
    Button mSubmitbtn;
    ImageButton mImgbtn;
    private static final int GALLERY_REQUEST = 1;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private Uri mImagUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activity);
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mTittle = findViewById(R.id.title);
        mDesc = findViewById(R.id.desc);
        mImgbtn = findViewById(R.id.imgbtn);
        mSubmitbtn = findViewById(R.id.submitbtn);
        mari();
    }

    private void mari() {
        mImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });
        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                final String title_val = mTittle.getText().toString();
                final String des_val = mDesc.getText().toString();
                if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(des_val) && mImagUri != null){
                    StorageReference filepath = mStorage.child("Blog_Images").child(mImagUri.getLastPathSegment());
                    filepath.putFile(mImagUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downloadUri = taskSnapshot.getDownloadUrl();
                            DatabaseReference newpost = mDatabase.push();
                            newpost.child("title").setValue(title_val);
                            newpost.child("desc").setValue(des_val);
                            newpost.child("image").setValue(downloadUri.toString());
                        }
                    });
                    Toast.makeText(getApplicationContext(),"Posted",Toast.LENGTH_LONG).show();
                }}
                catch(Exception e){
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImagUri = data.getData();
            mImgbtn.setImageURI(mImagUri);
        }
    }
}
