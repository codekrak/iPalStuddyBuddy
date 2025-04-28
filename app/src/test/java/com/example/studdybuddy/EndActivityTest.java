package com.example.studdybuddy;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.util.ReflectionHelpers;

import com.example.studdybuddy.BuildConfig;
import com.example.studdybuddy.R;
import android.robot.motion.RobotMotion;
import android.robot.speech.SpeechManager;
import android.robot.speech.SpeechService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@Config(constants = BuildConfig.class, sdk = 25)
@RunWith(RobolectricTestRunner.class)
public class EndActivityTest {

    @Mock
    private SpeechManager mockSpeechManager;

    @Captor
    private ArgumentCaptor<SpeechManager.TtsListener> ttsListenerCaptor;

    private ActivityController<EndActivity> controller;
    private EndActivity activity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Register mocked SpeechManager in the system services
        ShadowApplication.getInstance().setSystemService(
                SpeechService.SERVICE_NAME,
                mockSpeechManager
        );

        // Prepare intent with extras
        Intent intent = new Intent();
        intent.putExtra("SCORE_TEXT", "85");
        intent.putExtra("FEEDBACK", "Excellent performance");

        // Build the ActivityController and set AppCompat theme
        controller = Robolectric.buildActivity(EndActivity.class, intent);
        activity = controller.get();
        activity.setTheme(R.style.AppTheme);

        // Create and start the activity
        controller.create().start().resume();
        activity = controller.get();
    }

    @Test
    public void onCreate_shouldInitializeUiAndStartSpeaking() {
        // Verify that TextViews are updated
        TextView scoreView = (TextView) activity.findViewById(R.id.scoreTextView);
        TextView feedbackView = (TextView) activity.findViewById(R.id.feedbackTextView);
        assertEquals("85", scoreView.getText().toString());
        assertEquals("Excellent performance", feedbackView.getText().toString());

        // Verify TtsListener was set on SpeechManager
        verify(mockSpeechManager).setTtsListener(ttsListenerCaptor.capture());

        // Verify startSpeaking was called with full message
        String expectedMessage = "Your final score is 85. Excellent performance";
        verify(mockSpeechManager).startSpeaking(expectedMessage);
    }

    @Test
    public void restartButton_shouldLaunchMainActivityWithFlags() {
        // (1) Cast the View to Button
        Button restart = (Button) activity.findViewById(R.id.restartButton);
        assertNotNull(restart);

        // (2) Perform the click
        restart.performClick();

        // (3) Verify the next intent
        Intent next = ShadowApplication.getInstance().getNextStartedActivity();
        assertNotNull("Intent should not be null", next);
        assertEquals(MainActivity.class.getName(), next.getComponent().getClassName());
        int flags = next.getFlags();
        assertTrue((flags & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0);
        assertTrue((flags & Intent.FLAG_ACTIVITY_NEW_TASK) != 0);
    }

    @Test
    public void ttsListener_onEnd_shouldTriggerRobotMotionAction() {
        // Capture the listener set on SpeechManager
        verify(mockSpeechManager).setTtsListener(ttsListenerCaptor.capture());
        SpeechManager.TtsListener listener = ttsListenerCaptor.getValue();

        // Use a mock for RobotMotion so doAction() doesn't call real code
        RobotMotion mockMotion = mock(RobotMotion.class);
        ReflectionHelpers.setField(activity, "mRobotMotion", mockMotion);

        // Simulate speech finished
        listener.onEnd(42);

        // Since feedback contains "Excellent", expect CHEER action
        verify(mockMotion).doAction(RobotMotion.Action.CHEER);
    }
}
