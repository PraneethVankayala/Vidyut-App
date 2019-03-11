package com.example.vidyut;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ApiManager {

    private static Api service;
    private Context mContext;
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_PRAGMA = "Pragma";

    public ApiManager(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideCacheInterceptor())
                .cache(provideCache());


        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        service=retrofit.create(Api.class);

    }

    private Cache provideCache() {

        Cache cache = null;

        try {
            cache = new Cache(new File(mContext.getCacheDir(), "http-cache"),
                    10 * 1024 * 1024); // 10 MB
        } catch (Exception e) {
            Log.e(TAG, "Could not create Cache!");
        }

        return cache;
    }

    private Interceptor provideCacheInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());

            CacheControl cacheControl;

            if (isConnected()) {
                cacheControl = new CacheControl.Builder()
                        .maxAge(0, TimeUnit.SECONDS)
                        .build();
            } else {
                cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();
            }

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();

        };
    }

    private Interceptor provideOfflineCacheInterceptor() {
        return chain -> {
            Request request = chain.request();

            if (!isConnected()) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }


    private boolean isConnected() {

        try {
            android.net.ConnectivityManager e = (android.net.ConnectivityManager) mContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = e.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            Log.w(TAG, e.toString());
        }

        return false;
    }




    public void createId(AuthToken authToken, Callback<ResponseAuth> callback){
        Call<ResponseAuth> userCall = service.createId(authToken);
        userCall.enqueue(callback);
    }

    public void getUser(String authori,Callback<User> userCallback){
        Call<User> userCall = service.getUser(authori);
        userCall.enqueue(userCallback);
    }

    public static Call getRegWorkshops(String auth){
        Call<List<Registration>> registrationCall=service.getRegWorkshops(auth);
        return registrationCall;
    }

    public Call getRegContests(String auth){
        Call<List<Registration>> registrationCall=service.getRegContests(auth);
        return registrationCall;
    }

    public Call getorder(String auth){
        Call<List<MyOrder>> registrationCall=service.getorder(auth);
        return registrationCall;
    }

    public Call getWorkshops(){
        Call<List<Workshops>> registrationCall=service.getWorkshops();
        return registrationCall;
    }

    public Call getContests(){
        Call<List<Contests>> registrationCall=service.getContests();
        return registrationCall;
    }

    public Call getWorkshopDetails(int wid){
        Call<Workshops> workshop = service.getWorkshopDetails(wid);
        return workshop;
    }

    public Call getContestDetails(int id){
        Call<Contests> contest = service.getContestDetails(id);
        return  contest;
    }

    public void editDetails(String auth,String fname,String lname,String phno,int sex, Callback<Details> detailsCallback){
        Call<Details> detailsCall = service.editDetails(auth,fname,lname,phno,sex);
        detailsCall.enqueue(detailsCallback);
    }

    public void editEduDetails(String auth,String course,String major,String college,String institution,int year, Callback<EduDetails> eduDetailsCallback){
        Call<EduDetails> eduDetailsCall = service.editEduDetails(auth,course,major,college,institution,year);
        eduDetailsCall.enqueue(eduDetailsCallback);
    }

}
