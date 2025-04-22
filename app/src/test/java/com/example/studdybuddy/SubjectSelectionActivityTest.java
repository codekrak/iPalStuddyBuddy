package com.example.studdybuddy;

import android.content.Intent;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import android.view.View;  // Add this import


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectSelectionActivityTest {

    @Mock private Button mockMathButton;
    @Mock private Button mockReadingButton;
    @Mock private Button mockScienceButton;
    @Mock private Button mockGovernmentButton;
    @Captor private ArgumentCaptor<View.OnClickListener> clickCaptor;
    @Captor private ArgumentCaptor<Intent> intentCaptor;

    private SubjectSelectionActivity activity;

    @Before
    public void setUp() {
        activity = new SubjectSelectionActivity() {
            @Override
            public Button findViewById(int id) {
                if (id == R.id.mathButton) return mockMathButton;
                if (id == R.id.readingButton) return mockReadingButton;
                if (id == R.id.scienceButton) return mockScienceButton;
                if (id == R.id.governmentButton) return mockGovernmentButton;
                return null;
            }
        };
        activity.onCreate(null);
    }

    @Test
    public void mathButton_startsQuizWithMathSubject() {
        verify(mockMathButton).setOnClickListener(clickCaptor.capture());
        clickCaptor.getValue().onClick(null);

        verify(activity).startActivity(intentCaptor.capture());
        Intent intent = intentCaptor.getValue();
        assertEquals("math", intent.getStringExtra("subject"));
        assertEquals(QuizActivity.class.getName(),
                intent.getComponent().getClassName());
    }

    @Test
    public void readingButton_startsQuizWithReadingSubject() {
        verify(mockReadingButton).setOnClickListener(clickCaptor.capture());
        clickCaptor.getValue().onClick(null);

        verify(activity).startActivity(intentCaptor.capture());
        assertEquals("reading", intentCaptor.getValue().getStringExtra("subject"));
    }

    @Test
    public void scienceButton_startsQuizWithScienceSubject() {
        verify(mockScienceButton).setOnClickListener(clickCaptor.capture());
        clickCaptor.getValue().onClick(null);

        verify(activity).startActivity(intentCaptor.capture());
        assertEquals("science", intentCaptor.getValue().getStringExtra("subject"));
    }

    @Test
    public void governmentButton_startsQuizWithGovernmentSubject() {
        verify(mockGovernmentButton).setOnClickListener(clickCaptor.capture());
        clickCaptor.getValue().onClick(null);

        verify(activity).startActivity(intentCaptor.capture());
        assertEquals("government", intentCaptor.getValue().getStringExtra("subject"));
    }
}