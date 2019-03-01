package com.example.vidyut;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class HomeView extends RecyclerView.ViewHolder{

    ImageView image;
    CardView layout;

    public HomeView(View view){
        super(view);
        this.image = view.findViewById(R.id.image);
        this.layout = view.findViewById(R.id.cardview);
    }

}
