package com.example.androidproject;

public class notebook {
    String id;
    String title ;
    long createdAt;


    public notebook(){}

    public notebook(String id, String title,  long createdAt) {
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }


}

