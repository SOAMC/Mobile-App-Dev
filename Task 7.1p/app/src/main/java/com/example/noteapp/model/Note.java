package com.example.noteapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    // Instance variables
    private int id;
    private String name, content;

    // Constructors
    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Note(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Parcelable stuff
    protected Note(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(content);
    }
}
