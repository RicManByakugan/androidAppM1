package com.example.wm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // Add the base URL endpoint
    @GET("/")
    Call<YourResponseModel> getBaseEndpoint();
    @FormUrlEncoded
    @POST("/user/login") // Make sure the endpoint is correct based on your server API
    Call<User> loginUser(@Field("logName") String logName, @Field("password") String password);

    @GET("/post/")
    Call<List<Post>> getAllPost();
}
