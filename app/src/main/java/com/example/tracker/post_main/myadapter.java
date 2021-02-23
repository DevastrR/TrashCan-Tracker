package com.example.tracker.post_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tracker.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<Upload , myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<Upload> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Upload upload) {

       // holder.textView.setText(upload.getName());
        holder.textViewHeading.setText(upload.getHeading());
        holder.textViewComplaint.setText(upload.getDescription());


    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent,false) ;
        return new myviewholder(view) ;
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView textViewHeading ;
        TextView textViewComplaint ;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textViewHeading = (TextView) itemView.findViewById(R.id.textViewHeading) ;
            textViewComplaint = (TextView) itemView.findViewById(R.id.textViewDescription) ;
        }
    }
}
