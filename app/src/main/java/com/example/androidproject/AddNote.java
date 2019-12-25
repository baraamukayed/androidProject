package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity {

    EditText noteET;
    TextView onClickCancel;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        noteET = findViewById(R.id.noteET);
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v->{

            Note note = new Note();
//            Timestamp timestamp = new Timestamp(new Date());
//            Log.e("time" , timestamp.toString());
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            note.setCreatedAt(formattedDate);
            note.setDesc(noteET.getText().toString());

            String idd = getIntent().getExtras().getString("id");
            note.setNootbook_id(idd);


            String id = FirebaseDatabase.getInstance().getReference().child("Notebook").child("Note").push().getKey();
            note.setId(id);
            FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notebook").child(idd).child("Note").child(note.getId()).setValue(note);


            Intent intent = new Intent(AddNote.this , Notebook_list.class);
            startActivity(intent);
        });



    }

    public void onClickCancel(View v) {
        onClickCancel = findViewById(R.id.cancelTV);
        onClickCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNote.this , special_notebook.class);
                startActivity(intent);

            }
        });
    }
}

