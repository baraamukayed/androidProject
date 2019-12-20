package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signInActivity extends AppCompatActivity {

    TextView signUpTV , exitTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void onClickSU(View v) {
        signUpTV = findViewById(R.id.signUpTV);
        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signInActivity.this , signUpActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onClickExite(View v) {
        exitTV = findViewById(R.id.exitTV);
        exitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signInActivity.this , MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
