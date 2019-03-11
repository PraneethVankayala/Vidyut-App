package com.example.vidyut;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @PUT("farer/user/details")
    @FormUrlEncoded
    Call<Details> editDetails(@Header("Authorization") String authorization,
                              @Field("fname") String fname,
                              @Field("lname") String lname,
                              @Field("phno") String phno,
                              @Field("sex") int sex);

    @PUT("farer/user/education")
    @FormUrlEncoded
    Call<EduDetails> editEduDetails(@Header("Authorization") String authorization,
                                    @Field("course") String course,
                                    @Field("major") String major,
                                    @Field("college") String college,
                                    @Field("institution") String institution,
                                    @Field("year") int year);



}
