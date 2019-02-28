package com.example.vidyut;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeView> {

    private List<Home> home;
    ViewListener mlistener;

    private Context context;

    public HomeAdapter(List<Home> home,Context context,ViewListener mlistener){
        this.mlistener = mlistener ;
        this.context = context;
        this.home = home;
    }

    @Override
    public HomeView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home,parent,false);
        HomeView h = new HomeView(view,mlistener);
        return h;
    }

    @Override
    public void onBindViewHolder(HomeView holder, final int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mlistener.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return home.size();
    }
}
