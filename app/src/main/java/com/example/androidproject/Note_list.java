package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Note_list extends AppCompatActivity {

    RecyclerView notesList_rv;
    NoteAdapter noteAdapter;
    ImageButton returnIB;

    List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        returnIB = findViewById(R.id.returnIB);
        returnIB.setOnClickListener(v->{
            Intent intent = new Intent(Note_list.this , homeActivity.class);
            startActivity(intent);
        });



        getData();


        notesList_rv = findViewById(R.id.notesList_rv);
        notesList_rv.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this ,noteList);
        notesList_rv.setAdapter(noteAdapter);
    }

    private void getData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notebook").child("Note")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        noteList.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                            Note note = snapshot.getValue(Note.class);
                            noteList.add(note);

                        }
                        noteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }
}
