package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.illumination_test.OOP.Score;
import com.example.illumination_test.R;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class ScoreResult extends AppCompatActivity {

    Toolbar editToolbar;
    TextView totalScore, scorePercent, totalQuestions, correctAnsNo, wrongAnsNo, noAnsNo;
    MaterialButton reAttempt;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);

        Intent intent = getIntent();
        int total, correctAns, wrongAns, noAns;
        total = intent.getIntExtra("total", 0);
        correctAns = intent.getIntExtra("correct", 0);
        wrongAns = intent.getIntExtra("wrong", 0);
        noAns = intent.getIntExtra("noans", 0);

        float percent = (float) correctAns / total * 100;

        editToolbar = findViewById(R.id.editToolbar);

        totalScore = findViewById(R.id.totalScore);
        scorePercent = findViewById(R.id.scorePercent);
        totalQuestions = findViewById(R.id.totalQuestions);
        correctAnsNo = findViewById(R.id.correctAnsNo);
        wrongAnsNo = findViewById(R.id.wrongAnsNo);
        noAnsNo = findViewById(R.id.noAnsNo);
        reAttempt = findViewById(R.id.reAttempt);

        totalScore.setText(correctAns + "/" + total);
        scorePercent.setText(df.format(percent) + "%");
        totalQuestions.setText(String.valueOf(total));
        correctAnsNo.setText(String.valueOf(correctAns));
        wrongAnsNo.setText(String.valueOf(wrongAns));
        noAnsNo.setText(String.valueOf(noAns));

        reAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        editToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}