package com.example.studdybuddy; // Defines the package where this class is located

// ✅ Represents a single quiz question
public class StuddyBuddyQuestion {
    private String question; // ✅ Stores the question text
    private String[] choices; // ✅ Stores the multiple-choice answer options
    private int correctAnswerIndex; // ✅ Stores the index of the correct answer
    private String correctMessage; // ✅ Stores the feedback message for the correct answer

    // ✅ Constructor: Initializes a new question object with a question, choices, correct answer index, and feedback message
    public StuddyBuddyQuestion(String question, String[] choices, int correctAnswerIndex, String correctMessage) {
        this.question = question; // ✅ Assigns the provided question text to the instance variable
        this.choices = choices; // ✅ Assigns the provided answer choices to the instance variable
        this.correctAnswerIndex = correctAnswerIndex; // ✅ Assigns the index of the correct answer
        this.correctMessage = correctMessage; // ✅ Assigns the correct answer feedback message
    }

    // ✅ Getter method to retrieve the question text
    public String getQuestion() {
        return question;
    }

    // ✅ Getter method to retrieve the list of answer choices
    public String[] getChoices() {
        return choices;
    }

    // ✅ Getter method to retrieve the correct answer feedback message
    public String getCorrectMessage() {
        return correctMessage;
    }

    // ✅ New method to retrieve the correct answer text based on its index
    public String getCorrectAnswer() {
        return choices[correctAnswerIndex]; // ✅ Uses the correct answer index to return the actual answer text
    }
}
