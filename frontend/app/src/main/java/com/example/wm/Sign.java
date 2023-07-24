package com.example.wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Sign extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        LabelBottom();
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