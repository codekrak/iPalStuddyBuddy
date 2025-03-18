package com.example.studdybuddy;

import android.content.Context;

public class QuestionManager {
    private StuddyBuddyQuestions questions;
    private int questionIndex = 0;
    private int correctAnswers = 0;

    public QuestionManager(StuddyBuddyQuestions questions, Context context) {
        this.questions = questions;
    }

    // ✅ Get the next question and update the questionIndex
    public StuddyBuddyQuestion getNextQuestion() {
        if (questionIndex < questions.getSize()) {
            return questions.getQuestion(questionIndex++);
        }
        return null;
    }

    // ✅ Validate answer based on the correct index
    public boolean validateAnswer(String userAnswer) {
        StuddyBuddyQuestion currentQuestion = questions.getQuestion(questionIndex - 1); // Get last question asked
        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
            return true;
        }
        return false;
    }

    // ✅ Provide feedback after a question is answered
    public String getFeedback() {
        StuddyBuddyQuestion lastQuestion = questions.getQuestion(questionIndex - 1);
        return lastQuestion.getCorrectMessage();
    }

    // ✅ Get the final feedback based on the score
    public String getFinalFeedback() {
        if (correctAnswers >= 9) return "Excellent! You nailed it!";
        if (correctAnswers >= 7) return "Great job! You're almost there!";
        if (correctAnswers >= 5) return "Good effort! High five!";
        return "Better luck next time. Keep practicing!";
    }

    // ✅ Get current question number
    public int getCurrentQuestionNumber() {
        return questionIndex;
    }

    // ✅ Get final score
    public String finalScoreText() {
        return "Correct answers: " + correctAnswers + " / " + questions.getSize();
    }
}
