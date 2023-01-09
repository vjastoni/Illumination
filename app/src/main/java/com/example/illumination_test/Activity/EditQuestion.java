package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.illumination_test.RecViewClass.EditQuestionRecViewAdapter;
import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.R;

import java.util.ArrayList;

public class EditQuestion extends AppCompatActivity {

    private RecyclerView editQuestionRecView;
    Toolbar editToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        editQuestionRecView = findViewById(R.id.editQuestionRecView);
        editToolbar = findViewById(R.id.editToolbar);

        editToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArrayList<QuestionEdit> questionEdits = new ArrayList<>();
        questionEdits.add(new QuestionEdit("1", "What is the sum of 3 + 2?"));
        questionEdits.add(new QuestionEdit("2", "Add 8.563 and 4.8292."));
        questionEdits.add(new QuestionEdit("3", "What is 18 divided by 3?"));
        questionEdits.add(new QuestionEdit("4", "What is the square root of 64?"));
        questionEdits.add(new QuestionEdit("5", "What is 33 * 7?"));
        questionEdits.add(new QuestionEdit("6", "What's pi?"));
        questionEdits.add(new QuestionEdit("7", "What's 5 - 6?"));
        questionEdits.add(new QuestionEdit("8", "What is 8 * 4?"));
        questionEdits.add(new QuestionEdit("9", "Can you tell us what 4³ is?"));
        questionEdits.add(new QuestionEdit("10", "What's answer to √16"));
        questionEdits.add(new QuestionEdit("11", "What is 3 * 5 divided by 3?"));
        questionEdits.add(new QuestionEdit("12", "What is 1 * 69"));


        EditQuestionRecViewAdapter adapter = new EditQuestionRecViewAdapter(this);
        adapter.setQuestionEdits(questionEdits);

        editQuestionRecView.setAdapter(adapter);
        editQuestionRecView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}