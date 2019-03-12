package com.example.vidyut;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidyut.CloudMessaging.AppMessage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;

import static android.support.constraint.Constraints.TAG;

public class AccountWorkshops extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth = SignInActivity.Auth;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    View view;
    RecyclerView recyclerView;
    List<Registration> wokshopList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        recyclerView = view.findViewById(R.id.workshop_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new MyTask().execute();
        return view;

    }


    private void signOut() {
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        mGoogleSignInClient.signOut().addOnCompleteListener((Executor) this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(getContext(),SignInActivity.class);
                startActivity(intent);

            }
        });
    }


    public class MyTask extends AsyncTask<Void, Void, List<Registration>> {
        @Override
        protected List<Registration> doInBackground(Void... voids) {
            List<Registration> registrations = new ArrayList<>();


            Call<List<Registration>> call = ApiManager.getRegWorkshops(auth);
            try {
                registrations = call.execute().body();
                //For Subscription
                AppMessage.subscribetotopic(new ArrayList(registrations),1);

            } catch (IOException e) {
                e.printStackTrace();
            }catch (IllegalStateException e){
                signOut();
            }

            return registrations;
        }



        @Override
        protected void onPostExecute(List<Registration> registrations) {
            RegistrationAdapter recyclerViewAdapter = new RegistrationAdapter(registrations);
            recyclerView.setAdapter(recyclerViewAdapter);

        }

    }

}


