package com.example.invoidlibraryapplicationexample;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.invoidlibrary.UploadDocumentActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class AddDetails extends AppCompatActivity {

    Button buttonUploadDocument;
    String imageUrl ;
    EditText name,mobile,email,aboutYourself;
    ProgressBar progressBar;
    CardView saveAllDetail;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final CollectionReference collectionReference = db.collection("Detail");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        this.getSupportActionBar().hide();

        buttonUploadDocument=findViewById(R.id.UploadIdCard);
        saveAllDetail=findViewById(R.id.SaveAllDetailCardView);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        aboutYourself=findViewById(R.id.aboutYourself);
        progressBar=findViewById(R.id.progressBarUploadAllDocument);


        progressBar.setVisibility(View.INVISIBLE);

        buttonUploadDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddDetails.this, UploadDocumentActivity.class);
                startActivity(intent);

            }
        });

        saveAllDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAllEmployeeDetail();
            }
        });
    }

    @Override
    protected void onResume() {
        SharedPreferences mySharedPreferences = getSharedPreferences("URLLINK", Context.MODE_PRIVATE);
        imageUrl = mySharedPreferences.getString("URL", "");
        if(!imageUrl.isEmpty()){
            buttonUploadDocument.setText("Document Uploaded");
            buttonUploadDocument.setClickable(false);
        }
//        Toast.makeText(AddDetails.this, imageUrl, Toast.LENGTH_SHORT).show();
//        Log.d("Kartik", "onClick: "+imageUrl);
        super.onResume();
    }

    private void saveAllEmployeeDetail() {
        final String nameString = name.getText().toString().trim();
        final String mobileString = mobile.getText().toString().trim();
        final String emailString = email.getText().toString().trim();
        final String aboutYourselfString = aboutYourself.getText().toString().trim();
        final String imageUrlString= imageUrl;

        progressBar.setVisibility(View.VISIBLE);

        if (!TextUtils.isEmpty(nameString) &&
                !TextUtils.isEmpty(mobileString) &&
                !TextUtils.isEmpty(emailString) &&
                !TextUtils.isEmpty(aboutYourselfString) &&!TextUtils.isEmpty(imageUrlString)) {

                            EmployeeDetail employee = new EmployeeDetail();
                            employee.setName(nameString);
                            employee.setMobile(mobileString);
                            employee.setEmail(emailString);
                            employee.setAboutYourself(aboutYourselfString);
                            employee.setImageURL(imageUrlString);
                            collectionReference.add(employee).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    SharedPreferences preferences =getSharedPreferences("URLLINK",Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.clear();
                                    editor.apply();
                                    Intent intent=new Intent(AddDetails.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "onFailure: " + e.getMessage());
                                    Toast.makeText(AddDetails.this,"Task Failed",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
        else {
            if (TextUtils.isEmpty(imageUrlString)) {
                Toast.makeText(AddDetails.this, "Add an Image", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }else if(TextUtils.isEmpty(nameString)){
                Toast.makeText(AddDetails.this,"Enter Name",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }else if (TextUtils.isEmpty(mobileString)){
                Toast.makeText(AddDetails.this,"Enter Mobile Number",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }else if(TextUtils.isEmpty(emailString)){
                Toast.makeText(AddDetails.this,"Enter Email",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }else if (TextUtils.isEmpty(aboutYourselfString)){
                Toast.makeText(AddDetails.this,"Enter Your Personal Detail",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(AddDetails.this,MainActivity.class);
        startActivity(intent);
        SharedPreferences preferences =getSharedPreferences("URLLINK",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }
}