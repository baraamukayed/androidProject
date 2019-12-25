package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class special_notebook extends AppCompatActivity {


    TextView notebookNameTV;

    ImageButton returnIB ,addNoteIB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_notebook);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        String id = getIntent().getExtras().getString("id");

        addNoteIB = findViewById(R.id.addNoteIB);
        addNoteIB.setOnClickListener(v->{
            Intent intent = new Intent(special_notebook.this , AddNote.class);
            intent.putExtra("id",id);

            startActivity(intent);
        });




        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notebook").child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Notebook information
                        Notebook notebook = dataSnapshot.getValue(Notebook.class);
                        String nameNotebook = notebook.getTitle();
                        notebookNameTV = findViewById(R.id.notebookNameTV);
                        notebookNameTV.setText(nameNotebook+" Notebook");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    }

