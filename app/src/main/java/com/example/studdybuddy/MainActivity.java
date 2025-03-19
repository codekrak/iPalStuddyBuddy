package com.example.studdybuddy; // Defines the package where this class belongs

// Import necessary Android libraries
import android.content.Intent; // Used for navigating between activities
import android.os.Bundle; // Contains saved instance state for activity recreation
import android.support.v7.app.AppCompatActivity; // Base class for modern Android activities
import android.view.View; // Used for handling user interface interactions
import android.widget.Button; // Represents button UI elements

public class MainActivity extends AppCompatActivity { // Defines MainActivity, extending AppCompatActivity

    private Button startButton; // Declares a button for starting the quiz
    private Button exitButton; // Declares a button for exiting the app

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Called when the activity is created
        super.onCreate(savedInstanceState); // Calls the superclass method to maintain activity lifecycle
        setContentView(R.layout.activity_main); // Sets the UI layout from activity_main.xml

        // Initialize buttons by linking them to the corresponding XML IDs
        startButton = (Button) findViewById(R.id.startButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        // Set click listener for the Start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates an intent to navigate from MainActivity to StartActivity
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent); // Starts StartActivity
            }
        });

        // Set click listener for the Exit button
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Closes all activities and exits the app
            }
        });
    }
}
