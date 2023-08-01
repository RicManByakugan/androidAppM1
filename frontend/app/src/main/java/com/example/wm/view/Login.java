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

import com.example.wm.R;
import com.example.wm.controller.user.ControllerUser;

public class Login extends AppCompatActivity {
    private Button button;
    private ControllerUser controllerUser = new ControllerUser();
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

        String logName = "Man";
        String password = "secret";
        controllerUser.ConnexionUser(logName, password);
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