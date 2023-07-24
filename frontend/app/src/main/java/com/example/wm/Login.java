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
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LabelBottom();
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