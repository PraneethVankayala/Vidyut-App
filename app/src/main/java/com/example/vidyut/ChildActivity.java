package com.example.vidyut;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    List<Workshops> worksho=new ArrayList<>();
    List<Contests> contest=new ArrayList<Contests>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<ChildViewHolder> madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b = getIntent().getExtras();
        String name = b.getString("name");
        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.recycler1);
        if(name.equals("Workshops")) {
            new WorkShopTask().execute();
        }
        else{
            new ContestsTask().execute();
        }
        recyclerView.setLayoutManager(layoutManager);

    }

    private class WorkShopTask extends AsyncTask<Void,Void, List<Workshops>>{

        @Override
        protected List<Workshops> doInBackground(Void... voids) {
            List<Workshops> workshops=new ArrayList<>();
            Call<List<Workshops>> call=apiManager.getWorkshops();
            try {
                workshops=call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return workshops;
        }

        @Override
        protected void onPostExecute(List<Workshops> workshops) {
            if(workshops.size()!=0) {
                worksho.addAll(workshops);
                Toast.makeText(getApplicationContext(), workshops.get(1).getTitle(), Toast.LENGTH_LONG).show();
                madapter = new ChildAdapter(worksho);
                recyclerView.setAdapter(madapter);
                madapter.notifyDataSetChanged();
                super.onPostExecute(workshops);
            }
            else{
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }

        }
    }

    private class ContestsTask extends AsyncTask<Void,Void, List<Contests>>{

        @Override
        protected List<Contests> doInBackground(Void... voids) {
            List<Contests> workshops=new ArrayList<>();
            Call<List<Contests>> call=apiManager.getContests();
            try {
                workshops=call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return workshops;
        }

        @Override
        protected void onPostExecute(List<Contests> workshops) {
            if(workshops.size()!=0) {
                contest.addAll(workshops);
                Toast.makeText(getApplicationContext(), workshops.get(1).getTitle(), Toast.LENGTH_LONG).show();
                madapter = new ContestAdapter(contest);
                recyclerView.setAdapter(madapter);
                madapter.notifyDataSetChanged();
                super.onPostExecute(workshops);
            }
            else{
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }

        }
    }
}
