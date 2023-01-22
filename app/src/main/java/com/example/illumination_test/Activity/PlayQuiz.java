package com.example.illumination_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
import android.widget.Toast;

import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.OOP.CurrentQuiz;
import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.OOP.Score;
import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Quiz;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayQuiz extends AppCompatActivity {

    public static final String QUIZ = "quiz";

    Quiz quiz;

    ImageButton submitExam, btnFavorites;
    Toolbar backButton;
    RadioGroup playQuizOptionContainer;
    RadioButton playQuizOption1, playQuizOption2, playQuizOption3, playQuizOption4;
    MaterialCardView btnNextQuestion, btnPreviousQuestion;
    TextView playQuizQuestion, playQuizQuestionCounter;

    private int questionCounter;
    private int questionCountTotal;
    private CurrentQuiz currentQuestion;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<CurrentQuiz> playQuestions = new ArrayList<>();
    String selectedAns = "";
    ArrayList<String> answers;
    Score scores;

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
        btnFavorites = findViewById(R.id.btnFavorites);

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
                            answers = new ArrayList<>(Collections.nCopies(questionCountTotal, ""));

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PlayQuiz.this, "Saved to Favorites.", Toast.LENGTH_SHORT).show();
            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionCounter != -1 && !answers.isEmpty()){
                    setNewAnswer(selectedAns, questionCounter);
                }
                questionCounter++;
                if(questionCounter > questionCountTotal - 1 ){
                    finishQuiz();
                    return;
                }
                showNextQuestion(questionCounter);
                if(!answers.isEmpty() && answers.size() > questionCounter){
                    showAnswer(questionCounter);
                }
            }
        });

        btnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(questionCounter > questionCountTotal - 1)) setNewAnswer(selectedAns, questionCounter);
                questionCounter--;
                showNextQuestion(questionCounter);
                showAnswer(questionCounter);
            }
        });

        submitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswers();

                Intent intent = new Intent(getApplicationContext(), ScoreResult.class);
                intent.putExtra("total", scores.getTotal());
                intent.putExtra("correct", scores.getCorrectAns());
                intent.putExtra("wrong", scores.getWrongAns());
                intent.putExtra("noans", scores.getNoAns());
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
                        playQuizOption1.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption2.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption3.setTextColor(Color.parseColor("#FFFFFF"));
                        playQuizOption4.setTextColor(Color.parseColor("#FFFFFF"));
                        break;
                }
            }
        });
    }

    private void showNextQuestion(int pointer) {
        submitExam.setEnabled(false);
        btnNextQuestion.setEnabled(true);
        playQuizOptionContainer.clearCheck();
        if(questionCounter == 0 ) {
            btnPreviousQuestion.setEnabled(false);
        }else{
            btnPreviousQuestion.setEnabled(true);
        }
        currentQuestion = playQuestions.get(pointer);
        playQuizQuestionCounter.setText(pointer+1 + "/" + questionCountTotal);
        playQuizQuestion.setText(currentQuestion.getQuestion());
        playQuizOption1.setText(currentQuestion.getOption1());
        playQuizOption1.setClickable(true);
        playQuizOption2.setText(currentQuestion.getOption2());
        playQuizOption2.setClickable(true);
        playQuizOption3.setText(currentQuestion.getOption3());
        playQuizOption3.setClickable(true);
        playQuizOption4.setText(currentQuestion.getOption4());
        playQuizOption4.setClickable(true);

    }

    private void setNewAnswer(String answer, int pointer){
        answers.set(pointer, answer);
    }

    private void showAnswer(int pointer){
        String currentAns = answers.get(pointer);

        if(currentAns.equals(currentQuestion.getOption1())){
            selectedAns = currentAns;
            playQuizOption1.setChecked(true);
        }else if(currentAns.equals(currentQuestion.getOption2())){
            selectedAns = currentAns;
            playQuizOption2.setChecked(true);
        }else if(currentAns.equals(currentQuestion.getOption3())){
            selectedAns = currentAns;
            playQuizOption3.setChecked(true);
        }else if(currentAns.equals(currentQuestion.getOption4())){
            selectedAns = currentAns;
            playQuizOption4.setChecked(true);
        }else{
            selectedAns = "";
            playQuizOptionContainer.clearCheck();
        }
    }

    private void finishQuiz(){
        submitExam.setEnabled(true);
        btnNextQuestion.setEnabled(false);
        playQuizOptionContainer.clearCheck();
        playQuizQuestion.setText("End of quiz, tap check button to finish quiz.");
        playQuizOption1.setClickable(false);
        playQuizOption1.setText("");
        playQuizOption2.setClickable(false);
        playQuizOption2.setText("");
        playQuizOption3.setClickable(false);
        playQuizOption3.setText("");
        playQuizOption4.setClickable(false);
        playQuizOption4.setText("");
    }

    private void checkAnswers(){
        int correctAns = 0;
        int wrongAns = 0;
        int noAns = 0;
        int total = 0;
        for(int i = 0; i < answers.size(); i++){
            CurrentQuiz question = playQuestions.get(i);
            if(answers.get(i).equals(question.getCorrectAns())){
                correctAns++;
            }else{
                wrongAns++;
            }
            if(answers.get(i).equals("")){
                noAns++;
            }
            total = questionCountTotal;
        }

        scores = new Score(total, correctAns, wrongAns, noAns);
    }
}