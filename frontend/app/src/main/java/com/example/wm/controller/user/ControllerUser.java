package com.example.wm.controller.user;

import android.util.Log;

import com.example.wm.connexion.RetrofitClient;
import com.example.wm.model.YourResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerUser {

    public ControllerUser() {
    }

    public interface UserConnectCallback {
        void onUserConnectResult(boolean isConnected);
    }
    public void userConnect(String logName, String password, ControllerUser.UserConnectCallback callback) {
        Call<YourResponseModel> call = RetrofitClient.getApiService().loginUser(logName, password);
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    YourResponseModel responseData = response.body();
                    if (responseData.message.compareTo("LOGIN SUCCESSFULLY") == 0) {
                        callback.onUserConnectResult(true);
                        Log.d("MainActivity", "The response is: " + responseData.message);
                    } else {
                        callback.onUserConnectResult(false);
                        Log.d("MainActivity", "Response body is error: " + responseData.message);
                    }
                } else {
                    // Request failed (you can handle different HTTP error codes here)
                    callback.onUserConnectResult(false);
                    Log.e("MainActivity", "Request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                // Network request failed or other exceptions occurred
                callback.onUserConnectResult(false);
                Log.e("MainActivity", "Network request failed", t);
            }
        });
    }
}
