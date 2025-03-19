package com.example.studdybuddy; // ✅ Defines the package where this class is located

import android.app.Activity; // ✅ Imports the base Activity class
import android.content.Intent; // ✅ Imports Intent to navigate between activities
import android.os.Bundle; // ✅ Imports Bundle for activity state management
import android.view.View; // ✅ Imports View for handling button clicks
import android.widget.Button; // ✅ Imports Button class for UI interactions

// ✅ Activity class for selecting a quiz subject
public class SubjectSelectionActivity extends Activity {
    private Button mathButton, readingButton, scienceButton, governmentButton; // ✅ Declare subject selection buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ✅ Calls the parent constructor to initialize the activity
        setContentView(R.layout.activity_subject_selection); // ✅ Sets the layout for the activity

        // ✅ Link UI elements to corresponding views in XML
        mathButton = (Button) findViewById(R.id.mathButton);
        readingButton = (Button) findViewById(R.id.readingButton);
        scienceButton = (Button) findViewById(R.id.scienceButton);
        governmentButton = (Button) findViewById(R.id.governmentButton);

        // ✅ Set click listeners for each subject button to start the quiz with the selected subject
        mathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("math"); // ✅ Calls method to start quiz with "math" as subject
            }
        });

        readingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("reading"); // ✅ Calls method to start quiz with "reading" as subject
            }
        });

        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("science"); // ✅ Calls method to start quiz with "science" as subject
            }
        });

        governmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("government"); // ✅ Calls method to start quiz with "government" as subject
            }
        });
    }

    // ✅ Starts QuizActivity and passes the selected subject as an extra
    private void startQuiz(String subject) {
        Intent intent = new Intent(SubjectSelectionActivity.this, QuizActivity.class); // ✅ Creates intent for QuizActivity
        intent.putExtra("subject", subject); // ✅ Adds subject as extra data to the intent
        startActivity(intent); // ✅ Launches QuizActivity with the selected subject
    }
}

