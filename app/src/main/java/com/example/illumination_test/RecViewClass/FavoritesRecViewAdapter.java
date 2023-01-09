package com.example.illumination_test.RecViewClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.R;

import java.util.ArrayList;

public class FavoritesRecViewAdapter extends RecyclerView.Adapter<FavoritesRecViewAdapter.ViewHolder> {

    private ArrayList<Favorite> favorites = new ArrayList<>();

    private Context context;

    public FavoritesRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtEditQuestionNumber.setText(favorites.get(position).getNumber());
        holder.answerFavorite.setText(favorites.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void setFavorites(ArrayList<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtEditQuestionNumber;
        private EditText answerFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtEditQuestionNumber = itemView.findViewById(R.id.txtEditQuestionNumber);
            answerFavorite = itemView.findViewById(R.id.answerFavorite);
        }
    }
}
