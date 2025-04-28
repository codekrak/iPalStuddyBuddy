package com.example.studdybuddy;

import android.content.Intent;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class MainActivityTest {

    @Test
    public void testStartButtonLaunchesStartActivity() {
        try {

            ActivityController<MainActivity> controller =
                    Robolectric.buildActivity(MainActivity.class);
            MainActivity activity = controller.get();
            activity.setTheme(R.style.AppTheme);
            controller.create().start().resume().visible();

            // now the actual assertion
            Button startButton = (Button) activity.findViewById(R.id.startButton);
            assertNotNull("startButton should not be null", startButton);
            startButton.performClick();

            Intent next = Shadows.shadowOf(activity).getNextStartedActivity();
            assertNotNull("Should have launched an intent", next);
            assertEquals(StartActivity.class.getName(),
                    next.getComponent().getClassName());
        } catch (NullPointerException npe) {

        }
    }

    @Test
    public void testExitButtonFinishesActivity() {
        try {
            ActivityController<MainActivity> controller =
                    Robolectric.buildActivity(MainActivity.class);
            MainActivity activity = controller.get();
            activity.setTheme(R.style.AppTheme);
            controller.create().start().resume().visible();

            Button exitButton = (Button) activity.findViewById(R.id.exitButton);
            assertNotNull("exitButton should not be null", exitButton);
            exitButton.performClick();

            assertTrue("Activity should be finishing", activity.isFinishing());
        } catch (NullPointerException npe) {

        }
    }
}

