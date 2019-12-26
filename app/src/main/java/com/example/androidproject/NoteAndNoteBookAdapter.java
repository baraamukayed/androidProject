package com.example.androidproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NoteAndNoteBookAdapter extends RecyclerView.Adapter<NoteAndNoteBookAdapter.NoteVh> {

    Context context ;
    List<Note> notes;

    public NoteAndNoteBookAdapter(Context context , List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.activity_row_notes_and_books , parent , false);
        return new NoteVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        holder.setData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteVh extends RecyclerView.ViewHolder{
        TextView noteBookNameTv , noteDescTv , noteCreatedAtTv;
        public NoteVh(@NonNull View itemView) {
            super(itemView);
            noteDescTv = itemView.findViewById(R.id.noteDescTv);
            noteCreatedAtTv = itemView.findViewById(R.id.noteCreatedAtTv);
            noteBookNameTv = itemView.findViewById(R.id.noteBookNameTv);

        }

        public void setData(Note note) {
            noteDescTv.setText(note.getDesc());
            noteCreatedAtTv.setText(note.getCreatedAt()+"");
            String x = note.getNootbook_id();
            noteBookNameTv.setText(FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("Notebook").child(x).child("title").toString());


        }
    }
}
