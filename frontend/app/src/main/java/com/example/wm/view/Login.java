package com.example.wm.view;

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
import android.widget.Toast;

import com.example.wm.R;
import com.example.wm.controller.user.ControllerUser;
import com.example.wm.tools.Serializer;

import org.json.JSONObject;

public class Login extends AppCompatActivity {
    Button button;
    ControllerUser a = new ControllerUser();

    JSONObject userJson;

    private ControllerUser controllerUser = new ControllerUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VerificateUserStatus();
        LabelBottom();
        UserLogActivity();
    }

    private void VerificateUserStatus(){
        controllerUser.Status(new ControllerUser.StatusCallBack() {
            @Override
            public void onStatusResult(String userData) {
                Log.d("DATA USER STATUS*********************************************", "" + userData);
            }
        });
    }

    private void UserLogActivity(){
        button = (Button) findViewById(R.id.btnSign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textLogin = findViewById(R.id.textLogin);
                String loginText = textLogin.getText().toString();

                TextView textPass = findViewById(R.id.textPass);
                String passText = textPass.getText().toString();
                Toast.makeText(Login.this, "Connexion ...", Toast.LENGTH_SHORT).show();
                a.userConnect(loginText, passText, new ControllerUser.UserConnectCallback() {
                    @Override
                    public void onUserConnectResult(boolean isConnected, String dataUser) {
                        if (isConnected) {
                            Toast.makeText(Login.this, "Connexion successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Home.class);
                            /*intent.putExtra("user", dataUser);
                            try {
                                userJson = new JSONObject(dataUser);
                            }catch (Exception e){
                                Log.d("ERROR", "" + e.toString());
                            }*/
                            // STOCK USER INFORMATION
                            // Serializer.serialize("dataUser", dataUser, Login.this);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Connexion failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
        ss.setSpan(actionClicked, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        label.setText(ss);
        label.setMovementMethod(LinkMovementMethod.getInstance());
    }
}