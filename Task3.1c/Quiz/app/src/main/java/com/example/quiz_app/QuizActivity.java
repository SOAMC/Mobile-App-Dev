package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {


    String name;
    int progress = 1;
    int progressMax = 5;       
    int score = 0;
    int current_q = 0;
    int ans = 0;

    ArrayList<String> q_numbers = new ArrayList<String>();
    ArrayList<String> q_texts = new ArrayList<String>();
    ArrayList<String[]> q_answers = new ArrayList<String[]>();
    ArrayList<int[]> q_ans_val = new ArrayList<int[]>();


    TextView name2;
    TextView numb_of_q;
    ProgressBar prog_bar;
    TextView q_num;
    TextView q_text;
    Button Ans_1;
    Button Ans_2;
    Button Ans_3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        name2 = findViewById(R.id.heading2);
        numb_of_q = findViewById(R.id.q_num);
        prog_bar = findViewById(R.id.pro_bar);
        q_num = findViewById(R.id.q_head);
        q_text = findViewById(R.id.q_view);
        Ans_1 = findViewById(R.id.ans_1);
        Ans_2 = findViewById(R.id.ans_2);
        Ans_3 = findViewById(R.id.ans_3);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        prog_bar.setMax(progressMax);
        UpdateName(name);
        UpdateProgress(0);
        PopulateQuiz();
        ShowQuestions();
    }

    public void NextClick(View view) {
        if (progress + 1 > progressMax) {
            CompleteQuiz();
        }
        else{
            UpdateProgress(1);
            ShowQuestions();
        }

    }

    public void AnswerClickOne(View view){

       Check_ans(0, Ans_1);
    }
    public void AnswerClickTwo(View view){

        Check_ans(1, Ans_2);
    }
    public void AnswerClickThree(View view){

        Check_ans(2, Ans_3);
    }


    private void PopulateQuiz(){


        String[] answers;
        int[] ansvalues;

        q_numbers.add("Question 1");
        q_texts.add("Who founded Apple Computer?");
        answers = new String[]{"Stephen Fry","Steve Jobs","Bill Gates"};
        q_answers.add(answers);
        ansvalues = new int[]{0,1,0};
        q_ans_val.add(ansvalues);

        q_numbers.add("Question 2");
        q_texts.add("What does the Internet prefix WWW stand for?");
        answers = new String[]{"Western Washington World","Worldwide Weather","World Wide Web"};
        q_answers.add(answers);
        ansvalues = new int[]{0,0,1};
        q_ans_val.add(ansvalues);

        q_numbers.add("Question 3");
        q_texts.add("Which of these is not a kind of computer?");
        answers = new String[]{"Apple","Lada","Lenovo"};
        q_answers.add(answers);
        ansvalues = new int[]{0,1,0};
        q_ans_val.add(ansvalues);

        q_numbers.add("Question 4");
        q_texts.add("Which of these is not a telephone?");
        answers = new String[]{"Blackberry","Iphone","Ipod"};
        q_answers.add(answers);
        ansvalues = new int[]{0,0,1};
        q_ans_val.add(ansvalues);

        q_numbers.add("Question 5");
        q_texts.add("Will bitcoin(BTC) reach 100k in next 3 years?");
        answers = new String[]{"Yes","No","Maybe"};
        q_answers.add(answers);
        ansvalues = new int[]{0,0,1};
        q_ans_val.add(ansvalues);

    }

    private void Check_ans(int input, Button btn){
        if (ans == current_q) return;

        ans += 1;

        int curIndex = current_q - 1;

        if (q_ans_val.get(curIndex)[input] == 1){
            score += 1;
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.answer_green));
        }
        else {
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.answer_red));

            if (q_ans_val.get(curIndex)[0] == 1)
                Ans_1.setBackgroundColor(ContextCompat.getColor(this, R.color.answer_green));
            if (q_ans_val.get(curIndex)[1] == 1)
                Ans_2.setBackgroundColor(ContextCompat.getColor(this, R.color.answer_green));
            if (q_ans_val.get(curIndex)[2] == 1)
                Ans_3.setBackgroundColor(ContextCompat.getColor(this, R.color.answer_green));
        }

    }

    private void ShowQuestions(){
        current_q += 1;
        int index = current_q - 1;

        Ans_1.setBackgroundColor(ContextCompat.getColor(this, R.color.button_background));
        Ans_2.setBackgroundColor(ContextCompat.getColor(this, R.color.button_background));
        Ans_3.setBackgroundColor(ContextCompat.getColor(this, R.color.button_background));
        q_num.setText(q_numbers.get(index));
        q_text.setText(q_texts.get(index));
        Ans_1.setText(q_answers.get(index)[0]);
        Ans_2.setText(q_answers.get(index)[1]);
        Ans_3.setText(q_answers.get(index)[2]);
    }

    private void UpdateName(String name){
        name2.setText("Welcome " + name);
    }

    private void UpdateProgress(int step){
        progress = progress + step;
        prog_bar.setProgress(progress);
        numb_of_q.setText(progress + "/" + progressMax);
    }

    public void CompleteQuiz(){
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("Name", name);
        intent.putExtra("Score", score);
        intent.putExtra("ScoreMax", progressMax);
        startActivity(intent);
        finish();
    }
}