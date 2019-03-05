package com.example.vidyut;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static Api service;
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

    public Call getRegWorkshops(String auth){
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

}
