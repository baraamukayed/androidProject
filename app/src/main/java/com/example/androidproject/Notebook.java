package com.example.androidproject;

import com.google.firebase.database.FirebaseDatabase;

public class Notebook {
    String id;
    String title ;
    String createdAt;


    public Notebook(){}

    public Notebook(String id, String title, String createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public  static  String genarateID(){
        return FirebaseDatabase.getInstance().getReference().child("User").child("Notebook").push().getKey();
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

