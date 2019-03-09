package com.example.vidyut;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeView extends RecyclerView.ViewHolder {

   // TextView text3;
    ImageView image;
    CardView layout;

    public HomeView(View view){
        super(view);
       // this.text3   = view.findViewById(R.id.text12);
        this.image  = view.findViewById(R.id.image1);
        this.layout = view.findViewById(R.id.cardview);
    }

//    @Override
//    public void onClick(View view) {
//        //mlistener.onClick(view,getAdapterPosition());
//        Intent i = new Intent();
//
//    }
}
