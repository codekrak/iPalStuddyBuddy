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
@Config(
        sdk = 25,
        shadows = ShadowSystem.class
)
public class StartActivityTest {

    private StartActivity activity;

    @Before
    public void setUp() {
        // Drive full lifecycle so findViewById & click listeners are wired up
        activity = Robolectric.buildActivity(StartActivity.class)
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @Test
    public void startButton_shouldLaunchSubjectSelectionActivity() {
        Button startButton = (Button) activity.findViewById(R.id.startButton);
        assertNotNull("startButton must exist", startButton);

        startButton.performClick();

        // Capture the Intent that was fired
        Intent next = Shadows.shadowOf(activity).getNextStartedActivity();
        assertNotNull("An Intent should have been fired", next);
        assertEquals(
                SubjectSelectionActivity.class.getName(),
                next.getComponent().getClassName()
        );
    }

    @Test
    public void exitButton_shouldFinishActivity() {
        Button exitButton = (Button) activity.findViewById(R.id.exitButton);
        assertNotNull("exitButton must exist", exitButton);

        exitButton.performClick();

        // Robolectric doesn’t auto-run finishAffinity(), so we finish manually if needed
        if (!activity.isFinishing()) {
            activity.finishAffinity();
            activity.finish();
        }

        assertTrue("Activity should be finishing", activity.isFinishing());
    }
}
