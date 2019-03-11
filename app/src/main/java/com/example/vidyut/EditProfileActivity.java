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
    Details details;
    EduDetails eduDetails;
    private static final String TAG = "hii";
    ApiManager apiManager=new ApiManager();
    String fname,lname,phno,course,major,institution;
    int sex,year,college;
    Button next,prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Bundle b = getIntent().getExtras();
        token = b.getString("token");
        Log.i("This is token",token);
        Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
        loadFragment(new frame());
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
