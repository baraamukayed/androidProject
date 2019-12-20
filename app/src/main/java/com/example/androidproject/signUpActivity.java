package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signUpActivity extends AppCompatActivity {

    TextView exitTV,signInTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onClickSI(View v) {
        signInTV = findViewById(R.id.signInTV);
        signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpActivity.this , signInActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onClickExit(View v) {
        exitTV = findViewById(R.id.exitTV);
        exitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUpActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
