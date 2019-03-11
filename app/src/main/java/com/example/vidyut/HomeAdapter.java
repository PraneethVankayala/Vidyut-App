package com.example.vidyut;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


public class HomeAdapter extends RecyclerView.Adapter<HomeView> {

    private List<Home> home;
    private ViewGroup viewGroup;

    public HomeAdapter(List<Home> home){
        this.home = home;
    }

    @Override
    public HomeView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home,parent,false);
        HomeView h = new HomeView(view);
        this.viewGroup = parent;
        return h;
    }

    @Override
    public void onBindViewHolder(HomeView holder, final int position) {
        holder.image.setImageResource(home.get(position).getImage());
       // holder.text3.setText(home.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b =  new Bundle();
                String name = home.get(position).getName();
                b.putString("name",name);
                if(name.equals("Schedule")){
                   Intent intent = new Intent(viewGroup.getContext(),SchedulerActivity.class);
                   startActivity(viewGroup.getContext(),intent,null);
                }else{
                Intent intent=new Intent(viewGroup.getContext(),ChildActivity.class);
                intent.putExtras(b);
                startActivity(viewGroup.getContext(),intent,null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return home.size();
    }
}
