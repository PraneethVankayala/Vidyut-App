package com.amrita.vidyut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class ChildAdapter extends RecyclerView.Adapter<ChildViewHolder> {
    List<Workshops> workshops;
    ViewGroup viewGroup;
    int s;
    int flag;
    public ChildAdapter(List<Workshops> workshops) {
        this.workshops=workshops;
        this.s=s;
    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        ChildViewHolder ch=new ChildViewHolder(view);
        this.viewGroup = parent;
        return ch;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder holder, int position) {

            String title=workshops.get(position).getTitle();
            String desc=workshops.get(position).getSh();
            int fee=workshops.get(position).getFee();
            int id=workshops.get(position).getId();
                holder.textView.setText(title);
                holder.description.setText(desc);
        final String about=workshops.get(position).getAbout();
        final String venue=workshops.get(position).getVenue();
        final int department=workshops.get(position).getDepartment();
        final String rules=workshops.get(position).getRules();
        final String prereq=workshops.get(position).getPrereq();
        final String d1beg=workshops.get(position).getD1big();
        final String d2beg=workshops.get(position).getD2beg();
        final String d3beg=workshops.get(position).getD3beg();
        final String d1end=workshops.get(position).getD1end();
        final String d2end=workshops.get(position).getD2end();
        final String d3end=workshops.get(position).getD3end();
                String url="https://vidyut.amrita.edu/static/images/workshops/"+id+"a.jpg";
                Glide.with(viewGroup.getContext()).load(Uri.parse(url)).into(holder.imageView);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putInt("id",id);
                        b.putInt("flag",0);
                        Intent intent = new Intent(viewGroup.getContext(),Display.class);
                        Workshops workshops=new Workshops(id,title,about,venue,department,fee,rules,prereq,d1beg,d2beg,d3beg,d1end,d2end,d3end);
                        intent.putExtras(b);
                        intent.putExtra("list",workshops);
                        startActivity(viewGroup.getContext(),intent,null);
                    }
                });



    }

    @Override
    public int getItemCount() {
        return workshops.size();
    }
}
