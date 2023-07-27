package com.example.wm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // Add the base URL endpoint
    @GET("/")
    Call<YourResponseModel> getBaseEndpoint();
}
