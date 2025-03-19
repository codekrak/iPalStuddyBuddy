package com.example.studdybuddy; // ✅ Defines the package where this class is located

import android.widget.TextView; // ✅ Imports TextView to display the countdown timer
import android.os.CountDownTimer; // ✅ Imports CountDownTimer to handle countdown functionality

// ✅ TimerManager class handles quiz countdown functionality
public class TimerManager {
    private TextView timerTextView; // ✅ UI element to display remaining time
    private CountDownTimer countDownTimer; // ✅ Object to manage the countdown

    // ✅ Constructor initializes TimerManager with a TextView to display the countdown
    public TimerManager(TextView timerTextView) {
        this.timerTextView = timerTextView;
    }

    // ✅ Starts the countdown timer with a given timeout action
    public void startTimer(final Runnable onTimeout) {
        countDownTimer = new CountDownTimer(10000, 1000) { // ✅ 10-second timer with 1-second intervals
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time left: " + millisUntilFinished / 1100 + " seconds"); // ✅ Updates UI every second
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Time's up!"); // ✅ Displays "Time's up!" when countdown ends
                onTimeout.run(); // ✅ Executes the specified action when time runs out
            }
        }.start(); // ✅ Starts the countdown
    }

    // ✅ Stops the timer before it finishes
    public void stopTimer() {
        if (countDownTimer != null) { // ✅ Ensures the timer exists before attempting to cancel it
            countDownTimer.cancel(); // ✅ Cancels the countdown timer
        }
    }
}


