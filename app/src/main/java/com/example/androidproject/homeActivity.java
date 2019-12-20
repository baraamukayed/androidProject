package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity {
Button signOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        signOutBtn = findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(homeActivity.this , signInActivity.class);
            startActivity(intent);
        });
    }

}
