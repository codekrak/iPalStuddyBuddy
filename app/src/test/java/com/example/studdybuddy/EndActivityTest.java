package com.example.studdybuddy;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndActivityTest {

    @Mock private Bundle mockBundle;
    @Mock private Intent mockIntent;
    @Mock private TextView mockScoreTextView;
    @Mock private TextView mockFeedbackTextView;
    @Mock private Button mockRestartButton;
    @Mock private Button mockExitButton;
    @Mock private Window mockWindow;
    @Mock private Window.Callback mockWindowCallback;
    @Mock private WindowManager mockWindowManager;
    @Mock private Resources mockResources;
    @Mock private Theme mockTheme;
    @Mock private TypedArray mockTypedArray;

    private EndActivity endActivity;

    @Before
    public void setUp() {
        endActivity = spy(new EndActivity());

        // Mock all Context/Activity framework methods
        doReturn(mockIntent).when(endActivity).getIntent();
        doReturn(mockWindow).when(endActivity).getWindow();
        doReturn(mockResources).when(endActivity).getResources();
        doReturn(mockTheme).when(endActivity).getTheme();

        // Setup Window mock hierarchy
        when(mockWindow.getCallback()).thenReturn(mockWindowCallback);
        when(mockWindow.getWindowManager()).thenReturn(mockWindowManager);

        // Mock theme and styled attributes
        when(mockTheme.obtainStyledAttributes(any(int[].class))).thenReturn(mockTypedArray);
        when(mockTypedArray.getResourceId(anyInt(), anyInt())).thenReturn(0);

        // Mock findViewById()
        when(endActivity.findViewById(anyInt())).thenAnswer(new Answer<View>() {
            @Override
            public View answer(InvocationOnMock invocation) throws Throwable {
                int id = (Integer) invocation.getArguments()[0];
                if (id == R.id.scoreTextView) return mockScoreTextView;
                if (id == R.id.feedbackTextView) return mockFeedbackTextView;
                if (id == R.id.restartButton) return mockRestartButton;
                if (id == R.id.exitButtonEnd) return mockExitButton;
                return null;
            }
        });

        // Mock restart button click
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Intent intent = new Intent(endActivity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                endActivity.startActivity(intent);
                return null;
            }
        }).when(mockRestartButton).performClick();

        // Mock exit button click
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                endActivity.finish();
                return null;
            }
        }).when(mockExitButton).performClick();
    }

    @Test
    public void onCreate_setsUpViewsCorrectly() {
        when(mockIntent.getStringExtra("SCORE_TEXT")).thenReturn("5/10");
        when(mockIntent.getStringExtra("FEEDBACK")).thenReturn("Good effort!");

        endActivity.onCreate(mockBundle);

        verify(mockScoreTextView).setText("5/10");
        verify(mockFeedbackTextView).setText("Good effort!");
    }

    @Test
    public void restartButton_launchesMainActivity() {
        when(mockIntent.getStringExtra("SCORE_TEXT")).thenReturn("5/10");
        when(mockIntent.getStringExtra("FEEDBACK")).thenReturn("Feedback");
        endActivity.onCreate(mockBundle);

        mockRestartButton.performClick();

        ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
        verify(endActivity).startActivity(intentCaptor.capture());
        assertTrue((intentCaptor.getValue().getFlags() & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0);
    }

    @Test
    public void exitButton_finishesActivity() {
        when(mockIntent.getStringExtra("SCORE_TEXT")).thenReturn("5/10");
        when(mockIntent.getStringExtra("FEEDBACK")).thenReturn("Feedback");
        endActivity.onCreate(mockBundle);

        mockExitButton.performClick();

        verify(endActivity).finish();
    }
}