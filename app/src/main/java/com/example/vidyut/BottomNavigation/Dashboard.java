package com.example.vidyut.BottomNavigation;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vidyut.AccountAddons;
import com.example.vidyut.ApiManager;
import com.example.vidyut.AccountContests;
import com.example.vidyut.Data;
import com.example.vidyut.AccountWorkshops;
import com.example.vidyut.MyOrder;
import com.example.vidyut.R;
import com.example.vidyut.Registration;
//import com.example.vidyut.RegistrationLoader;
import com.example.vidyut.SignInActivity;
import com.example.vidyut.User;
import com.example.vidyut.ViewPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth;
    View view;
    Data data;
    public static String vids;
    private TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView image;
    TextView textView,textView1,textView2,textView3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;

        view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        tabLayout = view.findViewById(R.id.account_tablayout);
        viewPager = view.findViewById(R.id.account_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        //add frags
        image=view.findViewById(R.id.imageView2);
        textView=view.findViewById(R.id.user_name);
        textView1=view.findViewById(R.id.user_mail);
        textView2=view.findViewById(R.id.vid);
        textView3=view.findViewById(R.id.user_phone);
        detailsverify(auth);
        viewPagerAdapter.addFragment(new AccountWorkshops(),"Workshops");
        viewPagerAdapter.addFragment(new AccountContests(),"Contests");
        viewPagerAdapter.addFragment(new AccountAddons(),"Addons");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }


    public void detailsverify(String auth) {

        apiManager.getUser(auth, new Callback<User>() {
            boolean s;

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    User user = response.body();
                    String vid = user.getDatas().getVid();
                    boolean d = user.getDatas().isDetails();
                    boolean e = user.getDatas().isEdu();
                    String email = user.getDatas().getEmail();
                    String fname = user.getDatas().getFname();
                    String lname = user.getDatas().getLname();
                    String pic = user.getDatas().getPic();
                    String phno = user.getDatas().getPhno();
                    String s;
                    //Toast.makeText(getContext(), vid + email, Toast.LENGTH_LONG).show();
                    textView.setText(fname+" "+lname);
                    textView1.setText(email);
                    if(vid.length()==3){
                        s="V190"+vid;
                    }
                    else{
                        s="V19"+vid;
                    }
                    textView2.setText(s);
                    vids=vid;
                    textView3.setText(phno);
                    Glide.with(view.getContext()).load(Uri.parse(pic)).apply(RequestOptions.circleCropTransform()).into(image);
                    data = new Data(vid, email, fname, lname, pic, d, e, phno);

                } catch (NullPointerException n) {
                    n.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(),
                        "Error is " + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }

        });
    }

}