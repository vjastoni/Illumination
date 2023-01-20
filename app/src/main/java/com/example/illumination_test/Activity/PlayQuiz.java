package com.example.illumination_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.OOP.CurrentQuiz;
import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Quiz;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayQuiz extends AppCompatActivity {

    public static final String QUIZ = "quiz";

    Quiz quiz;

    ImageButton submitExam;
    Toolbar backButton;
    RadioGroup playQuizOptionContainer;
    RadioButton playQuizOption1, playQuizOption2, playQuizOption3, playQuizOption4;
    MaterialCardView btnNextQuestion, btnPreviousQuestion, playQuizOptionA;
    TextView playQuizQuestion, playQuizQuestionCounter;

    private int questionCounter;
    private int questionCountTotal;
    private CurrentQuiz currentQuestion;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<CurrentQuiz> playQuestions = new ArrayList<>();
    String selectedAns = "";
    ArrayList<String> answers = new ArrayList<>();

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
        btnPreviousQuestion.setEnabled(false);
        playQuizQuestion = findViewById(R.id.playQuizQuestion);
        playQuizOptionContainer = findViewById(R.id.playQuizOptionContainer);
        playQuizOption1 = findViewById(R.id.playQuizOption1);
        playQuizOption2 = findViewById(R.id.playQuizOption2);
        playQuizOption3 = findViewById(R.id.playQuizOption3);
        playQuizOption4 = findViewById(R.id.playQuizOption4);
        playQuizOptionA = findViewById(R.id.playQuizOptionA);

        quiz = getIntent().getParcelableExtra(QUIZ);
        String subject = quiz.getSubject();
        String userId = mAuth.getUid();

        final int[] questionNo = {1};
        questionCounter = -1;

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Users")
                .child(userId)
                .child("Quizzes")
                .child(subject);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot question : snapshot.getChildren()) {
                        if (!(question.getValue().equals(subject))) {
                            Question questions = question.getValue(Question.class);
                            playQuestions.add(new CurrentQuiz(questions.getQuestion(),
                                    questions.getOption1(),
                                    questions.getOption2(),
                                    questions.getOption3(),
                                    questions.getOption4(),
                                    questions.getAnswer(),
                                    question.getKey(),
                                    questions.getSubject()));
                            questionNo[0]++;
                            questionCountTotal = playQuestions.size();

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionCounter++;
                showNextQuestion(questionCounter);
            }
        });

        btnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionCounter--;
                showNextQuestion(questionCounter);
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

        playQuizOptionContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = playQuizOptionContainer.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.playQuizOption1:
                        selectedAns = playQuizOption1.getText().toString();
                        playQuizOption1.setTextColor(Color.parseColor("#667AFF"));
                        playQuizOption2.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption3.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption4.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case R.id.playQuizOption2:
                        selectedAns = playQuizOption2.getText().toString();
                        playQuizOption1.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption2.setTextColor(Color.parseColor("#667aff"));
                        playQuizOption3.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption4.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case R.id.playQuizOption3:
                        selectedAns = playQuizOption3.getText().toString();
                        playQuizOption1.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption2.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption3.setTextColor(Color.parseColor("#667aff"));
                        playQuizOption4.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                    case R.id.playQuizOption4:
                        selectedAns = playQuizOption4.getText().toString();
                        playQuizOption1.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption2.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption3.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption4.setTextColor(Color.parseColor("#667aff"));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showNextQuestion(int number) {
        if(questionCounter == 0 ) {
            btnPreviousQuestion.setEnabled(false);
        }else{
            btnPreviousQuestion.setEnabled(true);
        }
        currentQuestion = playQuestions.get(number);
        playQuizQuestionCounter.setText(number+1 + "/" + questionCountTotal);
        playQuizQuestion.setText(currentQuestion.getQuestion());
        playQuizOption1.setText("a.   " + currentQuestion.getOption1());
        playQuizOption2.setText("b.   " + currentQuestion.getOption2());
        playQuizOption3.setText("c.   " + currentQuestion.getOption3());
        playQuizOption4.setText("d.   " + currentQuestion.getOption4());

    }
}