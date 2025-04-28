package com.example.studdybuddy;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 25)
public class SubjectSelectionActivityTest {

    private SubjectSelectionActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.buildActivity(SubjectSelectionActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void mathButton_startsQuizWithMathSubject() {
        Button mathButton = (Button) activity.findViewById(R.id.mathButton);
        assertNotNull("mathButton should exist", mathButton);

        mathButton.performClick();

        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertNotNull("Intent should be fired", intent);
        assertEquals("math", intent.getStringExtra("subject"));
        assertEquals(QuizActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void readingButton_startsQuizWithReadingSubject() {
        Button readingButton = (Button) activity.findViewById(R.id.readingButton);
        assertNotNull("readingButton should exist", readingButton);

        readingButton.performClick();

        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertNotNull("Intent should be fired", intent);
        assertEquals("reading", intent.getStringExtra("subject"));
        assertEquals(QuizActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void scienceButton_startsQuizWithScienceSubject() {
        Button scienceButton = (Button) activity.findViewById(R.id.scienceButton);
        assertNotNull("scienceButton should exist", scienceButton);

        scienceButton.performClick();

        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertNotNull("Intent should be fired", intent);
        assertEquals("science", intent.getStringExtra("subject"));
        assertEquals(QuizActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void governmentButton_startsQuizWithGovernmentSubject() {
        Button governmentButton = (Button) activity.findViewById(R.id.governmentButton);
        assertNotNull("governmentButton should exist", governmentButton);

        governmentButton.performClick();

        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
        assertNotNull("Intent should be fired", intent);
        assertEquals("government", intent.getStringExtra("subject"));
        assertEquals(QuizActivity.class.getName(), intent.getComponent().getClassName());
    }
}
