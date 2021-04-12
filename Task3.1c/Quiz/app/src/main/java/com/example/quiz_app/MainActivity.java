package com.example.a31c_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Button bstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.input_name);
        bstart = findViewById(R.id.button_start);

    }

    public void StartClick(View view){
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("Name", name.getText().toString());
        startActivity(intent);
    }
}