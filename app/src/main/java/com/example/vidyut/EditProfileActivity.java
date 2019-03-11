package com.example.vidyut;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {


    String token;
    private static final String TAG = "hii";
    ApiManager apiManager=new ApiManager();
    String fname,lname,phno,course,major,college,institution;
    int sex,year;
    Button next,prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        loadFragment(new frame());
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);

    }

    public void retrivefrag(String fname,String lname,String phno,int sex){
        this.fname=fname;
        this.lname=lname;
        this.phno=phno;
        this.sex=sex;
        loadFragment(new edu());
    }

    public void details(String course,String major,String college,String institution,int year){
        this.course = course;
        this.major = major;
        this.college = college;
        this.institution = institution;
        this.year = year;
        setData();
        setEduData();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }

    }

    public void setData(){
        //Toast.makeText(getApplicationContext(),"Success1",Toast.LENGTH_SHORT).show();
        apiManager.editDetails(token,fname,lname,phno,sex,new Callback<Details>() {

            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                //Toast.makeText(getApplicationContext(),"Success2",Toast.LENGTH_SHORT).show();
                Details details = response.body();
                okhttp3.Response jspn=response.raw();
                //Toast.makeText(getApplicationContext(),jspn.toString(),Toast.LENGTH_LONG).show();
                if(response.isSuccessful()) {
                    // showResponse(response.body().toString());
                  //  Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setEduData(){
        apiManager.editEduDetails(token,course, major, college, institution,year, new Callback<EduDetails>() {
            @Override
            public void onResponse(Call<EduDetails> call, Response<EduDetails> response) {
                if(response.isSuccessful()) {
                    // showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<EduDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

}
