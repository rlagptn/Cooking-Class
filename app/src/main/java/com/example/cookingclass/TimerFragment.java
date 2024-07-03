package com.example.cookingclass;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private TextView textViewRecipeName;
    private TextView textViewTotalTime;
    private SeekBar seekBarTimer;
    private TextView textViewRemainingTime;
    private ProgressBar progressBarTimer;
    private Button buttonStart;
    private Button buttonPause;
    private Button buttonReset;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private long totalTimeInMillis = 600000;
    private long timeLeftInMillis = totalTimeInMillis;

    public TimerFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);

        textViewRecipeName = rootView.findViewById(R.id.textViewRecipeName);
        textViewTotalTime = rootView.findViewById(R.id.textViewTotalTime);
        seekBarTimer = rootView.findViewById(R.id.seekBarTimer);
        textViewRemainingTime = rootView.findViewById(R.id.textViewRemainingTime);
        progressBarTimer = rootView.findViewById(R.id.progressBarTimer);
        buttonStart = rootView.findViewById(R.id.buttonStart);
        buttonPause = rootView.findViewById(R.id.buttonPause);
        buttonReset = rootView.findViewById(R.id.buttonReset);

        // Initialize UI and set listeners
        initializeTimer();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            String recipeName = getArguments().getString("recipe_name");
            if (recipeName != null) {
                textViewRecipeName.setText(recipeName);
            }
        }

        if (savedInstanceState != null) {
            timeLeftInMillis = savedInstanceState.getLong("timeLeftInMillis");
            isTimerRunning = savedInstanceState.getBoolean("isTimerRunning");
            totalTimeInMillis = savedInstanceState.getLong("totalTimeInMillis");

            if (isTimerRunning) {
                startTimer();
            } else {
                updateCountDownText();
                updateButtons();
            }
        }
    }

    private void initializeTimer() {
        textViewTotalTime.setText("Total Cooking Time: " + (totalTimeInMillis / 60000) + " minutes");

        // Set up SeekBar
        seekBarTimer.setMax((int) totalTimeInMillis);
        seekBarTimer.setProgress((int) timeLeftInMillis);

        // Set up initial progress bar
        progressBarTimer.setMax((int) totalTimeInMillis);
        progressBarTimer.setProgress((int) timeLeftInMillis);

        updateCountDownText();
        updateButtons();
    }

    private void startTimer() {
        if (!isTimerRunning) {
            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                    seekBarTimer.setProgress((int) (totalTimeInMillis - millisUntilFinished));
                    progressBarTimer.setProgress((int) (totalTimeInMillis - millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    isTimerRunning = false;
                    updateButtons();
                }
            }.start();

            isTimerRunning = true;
            updateButtons();
        }
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
            updateButtons();
        }
    }

    private void resetTimer() {
        timeLeftInMillis = totalTimeInMillis;
        updateCountDownText();
        seekBarTimer.setProgress((int) totalTimeInMillis);
        progressBarTimer.setProgress((int) totalTimeInMillis);
        isTimerRunning = false;
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        textViewRemainingTime.setText("Remaining Time: " + timeLeftFormatted);
    }

    private void updateButtons() {
        if (isTimerRunning) {
            buttonStart.setEnabled(false);
            buttonPause.setEnabled(true);
            buttonPause.setVisibility(View.VISIBLE);
            buttonReset.setEnabled(false);
        } else {
            buttonStart.setEnabled(true);
            buttonPause.setEnabled(false);
            buttonPause.setVisibility(View.GONE);
            buttonReset.setEnabled(true);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timeLeftInMillis", timeLeftInMillis);
        outState.putBoolean("isTimerRunning", isTimerRunning);
        outState.putLong("totalTimeInMillis", totalTimeInMillis);
    }
}
