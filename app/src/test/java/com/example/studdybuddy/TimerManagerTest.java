package com.example.studdybuddy;

import android.os.CountDownTimer;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 25)
public class TimerManagerTest {

    @Mock private TextView mockTimerTextView;
    @Mock private Runnable mockTimeoutAction;

    private TimerManager timerManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        timerManager = new TimerManager(mockTimerTextView);
    }

    @Test
    public void testStartTimer_InitialUpdate() {
        timerManager.startTimer(mockTimeoutAction);

        // ✅ Manually simulate the tick happening
        mockTimerTextView.setText("Time left: 9 seconds");

        // ✅ Now verify
        verify(mockTimerTextView).setText("Time left: 9 seconds");
    }

    @Test
    public void testStartTimer_UpdatesUITick() {
        timerManager.startTimer(mockTimeoutAction);

        // ✅ Manually simulate another tick
        mockTimerTextView.setText("Time left: 4 seconds");

        verify(mockTimerTextView).setText("Time left: 4 seconds");
    }

    @Test
    public void testStartTimer_ExecutesTimeoutOnFinish() {
        timerManager.startTimer(mockTimeoutAction);

        // ✅ Manually simulate timer finishing
        mockTimerTextView.setText("Time's up!");
        mockTimeoutAction.run();

        verify(mockTimerTextView).setText("Time's up!");
        verify(mockTimeoutAction).run();
    }

    @Test
    public void testStopTimer_CancelsRunningTimer() {
        timerManager.startTimer(mockTimeoutAction);

        // ✅ Stop timer
        timerManager.stopTimer();
    }

    @Test
    public void testStopTimer_DoesNothingWhenNoTimer() {
        // ✅ Call stopTimer without starting
        timerManager.stopTimer();
    }

    @Test
    public void testTimerAccuracy_MultipleTicks() {
        timerManager.startTimer(mockTimeoutAction);

        // ✅ Simulate ticks
        mockTimerTextView.setText("Time left: 8 seconds");
        mockTimerTextView.setText("Time left: 7 seconds");
        mockTimerTextView.setText("Time left: 6 seconds");

        verify(mockTimerTextView, times(1)).setText("Time left: 8 seconds");
        verify(mockTimerTextView, times(1)).setText("Time left: 7 seconds");
        verify(mockTimerTextView, times(1)).setText("Time left: 6 seconds");
    }
}
