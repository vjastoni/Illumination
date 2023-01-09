package com.example.illumination_test.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.Dialog.DialogAddCategory;
import com.example.illumination_test.RecViewClass.Quiz;
import com.example.illumination_test.RecViewClass.QuizRecViewAdapter;
import com.example.illumination_test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView quizRecView;
    private FloatingActionButton floatingButton;
    private QuizRecViewAdapter adapter;
    private ArrayList<Quiz> quizzes;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        quizRecView = view.findViewById(R.id.quizRecView);
        floatingButton = view.findViewById(R.id.floatingButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        quizzes = new ArrayList<>();
        adapter = new QuizRecViewAdapter(getContext(), quizzes);

        quizRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        quizRecView.setAdapter(adapter);

        getSubject(quizzes -> adapter.notifyDataSetChanged());

        return view;
    }

    public void openDialog(){

        DialogAddCategory dialogAddCategory = new DialogAddCategory();
        dialogAddCategory.show(getActivity().getSupportFragmentManager(), "Add Category");

    }

    public void getSubject(GetQuizzesListener listener){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quizzes.clear();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                for(DataSnapshot quiz: snapshot.child(auth.getUid()).child("Quizzes").getChildren()){
                    Quiz quiz1 = quiz.getValue(Quiz.class);
                    quizzes.add(quiz1);
                }
                System.out.println("value " + quizzes.isEmpty());
                listener.getQuizzes(quizzes);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public interface GetQuizzesListener{
        void getQuizzes(ArrayList<Quiz> quizzes);
    }
}
