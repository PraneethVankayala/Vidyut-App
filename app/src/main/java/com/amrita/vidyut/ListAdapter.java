package com.amrita.vidyut;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.amrita.vidyut.NotificationData;
import com.amrita.vidyut.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.NotificationViewHolder> {

    Activity context;
    List<NotificationData> items;


    public ListAdapter(Activity mainActivity, ArrayList<NotificationData> dataArrayList) {

        this.context = mainActivity;
        this.items = dataArrayList;
    }



    @Override
    public ListAdapter.NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.list_data,parent,false);
        NotificationViewHolder ch=new NotificationViewHolder(view);
        return ch;
    }



    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int i) {


        NotificationData productItems = items.get(i);

        long time =  productItems.getTimeago();
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss.SSS");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        holder.name.setText(productItems.getText());
        holder.description.setText(productItems.getDesc());
        Glide.with(context).load(productItems.getImage()).into(holder.image);
        holder.time.setText(formatter.format(calendar.getTime()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder{

        TextView description, name , time;
        ImageView image;



        public NotificationViewHolder(View convertView){
            super(convertView);
            // this.text3   = view.findViewById(R.id.text12);
            name = (TextView) convertView.findViewById(R.id.title);
            description = (TextView) convertView.findViewById(R.id.description);
            image = (ImageView) convertView.findViewById(R.id.image);
            time = (TextView) convertView.findViewById(R.id.time) ;
        }

    }




}
