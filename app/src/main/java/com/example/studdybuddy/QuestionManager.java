package com.example.studdybuddy; // Defines the package where this class belongs

import android.content.Context; // Imports the Context class, used for accessing application-specific resources

// Manages the quiz questions, user progress, and scoring
public class QuestionManager {
    private StuddyBuddyQuestions questions; // Stores the list of quiz questions
    private int questionIndex = 0; // Keeps track of the current question number
    private int correctAnswers = 0; // Tracks the number of correct answers

    // Constructor initializes the QuestionManager with a list of questions
    public QuestionManager(StuddyBuddyQuestions questions, Context context) {
        this.questions = questions;
    }

    // ✅ Retrieves the next question and increments the questionIndex
    public StuddyBuddyQuestion getNextQuestion() {
        if (questionIndex < questions.getSize()) { // Ensures there are remaining questions
            return questions.getQuestion(questionIndex++); // Returns the current question and moves to the next
        }
        return null; // Returns null when there are no more questions
    }

    // ✅ Validates the user's answer against the correct answer
    public boolean validateAnswer(String userAnswer) {
        // Retrieves the last question asked (since index was already incremented)
        StuddyBuddyQuestion currentQuestion = questions.getQuestion(questionIndex - 1);

        // Compares user input with the correct answer (ignores case sensitivity)
        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            correctAnswers++; // Increments the correct answer count if the answer is correct
            return true; // Returns true for a correct answer
        }
        return false; // Returns false for an incorrect answer
    }

    // ✅ Retrieves feedback for the last answered question
    public String getFeedback() {
        StuddyBuddyQuestion lastQuestion = questions.getQuestion(questionIndex - 1); // Gets the last asked question
        return lastQuestion.getCorrectMessage(); // Returns the predefined feedback message
    }

    // ✅ Provides final feedback based on the user's score
    public String getFinalFeedback() {
        if (correctAnswers >= 9) return "Excellent! You nailed it!"; // 9 or more correct answers
        if (correctAnswers >= 7) return "Great job! You're almost there!"; // 7-8 correct answers
        if (correctAnswers >= 5) return "Good effort! High five!"; // 5-6 correct answers
        return "Better luck next time. Keep practicing!"; // Less than 5 correct answers
    }

    // ✅ Returns the final score as a string
    public String finalScoreText() {
        return "Correct answers: " + correctAnswers + " / " + questions.getSize(); // Formats the score as a string
    }
}

