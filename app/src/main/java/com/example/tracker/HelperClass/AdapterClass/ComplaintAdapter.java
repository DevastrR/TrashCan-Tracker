package com.example.tracker.HelperClass.AdapterClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracker.R;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.AdapterComplaintViewHolder>{
ArrayList<ComplaintHelperClass> complaintlogged ;
    // bridge between design and java
    public ComplaintAdapter(ArrayList<ComplaintHelperClass> complaintlogged) {
        this.complaintlogged = complaintlogged;
    }

    @NonNull
    @Override
    public AdapterComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_card_design , parent , false ) ;
        AdapterComplaintViewHolder adapterComplaintViewHolder = new AdapterComplaintViewHolder(view) ;
        return adapterComplaintViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComplaintViewHolder holder, int position) {
        ComplaintHelperClass helperClass   = complaintlogged.get(position) ;
        holder.complaint_heading_section.setText(helperClass.getTitle());
        holder.complaint_description_section.setText(helperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return complaintlogged.size();
    }


    // holds design
    public static class AdapterComplaintViewHolder extends RecyclerView.ViewHolder{

        TextView complaint_heading_section , complaint_description_section ;

        public AdapterComplaintViewHolder(@NonNull View itemView) {
            super(itemView);
            complaint_heading_section = itemView.findViewById(R.id.complaint_heading) ;
            complaint_description_section = itemView.findViewById(R.id.complaint_desc);
        }
    }

}
