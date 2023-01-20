package com.example.illumination_test.RecViewClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.Activity.AddEditQuestion;
import com.example.illumination_test.Activity.PlayQuiz;
import com.example.illumination_test.R;

import java.util.ArrayList;

public class QuizRecViewAdapter extends RecyclerView.Adapter<QuizRecViewAdapter.ViewHolder> {

    private ArrayList<Quiz> quizzes = new ArrayList<>();

    private Context context;

    public QuizRecViewAdapter(Context context, ArrayList<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.quizCategory.setText(quizzes.get(position).getSubject());
        holder.quizItem.setText(String.valueOf(quizzes.get(position).getNumberItems()));
        holder.quizProgressScore.setProgress(quizzes.get(position).getProgressNumber());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, quizzes.get(position).getSubject() + " Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddEditQuestion.class);
                intent.putExtra(AddEditQuestion.QUIZ, quizzes.get(position));
                context.startActivity(intent);
            }
        });

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayQuiz.class);
                intent.putExtra(AddEditQuestion.QUIZ, quizzes.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView quizCategory, quizItem;
        private CardView parent, playButton;
        private ProgressBar quizProgressScore;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quizCategory = itemView.findViewById(R.id.quizCategory);
            quizProgressScore = itemView.findViewById(R.id.quizProgressScore);
            parent = itemView.findViewById(R.id.parent);
            quizItem = itemView.findViewById(R.id.quizItem);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }
}
