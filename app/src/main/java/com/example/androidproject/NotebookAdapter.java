package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.NotebookVh> {

    Context context ;
    List<Notebook> notebookList;

    public NotebookAdapter(Context context ,  List<Notebook> notebookList) {
        this.context = context;
        this.notebookList = notebookList;
    }

    @NonNull
    @Override
    public NotebookVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_row_notebook , parent , false);
        return new NotebookVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookVh holder, int position) {
        holder.setData(notebookList.get(position));
    }

    @Override
    public int getItemCount() {
        return notebookList.size();
    }

    class NotebookVh extends RecyclerView.ViewHolder{
        TextView notebookName ;
        public NotebookVh(@NonNull View itemView) {
            super(itemView);
            notebookName = itemView.findViewById(R.id.notebookName);
//            notebookCreatedAt = itemView.findViewById(R.id.notebookCreatedAt);

        }

        public void setData(Notebook notebook) {
            notebookName.setText(notebook.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context , special_notebook.class);
                    intent.putExtra("id",notebook.getId().toString());
                    context.startActivity(intent);
                }
            });
//            notebookCreatedAt.setText(notebook.getCreatedAt()+"");

        }
    }
}
