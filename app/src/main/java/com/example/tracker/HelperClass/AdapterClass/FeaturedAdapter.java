package com.example.tracker.HelperClass.AdapterClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracker.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter <FeaturedAdapter.FeaturedViewHolder>{
    // work as bridge between values and design
    // stores values in adapter design
    ArrayList<FeaturedHelperClass> featuredLocations ;
    // while calling it from main activity we have to pass Arraylist to this method for getting the data
    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design , parent ,  false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view) ;
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position) ;
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return featuredLocations.size() ;
    }

    // holds design
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image ;
        TextView title , desc ;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.card_image_1) ;
            title = itemView.findViewById(R.id.card_image_1_heading) ;
            desc = itemView.findViewById(R.id.card_image_1_brief) ;



        }
    };
}
