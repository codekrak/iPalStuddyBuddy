package com.example.studdybuddy; // Defines the package where this class is located

import android.support.v7.app.AppCompatActivity; // Imports AppCompatActivity for backward compatibility
import android.content.Intent; // Imports Intent to handle navigation between activities
import android.os.Bundle; // Imports Bundle for activity state management
import android.view.View; // Imports View for UI interactions
import android.widget.Button; // Imports Button class to manage button UI elements

// ✅ Main activity that serves as the game's main menu
public class StartActivity extends AppCompatActivity {
    private Button startButton;  // ✅ Start button to navigate to subject selection
    private Button exitButton;   // ✅ Exit button to close the app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_start); // ✅ Sets the UI layout for the start screen

        // ✅ Explicit casting to fix "incompatible types" error
        startButton = (Button) findViewById(R.id.startButton); // Links start button to XML ID
        exitButton = (Button) findViewById(R.id.exitButton); // Links exit button to XML ID

        // ✅ Set up click listener for the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ Navigates to the SubjectSelectionActivity when start is clicked
                Intent intent = new Intent(StartActivity.this, SubjectSelectionActivity.class);
                startActivity(intent); // Starts the new activity
            }
        });

        // ✅ Set up click listener for the exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // ✅ Closes all activities in the app stack
                System.exit(0); // ✅ Forcefully terminates the app process
            }
        });
    }
}
