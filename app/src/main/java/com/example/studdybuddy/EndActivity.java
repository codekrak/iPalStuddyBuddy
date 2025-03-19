package com.example.studdybuddy; // Defines the package for this class

import android.content.Intent;
import android.os.Bundle;
import android.robot.motion.RobotMotion;
import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechService;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {

    private SpeechManager mSpeechManager;
    private RobotMotion mRobotMotion = new RobotMotion();

    // Speech Listener for TTS events
    private SpeechManager.TtsListener mTtsListener = new SpeechManager.TtsListener() {
        @Override
        public void onBegin(int requestId) {
            System.out.println("Speech Started: Request ID " + requestId);
        }

        @Override
        public void onEnd(int requestId) {
            System.out.println("Speech Finished: Request ID " + requestId);
        }

        @Override
        public void onError(int error) {
            System.out.println("Speech Error Occurred: " + error);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        // Initialize SpeechManager
        mSpeechManager = (SpeechManager) getSystemService(SpeechService.SERVICE_NAME);
        if (mSpeechManager == null) {
            System.out.println("ERROR: SpeechManager is NULL. Speech will NOT work.");
        } else {
            System.out.println("SUCCESS: SpeechManager initialized.");
            mSpeechManager.setTtsListener(mTtsListener); // Set the listener
        }

        // Get data from previous activity
        Intent intent = getIntent();
        String scoreText = intent.getStringExtra("SCORE_TEXT");
        String feedback = intent.getStringExtra("FEEDBACK");

        // Set UI text
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(scoreText);

        TextView feedbackTextView = (TextView) findViewById(R.id.feedbackTextView);
        feedbackTextView.setText(feedback);

        // 🔹 DEBUG LOG - Speech Testing 🔹
        System.out.println("DEBUG: Attempting speech test...");
        mSpeechManager.startSpeaking("Speech test. If you hear this, the speech system is working.");

        try {
            Thread.sleep(2000); // Allow 2 seconds for speech test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Format the full message for speech
        String fullMessage = "Your final score is " + scoreText + ". " + feedback;
        System.out.println("DEBUG: Speaking final message: " + fullMessage);

        // Speak out the final score and feedback
        mSpeechManager.startSpeaking(fullMessage);

        try {
            Thread.sleep(4000); // Allow 4 seconds for speech to complete before robot moves
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Robot motion based on feedback
        if (feedback.contains("Excellent")) {
            mRobotMotion.doAction(RobotMotion.Action.CHEER);
        } else if (feedback.contains("Great")) {
            mRobotMotion.doAction(RobotMotion.Action.CLAP);
        } else if (feedback.contains("Good")) {
            mRobotMotion.doAction(RobotMotion.Action.HIGHFIVE);
        } else {
            mRobotMotion.doAction(RobotMotion.Action.NO);
        }

        // Restart button
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(EndActivity.this, MainActivity.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(restartIntent);
                finish();
            }
        });

        // Exit button
        Button exitButtonEnd = (Button) findViewById(R.id.exitButtonEnd);
        exitButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
