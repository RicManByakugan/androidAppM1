package com.example.wm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // Add the base URL endpoint
    @GET("/")
    Call<YourResponseModel> getBaseEndpoint();
    @POST("/user/login")
    Call<User> loginUser(@Body User user);

    @GET("/post/")
    Call<List<Post>> getAllPost();
}
