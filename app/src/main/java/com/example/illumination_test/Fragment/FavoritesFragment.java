package com.example.illumination_test.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.illumination_test.R;
import com.example.illumination_test.RecViewClass.Favorite;
import com.example.illumination_test.RecViewClass.FavoritesRecViewAdapter;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private RecyclerView favoriteRecView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoriteRecView = view.findViewById(R.id.favoritesRecView);

        ArrayList<Favorite> favorites = new ArrayList<>();
        favorites.add(new Favorite("1", "a. Sun Micro Systems"));
        favorites.add(new Favorite("2", "a. Sun Micro Systems"));
        favorites.add(new Favorite("3", "a. Sun Micro Systems"));
        favorites.add(new Favorite("4", "a. Sun Micro Systems"));
        favorites.add(new Favorite("5", "a. Sun Micro Systems"));
        favorites.add(new Favorite("6", "a. Sun Micro Systems"));
        favorites.add(new Favorite("7", "a. Sun Micro Systems"));
        favorites.add(new Favorite("8", "a. Sun Micro Systems"));
        favorites.add(new Favorite("9", "a. Sun Micro Systems"));
        favorites.add(new Favorite("10", "a. Sun Micro Systems"));

        FavoritesRecViewAdapter adapter = new FavoritesRecViewAdapter(getContext());
        adapter.setFavorites(favorites);

        favoriteRecView.setAdapter(adapter);
        favoriteRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;

    }
}
