package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.Note;

public class CreateNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button save_b;
    EditText note_name, note_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        db = new DatabaseHelper(this);
        note_name = findViewById(R.id.ET_note_name);
        note_text = findViewById(R.id.ET_note_text);
        save_b = findViewById(R.id.b_update);
    }

    public void saveNote(View view) {

        String name = note_name.getText().toString();
        String content = note_text.getText().toString();

        if (name.equals("")) Toast.makeText(CreateNoteActivity.this, "Please enter note name",
                Toast.LENGTH_SHORT).show();
        else {
            long result = db.insertNote(new Note(name, content));

            if (result > 0){
                Toast.makeText(CreateNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
            else Toast.makeText(CreateNoteActivity.this, "Error: failed to save the note",
                    Toast.LENGTH_SHORT).show();
        }
    }
}