package com.example.noteapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.noteapp.model.Note;
import com.example.noteapp.util.DbInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, DbInfo.DATABASE_NAME, null, DbInfo.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // if something's not right, maybe the spaces in the parentheses should be removed
        // Query
        String CREATE_NOTE_TABLE = "CREATE TABLE " + DbInfo.TABLE_NAME + "(" +
                DbInfo.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DbInfo.NOTE_NAME +
                " TEXT," + DbInfo.NOTE_CONTENT + " TEXT)";

        // Execute query
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop/delete existing table
        String DROP_TABLE = "DROP TABLE " + DbInfo.TABLE_NAME;
        db.execSQL(DROP_TABLE, new String[]{ DbInfo.TABLE_NAME });

        // Create a new one
        onCreate(db);
    }

    // Method to insert a new note to the table
    public long insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Put values to be inserted to contentValues
        values.put(DbInfo.NOTE_NAME, note.getName());
        values.put(DbInfo.NOTE_CONTENT, note.getContent());

        long newRowId = db.insert(DbInfo.TABLE_NAME, null, values);
        db.close(); // Close the database to prevent memory leak

        return newRowId;
    }

    // Method to update a note in a database
    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Put values to be updated to contentValues
        values.put(DbInfo.NOTE_ID, note.getId());
        values.put(DbInfo.NOTE_NAME, note.getName());
        values.put(DbInfo.NOTE_CONTENT, note.getContent());

        // Update the record where the note id matches with the id of current selected note
        int rowsAffected = db.update(DbInfo.TABLE_NAME, values, DbInfo.NOTE_ID + "=?",
                new String[]{ String.valueOf(note.getId()) });
        db.close(); // Close the database to prevent memory leak

        return rowsAffected;
    }

    // Method to delete a note from the database
    public int deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete the record where the note id matches with the id of current selected note
        int rowsAffected = db.delete(DbInfo.TABLE_NAME, DbInfo.NOTE_ID + "=?",
                new String[]{ String.valueOf(note.getId()) });
        db.close(); // Close the database to prevent memory leak

        return rowsAffected;
    }

    // Method to get all notes stored in the database
    public List<Note> getNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> notes = new ArrayList<>();   // List to store all notes from the database

        String query = "SELECT * FROM " + DbInfo.TABLE_NAME;  // Select all fields from the table
        Cursor cursor = db.rawQuery(query, null);

        // Get and add each row to notes list
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                // Get note id, name and content from the the table
                int id = cursor.getInt(cursor.getColumnIndex(DbInfo.NOTE_ID));
                String name = cursor.getString(cursor.getColumnIndex(DbInfo.NOTE_NAME));
                String content = cursor.getString(cursor.getColumnIndex(DbInfo.NOTE_CONTENT));

                // Add it to the note list
                notes.add(new Note(id, name, content));

                cursor.moveToNext();
            }
        }

        // Close both cursor and database to prevent memory leak
        cursor.close();
        db.close();

        // Return the notes
        return notes;
    }
}
