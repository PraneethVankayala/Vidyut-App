package com.example.vidyut;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class HomeView extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ViewListener mlistener;
    private ImageView image;
    private CardView layout;

    public HomeView(View view, ViewListener listener){
        super(view);
        this.mlistener = listener;
        this.image = view.findViewById(R.id.image);
        this.layout = view.findViewById(R.id.cardview);
        view.setOnClickListener(this);
    }
    public ImageView getImage() {
        return image;
    }

    public CardView getView(){ return layout;}

    @Override
    public void onClick(View v) {
        mlistener.onClick(v,getAdapterPosition());
    }

}
