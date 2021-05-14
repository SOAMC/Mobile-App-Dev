package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button create_B, list_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_B = (Button)findViewById(R.id.b_create);
        list_B = (Button)findViewById(R.id.b_show);
    }

    public void createNote(View view) {
        Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent = new Intent(MainActivity.this, NoteListActivity.class);
        startActivity(intent);
    }
}