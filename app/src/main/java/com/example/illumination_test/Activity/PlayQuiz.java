package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.R;
import com.google.android.material.card.MaterialCardView;

import java.util.Collections;
import java.util.List;

public class PlayQuiz extends AppCompatActivity {

    ImageButton submitExam;
    Toolbar backButton;
    RadioGroup playQuizOptionContainer;
    RadioButton playQuizOption1, playQuizOption2, playQuizOption3, playQuizOption4;
    MaterialCardView btnNextQuestion, btnPreviousQuestion;
    TextView playQuizQuestion, playQuizQuestionCounter;

    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        submitExam = findViewById(R.id.submitExam);
        backButton = findViewById(R.id.backButton);
        playQuizQuestionCounter = findViewById(R.id.playQuizQuestionCounter);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        btnPreviousQuestion = findViewById(R.id.btnPreviousQuestion);
        playQuizQuestion = findViewById(R.id.playQuizQuestion);
        playQuizOptionContainer = findViewById(R.id.playQuizOptionContainer);
        playQuizOption1 = findViewById(R.id.playQuizOption1);
        playQuizOption2 = findViewById(R.id.playQuizOption2);
        playQuizOption3 = findViewById(R.id.playQuizOption3);
        playQuizOption4 = findViewById(R.id.playQuizOption4);

        textColorDefaultRb = playQuizOption1.getTextColors();

        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showQuestion();

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });

        btnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPreviousQuestion();
            }
        });

        submitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScoreResult.class);
                startActivity(intent);
            }
        });

        backButton.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void showQuestion(){
//        currentQuestion = questionList.get(questionCounter);
//
//        playQuizQuestion.setText(currentQuestion.getQuestion());
//        playQuizOption1.setText(currentQuestion.getOption1());
//        playQuizOption2.setText(currentQuestion.getOption2());
//        playQuizOption3.setText(currentQuestion.getOption3());
//        playQuizOption4.setText(currentQuestion.getOption4());
//
//        questionCounter++;
//        playQuizQuestionCounter.setText(questionCounter + "/" + questionCountTotal);
    }

    private void showNextQuestion(){
//        if (questionCounter == 1){
//            btnPreviousQuestion.setEnabled(false);
//        }
//
//        playQuizOption1.setTextColor(textColorDefaultRb);
//        playQuizOption2.setTextColor(textColorDefaultRb);
//        playQuizOption3.setTextColor(textColorDefaultRb);
//        playQuizOption4.setTextColor(textColorDefaultRb);
//        playQuizOptionContainer.clearCheck();
//
//        if(questionCounter < questionCountTotal){
//            currentQuestion = questionList.get(questionCounter);
//
//            playQuizQuestion.setText(currentQuestion.getQuestion());
//            playQuizOption1.setText(currentQuestion.getOption1());
//            playQuizOption2.setText(currentQuestion.getOption2());
//            playQuizOption3.setText(currentQuestion.getOption3());
//            playQuizOption4.setText(currentQuestion.getOption4());
//
//            questionCounter++;
//            playQuizQuestionCounter.setText(questionCounter + "/" + questionCountTotal);
//        }else{
//            finishQuiz();
//        }
    }

    private void showPreviousQuestion(){
//        playQuizOption1.setTextColor(textColorDefaultRb);
//        playQuizOption2.setTextColor(textColorDefaultRb);
//        playQuizOption3.setTextColor(textColorDefaultRb);
//        playQuizOption4.setTextColor(textColorDefaultRb);
//
//        if(questionCounter < questionCountTotal){
//            currentQuestion = questionList.get(questionCounter);
//
//            playQuizQuestion.setText(currentQuestion.getQuestion());
//            playQuizOption1.setText(currentQuestion.getOption1());
//            playQuizOption2.setText(currentQuestion.getOption2());
//            playQuizOption3.setText(currentQuestion.getOption3());
//            playQuizOption4.setText(currentQuestion.getOption4());
//
//            questionCounter--;
//            playQuizQuestionCounter.setText(questionCounter + "/" + questionCountTotal);
//        }else{
//            finish();
//        }
    }

    private void finishQuiz(){
        finish();
    }
}