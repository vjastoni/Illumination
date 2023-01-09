package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Quiz;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddQuestion extends AppCompatActivity {

    Toolbar addQuestionToolbar;
    TextInputEditText edtQuestion, edtOption1, edtOption2, edtOption3, edtOption4;
    RadioButton rbtOption1, rbtOption2, rbtOption3, rbtOption4;
    RadioGroup rdgOptionsContainer;
    MaterialButton btnAddQuestion;
    int choice = 0;

    private List<Question> questionList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        addQuestionToolbar = findViewById(R.id.addQuestionToolbar);
        edtQuestion = findViewById(R.id.edtQuestion);
        edtOption1 = findViewById(R.id.edtOption1);
        edtOption2 = findViewById(R.id.edtOption2);
        edtOption3 = findViewById(R.id.edtOption3);
        edtOption4 = findViewById(R.id.edtOption4);
        rdgOptionsContainer = findViewById(R.id.rdgOptionsContainer);
        rbtOption1 = findViewById(R.id.rbtOption1);
        rbtOption2 = findViewById(R.id.rbtOption2);
        rbtOption3 = findViewById(R.id.rbtOption3);
        rbtOption4 = findViewById(R.id.rbtOption4);
        btnAddQuestion = findViewById(R.id.btnAddQuestion);

        rdgOptionsContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id = rdgOptionsContainer.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbtOption1:
                        choice = 1;
                        break;
                    case R.id.rbtOption2:
                        choice = 2;
                        break;
                    case R.id.rbtOption3:
                        choice = 3;
                        break;
                    case R.id.rbtOption4:
                        choice = 4;
                        break;
                    default:
                        // Your code
                        break;
                }
            }
        });

        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getApplicationContext(), "Successfully added!", Toast.LENGTH_SHORT).show();


                edtQuestion.setText("");
                edtOption1.setText("");
                edtOption2.setText("");
                edtOption3.setText("");
                edtOption4.setText("");

            }
        });

        addQuestionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}