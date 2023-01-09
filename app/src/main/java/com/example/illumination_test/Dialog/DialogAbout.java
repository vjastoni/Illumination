package com.example.illumination_test.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.illumination_test.R;

public class DialogAbout extends AppCompatDialogFragment {

    LinearLayout aboutUsDialog;

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_about_us, null);

        builder.setView(view);

        aboutUsDialog = view.findViewById(R.id.aboutUsDialog);
        aboutUsDialog.setBackgroundColor(Color.argb(0,0,0,0));

        return builder.create();
    }
}
