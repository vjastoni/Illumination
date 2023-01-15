package com.example.illumination_test.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    String sQuestion, sOption1, sOption2, sOption3, sOption4, sAnswer, subject;
    DatabaseReference databaseReference;
    String correctAns = "";
    Boolean changedAnswer = false;
    String questionNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_question);
        subject = getIntent().getStringExtra("Subject");
        questionNo = getIntent().getStringExtra("questionNo");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

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

        System.out.println(questionNo);

        rdgOptionsContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id = rdgOptionsContainer.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbtUpdateOption1:
                        sAnswer = "option1";
                        System.out.println(sAnswer);
                        changedAnswer = true;
                        break;
                    case R.id.rbtUpdateOption2:
                        sAnswer = "option2";
                        System.out.println(sAnswer);
                        changedAnswer = true;
                        break;
                    case R.id.rbtUpdateOption3:
                        sAnswer = "option3";
                        System.out.println(sAnswer);
                        changedAnswer = true;
                        break;
                    case R.id.rbtUpdateOption4:
                        sAnswer = "option4";
                        System.out.println(sAnswer);
                        changedAnswer = true;
                        break;
                    default:
                        System.out.println(sAnswer);
                        changedAnswer = false;
                        break;
                }
            }
        });

        btnUpdateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isQuestionChange() || isOptionAChange() || isOptionBChange() || isOptionCChange() || isOptionDChange() || isAnswerChange()) {
                    Toast.makeText(UpdateQuestion.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateQuestion.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showQuestion() {
        Intent intent = getIntent();

        sQuestion = intent.getStringExtra("question");
        sOption1 = intent.getStringExtra("option1");
        sOption2 = intent.getStringExtra("option2");
        sOption3 = intent.getStringExtra("option3");
        sOption4 = intent.getStringExtra("option4");
        sAnswer = intent.getStringExtra("answer");

        switch (sAnswer){
            case "option1":
                rbtUpdateOption1.setChecked(true);
                break;
            case "option2":
                rbtUpdateOption2.setChecked(true);
                break;
            case "option3":
                rbtUpdateOption3.setChecked(true);
                break;
            case "option4":
                rbtUpdateOption4.setChecked(true);
                break;
            default:
                break;
        }


        updateQuestion.setText(sQuestion);
        updateOption1.setText(sOption1);
        updateOption2.setText(sOption2);
        updateOption3.setText(sOption3);
        updateOption4.setText(sOption4);

    }

    public boolean isQuestionChange() {
        if (!sQuestion.equals(updateQuestion.getText().toString())) {
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("question")
                    .setValue(updateQuestion.getText().toString());
            sQuestion = updateQuestion.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isOptionAChange() {
        if(!sOption1.equals(updateOption1.getText().toString())){
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("option1")
                    .setValue(updateOption1.getText().toString());
            sOption1 = updateOption1.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isOptionBChange() {
        if(!sOption2.equals(updateOption2.getText().toString())){
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("option2")
                    .setValue(updateOption2.getText().toString());
            sOption2 = updateOption2.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isOptionCChange() {
        if(!sOption3.equals(updateOption3.getText().toString())){
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("option3")
                    .setValue(updateOption3.getText().toString());
            sOption3 = updateOption3.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isOptionDChange() {
        if(!sOption4.equals(updateOption4.getText().toString())){
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("option4")
                    .setValue(updateOption4.getText().toString());
            sOption4 = updateOption4.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isAnswerChange() {
        if(changedAnswer){
            databaseReference.child("Quizzes")
                    .child(subject)
                    .child(questionNo)
                    .child("answer")
                    .setValue(sAnswer);
            return true;
        }else{
            System.out.println("No change answer");
            return false;
        }
    }

}