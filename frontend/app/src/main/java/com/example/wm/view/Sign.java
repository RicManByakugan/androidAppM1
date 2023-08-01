package com.example.wm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wm.R;
import com.example.wm.controller.user.ControllerUser;

public class Sign extends AppCompatActivity {
    Button button;
    ControllerUser a = new ControllerUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        LabelBottom();
         button = (Button) findViewById(R.id.btnSign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textName = findViewById(R.id.nameUser);
                String nameText = textName.getText().toString();

                TextView textFirst= findViewById(R.id.firstNameUser);
                String firstText = textFirst.getText().toString();

                TextView textLogin = findViewById(R.id.logName);
                String loginText = textLogin.getText().toString();

                TextView textPass = findViewById(R.id.pswOne);
                String passText = textPass.getText().toString();
                Toast.makeText(Sign.this, "Registration ...", Toast.LENGTH_SHORT).show();
                a.userRegister(nameText,firstText,loginText, passText, new ControllerUser.UserRegisterCallback() {
                    @Override
                    public void onUserRegisterResult(boolean isConnected) {
                        if (isConnected) {
                            Toast.makeText(Sign.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Sign.this, Login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Sign.this, "Registration failed", Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(Sign.this, "To login", Toast.LENGTH_LONG);
                Intent intent = new Intent(Sign.this, Login.class);
                startActivity(intent);
            }
        };
        ss.setSpan(actionClicked, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        label.setText(ss);
        label.setMovementMethod(LinkMovementMethod.getInstance());
    }
}