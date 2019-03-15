package com.amrita.vidyut;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.PrintStreamPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaychang.srv.SimpleCell;
import com.jaychang.srv.SimpleRecyclerView;
import com.jaychang.srv.decoration.SectionHeaderProvider;
import com.jaychang.srv.decoration.SimpleSectionHeaderProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SchedulerActivity extends Fragment {


    private List<Schedule> scheduleList;
    private SimpleRecyclerView simpleRecyclerView;
    Typeface typeface;
    private static final String KEY_title = "Title";
    private static final String KEY_start = "Start_Time";
    private static final String KEY_end = "End_Time";
    private static final String KEY_cat = "Category";
    private static final String KEY_day = "Day";
    private static final String KEY_venue = "Venue";
    private Dialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_scheduler, container,false);
        TextView textView=new TextView(getActivity().getApplicationContext());
        typeface = ResourcesCompat.getFont(getActivity(), R.font.frontage_bold);
        textView.setTypeface(typeface);
        textView.setText("Schedule");
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        ActionBar ab = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(ab!=null) {
            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            ab.setCustomView(textView);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
            ab.setBackgroundDrawable(colorDrawable);
            ab.setDisplayShowTitleEnabled(false);
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
            ab.setHomeAsUpIndicator(upArrow);
        }
        progessdialog(getActivity());
        simpleRecyclerView = view.findViewById(R.id.schedule_recycler);
        scheduleList = new ArrayList<>();
        new GetDataTask().execute();
        return view;
    }

    private void progessdialog(Context mcontext) {
        progress = new Dialog(mcontext);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setContentView(R.layout.dialog_loading);
        ProgressBar bar = progress.findViewById(R.id.new_post_progress);
        bar.setVisibility(View.VISIBLE);
        progress.show();
    }

    private void addRecyclerViewHearder(){
        SectionHeaderProvider<Schedule> scheduleSectionHeaderProvider = new SimpleSectionHeaderProvider<Schedule>() {
            @NonNull
            @Override
            public View getSectionHeaderView(@NonNull Schedule schedule, int i) {
                View view;
                view =  LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.item_display_header,null,false);
                TextView textView = view.findViewById(R.id.headerTxt);
                if(schedule.getDate().contains("15")){
                    textView.setText("Day 1");
                }else if(schedule.getDate().contains("16")){
                    textView.setText("Day 2");
                }else if(schedule.getDate().contains("17")){
                    textView.setText("Day 3");
                }
                return view;
            }

            @Override
            public boolean isSameSection(@NonNull Schedule schedule, @NonNull Schedule t1) {
                return schedule.getDate().equals(t1.getDate());
            }

            @Override
            public boolean isSticky() {
                return true;
            }
        };

        simpleRecyclerView.setSectionHeader(scheduleSectionHeaderProvider);
    }

    private void binddata(){
        List<Schedule> schedules = scheduleList;
        //CUSTOM SORT ACCORDING TO CATEGORIES
        Collections.sort(schedules, new Comparator<Schedule>(){
            public int compare(Schedule schedule, Schedule nextschedule) {
                return schedule.getTimeId() - nextschedule.getTimeId();
            }
        });
        List<ScheduleCell> cells = new ArrayList<>();
        for (Schedule galaxy : schedules) {
            ScheduleCell cell = new ScheduleCell(galaxy);
            // There are two default cell listeners: OnCellClickListener<CELL, VH, T> and OnCellLongClickListener<CELL, VH, T>
            cell.setOnCellClickListener(new SimpleCell.OnCellClickListener() {
                @Override
                public void onCellClicked(@NonNull Object o) {

                }
            });

            cells.add(cell);
        }

        simpleRecyclerView.addCells(cells);
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {

        int jIndex;


        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            JSONObject jsonObject = JSONpraser.getDataFromWeb();
            try {
                if (jsonObject != null) {
                    if(jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("Sheet1");
                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {
                                Timer eight = new Timer("10am",0);
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                if(innerObject.getString(KEY_title)!=null&&innerObject.getString(KEY_cat)!=null&&innerObject.getString(KEY_day)!=null&&innerObject.getString(KEY_venue)!=null)
                                {     Log.d("8289856057",String.valueOf(innerObject.getInt(KEY_start)));
                                    scheduleList.add(new Schedule(innerObject.getString(KEY_title),innerObject.getString(KEY_cat),innerObject.getString(KEY_day),innerObject.getInt(KEY_start)+" to "+innerObject.getInt(KEY_end),eight,innerObject.getString(KEY_venue)));
                                 }
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if(scheduleList.size() > 0) {
                Log.d("8289856057","here");
                addRecyclerViewHearder();
                binddata();
            }

            if(progress!=null){
                progress.dismiss();
            }

        }
    }
}
