package com.example.studdybuddy;

import android.widget.TextView;
import android.os.CountDownTimer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TimerManagerTest {

    @Mock private TextView mockTextView;
    @Mock private Runnable mockTimeoutAction;
    @Captor private ArgumentCaptor<CountDownTimer> timerCaptor;

    private TimerManager timerManager;

    @Before
    public void setUp() {
        timerManager = new TimerManager(mockTextView);
    }

    @Test
    public void testStartTimer_InitializesCountdown() {
        // When
        timerManager.startTimer(mockTimeoutAction);

        // Then
        verify(mockTextView).setText("Time left: 9 seconds");
    }

    @Test
    public void testStartTimer_UpdatesUITick() {
        // Given
        timerManager.startTimer(mockTimeoutAction);
        CountDownTimer timer = timerCaptor.getValue();

        // When
        timer.onTick(5000); // 5 seconds remaining

        // Then
        verify(mockTextView).setText("Time left: 4 seconds");
    }

    @Test
    public void testStartTimer_ExecutesTimeoutOnFinish() {
        // Given
        timerManager.startTimer(mockTimeoutAction);
        CountDownTimer timer = timerCaptor.getValue();

        // When
        timer.onFinish();

        // Then
        verify(mockTextView).setText("Time's up!");
        verify(mockTimeoutAction).run();
    }

    @Test
    public void testStopTimer_CancelsRunningTimer() {
        // Given
        timerManager.startTimer(mockTimeoutAction);
        CountDownTimer timer = timerCaptor.getValue();

        // When
        timerManager.stopTimer();

        // Then
        verify(timer).cancel();
    }

    @Test
    public void testStopTimer_DoesNothingWhenNoTimer() {
        // When
        timerManager.stopTimer();

        // Then
        // No exception should be thrown
    }

    @Test
    public void testTimerAccuracy_MultipleTicks() {
        // Given
        timerManager.startTimer(mockTimeoutAction);
        CountDownTimer timer = timerCaptor.getValue();

        // When
        timer.onTick(9000); // 9 seconds
        timer.onTick(8000); // 8 seconds
        timer.onTick(7000); // 7 seconds

        // Then
        verify(mockTextView, times(1)).setText("Time left: 8 seconds");
        verify(mockTextView, times(1)).setText("Time left: 7 seconds");
        verify(mockTextView, times(1)).setText("Time left: 6 seconds");
    }
}