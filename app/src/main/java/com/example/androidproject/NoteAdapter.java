package com.example.androidproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVh> {

    Context context ;
    List<Note> notes;

    public NoteAdapter(Context context , List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.activity_row_note , parent , false);
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
        TextView  noteDesc , noteCreatedAtTv;
        public NoteVh(@NonNull View itemView) {
            super(itemView);
            noteDesc = itemView.findViewById(R.id.noteDescTv);
            noteCreatedAtTv = itemView.findViewById(R.id.noteCreatedAtTv);

        }

        public void setData(Note note) {
            noteDesc.setText(note.getDesc());
            noteCreatedAtTv.setText(note.getCreatedAt()+"");

        }
    }
}
