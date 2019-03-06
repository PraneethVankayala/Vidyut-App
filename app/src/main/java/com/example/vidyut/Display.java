package com.example.vidyut;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
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
    TextView txtView1,txtView2,txtView3,txtView4,txtView5,txtView6,txtView7;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        imageView=findViewById(R.id.displayimage);
        txtView1 = findViewById(R.id.name1);
        txtView2 = findViewById(R.id.dep);
        txtView3 = findViewById(R.id.venue);
        txtView4 = findViewById(R.id.shot);
        txtView5 = findViewById(R.id.rules);
        txtView6 = findViewById(R.id.prereq);
        txtView7 = findViewById(R.id.seats);
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
        txtView1.setText(workshop.getTitle());
        txtView2.setText(workshop.getDepartment());
        txtView3.setText(workshop.getVenue());
        txtView4.setText(workshop.getAbout());
        txtView5.setText(Html.fromHtml(workshop.getRules()));
        txtView6.setText(Html.fromHtml(workshop.getPrereq()));
        txtView7.setText(workshop.getSeats());
        String url="https://devhub.amblygon.org/static/images/workshops/"+workshop.getId()+"a.jpg";
        Glide.with(getApplicationContext()).load(Uri.parse(url)).into(imageView);
    }

    private void Display2(Contests contests){
        txtView1.setText(contests.getTitle());
        txtView2.setText(contests.getDept());
        txtView3.setText(contests.getVenue());
        txtView4.setText(contests.getAbout());
        txtView5.setText(Html.fromHtml(contests.getRules()));
        txtView6.setText(Html.fromHtml(contests.getPrereq()));
        txtView7.setText(Integer.toString(contests.getP1()));
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
