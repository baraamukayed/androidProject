package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homeActivity extends AppCompatActivity {
Button signOutBtn ,addNoteBookBtn ;
TextView  showAllNotebooks ;
    RecyclerView notebooksList_rv , notesAndBooksList_rv;
    NotebookAdapter notebookAdapter;
    NoteAndNoteBookAdapter noteAndNoteBookAdapter;
    NoteAdapter noteAdapter;
    List<Notebook> notebookList  = new ArrayList<>();
    List<Note> noteList  = new ArrayList<>();




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


        signOutBtn = findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(homeActivity.this , signInActivity.class);
            startActivity(intent);
        });

        /// get data to Notebook

        getData1();

        notebooksList_rv = findViewById(R.id.notebooksList_rv);
//        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        notebooksList_rv.setLayoutManager(linearLayoutManager);
        notebookAdapter = new NotebookAdapter(this ,notebookList);
        notebooksList_rv.setAdapter(notebookAdapter);

        // get data to Notes and Notebook

        getData2();
        notesAndBooksList_rv =findViewById(R.id.notesAndBooksList_rv);
        notesAndBooksList_rv.setLayoutManager(new LinearLayoutManager(this));
        noteAndNoteBookAdapter = new NoteAndNoteBookAdapter(this  , noteList);
        notesAndBooksList_rv.setAdapter(noteAndNoteBookAdapter);



    }

    public void onClickShowAll(View v) {
        showAllNotebooks = findViewById(R.id.showAllNotebooks);
        showAllNotebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeActivity.this , Notebook_list.class);
                startActivity(intent);

            }
        });
    }

    private void getData1() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notebook")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        notebookList.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                            Notebook notebook = snapshot.getValue(Notebook.class);
                            Log.e("notebook",notebook.toString());

                            notebookList.add(notebook);

                        }
                        notebookAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }

    private void getData2() {


    }

}
