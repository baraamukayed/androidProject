package com.example.androidproject;

public class Note {

    String id;
    String nootbook_id ;
    String desc;
    String createdAt;
    long lastUpdate;


    public Note(){}

    public Note(String id, String nootbook_id, String desc, String createdAt, long lastUpdate) {
        this.id = id;
        this.nootbook_id = nootbook_id;
        this.desc = desc;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNootbook_id() {
        return nootbook_id;
    }

    public void setNootbook_id(String nootbook_id) {
        this.nootbook_id = nootbook_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
