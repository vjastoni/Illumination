package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.illumination_test.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateQuestion extends AppCompatActivity {

    Toolbar addQuestionToolbar;
    TextView totalOfQuestion;
    TextInputEditText updateQuestion, updateOption1, updateOption2, updateOption3, updateOption4;
    RadioButton rbtUpdateOption1, rbtUpdateOption2, rbtUpdateOption3, rbtUpdateOption4;
    RadioGroup rdgOptionsContainer;
    MaterialButton btnUpdateQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);

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
        
    }
}