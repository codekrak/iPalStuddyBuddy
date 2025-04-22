package com.example.studdybuddy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StartActivityTest {

    @Mock private Button mockStartButton;
    @Mock private Button mockExitButton;
    @Mock private View mockView;
    @Captor private ArgumentCaptor<View.OnClickListener> clickListenerCaptor;
    @Captor private ArgumentCaptor<Intent> intentCaptor;

    private StartActivity startActivity;

    @Before
    public void setUp() {
        startActivity = new StartActivity() {
            @Override
            public Button findViewById(int id) {  // Changed return type to Button
                if (id == R.id.startButton) return mockStartButton;
                if (id == R.id.exitButton) return mockExitButton;
                return null;
            }
        };

        // Initialize the activity
        startActivity.onCreate(null);
    }

    @Test
    public void startButton_launchesSubjectSelection() {
        // Verify click listener was set
        verify(mockStartButton).setOnClickListener(clickListenerCaptor.capture());

        // Simulate button click
        View.OnClickListener listener = clickListenerCaptor.getValue();
        listener.onClick(mockView);

        // Verify correct activity launch
        verify(startActivity).startActivity(intentCaptor.capture());
        Intent capturedIntent = intentCaptor.getValue();
        assertEquals(SubjectSelectionActivity.class.getName(),
                capturedIntent.getComponent().getClassName());
    }

    @Test
    public void exitButton_closesApplication() {
        // Verify click listener was set
        verify(mockExitButton).setOnClickListener(clickListenerCaptor.capture());

        // Simulate button click
        View.OnClickListener listener = clickListenerCaptor.getValue();
        listener.onClick(mockView);

        // Verify app closure
        verify(startActivity).finishAffinity();
    }
}