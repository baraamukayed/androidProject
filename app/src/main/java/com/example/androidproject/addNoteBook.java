package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class addNoteBook extends AppCompatActivity {

    Button saveBtn;
    EditText noteBookET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_book);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        noteBookET = findViewById(R.id.noteBookET);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v->{

            notebook noteBook = new notebook();
            noteBook.setCreatedAt(new Date().getTime());
            noteBook.setTitle(noteBookET.toString());

            String id = FirebaseDatabase.getInstance().getReference().child("Notebook").push().getKey();
            noteBook.setId(id);
            FirebaseDatabase.getInstance().getReference().child("Notebook").child(id).setValue(noteBook);



            Intent intent = new Intent(addNoteBook.this , homeActivity.class);
            startActivity(intent);
        });

        FirebaseDatabase.getInstance().getReference().child("Notebook").child("-93bQdqiEzTYOI7Qihu4PXMzozgK2")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Long createdAt = dataSnapshot.child("createdAt").getValue(Long.class);
                        String id = dataSnapshot.child("id").getValue(String.class);
                        String title = dataSnapshot.child("title").getValue(String.class);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {    }
                });

    }
}
