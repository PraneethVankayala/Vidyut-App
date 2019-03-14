package com.amrita.vidyut.BottomNavigation;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.amrita.vidyut.AccountAddons;
import com.amrita.vidyut.ApiManager;
import com.amrita.vidyut.AccountContests;
import com.amrita.vidyut.Data;
import com.amrita.vidyut.AccountWorkshops;
import com.amrita.vidyut.MyOrder;
import com.amrita.vidyut.R;
import com.amrita.vidyut.Registration;
//import com.amrita.vidyut.RegistrationLoader;
import com.amrita.vidyut.SignInActivity;
import com.amrita.vidyut.User;
import com.amrita.vidyut.ViewPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
    int cacheSize = (int)(0.1 * 1024 * 1024);
    TextView textView,textView1,textView2,textView3;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;

        view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        tabLayout = view.findViewById(R.id.account_tablayout);
        viewPager = view.findViewById(R.id.account_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        new DetailsVerify().execute();

        //add frags
        image=view.findViewById(R.id.imageView2);
        textView=view.findViewById(R.id.user_name);
        textView1=view.findViewById(R.id.user_mail);
        textView2=view.findViewById(R.id.vid);
        textView3=view.findViewById(R.id.user_phone);
        return view;

    }

    public class DetailsVerify extends AsyncTask<Void,Void,User>{

        @Override
        protected User doInBackground(Void... voids) {
            Cache cache = new Cache(getActivity().getCacheDir(), cacheSize);
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
            ApiManager apiManager = new ApiManager(okHttpClient);
            Call<User> call = apiManager.getUser(auth);
            User user=null;
            try {
                user=call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            try{
            String vid = user.getDatas().getVid();
            boolean d = user.getDatas().isDetails();
            boolean e = user.getDatas().isEdu();
            String email = user.getDatas().getEmail();
            String fname = user.getDatas().getFname();
            String lname = user.getDatas().getLname();
            String pic = user.getDatas().getPic();
            String phno = user.getDatas().getPhno();
            String farer=user.getDatas().getFarer();
            String s;
            //Toast.makeText(getContext(), vid + email, Toast.LENGTH_LONG).show();
            textView.setText(fname+" "+lname);
            textView1.setText(email);
            if(vid.length()==3){
                s="V190"+vid;
            }
            else if(vid.length()==2){
                s="V1900"+vid;
            }
            else if(vid.length()==1){
                s="V19000"+vid;
            }
            else{
                s="V19"+vid;
            }
            textView2.setText(s);
            vids=vid;
            textView3.setText(phno);
            Glide.with(view.getContext()).load(Uri.parse(pic)).apply(RequestOptions.circleCropTransform()).into(image);
            data = new Data(vid, email, fname, lname, pic, d, e, phno,farer);
            addpager();

        } catch (NullPointerException n) {
            n.printStackTrace();
        }

    }
    }
    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }


    private void addpager(){
        if(viewPagerAdapter!=null&&viewPager!=null&&tabLayout!=null) {
            viewPagerAdapter.addFragment(new AccountWorkshops(), "Workshops");
            viewPagerAdapter.addFragment(new AccountContests(), "Contests");
            viewPagerAdapter.addFragment(new AccountAddons(), "Addons");

            viewPager.setAdapter(viewPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
    }
}