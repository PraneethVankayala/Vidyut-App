package com.example.vidyut;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidyut.CloudMessaging.AppMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class AccountAddons extends Fragment {

    ApiManager apiManager = new ApiManager();
    String auth=SignInActivity.Auth;;
    View view;
    RecyclerView recyclerView;
    List<MyOrder> wokshopList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info,container,false);
        recyclerView = view.findViewById(R.id.workshop_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new orderTask().execute();
        return view;

    }

    private class orderTask extends AsyncTask<Void,Void,List<MyOrder>> {

        @Override
        protected List<MyOrder> doInBackground(Void... voids) {
            List<MyOrder> order=new ArrayList<>();
            Call<List<MyOrder>> call2=apiManager.getorder(auth);
            try {
                order=call2.execute().body();
                AppMessage.subscribetotopic(new ArrayList(order),2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return order;
        }

        @Override
        protected void onPostExecute(List<MyOrder> myOrders) {
               AddOnAdapter recyclerViewAdapter = new AddOnAdapter(myOrders);
               recyclerView.setAdapter(recyclerViewAdapter);

        }
    }
}
