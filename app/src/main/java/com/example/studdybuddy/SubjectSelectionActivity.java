package com.example.studdybuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectSelectionActivity extends Activity {
    private Button mathButton, readingButton, scienceButton, governmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selection);

        // Link UI elements
        mathButton = (Button) findViewById(R.id.mathButton);
        readingButton = (Button) findViewById(R.id.readingButton);
        scienceButton = (Button) findViewById(R.id.scienceButton);
        governmentButton = (Button) findViewById(R.id.governmentButton);

        // Set click listeners for each subject button
        mathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("math");
            }
        });

        readingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("reading");
            }
        });

        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("science");
            }
        });

        governmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("government");
            }
        });
    }

    // Start QuizActivity with the selected subject
    private void startQuiz(String subject) {
        Intent intent = new Intent(SubjectSelectionActivity.this, QuizActivity.class);
        intent.putExtra("subject", subject);
        startActivity(intent);
    }
}

