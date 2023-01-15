package com.example.illumination_test.RecViewClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.Activity.AddQuestion;
import com.example.illumination_test.Activity.EditQuestion;
import com.example.illumination_test.Activity.UpdateQuestion;
import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditQuestionRecViewAdapter extends RecyclerView.Adapter<EditQuestionRecViewAdapter.ViewHolder> {

    private ArrayList<QuestionEdit> questionEdits = new ArrayList<>();

    private Context context;

    public EditQuestionRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_quiz_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtEditQuestionNumber.setText(questionEdits.get(position).getNumber());
        holder.questionHolder.setText(questionEdits.get(position).getQuestion());
        holder.editQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateQuestion.class);
//                intent.putExtra(AddQuestion.QUESTION, questionEdits.get(position));
                intent.putExtra("question", questionEdits.get(position).getQuestion());
                intent.putExtra("option1", questionEdits.get(position).getOption1());
                intent.putExtra("option2", questionEdits.get(position).getOption2());
                intent.putExtra("option3", questionEdits.get(position).getOption3());
                intent.putExtra("option4", questionEdits.get(position).getOption4());
                intent.putExtra("answer", questionEdits.get(position).getAnswer());
                intent.putExtra("Subject", questionEdits.get(position).getSubject());
                intent.putExtra("questionNo", questionEdits.get(position).getQuestionNo());
                context.startActivity(intent);
            }
        });
        holder.deleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference databaseReference;
                databaseReference = FirebaseDatabase.getInstance().getReference("Users")
                        .child(user.getUid())
                        .child("Quizzes")
                        .child(questionEdits.get(position).getSubject())
                        .child(questionEdits.get(position).getQuestionNo());
                databaseReference.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionEdits.size();
    }

    public void setQuestionEdits(ArrayList<QuestionEdit> questionEdits) {
        this.questionEdits = questionEdits;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtEditQuestionNumber, questionHolder;
        private CardView parent, editQuestion, deleteQuestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEditQuestionNumber = itemView.findViewById(R.id.txtEditQuestionNumber);
            questionHolder = itemView.findViewById(R.id.questionHolder);
            parent = itemView.findViewById(R.id.parent);
            editQuestion = itemView.findViewById(R.id.editQuestion);
            deleteQuestion = itemView.findViewById(R.id.deleteQuestion);

        }
    }

}
