package com.example.studdybuddy; // Defines the package for this class

// Import necessary Android and robot-specific libraries
import android.content.Intent; // Used for navigating between activities
import android.os.Bundle; // Contains saved instance state for activity recreation
import android.robot.motion.RobotMotion; // Controls robot motion actions
import android.robot.speech.SpeechManager; // Handles text-to-speech
import android.robot.speech.SpeechService; // Provides speech-related services
import android.view.View; // Used for handling user interface interactions
import android.widget.Button; // Represents button UI elements
import android.widget.TextView; // Represents text UI elements
import android.support.v7.app.AppCompatActivity; // Base class for activities in Android

public class EndActivity extends AppCompatActivity { // Defines the EndActivity class, extending AppCompatActivity

    private SpeechManager mSpeechManager; // Handles speech synthesis
    private RobotMotion mRobotMotion = new RobotMotion(); // Controls robot movements

    // Defines a listener for speech events
    private SpeechManager.TtsListener mTtsListener = new SpeechManager.TtsListener() {
        @Override
        public void onBegin(int requestId) {} // Triggered when speech starts

        @Override
        public void onEnd(int requestId) {} // Triggered when speech ends

        @Override
        public void onError(int error) {} // Handles speech errors
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Called when the activity is created
        super.onCreate(savedInstanceState); // Calls superclass method to maintain activity lifecycle
        setContentView(R.layout.activity_end); // Sets the UI layout from activity_end.xml

        // Initializes the speech manager using the system service
        mSpeechManager = (SpeechManager) getSystemService(SpeechService.SERVICE_NAME);
        mSpeechManager.setTtsListener(mTtsListener); // Assigns the TTS listener

        // Retrieves intent data passed from MainActivity
        Intent intent = getIntent();
        String scoreText = intent.getStringExtra("SCORE_TEXT"); // Gets the score text
        String feedback = intent.getStringExtra("FEEDBACK"); // Gets feedback message

        // Sets the retrieved score text into the corresponding TextView
        TextView scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        scoreTextView.setText(scoreText);

        // Sets the retrieved feedback text into the corresponding TextView
        TextView feedbackTextView = (TextView) findViewById(R.id.feedbackTextView);
        feedbackTextView.setText(feedback);

        // Makes the robot speak the feedback aloud
        mSpeechManager.startSpeaking(feedback);

        // Robot performs an action based on the feedback received
        if (feedback.contains("Excellent")) {
            mRobotMotion.doAction(RobotMotion.Action.CHEER); // Cheer animation for excellent feedback
        }
        else if (feedback.contains("Great")) {
            mRobotMotion.doAction(RobotMotion.Action.CLAP); // Clapping animation for great feedback
        }
        else if (feedback.contains("Good")) {
            mRobotMotion.doAction(RobotMotion.Action.HIGHFIVE); // High five animation for good feedback
        }
        else {
            mRobotMotion.doAction(RobotMotion.Action.NO); // Negative response animation for low feedback
        }

        // Restart game button: Returns user to MainActivity
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(EndActivity.this, MainActivity.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clears activity stack before launching MainActivity
                startActivity(restartIntent); // Starts MainActivity
                finish(); // Closes the current EndActivity
            }
        });

        // Exit button: Closes the application
        Button exitButtonEnd = (Button) findViewById(R.id.exitButtonEnd);
        exitButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes the activity
                System.exit(0); // Forces the app to close completely
            }
        });
    }
}
