package com.example.wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LabelBottom();
        button = (Button) findViewById(R.id.btnSign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });
        /*Call<YourResponseModel> call = RetrofitClient.getApiService().getBaseEndpoint();
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    // Base URL request was successful
                    YourResponseModel responseData = response.body();
                    if (responseData != null) {
                        // Convert the response body to a JSON string
                        String jsonResponse = new Gson().toJson(responseData);
                        Log.d("MainActivity", "JSON Response: " + jsonResponse);
                    } else {
                        Log.d("MainActivity", "Response body is empty.");
                    }
                } else {
                    // Base URL request failed (you can handle different HTTP error codes here)
                    Log.e("MainActivity", "Base URL request failed. HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<YourResponseModel> call, Throwable t) {
                // Network request failed or other exceptions occurred
                Log.e("MainActivity", "Network request failed", t);
            }
        });*/
        String logName = "Man";
        String password = "secret";
        Call<User> call = RetrofitClient.getApiService().loginUser(logName, password);
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

    private void LabelBottom(){
        TextView label = findViewById(R.id.loglbl);
        SpannableString ss = new SpannableString(label.getText().toString());
        ClickableSpan actionClicked = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //Toast.makeText(Login.this, "To Sign", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, Sign.class);
                startActivity(intent);
            }
        };
        ss.setSpan(actionClicked, 7, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        label.setText(ss);
        label.setMovementMethod(LinkMovementMethod.getInstance());
    }
}