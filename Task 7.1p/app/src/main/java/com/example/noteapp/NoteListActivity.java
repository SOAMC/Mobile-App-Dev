package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.Note;
import com.example.noteapp.util.NotesAdapter;
import java.util.List;

public class NoteListActivity extends AppCompatActivity implements NotesAdapter.OnNoteListener{
    private RecyclerView Note_Lists;
    private NotesAdapter note_adapter;
    private List<Note> notes;
    private TextView note_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        DatabaseHelper db = new DatabaseHelper(this);

        notes = db.getNotes();

        Note_Lists = findViewById(R.id.note_list);
        note_adapter = new NotesAdapter(notes, this, this);
        Note_Lists.setAdapter(note_adapter);
        Note_Lists.setLayoutManager(new LinearLayoutManager(this));

        note_num = findViewById(R.id.noNotesStoredTextView);
        if (notes.isEmpty()) note_num.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(NoteListActivity.this, ModifyNoteActivity.class);
        intent.putExtra("note_pos", notes.get(position));
        startActivity(intent);
        finish();
    }
}