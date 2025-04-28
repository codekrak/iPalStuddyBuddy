package com.example.studdybuddy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class QuizActivityTest {
    private QuizActivity activity;

    @Before
    public void setUp() {
        Intent intent = new Intent(RuntimeEnvironment.application, QuizActivity.class);
        intent.putExtra("subject", "math");
        activity = Robolectric
                .buildActivity(QuizActivity.class, intent)
                .create()
                .start()
                .resume()
                .get();
    }

    @Test
    public void testQuizLoadsCorrectQuestion() {
        TextView questionText = (TextView) activity.findViewById(R.id.questionTextView);
        assertNotNull("questionTextView should exist", questionText);
        String text = questionText.getText().toString();
        assertFalse("Question text should not be empty", text.isEmpty());
    }

    @Test
    public void testAnswerButton1ClickAdvancesOrFinishes() {
        // cast to Button
        Button btn1 = (Button) activity.findViewById(R.id.answerButton1);
        assertNotNull("answerButton1 should exist", btn1);
        TextView before = (TextView) activity.findViewById(R.id.questionTextView);
        String beforeText = before.getText().toString();

        // perform click
        btn1.performClick();

        // either quiz continues (questionText changes)...
        TextView after = (TextView) activity.findViewById(R.id.questionTextView);
        if (!after.getText().toString().equals(beforeText)) {
            assertNotEquals("Question text should update after click",
                    beforeText, after.getText().toString());
        } else {
            // ...or, if it's the end, backToMenuButton shows and finishes
            Button backBtn = (Button) activity.findViewById(R.id.backToMenuButton);
            assertNotNull("backToMenuButton should exist", backBtn);
            assertEquals(View.VISIBLE, backBtn.getVisibility());
            backBtn.performClick();
            assertTrue("Activity should finish when backToMenuButton clicked",
                    activity.isFinishing());
        }
    }
}
