package com.example.wm.connexion;

import com.example.wm.model.Post;
import com.example.wm.model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("/user/login") // Make sure the endpoint is correct based on your server API
    Call<User> loginUser(@Field("logName") String logName, @Field("password") String password);

    @GET("/post/")
    Call<List<Post>> getAllPost();
}
