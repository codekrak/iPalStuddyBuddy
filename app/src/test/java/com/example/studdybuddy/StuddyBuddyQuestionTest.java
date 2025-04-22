package com.example.studdybuddy;

import org.junit.Test;
import static org.junit.Assert.*;

public class StuddyBuddyQuestionTest {

    @Test
    public void getCorrectAnswer_returnsCorrectChoice() {
        String[] choices = {"A", "B", "C"};
        StuddyBuddyQuestion question = new StuddyBuddyQuestion("Test", choices, 1, "Correct");
        assertEquals("B", question.getCorrectAnswer());
    }

    @Test
    public void getChoices_returnsAllOptions() {
        String[] choices = {"A", "B", "C"};
        StuddyBuddyQuestion question = new StuddyBuddyQuestion("Test", choices, 1, "Correct");
        assertArrayEquals(choices, question.getChoices());
    }
}