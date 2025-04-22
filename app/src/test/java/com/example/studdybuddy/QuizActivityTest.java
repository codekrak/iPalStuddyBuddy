package com.example.studdybuddy;

import android.content.Intent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuizActivityTest {

    @Mock private Intent mockIntent;
    private QuizActivity quizActivity;

    @Before
    public void setUp() {
        quizActivity = new QuizActivity();
        when(mockIntent.getStringExtra("subject")).thenReturn("math");
        quizActivity.setIntent(mockIntent);
    }

    @Test
    public void testSubjectInitialization() {
        // Execute onCreate
        quizActivity.onCreate(null);

        // Verify subject was set correctly
        assertEquals("math", quizActivity.getSelectedSubject());
    }

    @Test
    public void testDefaultSubjectWhenNull() {
        // Setup null subject case
        when(mockIntent.getStringExtra("subject")).thenReturn(null);
        quizActivity.setIntent(mockIntent);

        // Execute
        quizActivity.onCreate(null);

        // Verify default
        assertEquals("math", quizActivity.getSelectedSubject());
    }
}