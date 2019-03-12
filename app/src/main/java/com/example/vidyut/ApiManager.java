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

    public void getClgList(Callback<List<ClgList>> clg){
        Call<List<ClgList>> getClgListdet = service.getClgList();
        getClgListdet.enqueue(clg);
    }

}
