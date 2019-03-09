package com.example.vidyut;

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
            String dept=workshops.get(position).getDepartment();
                holder.textView.setText(title);
                holder.description.setText(desc);
                holder.availability.setVisibility(View.GONE);
//                String see="Rs:"+String.valueOf(fee);
//                holder.availability.setText(see);
                String url="https://devhub.amblygon.org/static/images/workshops/"+id+"a.jpg";
                Glide.with(viewGroup.getContext()).load(Uri.parse(url)).into(holder.imageView);
                holder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putInt("id",id);
                        b.putInt("flag",0);
                        Intent intent = new Intent(viewGroup.getContext(),Display.class);
                        intent.putExtras(b);
                        startActivity(viewGroup.getContext(),intent,null);
                    }
                });



    }

    @Override
    public int getItemCount() {
        return workshops.size();
    }
}
