package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homeActivity extends AppCompatActivity {
Button signOutBtn ,addNoteBookBtn ;
TextView createNoteBook ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addNoteBookBtn = findViewById(R.id.addNoteBookBtn);
        addNoteBookBtn.setOnClickListener(v->{
            Intent intent = new Intent(homeActivity.this , addNoteBook.class);
            startActivity(intent);
        });

        createNoteBook = findViewById(R.id.createNoteBook);
        createNoteBook.setOnClickListener(v->{
            Intent intent = new Intent(homeActivity.this , addNoteBook.class);
            startActivity(intent);
        });
        signOutBtn = findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(homeActivity.this , signInActivity.class);
            startActivity(intent);
        });



    }

}
