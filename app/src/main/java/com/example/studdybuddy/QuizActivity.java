package com.example.studdybuddy; // Defines the package where this class belongs

import android.app.Activity; // Imports Activity class to create an Android screen
import android.content.Intent; // Imports Intent for screen navigation
import android.os.Bundle; // Imports Bundle for activity state management
import android.view.View; // Imports View for UI interaction
import android.widget.Button; // Imports Button class to handle button UI elements
import android.widget.TextView; // Imports TextView class for displaying text

// Class for managing the quiz screen
public class QuizActivity extends Activity {
    private TextView questionTextView, scoreTextView, timerTextView; // Text fields for questions, scores, and timer
    private Button[] answerButtons = new Button[4]; // Array to store four answer buttons
    private Button backToMenuButton; // ✅ Button to return to the main menu
    private QuestionManager questionManager; // Manages questions and scoring
    private StuddyBuddyQuestion currentQuestion; // Stores the current question
    private TimerManager timerManager; // Manages countdown timer
    private String selectedSubject; // ✅ Stores the selected subject chosen by the user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // Sets the layout file for this activity

        // ✅ Retrieve the selected subject from intent data
        Intent intent = getIntent();
        selectedSubject = intent.getStringExtra("subject");

        // ✅ If no subject is received, default to "math"
        if (selectedSubject == null) {
            selectedSubject = "math";
        }

        // ✅ Initialize QuestionManager with selected subject
        questionManager = new QuestionManager(new StuddyBuddyQuestions(selectedSubject), this);

        // ✅ Link UI elements to their XML components
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        answerButtons[0] = (Button) findViewById(R.id.answerButton1);
        answerButtons[1] = (Button) findViewById(R.id.answerButton2);
        answerButtons[2] = (Button) findViewById(R.id.answerButton3);
        answerButtons[3] = (Button) findViewById(R.id.answerButton4);
        backToMenuButton = (Button) findViewById(R.id.backToMenuButton); // ✅ Back to menu button

        // Hide the "Back to Menu" button at the start
        backToMenuButton.setVisibility(View.GONE);

        // ✅ Initialize TimerManager for countdown functionality
        timerManager = new TimerManager(timerTextView);

        // ✅ Set up click listener for the "Back to Menu" button
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, StartActivity.class); // Navigate to StartActivity
                startActivity(intent); // Start main menu activity
                finish(); // ✅ Close current activity to prevent returning to quiz when pressing "Back"
            }
        });

        // ✅ Load the first question when the quiz starts
        loadNextQuestion();
    }

    // ✅ Method to load the next question from QuestionManager
    private void loadNextQuestion() {
        currentQuestion = questionManager.getNextQuestion(); // Retrieves the next question

        // ✅ If there are no more questions, show final feedback
        if (currentQuestion == null) {
            questionTextView.setText(questionManager.getFinalFeedback()); // Display final feedback

            // ✅ Hide answer buttons since the quiz is over
            for (Button button : answerButtons) {
                button.setVisibility(View.GONE);
            }
            backToMenuButton.setVisibility(View.VISIBLE); // ✅ Show the "Back to Menu" button

            return; // Stop further execution
        }

        // ✅ Display question text
        questionTextView.setText(currentQuestion.getQuestion());
        String[] choices = currentQuestion.getChoices(); // Retrieve answer choices

        // ✅ Assign answer choices to buttons and set up click listeners
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(choices[i]); // Set button text to choice
            final int finalI = i; // ✅ Java 7 Fix: Make variable final for inner class
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleAnswer(finalI); // Handle answer selection
                }
            });
        }

        // ✅ Start the countdown timer for answering the question
        timerManager.startTimer(new Runnable() {
            @Override
            public void run() {
                // ✅ Time is up, display the correct answer
                questionTextView.setText("Time's up! The correct answer was: " + currentQuestion.getCorrectAnswer());
                loadNextQuestion(); // Load the next question
            }
        });
    }

    // ✅ Handles the user's answer selection
    private void handleAnswer(int chosenIndex) {
        timerManager.stopTimer(); // ✅ Stop the timer when the user selects an answer

        boolean isCorrect = questionManager.validateAnswer(currentQuestion.getChoices()[chosenIndex]); // ✅ Validate answer
        scoreTextView.setText(questionManager.finalScoreText()); // ✅ Update score display

        if (isCorrect) {
            questionTextView.setText("Correct! " + questionManager.getFeedback()); // ✅ Display correct feedback
        } else {
            questionTextView.setText("Incorrect! " + questionManager.getFeedback()); // ✅ Display incorrect feedback
        }

        // ✅ Load the next question after answering
        loadNextQuestion();
    }
}
