package com.amrita.vidyut;

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
        holder.description.setText(desc);
        final String about=contests.get(position).getAbout();
        final String venue=contests.get(position).getVenue();
        final String department=contests.get(position).getDept();
        final String rules=contests.get(position).getRules();
        final String prereq=contests.get(position).getPrereq();
        final String d1beg=contests.get(position).getD1beg();
        final String d2beg=contests.get(position).getD2beg();
        final String d3beg=contests.get(position).getD3beg();
        final String d1end=contests.get(position).getD1end();
        final String d2end=contests.get(position).getD2end();
        final String d3end=contests.get(position).getD3end();
        final int p1=contests.get(position).getP1();
        final int p2=contests.get(position).getP2();
        final int p3=contests.get(position).getP3();
        String url="https://vidyut.amrita.edu/static/images/contests/"+id+"a.jpg";
        Glide.with(viewGroup.getContext()).load(Uri.parse(url)).into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("id",id);
                b.putInt("flag",1);
                Intent intent = new Intent(viewGroup.getContext(),Display.class);
                Contests workshops=new Contests(id,title,department,about,p1,p2,p3,venue,fee,rules,prereq,d1beg,d2beg,d3beg,d1end,d2end,d3end);
                intent.putExtras(b);
                intent.putExtra("list",workshops);
                startActivity(viewGroup.getContext(),intent,null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contests.size();
    }
}
