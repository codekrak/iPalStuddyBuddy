package com.example.studdybuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {
    private TextView questionTextView, scoreTextView, timerTextView;
    private Button[] answerButtons = new Button[4];
    private Button backToMenuButton; // ✅ Added: Button to return to the main menu
    private QuestionManager questionManager;
    private StuddyBuddyQuestion currentQuestion;
    private TimerManager timerManager;
    private String selectedSubject; // ✅ Store selected subject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // ✅ Retrieve selected subject from intent
        Intent intent = getIntent();
        selectedSubject = intent.getStringExtra("subject");

        // ✅ Ensure selected subject is not null
        if (selectedSubject == null) {
            selectedSubject = "math"; // Default to math if no subject is passed
        }

        // ✅ Initialize QuestionManager with selected subject
        questionManager = new QuestionManager(new StuddyBuddyQuestions(selectedSubject), this);

        // ✅ Link UI elements
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        answerButtons[0] = (Button) findViewById(R.id.answerButton1);
        answerButtons[1] = (Button) findViewById(R.id.answerButton2);
        answerButtons[2] = (Button) findViewById(R.id.answerButton3);
        answerButtons[3] = (Button) findViewById(R.id.answerButton4);
        backToMenuButton = (Button) findViewById(R.id.backToMenuButton); // ✅ Added

        // Hide the back button at the start
        backToMenuButton.setVisibility(View.GONE);

        // ✅ Initialize TimerManager
        timerManager = new TimerManager(timerTextView);

        // ✅ Set click listener for back to menu button
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, StartActivity.class);
                startActivity(intent);
                finish(); // ✅ Ensure QuizActivity is closed when returning to main menu
            }
        });

        // ✅ Load first question
        loadNextQuestion();
    }

    // ✅ Load the next question from QuestionManager
    private void loadNextQuestion() {
        currentQuestion = questionManager.getNextQuestion();

        if (currentQuestion == null) {
            // ✅ No more questions, display final feedback
            questionTextView.setText(questionManager.getFinalFeedback());

            // ✅ Hide answer buttons and show "Back to Menu" button
            for (Button button : answerButtons) {
                button.setVisibility(View.GONE);
            }
            backToMenuButton.setVisibility(View.VISIBLE); // ✅ Show button to return to main menu

            return;
        }

        questionTextView.setText(currentQuestion.getQuestion());
        String[] choices = currentQuestion.getChoices();

        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(choices[i]);
            final int finalI = i; // ✅ Required for inner class in Java 7
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleAnswer(finalI);
                }
            });
        }

        // ✅ Start Timer
        timerManager.startTimer(new Runnable() {
            @Override
            public void run() {
                questionTextView.setText("Time's up! The correct answer was: " + currentQuestion.getCorrectAnswer());
                loadNextQuestion();
            }
        });
    }

    // ✅ Handle user answer selection
    private void handleAnswer(int chosenIndex) {
        // ✅ Stop Timer when user answers
        timerManager.stopTimer();

        boolean isCorrect = questionManager.validateAnswer(currentQuestion.getChoices()[chosenIndex]);
        scoreTextView.setText(questionManager.finalScoreText());

        if (isCorrect) {
            questionTextView.setText("Correct! " + questionManager.getFeedback()); // ✅ Correct usage of getFeedback()
        } else {
            questionTextView.setText("Incorrect! " + questionManager.getFeedback()); // ✅ Properly retrieve feedback
        }

        // ✅ Load the next question after answering
        loadNextQuestion();
    }
}
