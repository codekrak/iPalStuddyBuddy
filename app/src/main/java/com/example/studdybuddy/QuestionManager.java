package com.example.studdybuddy;
import android.content.Context;

public class QuestionManager {
    private StuddyBuddyQuestions questions;
    private int questionIndex = 0;
    private int correctAnswers = 0;

    public QuestionManager(StuddyBuddyQuestions questions, Context context) {
        this.questions = questions;
    }

    public StuddyBuddyQuestion getNextQuestion() {
        if (questionIndex < questions.getSize()) {
            return questions.getQuestion(questionIndex);
        } else {
            return null;
        }
    }

    public boolean validateAnswer (String userAnswer) {
        StuddyBuddyQuestion currentQuestion = questions.getQuestion(questionIndex);
        questionIndex++;

        if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            correctAnswers++;
            return true;
        } else {
            return false;
        }
    }

    public String getFinalFeedback() {
        if (correctAnswers >= 9) return "Excellent! You nailed it!";
        if (correctAnswers >= 7) return "Great job! You're almost there!";
        if (correctAnswers >= 5) return "Good effort! High five!";
        return "Better luck next time. Keep practicing!";
    }

    public int getCurrentQuestionNumber() {
        return questionIndex + 1;
    }

    public String finalScoreText() {
        return "Correct answers: " + correctAnswers + " / " + questions.getSize();
    }
}
