package com.example.vidyut;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vidyut.CloudMessaging.AppMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class AccountContests extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth=SignInActivity.Auth;;
    View view;
    RecyclerView recyclerView;
    List<Registration> wokshopList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dash,container,false);
        recyclerView = view.findViewById(R.id.workshop_recycler1);
        ContestRegAdapter recyclerViewAdapter = new ContestRegAdapter(wokshopList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new MyTask().execute();
        return view;

    }

    private class MyTask extends AsyncTask<Void, Void, List<Registration>> {
        @Override
        protected List<Registration> doInBackground(Void... voids) {
            List<Registration> registrations = new ArrayList<>();

            Call<List<Registration>> call = apiManager.getRegContests(auth);
            try {
                registrations = call.execute().body();
                AppMessage.subscribetotopic(new ArrayList(registrations),1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return registrations;
        }


        @Override
        protected void onPostExecute(List<Registration> registrations) {
            ContestRegAdapter recyclerViewAdapter = new ContestRegAdapter(registrations);
            recyclerView.setAdapter(recyclerViewAdapter);

        }

    }
}