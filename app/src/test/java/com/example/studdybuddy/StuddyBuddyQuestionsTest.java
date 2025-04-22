package com.example.studdybuddy;

import org.junit.Test;
import static org.junit.Assert.*;

public class StuddyBuddyQuestionsTest {

    @Test
    public void getSize_returnsCorrectCount() {
        StuddyBuddyQuestions questions = new StuddyBuddyQuestions("math");
        assertEquals(10, questions.getSize());
    }

    @Test
    public void getQuestion_returnsValidQuestion() {
        StuddyBuddyQuestions questions = new StuddyBuddyQuestions("math");
        assertNotNull(questions.getQuestion(0));
    }
}