package com.example.illumination_test.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.illumination_test.OOP.User;
import com.example.illumination_test.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private TextView loginTV;
    private Button registerBTN;
    private EditText emailET, passwordET, confirmPassET;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        setContentView(R.layout.activity_register);
        loginTV = findViewById(R.id.loginTV);
        registerBTN = findViewById(R.id.registerBTN);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        confirmPassET = findViewById(R.id.confirmPassET);
        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String pwd = passwordET.getText().toString();
                String confirmPwd = confirmPassET.getText().toString();

                if (!pwd.equals(confirmPwd)) {
                    Toast.makeText(Register.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(confirmPwd)) {
                    Toast.makeText(Register.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    databaseReference.child(mAuth.getUid()).setValue(new User(email, email.substring(0, email.indexOf("@"))));
                                    Toast.makeText(Register.this, "Register Successful.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(Register.this, Login.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
                    } catch (AssertionError error) {
                        error.printStackTrace();
                    }

                }
            }
        });
    }
}