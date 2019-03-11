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

public class RegistrationAdapter extends RecyclerView.Adapter<ChildViewHolder> {
    List<Registration> workshopList;
    ViewGroup viewGroup;
    public RegistrationAdapter(List<Registration> wokshopList) {
        this.workshopList=wokshopList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list,viewGroup,false);
        ChildViewHolder ch=new ChildViewHolder(view);
        this.viewGroup = viewGroup;
        return ch;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {
        String title=workshopList.get(position).getTitle();
        String desc=workshopList.get(position).getTime();
    //    int fee=workshopList.get(position).getFee();
        int id=workshopList.get(position).getEid();
        childViewHolder.textView.setText(title);
        childViewHolder.description.setText(desc);
    //     String see="Rs:"+String.valueOf(fee);
   //     childViewHolder.availability.setText(see);
        String url="https://vidyut.amrita.edu/static/images/workshops/"+id+"a.jpg";
        Glide.with(viewGroup.getContext()).load(Uri.parse(url)).into(childViewHolder.imageView);
        childViewHolder.layout.setOnClickListener(new View.OnClickListener() {
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
        return workshopList.size();
    }
}
