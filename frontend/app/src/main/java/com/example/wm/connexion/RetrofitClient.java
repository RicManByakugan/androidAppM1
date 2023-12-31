package com.example.wm.connexion;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    //private static final String BASE_URL = "http://192.168.88.20:3000";
    //private static final String BASE_URL = "http://192.168.145.48:3000";
    private static final String BASE_URL = "http://wm-bkkp.onrender.com/";
    private static Retrofit retrofit;
    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
