package com.example.cookingclass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {

    private EditText feedbackEditText;
    private Switch anonymousSwitch;
    private RatingBar ratingBar;
    private Button submitButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        feedbackEditText = view.findViewById(R.id.feedbackEditText);
        anonymousSwitch = view.findViewById(R.id.anonymousSwitch);
        ratingBar = view.findViewById(R.id.ratingBar);
        submitButton = view.findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackEditText.getText().toString();
                boolean isAnonymous = anonymousSwitch.isChecked();
                float rating = ratingBar.getRating();

                Toast.makeText(getContext(), "Feedback submitted with rating: " + rating, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
