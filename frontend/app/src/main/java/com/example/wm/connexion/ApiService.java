package com.example.wm.connexion;

import com.example.wm.model.Post;
import com.example.wm.model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

<<<<<<< Updated upstream:frontend/app/src/main/java/com/example/wm/connexion/ApiService.java
=======
    // Add the base URL endpoint
    // Base Endpoint
    @GET("/")
    Call<YourResponseModel> getBaseEndpoint();

    // User Endpoints
    @GET("/user/")
    Call<YourResponseModel> getUser();

    @GET("/user/notification")
    Call<YourResponseModel> getUserNotification();

    @GET("/user/preference")
    Call<YourResponseModel> getUserPreference();

>>>>>>> Stashed changes:frontend/app/src/main/java/com/example/wm/ApiService.java
    @FormUrlEncoded
    @POST("/user/preference/add")
    Call<YourResponseModel> addUserPreference(@Field("userId") String userId, @Field("preference") String preference);

    @FormUrlEncoded
    @POST("/user/notification/add")
    Call<YourResponseModel> addUserNotification(@Field("message") String message, @Field("user") String user);

    @FormUrlEncoded
    @POST("/user/subscribe")
    Call<YourResponseModel> addUserSubscription(@Field("name") String name, @Field("firstname") String firstname,
                                                @Field("logname") String logname, @Field("password") String password);

    @FormUrlEncoded
    @POST("/user/login")
    Call<YourResponseModel> loginUser(@Field("logName") String logName, @Field("password") String password);

    @POST("/user/logout")
    Call<YourResponseModel> logoutUser();

    // Post Endpoints
    @GET("/post/:idPost")
    Call<YourResponseModel> getPost(@Field("idPost") String idPost);

    @GET("/post/")
<<<<<<< Updated upstream:frontend/app/src/main/java/com/example/wm/connexion/ApiService.java
    Call<Post> getAllPost();

    @GET("/post/{id}")
    Call<Post> getOnePost(@Path("id") String id);
=======
    Call<List<Post>> getAllPosts();

    @FormUrlEncoded
    @POST("/post/search")
    Call<YourResponseModel> searchPost(@Field("cleSearch") String cleSearch);

    @FormUrlEncoded
    @POST("/post/")
    Call<YourResponseModel> addPost(@Field("param1") String param1, @Field("param2") String param2);

>>>>>>> Stashed changes:frontend/app/src/main/java/com/example/wm/ApiService.java
}
