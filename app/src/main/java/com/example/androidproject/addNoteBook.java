package com.example.androidproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addNoteBook extends AppCompatActivity {

    Button saveBtn;
    EditText noteBookET;
    TextView cancelTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_book);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        noteBookET = findViewById(R.id.noteBookET);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v->{

            Notebook noteBook = new Notebook();
//            Timestamp timestamp = new Timestamp(new Date());
//            Log.e("time" , timestamp.toString());
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c);
            noteBook.setCreatedAt(formattedDate);
            noteBook.setTitle(noteBookET.getText().toString());

            String id = FirebaseDatabase.getInstance().getReference().child("Notebook").push().getKey();
            noteBook.setId(id);
            FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Notebook").child(noteBook.genarateID()).setValue(noteBook);


            Intent intent = new Intent(addNoteBook.this , homeActivity.class);
            startActivity(intent);
        });



    }

    public void onClickCancel(View v) {
        cancelTV = findViewById(R.id.cancelTV);
        cancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addNoteBook.this , homeActivity.class);
                startActivity(intent);

            }
        });
    }
}
