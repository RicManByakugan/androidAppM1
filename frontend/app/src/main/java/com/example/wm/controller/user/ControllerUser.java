package com.example.wm.controller.user;

import android.util.Log;

import com.example.wm.connexion.RetrofitClient;
import com.example.wm.model.YourResponseModel;
import com.google.gson.Gson;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class ControllerUser {

    public ControllerUser() {
    }

    public interface UserConnectCallback {
        void onUserConnectResult(boolean isConnected, String jsonUser);
    }
    public void userConnect(String logName, String password, ControllerUser.UserConnectCallback callback) {
        Call<YourResponseModel> call = RetrofitClient.getApiService().loginUser(logName, password);
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    YourResponseModel responseData = response.body();
                    if (responseData.message.compareTo("LOGIN SUCCESSFULLY") == 0) {
                        callback.onUserConnectResult(true, new Gson().toJson(response.body()));
                        Log.d("MainActivity", "The response is: " + responseData.message);
                    } else {
                        callback.onUserConnectResult(false, new Gson().toJson(response.body()));
                        Log.d("MainActivity", "Response body is error: " + responseData.message);
                    }
                } else {
                    // Request failed (you can handle different HTTP error codes here)
                    callback.onUserConnectResult(false, new Gson().toJson(response.body()));
                    Log.e("MainActivity", "Request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                // Network request failed or other exceptions occurred
                if (t instanceof SocketTimeoutException) {
                    // Handle socket timeout exception
                    callback.onUserConnectResult(false, new Gson().toJson(""));
                    Log.e("MainActivity", "Socket request failed", t);
                } else {
                    // Handle other exceptions
                    callback.onUserConnectResult(false, new Gson().toJson(""));
                    Log.e("MainActivity", "Network request failed", t);
                }

            }
        });
    }

    public interface UserRegisterCallback {
        void onUserRegisterResult(boolean isConnected);
    }
    public void userRegister( String name,  String firstname,
                            String logname,  String password, ControllerUser.UserRegisterCallback callback) {
        Call<YourResponseModel> call = RetrofitClient.getApiService().addUserSubscription( name, firstname,
                logname, password);
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    YourResponseModel responseData = response.body();
                    if (responseData.message.compareTo("SUBSCRIBE SUCCESSFULLY") == 0) {
                        callback.onUserRegisterResult(true);
                        Log.d("MainActivity", "The response is: " + responseData.message);
                    } else {
                        callback.onUserRegisterResult(false);
                        Log.d("MainActivity", "Response body is error: " + responseData.message);
                    }
                } else {
                    // Request failed (you can handle different HTTP error codes here)
                    callback.onUserRegisterResult(false);
                    Log.e("MainActivity", "Request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                // Network request failed or other exceptions occurred
                callback.onUserRegisterResult(false);
                Log.e("MainActivity", "Network request failed", t);
            }
        });
    }

    public interface StatusCallBack {
        void onStatusResult(String userData);
    }

    public void Status(ControllerUser.StatusCallBack callBack){
        Call<YourResponseModel> call = RetrofitClient.getApiService().getUser();
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()){
                    Log.d("O", "O");
                    //callBack.onStatusResult(new Gson().toJson(response.body()).toString());
                }else {
                    Log.d("Erreur", "Erreur One");
                }
            }
            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                Log.d("Erreur", "Erreur" + t.getMessage());
            }
        });
    }

    public interface UserLogoutCallback {
        void onUserLogoutResult(boolean isConnected);
    }
    public void userLogout( ControllerUser.UserLogoutCallback callback) {
        Call<YourResponseModel> call = RetrofitClient.getApiService().logoutUser();
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    YourResponseModel responseData = response.body();
                    if (responseData.message.compareTo("LOGOUT SUCCESSFULLY") == 0) {
                        callback.onUserLogoutResult(true);
                        Log.d("MainActivity", "The response is: " + responseData.message);
                    } else {
                        callback.onUserLogoutResult(false);
                        Log.d("MainActivity", "Response body is error: " + responseData.message);
                    }
                } else {
                    // Request failed (you can handle different HTTP error codes here)
                    callback.onUserLogoutResult(false);
                    Log.e("MainActivity", "Request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                // Network request failed or other exceptions occurred
                callback.onUserLogoutResult(false);
                Log.e("MainActivity", "Network request failed", t);
            }
        });
    }


}
