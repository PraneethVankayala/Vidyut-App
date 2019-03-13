package com.amrita.vidyut;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidyut.ClgList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {


    String token;
    Details details;
    EduDetails eduDetails;
    private static final String TAG = "hii";
    ApiManager apiManager=new ApiManager();
    String fname,lname,phno,course,major,institution;
    int sex,year,college;
    Button next,prev;
    List<ClgList> clg;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        Log.i("This is token",token);
        TextView textView=new TextView(getApplicationContext());
        Typeface typeface = ResourcesCompat.getFont(this, R.font.frontage_bold);
        textView.setTypeface(typeface);
        textView.setText("VIDYUT");
        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        ActionBar ab = getSupportActionBar();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffffff"));
        ab.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadFragment(new frame());
        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
        new clg().execute();
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
    }

    public void retrivefrag(String fname,String lname,String phno,int sex){
        this.fname=fname;
        this.lname=lname;
        this.phno=phno;
        this.sex=sex;
        Toast.makeText(getApplicationContext(),"hgg"+fname+lname+phno+sex,Toast.LENGTH_SHORT).show();
        details=new Details(fname,lname,phno,sex);
        Toast.makeText(getApplicationContext(),"hii"+details.getName()+details.getLname()+details.getPhnumber()+details.getGender(),Toast.LENGTH_SHORT).show();
        loadFragment(new edu());
    }

    public void details(String course,String major,int college,String institution,int year){
        this.course = course;
        this.major = major;
        this.college = college;
        this.institution = institution;
        this.year = year;
        eduDetails=new EduDetails(course,major,college,institution,year);
        Toast.makeText(getApplicationContext(),eduDetails.college+eduDetails.course+eduDetails.major+eduDetails.institution,Toast.LENGTH_SHORT).show();
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



    private class clg extends AsyncTask<Void,Void,List<ClgList>>{

        @Override
        protected List<ClgList> doInBackground(Void...voids) {
            clg = new ArrayList<>();
            Call<List<ClgList>> call = apiManager.getClgList();
            try {
                clg = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return clg;
        }
        @Override
        protected void onPostExecute(List<ClgList> clgLists) {
                Toast.makeText(getApplicationContext(), clgLists.get(6).getName(), Toast.LENGTH_SHORT).show();
            super.onPostExecute(clgLists);
        }
    }


//    public void clg(){
//        apiManager.getClgList(new Callback<List<ClgList>>() {
//            @Override
//            public void onResponse(Call<List<ClgList>> call, Response<List<ClgList>> response) {
//                clg1 = response.body();
//                for(int i= 0;i<clg1.size();i++){
//                    clg.add(clg1.get(i));
//                }
//            }
//            @Override
//            public void onFailure(Call<List<ClgList>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),
//                        "Error is " + t.getMessage()
//                        , Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    public void setData(){
        apiManager.editDetails(token,details,new Callback<ResponseDet>() {
            @Override
            public void onResponse(Call<ResponseDet> call, Response<ResponseDet> response) {
                ResponseDet details = response.body();
                Toast.makeText(getApplicationContext(),"set"+details.getMessage(),Toast.LENGTH_SHORT).show();
                Log.i(TAG, "post submitted to API." + response.body().toString());
            }
            @Override
            public void onFailure(Call<ResponseDet> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setEduData(){
        apiManager.editEduDetails(token,eduDetails, new Callback<ResponseDet>() {
            @Override
            public void onResponse(Call<ResponseDet> call, Response<ResponseDet> response) {
                    // showResponse(response.body().toString());
                    ResponseDet responseDet=response.body();
                    Toast.makeText(getApplicationContext(),"setEdu"+responseDet.getMessage(),Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "post submitted to API." + response.body().toString());

            }

            @Override
            public void onFailure(Call<ResponseDet> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

}
