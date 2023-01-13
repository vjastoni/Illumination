package com.example.illumination_test.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UpdateQuestion extends AppCompatActivity {


    public static final String QUESTION = "question";
    Question question;
    Toolbar addQuestionToolbar;
    TextView totalOfQuestion;
    TextInputEditText edtQuestion, edtOption1, edtOption2, edtOption3, edtOption4;
    RadioButton rbtOption1, rbtOption2, rbtOption3, rbtOption4;
    RadioGroup rdgOptionsContainer;
    MaterialButton btnUpdateQuestion;
    int choice = 0;
    String correctAns = "";
    long questionSize = 1;

    private List<Question> questionList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        question = getIntent().getParcelableExtra(QUESTION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        DatabaseReference databaseReference;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        totalOfQuestion = findViewById(R.id.totalOfQuestion);

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
        btnUpdateQuestion = findViewById(R.id.btnUpdateQuestion);

        rdgOptionsContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int id = rdgOptionsContainer.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rbtOption1:
                        correctAns = "option" + 1;
                        break;
                    case R.id.rbtOption2:
                        correctAns = "option" + 2;
                        break;
                    case R.id.rbtOption3:
                        correctAns = "option" + 3;
                        break;
                    case R.id.rbtOption4:
                        correctAns = "option" + 4;
                        break;
                    default:
                        // Your code
                        break;
                }
            }
        });

        btnUpdateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionNo = "Question"+questionSize;

                Question question = new Question(edtQuestion.getText().toString(),
                        edtOption1.getText().toString(),
                        edtOption2.getText().toString(),
                        edtOption3.getText().toString(),
                        edtOption4.getText().toString(),
                        correctAns);


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