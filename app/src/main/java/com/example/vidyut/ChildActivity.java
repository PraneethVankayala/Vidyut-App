package com.example.vidyut;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChildActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Workshops> worksho=new ArrayList<>();
    List<Contests> contest=new ArrayList<Contests>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter<ChildViewHolder> madapter;
    Spinner spinner;
    String name;
    int cacheSize = (int)(0.5 * 1024 * 1024);
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView=new TextView(getApplicationContext());
        typeface = ResourcesCompat.getFont(this, R.font.frontage_bold);
        textView.setTypeface(typeface);
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        Bundle b = getIntent().getExtras();
        if(b!=null) {
            name = b.getString("name");
        }
        if(name.equals("Workshops")){
            textView.setText("WORKSHOPS");
        }
        else if(name.equals("Contests")){
            textView.setText("CONTESTS");
        }
        else if(name.equals("Schedule")){
            textView.setText("SCHEDULE");
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String dept[] = {"Select Department","CSE","ECE", "ME", "Physics", "Chemistry", "English", "Biotech","BUG", "Commerce and Management", "Civil", "EEE", "Gaming", "Maths", "Others"};
        spinner=findViewById(R.id.planets_spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,dept);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
        ab.setBackgroundDrawable(colorDrawable);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        textView=findViewById(R.id.textView2);
        imageView=findViewById(R.id.hom);
        progressBar = findViewById(R.id.progressBar2);
        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setNestedScrollingEnabled(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(name.equals("Workshops")) {
                    new WorkShopTask().execute(position);
                }
                else if(name.equals("Contests")){
                    new ContestsTask().execute(position);
                }else if(name.equals("Schedule")){

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

            Cache cache = new Cache(getCacheDir(), cacheSize);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                       .cache(cache).addInterceptor(new Interceptor() {

                        @Override
                        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                            Request request = chain.request();

                            if (!isNetworkAvailable()) {

                                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \

                                request = request

                                        .newBuilder()

                                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)

                                        .build();

                            }

                            return chain.proceed(request);

                        }

                    })

                    .build();


//            Retrofit.Builder builder = new Retrofit.Builder()
//
//                    .baseUrl("https://hub.amblygon.org")
//
//                    .client(okHttpClient)
//
//                    .addConverterFactory(GsonConverterFactory.create());



//            Retrofit retrofit = builder.build();
            ApiManager apiManager = new ApiManager(okHttpClient);
            int pos=In[0];
            List<Workshops> workshops=new ArrayList<>();
            List<Workshops> workshops1=new ArrayList<>();
            Call<List<Workshops>> call=apiManager.getWorkshops();
            try {
                workshops=call.execute().body();
                for(int i=0;i<workshops.size();i++){
                    if(workshops.get(i).getDepartment()==pos||pos==0){
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
                worksho.clear();
                madapter = new ChildAdapter(worksho);
                madapter.notifyDataSetChanged();
                recyclerView.setAdapter(madapter);
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }

        }
    }

    private class ContestsTask extends AsyncTask<Integer,Void, List<Contests>>{

        @Override
        protected List<Contests> doInBackground(Integer...In) {
            Cache cache = new Cache(getCacheDir(), cacheSize);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache).addInterceptor(new Interceptor() {

                        @Override
                        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                            Request request = chain.request();

                            if (!isNetworkAvailable()) {

                                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \

                                request = request

                                        .newBuilder()

                                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)

                                        .build();

                            }

                            return chain.proceed(request);

                        }

                    })

                    .build();
            int pos=In[0];
            List<Contests> workshops=new ArrayList<>();
            List<Contests> workshops1=new ArrayList<>();
            ApiManager apiManager = new ApiManager(okHttpClient);
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
                contest.clear();
                madapter = new ContestAdapter( contest);
                madapter.notifyDataSetChanged();
                recyclerView.setAdapter(madapter);
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                textView.setText("Contests Not Available");
                textView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Bonda",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 2404) {
            if(data != null) {
                name = data.getStringExtra("name");
            }
        }
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
