package com.example.vidyut.BottomNavigation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vidyut.ApiManager;
import com.example.vidyut.Data;
import com.example.vidyut.R;
import com.example.vidyut.SignInActivity;
import com.example.vidyut.User;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCode extends Fragment {
    ApiManager apiManager = new ApiManager();
    String auth;
    ImageView imageView;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;
        View view=inflater.inflate(R.layout.fragment_qrcode,null);
        imageView=view.findViewById(R.id.qrcode);
        textView=view.findViewById(R.id.vidqr);
        detailsverify(auth,view);

        return view;
    }

    public void detailsverify(String auth,View view) {

        apiManager.getUser(auth, new Callback<User>() {
            boolean s;

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    User user = response.body();
                    String vid = user.getDatas().getVid();
                    if(vid.length()==3){
                        textView.setText("V190"+vid);
                    }
                    else{
                        textView.setText("V19"+vid);
                    }
                    String url="https://vidyut.amrita.edu/static/images/QR/"+vid+".png";
                        Glide.with(view.getContext()).load(Uri.parse(url)).into(imageView);


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

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}