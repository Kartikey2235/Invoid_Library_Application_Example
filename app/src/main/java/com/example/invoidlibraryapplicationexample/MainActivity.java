package com.example.invoidlibraryapplicationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.invoidlibrary.UploadDocumentActivity;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
    public FloatingActionButton buttonAddDetail;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerAdapter;

    private CollectionReference collectionReference=db.collection("Detail");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().hide();

        show();

        buttonAddDetail=findViewById(R.id.fab);
        buttonAddDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddDetails.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void show(){
        Query query=collectionReference.orderBy("name");
        FirestoreRecyclerOptions<EmployeeDetail> options=new FirestoreRecyclerOptions.Builder<EmployeeDetail>().setQuery(query,EmployeeDetail.class).build();
        recyclerAdapter =new RecyclerViewAdapter(options,this);
        recyclerView=findViewById(R.id.recyclerViewListShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(recyclerAdapter!=null){
            recyclerAdapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(recyclerAdapter!=null){
            recyclerAdapter.stopListening();
        }
    }
}