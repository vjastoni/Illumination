package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.illumination_test.Dialog.DialogAddCategory;
import com.example.illumination_test.Dialog.DialogAddSubCategory;
import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Quiz;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddEditQuestion extends AppCompatActivity {

    public static final String QUIZ = "quiz";

    Quiz quiz;

    Toolbar addAndEditToolbar;
    TextView categoryLabel;
    MaterialCardView buttonAddQuestion, buttonEditQuestion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_question);

        quiz = getIntent().getParcelableExtra(QUIZ);

        addAndEditToolbar = findViewById(R.id.addAndEditToolbar);
        buttonAddQuestion = findViewById(R.id.buttonAddQuestion);
        buttonEditQuestion = findViewById(R.id.buttonEditQuestion);
        categoryLabel = findViewById(R.id.categoryLabel);

        categoryLabel.setText(quiz.getSubject());

        buttonEditQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editQuestion = new Intent(getApplicationContext(), EditQuestion.class);
                startActivity(editQuestion);
            }
        });

        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQuestion = new Intent(getApplicationContext(), AddQuestion.class);
                startActivity(addQuestion);
            }
        });

        addAndEditToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}