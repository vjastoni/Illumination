package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.illumination_test.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateQuestion extends AppCompatActivity {

    Toolbar addQuestionToolbar;
    TextView totalOfQuestion;
    TextInputEditText updateOption1;
    TextInputEditText updateOption2;
    TextInputEditText updateOption3;
    TextInputEditText updateOption4;
    TextInputEditText updateQuestion;
    RadioButton rbtUpdateOption1, rbtUpdateOption2, rbtUpdateOption3, rbtUpdateOption4;
    RadioGroup rdgOptionsContainer;
    MaterialButton btnUpdateQuestion;
    String sQuestion, sOption1, sOption2, sOption3, sOption4, sAnswer;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

//        String subject = getIntent().getStringExtra("Subject");
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Quizzes").child(subject);

        addQuestionToolbar = findViewById(R.id.addQuestionToolbar);
        totalOfQuestion = findViewById(R.id.totalOfQuestion);
        updateQuestion = findViewById(R.id.updateQuestion);
        updateOption1 = findViewById(R.id.updateOption1);
        updateOption2 = findViewById(R.id.updateOption2);
        updateOption3 = findViewById(R.id.updateOption3);
        updateOption4 = findViewById(R.id.updateOption4);
        rdgOptionsContainer = findViewById(R.id.rdgOptionsContainer);
        rbtUpdateOption1 = findViewById(R.id.rbtUpdateOption1);
        rbtUpdateOption2 = findViewById(R.id.rbtUpdateOption2);
        rbtUpdateOption3 = findViewById(R.id.rbtUpdateOption3);
        rbtUpdateOption4 = findViewById(R.id.rbtUpdateOption4);
        btnUpdateQuestion = findViewById(R.id.btnUpdateQuestion);

        showQuestion();

    }

    public void showQuestion() {
        Intent intent = getIntent();

        sQuestion = intent.getStringExtra("question");
        sOption1 = intent.getStringExtra("option1");
        sOption2 = intent.getStringExtra("option2");
        sOption3 = intent.getStringExtra("option3");
        sOption4 = intent.getStringExtra("option4");


        updateQuestion.setText(sQuestion);
        updateOption1.setText(sOption1);
        updateOption2.setText(sOption2);
        updateOption3.setText(sOption3);
        updateOption4.setText(sOption4);

    }
}