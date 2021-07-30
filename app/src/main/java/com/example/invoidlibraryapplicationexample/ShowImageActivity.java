package com.example.invoidlibraryapplicationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowImageActivity extends AppCompatActivity {

    ImageView imageView;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        imageView=findViewById(R.id.ShowFinalDocument);

        imageUrl = getIntent().getStringExtra("URL");
        Picasso.get().load(imageUrl).placeholder(R.drawable.maleone).fit().into(imageView);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ShowImageActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        imageUrl = getIntent().getStringExtra("URL");
        super.onStart();
    }
}