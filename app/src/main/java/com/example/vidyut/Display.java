package com.example.vidyut;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Display extends AppCompatActivity {

    ApiManager apiManager = new ApiManager();
    int id;
    ProgressBar progressBar;
    TextView txtView1,txtView2,txtView3,txtView4,txtView5,txtView6,txtView7,txtView8,txtView9,txtView10,txtView11,txtView12,txtView13,txtView14,txtView15;
    View vi;
    String dept[] = {"Select Department","CSE","ECE", "ME", "Physics", "Chemistry", "English", "Biotech","BUG", "Commerce and Management", "Civil", "EEE", "Gaming", "Maths", "Others"};
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
//        ActionBar ab = getSupportActionBar();
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
//        ab.setBackgroundDrawable(colorDrawable);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressBar = findViewById(R.id.progressBar3);
        imageView=findViewById(R.id.displayimage);
        txtView1 = findViewById(R.id.name1);
        txtView2 = findViewById(R.id.dep);
        txtView3 = findViewById(R.id.venue);
        txtView4 = findViewById(R.id.shot);
        txtView5 = findViewById(R.id.rules);
        txtView6 = findViewById(R.id.prereq);
        txtView7 = findViewById(R.id.firstprize);
        txtView8 = findViewById(R.id.about);
        txtView9 = findViewById(R.id.secondprize);
        txtView10 = findViewById(R.id.thirdprize);
        txtView11 = findViewById(R.id.fee);
        txtView12 = findViewById(R.id.date);
        txtView13 = findViewById(R.id.fprize);
        txtView14 = findViewById(R.id.secprize);
        txtView15 = findViewById(R.id.thdprize);
        vi = findViewById(R.id.divider2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getInt("id");
        int flag = bundle.getInt("flag");
        if(flag == 0) {
            new DisplayDetails().execute();
        }else {
            new ContestDetails().execute();
        }
    }

    private void Display(Workshops workshop){
        progressBar.setVisibility(View.GONE);
        txtView1.setText(workshop.getTitle());
        txtView2.setText(dept[Integer.parseInt(workshop.getDepartment())]);
        txtView3.setText(workshop.getVenue());
        txtView4.setText(workshop.getAbout());
        txtView5.setText(Html.fromHtml(workshop.getRules()));
        txtView6.setText(Html.fromHtml(workshop.getPrereq()));
        txtView7.setVisibility(View.GONE);
        txtView11.append(Integer.toString(workshop.getFee()));
        txtView12.setText(workshop.getD1dur());
        txtView9.setVisibility(View.GONE);
        txtView10.setVisibility(View.GONE);
        txtView13.setVisibility(View.GONE);
        txtView14.setVisibility(View.GONE);
        txtView15.setVisibility(View.GONE);
        vi.findViewById(View.GONE);
        txtView8.setText("About");
        String url="https://devhub.amblygon.org/static/images/workshops/"+workshop.getId()+"a.jpg";
        Glide.with(getApplicationContext()).load(Uri.parse(url)).into(imageView);
    }

    private void Display2(Contests contests){
        progressBar.setVisibility(View.GONE);
        txtView1.setText(contests.getTitle());
        txtView2.setText(dept[Integer.parseInt(contests.getDept())]);
        txtView3.setText(contests.getVenue());
        txtView4.setText(contests.getAbout());
        txtView5.setText(Html.fromHtml(contests.getRules()));
        if(Html.fromHtml(contests.getPrereq()).equals("")){
            txtView6.setVisibility(View.GONE);
        }else{
            txtView6.setText(Html.fromHtml(contests.getPrereq()));
        }
        txtView7.append(Integer.toString(contests.getP1()));
        txtView8.setText("About");
        txtView9.append(Integer.toString(contests.getP2()));
        txtView10.append(Integer.toString(contests.getP3()));
        txtView11.append(Integer.toString(contests.getFee()));
        txtView12.setText(contests.getD1dur());
        String url="https://devhub.amblygon.org/static/images/contests/"+contests.getId()+"a.jpg";
        Glide.with(getApplicationContext()).load(Uri.parse(url)).into(imageView);
    }

    private class DisplayDetails extends AsyncTask<Void, Void, Workshops> {


        @Override
        protected Workshops doInBackground(Void... voids) {
            Workshops workshop = null;
            Call<Workshops> call = apiManager.getWorkshopDetails(id);
            try {
                workshop=call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return workshop;
        }

        @Override
        protected void onPostExecute(Workshops workshop) {
            Toast.makeText(getApplicationContext(),workshop.getTitle(),Toast.LENGTH_SHORT).show();
            Display(workshop);
            super.onPostExecute(workshop);

        }
    }

    private class ContestDetails extends AsyncTask<Void,Void,Contests>{

        @Override
        protected Contests doInBackground(Void... voids) {
            Contests contest = null;
            Call<Contests> call = apiManager.getContestDetails(id);
            try{
                contest = call.execute().body();
            }catch (IOException e){
                e.printStackTrace();
            }
            return contest;
        }

        @Override
        protected void onPostExecute(Contests contests) {
            Toast.makeText(getApplicationContext(),contests.getTitle(),Toast.LENGTH_SHORT).show();
            Display2(contests);
            super.onPostExecute(contests);
        }
    }
}
