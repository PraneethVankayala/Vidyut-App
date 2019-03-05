package com.example.vidyut.BottomNavigation;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vidyut.ApiManager;
import com.example.vidyut.Data;
import com.example.vidyut.MyOrder;
import com.example.vidyut.R;
import com.example.vidyut.Registration;
//import com.example.vidyut.RegistrationLoader;
import com.example.vidyut.SignInActivity;
import com.example.vidyut.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth;
    Data data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;
        Toast.makeText(getContext(), auth, Toast.LENGTH_LONG).show();
        detailsverify(auth);
//        workshops(auth);
        new MyTask().execute();
        new orderTask().execute();
        return inflater.inflate(R.layout.fragment_dashboard, null);
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
                    Toast.makeText(getContext(), vid + email, Toast.LENGTH_LONG).show();

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


    private class MyTask extends AsyncTask<Void,Void,List<Registration>>

    {
        @Override
        protected List<Registration> doInBackground(Void... voids) {
            List<Registration> registrations=new ArrayList<>();

            Call<List<Registration>> call = apiManager.getRegWorkshops(auth);
            Call<List<Registration>> call1=apiManager.getRegContests(auth);
            try {
                registrations=call.execute().body();
                registrations.addAll(call1.execute().body());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return registrations;
        }



        @Override
        protected void onPostExecute(List<Registration> registrations) {

                for (int i = 0; i < registrations.size(); i++) {
                    Toast.makeText(getContext(), registrations.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                }

        }
    }

    private class orderTask extends AsyncTask<Void,Void,List<MyOrder>>{

        @Override
        protected List<MyOrder> doInBackground(Void... voids) {
            List<MyOrder> order=new ArrayList<>();
            Call<List<MyOrder>> call2=apiManager.getorder(auth);
            try {
                order=call2.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return order;
        }

        @Override
        protected void onPostExecute(List<MyOrder> myOrders) {
            for (int i = 0; i < myOrders.size(); i++) {
                Toast.makeText(getContext(), myOrders.get(i).getPurchase(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}