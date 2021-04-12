package com.example.a31c_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    String name;
    int score;
    int scoreMax;

    TextView name2;
    TextView result_;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        name2 = findViewById(R.id.heading2);
        result_ = findViewById(R.id.result);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        score = intent.getIntExtra("Score", 0);
        scoreMax = intent.getIntExtra("ScoreMax", 0);

        UpdateName();
        UpdateScore(score, scoreMax);
    }

    private void UpdateName(){
        name2.setText("Congratulations " + name + "!");
    }

    public void UpdateScore(int score, int scoreMax){
        result_.setText(score + "/" + scoreMax);
    }

    public void RestartClick(View view) {
        finish();
    }

    public void FinishClick(View view) {
        this.finishAffinity();
    }
}