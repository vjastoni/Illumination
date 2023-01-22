package com.example.illumination_test.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.illumination_test.Fragment.HomeFragment;
import com.example.illumination_test.OOP.Exam;
import com.example.illumination_test.OOP.Question;
import com.example.illumination_test.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DialogAddCategory extends AppCompatDialogFragment {

    MaterialButton btnCancel, btnAdd;
    RelativeLayout dialogContainer;
    EditText edtCategoryName;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference databaseReference;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_category, null);

        builder.setView(view);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnAdd = view.findViewById(R.id.btnAdd);
        edtCategoryName = view.findViewById(R.id.edtCategoryName);
        dialogContainer = view.findViewById(R.id.dialogContainer);
        String userName = user.getEmail().substring(0, user.getEmail().indexOf("@"));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCategory = edtCategoryName.getText().toString();
                Map<String, Question> questionsMap = new HashMap<>();
                Exam exam = new Exam(newCategory, questionsMap);
                System.out.println(questionsMap);
                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Quizzes");
                databaseReference.child(newCategory).setValue(exam);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

}
