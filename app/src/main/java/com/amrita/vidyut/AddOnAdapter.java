package com.amrita.vidyut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class AddOnAdapter extends RecyclerView.Adapter<ChildViewHolder> {
    List<MyOrder> workshopList;
    ViewGroup viewGroup;
    public AddOnAdapter(List<MyOrder> wokshopList) {
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
        String title=workshopList.get(position).getPurchase();
        String desc=workshopList.get(position).getPurtime();
       // int fee=workshopList.get(position).getTotal();
        childViewHolder.textView.setText(title);
        childViewHolder.description.setText(desc);
      //  String see="Rs:"+String.valueOf(fee);
     //   childViewHolder.availability.setText(see);
        childViewHolder.imageView.setImageResource(R.drawable.addons1);
        childViewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }
}
