package com.example.vidyut;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;

public class AccountWorkshops extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth = SignInActivity.Auth;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    View view;
    RecyclerView recyclerView;
    List<Registration> wokshopList = new ArrayList<>();
    TextView textView;
    LinearLayout linearLayout;
    Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        recyclerView = view.findViewById(R.id.workshop_recycler);
        textView=view.findViewById(R.id.textView2);
        linearLayout=view.findViewById(R.id.linear2);
        button=view.findViewById(R.id.reg);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new MyTask().execute();
        return view;

    }

    private class MyTask extends AsyncTask<Void, Void, List<Registration>> {
        @Override
        protected List<Registration> doInBackground(Void... voids) {
            List<Registration> registrations = new ArrayList<>();

            Call<List<Registration>> call = apiManager.getRegWorkshops(auth);
            try {
                registrations = call.execute().body();

            } catch (IOException e) {
                e.printStackTrace();
            }catch (IllegalStateException e){
                signOut();
            }

            return registrations;
        }


        @Override
        protected void onPostExecute(List<Registration> registrations) {
            if(registrations.size()!=0) {
                textView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                RegistrationAdapter recyclerViewAdapter = new RegistrationAdapter(registrations);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            else{
                  textView.setVisibility(View.VISIBLE);
                  linearLayout.setVisibility(View.VISIBLE);
                  button.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Intent intent=new Intent(getActivity(),ChildActivity.class);
                          Bundle b=new Bundle();
                          b.putString("name","Workshops");
                          intent.putExtras(b);
                          startActivity(intent);
                      }
                  });
            }

        }

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
}
