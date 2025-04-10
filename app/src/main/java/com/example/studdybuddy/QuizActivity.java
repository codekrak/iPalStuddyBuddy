package com.example.studdybuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechService;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity implements SpeechListener {

    private TextView questionTextView, scoreTextView, timerTextView;
    private Button[] answerButtons = new Button[4];
    private Button backToMenuButton;
    private QuestionManager questionManager;
    private StuddyBuddyQuestion currentQuestion;
    private TimerManager timerManager;
    private String selectedSubject;
    private SpeechManager mSpeechManager;
    private SpeechRecognizerManager speechRecognizerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get selected subject or default to math
        Intent intent = getIntent();
        selectedSubject = intent.getStringExtra("subject");
        if (selectedSubject == null) selectedSubject = "math";

        // Initialize logic and speech systems
        questionManager = new QuestionManager(new StuddyBuddyQuestions(selectedSubject), this);
        mSpeechManager = (SpeechManager) getSystemService(SpeechService.SERVICE_NAME);
        speechRecognizerManager = new SpeechRecognizerManager(this, this);

        // Link UI
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        answerButtons[0] = (Button) findViewById(R.id.answerButton1);
        answerButtons[1] = (Button) findViewById(R.id.answerButton2);
        answerButtons[2] = (Button) findViewById(R.id.answerButton3);
        answerButtons[3] = (Button) findViewById(R.id.answerButton4);
        backToMenuButton = (Button) findViewById(R.id.backToMenuButton);
        backToMenuButton.setVisibility(View.GONE);

        // Timer setup
        timerManager = new TimerManager(timerTextView);

        // Back to menu listener
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(QuizActivity.this, StartActivity.class);
                startActivity(menuIntent);
                finish();
            }
        });

        loadNextQuestion();
    }

    private void loadNextQuestion() {
        currentQuestion = questionManager.getNextQuestion();

        if (currentQuestion == null) {
            questionTextView.setText(questionManager.getFinalFeedback());
            for (Button b : answerButtons) {
                b.setVisibility(View.GONE);
            }
            backToMenuButton.setVisibility(View.VISIBLE);
            return;
        }

        // Speak question and listen for voice input
        questionTextView.setText(currentQuestion.getQuestion());
        if (mSpeechManager != null) {
            mSpeechManager.startSpeaking(currentQuestion.getQuestion());
        }
        speechRecognizerManager.startListening();

        final String[] choices = currentQuestion.getChoices();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(choices[i]);
            final int index = i;
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleAnswer(index);
                }
            });
        }

        // Timer expiration logic
        timerManager.startTimer(new Runnable() {
            @Override
            public void run() {
                questionTextView.setText("Time's up! The correct answer was: " + currentQuestion.getCorrectAnswer());
                speechRecognizerManager.stopListening();
                loadNextQuestion();
            }
        });
    }

    private void handleAnswer(int chosenIndex) {
        timerManager.stopTimer();
        speechRecognizerManager.stopListening();

        boolean isCorrect = questionManager.validateAnswer(currentQuestion.getChoices()[chosenIndex]);
        scoreTextView.setText(questionManager.finalScoreText());

        if (isCorrect) {
            questionTextView.setText("Correct! " + questionManager.getFeedback());
        } else {
            questionTextView.setText("Incorrect! " + questionManager.getFeedback());
        }

        loadNextQuestion();
    }

    @Override
    public void onSpeechResult(String result) {
        if (currentQuestion == null) return;

        String[] choices = currentQuestion.getChoices();
        for (int i = 0; i < choices.length; i++) {
            if (result.equalsIgnoreCase(choices[i])) {
                handleAnswer(i);
                return;
            }
        }

        questionTextView.setText("Didn't catch that. Please tap the answer.");
    }

    @Override
    public void onSpeechError(String error) {
        questionTextView.setText("Speech error: " + error + ". Tap an answer to continue.");
    }
}
