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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    String back;
    String reg;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
        ab.setBackgroundDrawable(colorDrawable);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressBar = findViewById(R.id.progressBar3);
        imageView=findViewById(R.id.displayimage);
        button = findViewById(R.id.register);
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
            back="Workshops";
        }else {
            new ContestDetails().execute();
            back="Contests";
        }
    }

    private void Display(Workshops workshop){
        progressBar.setVisibility(View.GONE);
        txtView1.setText(workshop.getTitle());
        txtView2.setVisibility(View.VISIBLE);
        txtView2.setText(dept[Integer.parseInt(workshop.getDepartment())]);
        txtView3.setText(workshop.getVenue());
        txtView4.setText(workshop.getAbout());
        button.setVisibility(View.VISIBLE);
        txtView5.setText(Html.fromHtml(workshop.getRules()));
        txtView6.setText(Html.fromHtml(workshop.getPrereq()));
        txtView7.setVisibility(View.GONE);
        txtView11.append(Integer.toString(workshop.getFee()));
        if(!(workshop.getD1dur().equals(""))){
            txtView12.setText("Day1 : ");
            txtView12.append(workshop.getD1dur()+"\n");
        }
        if(!(workshop.getD2dur().equals(""))){
            txtView12.append("Day2 : ");
            txtView12.append(workshop.getD2dur()+"\n");
        }if(!(workshop.getD3dur().equals(""))){
            txtView12.append("Day3 : ");
            txtView12.append(workshop.getD3dur());
        }
        txtView9.setVisibility(View.GONE);
        txtView10.setVisibility(View.GONE);
        txtView13.setVisibility(View.GONE);
        txtView14.setVisibility(View.GONE);
        txtView15.setVisibility(View.GONE);
        int rseats = workshop.getRmseats();
        if(rseats == 0){
            button.setClickable(false);
        }
        vi.findViewById(View.GONE);
        txtView8.setText("About");
        reg= "https://vidyut.amrita.edu/workshops/"+String.valueOf(workshop.getId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(reg));
                startActivity(i);
            }
        });
        String url="https://vidyut.amrita.edu/static/images/workshops/"+workshop.getId()+"a.jpg";
        Glide.with(getApplicationContext()).load(Uri.parse(url)).into(imageView);
    }

    private void Display2(Contests contests){
        progressBar.setVisibility(View.GONE);
        txtView1.setText(contests.getTitle());
        txtView2.setVisibility(View.VISIBLE);
        txtView2.setText(dept[Integer.parseInt(contests.getDept())]);
        txtView3.setText(contests.getVenue());
        txtView4.setText(contests.getAbout());
        button.setVisibility(View.VISIBLE);
        txtView5.setText(Html.fromHtml(contests.getRules()));
        if(Html.fromHtml(contests.getPrereq()).equals("")){
            txtView6.setVisibility(View.GONE);
        }else{
            txtView6.setText(Html.fromHtml(contests.getPrereq()));
        }
        if((contests.getP1()) != 0 ){
            txtView7.append(Integer.toString(contests.getP1()));
        }else{
            txtView13.setVisibility(View.GONE);
            txtView7.setVisibility(View.GONE);
        }
        if((contests.getP2()) != 0 ){
            txtView9.append(Integer.toString(contests.getP2()));
        }else{
            txtView14.setVisibility(View.GONE);
            txtView9.setVisibility(View.GONE);
        }
        if((contests.getP3()) != 0 ){
            txtView10.append(Integer.toString(contests.getP3()));
        }else{
            txtView15.setVisibility(View.GONE);
            txtView10.setVisibility(View.GONE);
        }
        txtView8.setText("About");
        txtView11.append(Integer.toString(contests.getFee()));
        if(!(contests.getD1dur().equals(""))){
            txtView12.setText("Day1 : ");
            txtView12.append(contests.getD1dur()+"\n");
        }
        if(!(contests.getD2dur().equals(""))){
            txtView12.append("Day2 : ");
            txtView12.append(contests.getD2dur()+"\n");
        }if(!(contests.getD3dur().equals(""))){
            txtView12.append("Day3 : ");
            txtView12.append(contests.getD3dur());
        }
        reg= "https://vidyut.amrita.edu/contests/"+String.valueOf(contests.getId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(reg));
                startActivity(i);
            }
        });
        String url="https://vidyut.amrita.edu/static/images/contests/"+contests.getId()+"a.jpg";
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent= new Intent();
                intent.putExtra("name", back);
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
