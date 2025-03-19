package com.example.studdybuddy;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button startButton;  // Matches XML ID
    private Button exitButton;   // Matches XML ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_start); // ✅ Ensure this is correct

        // ✅ Explicit casting to fix "incompatible types" error
        startButton = (Button) findViewById(R.id.startButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        // Start button click listener -> Navigates to SubjectSelectionActivity
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SubjectSelectionActivity.class);
                startActivity(intent);
            }
        });

        // Exit button click listener -> Properly closes the app
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // ✅ Ensures all activities are closed and exits app
                System.exit(0); // ✅ Forcefully terminates the app
            }
        });
    }
}
