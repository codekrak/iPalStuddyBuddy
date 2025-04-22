package com.example.studdybuddy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock Button mockStartButton;
    @Mock Button mockExitButton;
    @Mock View mockView;

    private MainActivity activity;
    private Intent capturedIntent;
    private View.OnClickListener startListener;
    private View.OnClickListener exitListener;

    @Before
    public void setUp() {
        // Create activity with mocked behavior
        activity = new MainActivity() {
            @Override
            public View findViewById(int id) {
                if (id == R.id.startButton) return mockStartButton;
                if (id == R.id.exitButton) return mockExitButton;
                return null;
            }

            @Override
            public void startActivity(Intent intent) {
                capturedIntent = intent;
            }
        };

        // Setup mock button behavior using anonymous classes
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                startListener = (View.OnClickListener) invocation.getArguments()[0];
                return null;
            }
        }).when(mockStartButton).setOnClickListener(any(View.OnClickListener.class));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                exitListener = (View.OnClickListener) invocation.getArguments()[0];
                return null;
            }
        }).when(mockExitButton).setOnClickListener(any(View.OnClickListener.class));

        capturedIntent = null;
        startListener = null;
        exitListener = null;
    }

    @Test
    public void testStartButtonLaunchesStartActivity() {
        // Trigger onCreate
        activity.onCreate(null);

        // Verify listener was set
        assertNotNull("Start button listener should be set", startListener);

        // Simulate button click
        startListener.onClick(mockView);

        // Verify StartActivity was launched
        assertNotNull("Intent should have been created", capturedIntent);
        assertEquals("Should launch StartActivity",
                StartActivity.class.getName(),
                capturedIntent.getComponent().getClassName());
    }

    @Test
    public void testExitButtonFinishesActivity() {
        // Create spy to verify finishAffinity()
        MainActivity spyActivity = spy(activity);

        // Trigger onCreate
        spyActivity.onCreate(null);

        // Verify listener was set
        assertNotNull("Exit button listener should be set", exitListener);

        // Simulate button click
        exitListener.onClick(mockView);

        // Verify finishAffinity was called
        verify(spyActivity).finishAffinity();
    }
}