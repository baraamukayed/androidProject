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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notebook_list extends AppCompatActivity {

    RecyclerView notebooksList_rv;
    NotebookAdapter notebookAdapter;
    ImageButton addNotebookIB ,returnIB;

    List<Notebook> notebookList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        addNotebookIB = findViewById(R.id.addNotebookIB);
        addNotebookIB.setOnClickListener(v->{
            Intent intent = new Intent(Notebook_list.this , addNoteBook.class);
            startActivity(intent);
        });

        returnIB = findViewById(R.id.returnIB);
        returnIB.setOnClickListener(v->{
            Intent intent = new Intent(Notebook_list.this , homeActivity.class);
            startActivity(intent);
        });

        getData();

        notebooksList_rv = findViewById(R.id.notebooksList_rv);
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,3);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        notebooksList_rv.setLayoutManager(layoutManager);
        notebookAdapter = new NotebookAdapter(this ,notebookList);
        notebooksList_rv.setAdapter(notebookAdapter);
    }


    private void getData() {
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


}
