package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.Note;

public class ModifyNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText ET_note_name, ET_note_text;
    Button update_b, delete_b;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);

        db = new DatabaseHelper(this);
        ET_note_name = findViewById(R.id.ET_note_name_mod);
        ET_note_text = findViewById(R.id.ET_note_text_mod);
        update_b = findViewById(R.id.b_update);
        delete_b = findViewById(R.id.b_delete);

        note = getIntent().getParcelableExtra("note_pos");

        ET_note_name.setText(note.getName());
        ET_note_text.setText(note.getContent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ModifyNoteActivity.this, NoteListActivity.class);
        startActivity(intent);
        finish();
    }


    public void updateNote(View view) {
        int id = note.getId();
        String newName = ET_note_name.getText().toString();
        String newContent = ET_note_text.getText().toString();

        if (newName.equals("")) Toast.makeText(ModifyNoteActivity.this, "Please enter note name",
                Toast.LENGTH_SHORT).show();
        else {
            int rowsAffected = db.updateNote(new Note(id, newName, newContent));

            if (rowsAffected > 0) {
                Toast.makeText(ModifyNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyNoteActivity.this, NoteListActivity.class);
                startActivity(intent);
                finish();   // Since the note is saved, close the activity
            }
            else Toast.makeText(ModifyNoteActivity.this, "Error: failed to save the note",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteNote(View view) {
        int rowsAffected = db.deleteNote(note);

        if (rowsAffected > 0) {
            Toast.makeText(ModifyNoteActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ModifyNoteActivity.this, NoteListActivity.class);
            startActivity(intent);
            finish();
        }
        else Toast.makeText(ModifyNoteActivity.this, "Error: failed to delete",
                Toast.LENGTH_SHORT).show();
    }
}