package com.example.vidyut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class ContestAdapter extends RecyclerView.Adapter<ChildViewHolder> {

    List<Contests> contests;
    ViewGroup viewGroup;

    public ContestAdapter(List<Contests> contest) {
        this.contests=contest;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        ChildViewHolder ch=new ChildViewHolder(view);
        this.viewGroup = parent;
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        String title=contests.get(position).getTitle();
        String desc=contests.get(position).getShor();
        int fee=contests.get(position).getFee();
        int id=contests.get(position).getId();
        holder.textView.setText(title);
    //    holder.availability.setVisibility(View.GONE);
        holder.description.setText(desc);
       // String see="Rs:"+String.valueOf(fee);
       // holder.availability.setText(see);
        String url="https://vidyut.amrita.edu/static/images/contests/"+id+"a.jpg";
        Glide.with(viewGroup.getContext()).load(Uri.parse(url)).into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id",id);
                b.putInt("flag",1);
                Intent intent = new Intent(viewGroup.getContext(),Display.class);
                intent.putExtras(b);
                startActivity(viewGroup.getContext(),intent,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contests.size();
    }
}
