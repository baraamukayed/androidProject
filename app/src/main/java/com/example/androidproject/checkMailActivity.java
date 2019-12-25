package com.example.androidproject;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class checkMailActivity extends AppCompatActivity {

    TextView exitTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_mail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }

    public void onClickExit(View v) {
        exitTV = findViewById(R.id.exitTV);
        exitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkMailActivity.this , forgetPasswordActivity.class);
                startActivity(intent);

            }
        });
    }
}
