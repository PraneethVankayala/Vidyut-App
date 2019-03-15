package com.amrita.vidyut;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amrita.vidyut.Schedule;
import com.amrita.vidyut.R;
import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleViewHolder;

public class ScheduleCell extends SimpleCell<Schedule,ScheduleCell.ViewHolder> {


    public ScheduleCell(@NonNull Schedule item) {
        super(item);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_schedules;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull View view) {
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Context context, Object o) {

        viewHolder.tittle.setText(getItem().getTittle());
        viewHolder.dec.setText(getItem().getDesc());
        viewHolder.date.setText(getItem().getDate());
        viewHolder.time.setText(getItem().getTime());
        String venue = "Venue : "+getItem().getVenue();
        viewHolder.venue.setText(venue);


    }

    static class ViewHolder extends SimpleViewHolder {

        private TextView tittle, dec, date, time,venue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tittle = itemView.findViewById(R.id.schedule_tittle);
            dec = itemView.findViewById(R.id.schedule_short);
            date = itemView.findViewById(R.id.schedule_date);
            time = itemView.findViewById(R.id.schedule_time);
            venue = itemView.findViewById(R.id.schedule_venue);
        }


    }
}