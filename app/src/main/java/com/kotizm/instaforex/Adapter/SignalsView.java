package com.kotizm.instaforex.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotizm.instaforex.R;

class SignalsView extends RecyclerView.ViewHolder {

    TextView textView;

    SignalsView(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.cardTextView);
    }
}