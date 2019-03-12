package com.example.vidyut;

import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static Api service;

    public ApiManager(OkHttpClient okHttpClient){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        service=retrofit.create(Api.class);

    }


    public ApiManager(){


        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service=retrofit.create(Api.class);

    }


    public void createId(AuthToken authToken, Callback<ResponseAuth> callback){
        Call<ResponseAuth> userCall = service.createId(authToken);
        userCall.enqueue(callback);
    }

    public Call getUser(String authori){
        Call<User> userCall = service.getUser(authori);
        return userCall;
    }
    public void getUser(String authori,Callback<User> callback){
        Call<User> userCall = service.getUser(authori);
        userCall.enqueue(callback);
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

    public void editDetails(String auth,Details details, Callback<ResponseDet> detailsCallback){
        Call<ResponseDet> detailsCall = service.editDetails(auth,details);
        detailsCall.enqueue(detailsCallback);
    }

    public void editEduDetails(String auth,EduDetails eduDetails, Callback<ResponseDet> eduDetailsCallback){
        Call<ResponseDet> eduDetailsCall = service.editEduDetails(auth,eduDetails);
        eduDetailsCall.enqueue(eduDetailsCallback);
    }

}
