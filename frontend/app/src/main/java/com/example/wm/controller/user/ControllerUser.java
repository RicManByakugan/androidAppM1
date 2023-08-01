package com.example.wm.controller.user;

import android.util.Log;

import com.example.wm.connexion.RetrofitClient;
import com.example.wm.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerUser {

    public ControllerUser() {
    }

    public void ConnexionUser(String logname, String password){
        Call<User> call = RetrofitClient.getApiService().loginUser(logname, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Request was successful
                    User responseData = response.body();
                    if (responseData != null ) {
                        // Convert the response body to a JSON string
                        String jsonResponse = new Gson().toJson(responseData);
                        Log.d("MainActivity", "JSON Response: " + jsonResponse);
                    } else {
                        Log.d("MainActivity", "Response body is empty.");
                    }
                } else {
                    // Request failed (you can handle different HTTP error codes here)
                    Log.e("MainActivity", "Request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Network request failed or other exceptions occurred
                Log.e("MainActivity", "Network request failed", t);
            }
        });
    }
}
