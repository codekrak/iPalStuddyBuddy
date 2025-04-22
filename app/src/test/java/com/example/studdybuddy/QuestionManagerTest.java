package com.example.studdybuddy;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuestionManagerTest {

    @Mock Context mockContext;
    @Mock StuddyBuddyQuestions mockQuestions;

    private QuestionManager questionManager;

    @Before
    public void setUp() {
        // Basic setup that all tests need
        when(mockQuestions.getSize()).thenReturn(3);
        questionManager = new QuestionManager(mockQuestions, mockContext);
    }

    /* Core Functionality Tests */
    @Test
    public void initialization_works() {
        assertNotNull(questionManager);
    }

    @Test
    public void getNextQuestion_returnsQuestionsInOrder() {
        // Setup mock questions just for this test
        StuddyBuddyQuestion q1 = mock(StuddyBuddyQuestion.class);
        StuddyBuddyQuestion q2 = mock(StuddyBuddyQuestion.class);
        StuddyBuddyQuestion q3 = mock(StuddyBuddyQuestion.class);

        when(mockQuestions.getQuestion(0)).thenReturn(q1);
        when(mockQuestions.getQuestion(1)).thenReturn(q2);
        when(mockQuestions.getQuestion(2)).thenReturn(q3);

        assertEquals(q1, questionManager.getNextQuestion());
        assertEquals(q2, questionManager.getNextQuestion());
        assertEquals(q3, questionManager.getNextQuestion());
    }

    @Test
    public void getNextQuestion_returnsNullWhenNoMoreQuestions() {
        // Setup 1 question
        when(mockQuestions.getSize()).thenReturn(1);
        when(mockQuestions.getQuestion(0)).thenReturn(mock(StuddyBuddyQuestion.class));

        assertNotNull(questionManager.getNextQuestion());
        assertNull(questionManager.getNextQuestion());
    }

    @Test
    public void validateAnswer_incrementsScoreForCorrectAnswer() {
        StuddyBuddyQuestion q = mock(StuddyBuddyQuestion.class);
        when(mockQuestions.getQuestion(0)).thenReturn(q);
        when(q.getCorrectAnswer()).thenReturn("Paris");

        questionManager.getNextQuestion();
        assertTrue(questionManager.validateAnswer("Paris"));
    }

    @Test
    public void validateAnswer_isCaseInsensitive() {
        StuddyBuddyQuestion q = mock(StuddyBuddyQuestion.class);
        when(mockQuestions.getQuestion(0)).thenReturn(q);
        when(q.getCorrectAnswer()).thenReturn("Paris");

        questionManager.getNextQuestion();
        assertTrue(questionManager.validateAnswer("pAris"));
    }

    @Test
    public void getFeedback_returnsCorrectMessageAfterAnswer() {
        StuddyBuddyQuestion q = mock(StuddyBuddyQuestion.class);
        when(mockQuestions.getQuestion(0)).thenReturn(q);
        when(q.getCorrectAnswer()).thenReturn("Paris");
        when(q.getCorrectMessage()).thenReturn("Good job!");

        questionManager.getNextQuestion();
        questionManager.validateAnswer("Paris");
        assertEquals("Good job!", questionManager.getFeedback());
    }

    /* Score Calculation Tests */
    @Test
    public void getFinalFeedback_returnsExcellentForHighScore() {
        // Setup 10 questions
        when(mockQuestions.getSize()).thenReturn(10);
        List<StuddyBuddyQuestion> questions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            StuddyBuddyQuestion q = mock(StuddyBuddyQuestion.class);
            when(q.getCorrectAnswer()).thenReturn("Answer"+i);
            questions.add(q);
            when(mockQuestions.getQuestion(i)).thenReturn(q);
        }

        QuestionManager manager = new QuestionManager(mockQuestions, mockContext);

        // Answer all correctly
        for (int i = 0; i < 10; i++) {
            manager.getNextQuestion();
            manager.validateAnswer("Answer"+i);
        }

        assertEquals("Excellent! You nailed it!", manager.getFinalFeedback());
    }

    @Test
    public void finalScoreText_formatsCorrectly() {
        // Setup 5 questions
        when(mockQuestions.getSize()).thenReturn(5);
        QuestionManager manager = new QuestionManager(mockQuestions, mockContext);

        // Setup 3 correct answers
        for (int i = 0; i < 3; i++) {
            StuddyBuddyQuestion q = mock(StuddyBuddyQuestion.class);
            when(mockQuestions.getQuestion(i)).thenReturn(q);
            when(q.getCorrectAnswer()).thenReturn("Correct");
            manager.getNextQuestion();
            manager.validateAnswer("Correct");
        }

        assertEquals("Correct answers: 3 / 5", manager.finalScoreText());
    }
}