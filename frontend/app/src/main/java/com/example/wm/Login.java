package com.example.wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        Call<YourResponseModel> call = RetrofitClient.getApiService().getBaseEndpoint();
        call.enqueue(new Callback<YourResponseModel>() {
            @Override
            public void onResponse(Call<YourResponseModel> call, Response<YourResponseModel> response) {
                if (response.isSuccessful()) {
                    // Base URL request was successful
                    Log.d("MainActivity", "Base URL request success!");
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