package com.amrita.vidyut.BottomNavigation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.amrita.vidyut.ApiManager;
import com.amrita.vidyut.Data;
import com.amrita.vidyut.R;
import com.amrita.vidyut.SignInActivity;
import com.amrita.vidyut.User;

import java.io.IOException;
import java.net.URI;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCode extends Fragment {
    String auth;
    ImageView imageView;
    TextView textView;
    int cacheSize = (int)(0.3 * 1024 * 1024);
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        auth = SignInActivity.Auth;
        view=inflater.inflate(R.layout.fragment_qrcode,null);
        imageView=view.findViewById(R.id.qrcode);
        textView=view.findViewById(R.id.vidqr);
        new QRclass().execute(auth);

        return view;
    }

     public class QRclass extends AsyncTask<String,Void,User> {

         @Override
         protected User doInBackground(String... strings) {
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
            Call<User> call=apiManager.getUser(auth);
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
             String vid = user.getDatas().getVid();
             try{
                 if(vid.length()==3){
                     textView.setText("V190"+vid);
                 }
                 else{
                     textView.setText("V19"+vid);
                 }
                 String url="https://vidyut.amrita.edu/static/images/QR/"+vid+".png";
                 Glide.with(view.getContext()).load(Uri.parse(url)).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);

             }catch (NullPointerException ne){

             }

             super.onPostExecute(user);
         }
     }



    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}