package com.example.vidyut;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    String BASE_URL="https://hub.amblygon.org";

    @POST("farer/auth/user")
    Call<ResponseAuth>  createId(@Body AuthToken authToken);

    @GET("farer/auth/user")
    Call<User> getUser(@Header("Authorization") String authorization);

    @GET("events/registration/workshops")
    Call<List<Registration>> getRegWorkshops(@Header("Authorization") String authorization);

    @GET("events/registration/contests")
    Call<List<Registration>> getRegContests(@Header("Authorization") String authorization);

    @GET("addons/order/my")
    Call<List<MyOrder>> getorder(@Header("Authorization") String authorization);

    @GET("events/workshops")
    Call<List<Workshops>> getWorkshops();

    @GET("events/contests")
    Call<List<Contests>> getContests();

    @GET("events/workshops/{Id}")
    Call<Workshops> getWorkshopDetails(@Path("Id") int id);

    @GET("events/contests/{Id}")
    Call<Contests> getContestDetails(@Path("Id") int id);
}
