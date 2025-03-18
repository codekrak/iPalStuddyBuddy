package com.example.studdybuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {
    private TextView questionTextView, scoreTextView, timerTextView;
    private Button[] answerButtons = new Button[4];
    private QuestionManager questionManager;
    private StuddyBuddyQuestion currentQuestion;
    private TimerManager timerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionManager = new QuestionManager(new StuddyBuddyQuestions(), this);

        // ✅ Link UI elements
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        answerButtons[0] = (Button) findViewById(R.id.answerButton1);
        answerButtons[1] = (Button) findViewById(R.id.answerButton2);
        answerButtons[2] = (Button) findViewById(R.id.answerButton3);
        answerButtons[3] = (Button) findViewById(R.id.answerButton4);

        // ✅ Initialize TimerManager
        timerManager = new TimerManager(timerTextView);

        // ✅ Load first question
        loadNextQuestion();
    }

    // ✅ Load the next question from QuestionManager
    private void loadNextQuestion() {
        currentQuestion = questionManager.getNextQuestion();

        if (currentQuestion == null) {
            // No more questions, display final feedback
            questionTextView.setText(questionManager.getFinalFeedback());
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

        // ✅ Java 7 Fix: Use Anonymous Inner Class instead of Lambda
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
            questionTextView.setText("Correct! " + currentQuestion.getCorrectMessage()); // ✅ Correct usage
        } else {
            questionTextView.setText("Incorrect! The correct answer was: " + currentQuestion.getCorrectAnswer());
        }

        // ✅ Load the next question after answering
        loadNextQuestion();
    }
}
