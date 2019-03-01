package com.example.vidyut;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeView> {

    private List<Home> home;

    private Context context;

    public HomeAdapter(List<Home> home,Context context){
        this.context = context;
        this.home = home;
    }

    @Override
    public HomeView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home,parent,false);
        HomeView h = new HomeView(view);
        return h;
    }

    @Override
    public void onBindViewHolder(HomeView holder, final int position) {
        holder.image.setImageResource(home.get(position).getImage());
        holder.text3.setText(home.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return home.size();
    }
}
