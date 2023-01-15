package com.example.illumination_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.RecViewClass.EditQuestionRecViewAdapter;
import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Quiz;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditQuestion extends AppCompatActivity {

    private RecyclerView editQuestionRecView;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;
    Toolbar editToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String subject = getIntent().getStringExtra("Subject");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);
        ArrayList<QuestionEdit> questionEdits = new ArrayList<>();
        final int[] questionNo = {1};

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                .child(user.getUid())
                .child("Quizzes")
                .child(subject);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot question : snapshot.getChildren()) {
                        if (!(question.getValue().equals(subject))) {
                            Question questions = question.getValue(Question.class);
                            questionEdits.add(new QuestionEdit(String.valueOf(questionNo[0]), questions.getQuestion(),
                                    questions.getOption1(), questions.getOption2(),
                                    questions.getOption3(), questions.getOption4(),
                                    questions.getAnswer(), subject, question.getKey()));
                            EditQuestionRecViewAdapter adapter = new EditQuestionRecViewAdapter(EditQuestion.this);
                            adapter.setQuestionEdits(questionEdits);
                            questionNo[0]++;
                            editQuestionRecView.setAdapter(adapter);
                            editQuestionRecView.setLayoutManager(new GridLayoutManager(EditQuestion.this, 2));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editQuestionRecView = findViewById(R.id.editQuestionRecView);
        editToolbar = findViewById(R.id.editToolbar);

        editToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}