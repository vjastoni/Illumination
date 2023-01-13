package com.example.illumination_test.RecViewClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.Activity.AddEditQuestion;
import com.example.illumination_test.Activity.PlayQuiz;
import com.example.illumination_test.Activity.UpdateQuestion;
import com.example.illumination_test.Fragment.QuestionEdit;
import com.example.illumination_test.R;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_quiz_item,parent, false);
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
                intent.putExtra(UpdateQuestion.QUESTION, questionEdits.get(position));
                context.startActivity(intent);
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtEditQuestionNumber, questionHolder;
        private CardView parent, editQuestion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEditQuestionNumber = itemView.findViewById(R.id.txtEditQuestionNumber);
            questionHolder = itemView.findViewById(R.id.questionHolder);
            parent = itemView.findViewById(R.id.parent);
            editQuestion = itemView.findViewById(R.id.editQuestion);
        }
    }
}
