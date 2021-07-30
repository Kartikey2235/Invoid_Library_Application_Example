package com.example.invoidlibraryapplicationexample;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends FirestoreRecyclerAdapter<EmployeeDetail,RecyclerViewAdapter.ViewHolder> {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Context context;
    public RecyclerViewAdapter(@NonNull FirestoreRecyclerOptions<EmployeeDetail> options, Context context) {
        super(options);
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull EmployeeDetail model) {
        holder.name.setText(model.getName());
        holder.mobile.setText(model.getMobile());
        holder.email.setText(model.getEmail());
        String imageUrl = model.getImageURL();

        holder.imageShowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ShowImageActivity.class);
                intent.putExtra("URL",imageUrl);
                context.startActivity(intent);
            }
        });

        if (position % 2 == 0){
            holder.recyclerImageView.setImageResource(R.drawable.maletwo);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView mobile;
        public TextView email;
        public ImageView imageShowIcon;
        public ImageView recyclerImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.RecyclerviewName);
            mobile = itemView.findViewById(R.id.RecyclerviewMobile);
            email = itemView.findViewById(R.id.RecyclerviewEmail);
            imageShowIcon = itemView.findViewById(R.id.ImageShowDocument);
            recyclerImageView=itemView.findViewById(R.id.recyclerviewImageView);

        }
    }
}