package com.example.studdybuddy;

public class StuddyBuddyQuestion {
    private String question;
    private String[] choices;
    private int correctAnswerIndex;
    private String correctMessage;

    public StuddyBuddyQuestion(String question, String[] choices, int correctAnswerIndex, String correctMessage) {
        this.question = question;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
        this.correctMessage = correctMessage;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getCorrectMessage() {
        return correctMessage;
    }

    // ✅ New method to return the correct answer
    public String getCorrectAnswer() {
        return choices[correctAnswerIndex];
    }
}
