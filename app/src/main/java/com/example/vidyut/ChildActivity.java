package com.example.vidyut;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ChildActivity extends AppCompatActivity {
    ApiManager apiManager = new ApiManager();
    TextView textView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Workshops> worksho=new ArrayList<>();
    List<Contests> contest=new ArrayList<Contests>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<ChildViewHolder> madapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String dept[] = {"Select Department","CSE","ECE", "ME", "Physics", "Chemistry", "English", "Biotech","BUG", "Commerce and Management", "Civil", "EEE", "Gaming", "Maths", "Others"};
         spinner=findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,dept);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        ActionBar ab = getSupportActionBar();
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
//        ab.setBackgroundDrawable(colorDrawable);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.lightBlack), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        progressBar = findViewById(R.id.progressBar2);
        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setNestedScrollingEnabled(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(name.equals("Workshops")) {
                    new WorkShopTask().execute(position);
                }
                else{
                    new ContestsTask().execute(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(name.equals("Workshops")) {
                    new WorkShopTask().execute(0);
                }
                else{
                    new ContestsTask().execute(0);
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);

    }



    private class WorkShopTask extends AsyncTask<Integer,Void, List<Workshops>>{

        int s;
        @Override
        protected List<Workshops> doInBackground(Integer...In) {
            int pos=In[0];
            List<Workshops> workshops=new ArrayList<>();
            List<Workshops> workshops1=new ArrayList<>();
            Call<List<Workshops>> call=apiManager.getWorkshops();
            try {
                workshops=call.execute().body();
                for(int i=0;i<workshops.size();i++){
                    if(Integer.parseInt(workshops.get(i).getDepartment())==pos||pos==0){
                        workshops1.add(workshops.get(i));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return workshops1;
        }

        @Override
        protected void onPostExecute(List<Workshops> workshops) {
            if(workshops.size()!=0) {
                worksho.clear();
                worksho.addAll(workshops);
                Toast.makeText(getApplicationContext(), workshops.get(0).getTitle(), Toast.LENGTH_LONG).show();
                madapter = new ChildAdapter(worksho);
                madapter.notifyDataSetChanged();
                recyclerView.setAdapter(madapter);
                progressBar.setVisibility(View.INVISIBLE);
                super.onPostExecute(workshops);
            }
            else{
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }

        }
    }

    private class ContestsTask extends AsyncTask<Integer,Void, List<Contests>>{

        @Override
        protected List<Contests> doInBackground(Integer...In) {
            int pos=In[0];
            List<Contests> workshops=new ArrayList<>();
            List<Contests> workshops1=new ArrayList<>();
            Call<List<Contests>> call=apiManager.getContests();
            try {
                workshops = call.execute().body();
                for (int i = 0; i < workshops.size(); i++) {
                    if (Integer.parseInt(workshops.get(i).getDept()) == pos || pos == 0) {
                        workshops1.add(workshops.get(i));
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            return workshops1;
        }
            @Override
        protected void onPostExecute(List<Contests> workshops) {
            if(workshops.size()!=0) {
                contest.clear();
                contest.addAll(workshops);
                Toast.makeText(getApplicationContext(), workshops.get(1).getTitle(), Toast.LENGTH_LONG).show();
                madapter = new ContestAdapter(contest);
                madapter.notifyDataSetChanged();
                recyclerView.setAdapter(madapter);
                progressBar.setVisibility(View.INVISIBLE);
                super.onPostExecute(workshops);
            }
            else{
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }

        }
    }
}
