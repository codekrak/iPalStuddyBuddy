package com.example.studdybuddy; // Defines the package where this class is located

import java.util.ArrayList;
import java.util.List; // ✅ Import necessary Java utilities for handling lists

// ✅ Class to manage a list of quiz questions based on the selected subject
public class StuddyBuddyQuestions {
    private List<StuddyBuddyQuestion> questions; // ✅ Stores the list of questions for a subject

    // ✅ Constructor: Initializes questions based on the selected subject
    public StuddyBuddyQuestions(String subject) {
        questions = new ArrayList<>(); // ✅ Initializes an empty list of questions

        // ✅ Ensure subject is valid and not null
        if (subject == null || subject.trim().isEmpty()) {
            subject = "Math"; // ✅ Default to "Math" if no subject is provided
        }

        // ✅ Normalize input to lowercase for case-insensitive subject matching
        loadQuestionsBySubject(subject.toLowerCase());
    }

    // ✅ Loads questions based on the selected subject
    private void loadQuestionsBySubject(String subject) {
        switch (subject) {
            case "math":
                // ✅ Adds Math-related questions to the list
                questions.add(new StuddyBuddyQuestion("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, 2, "Correct, 5 + 3 equals 8."));
                questions.add(new StuddyBuddyQuestion("What is 12 ÷ 4?", new String[]{"2", "3", "4", "5"}, 1, "Correct, 12 divided by 4 is 3."));
                questions.add(new StuddyBuddyQuestion("What is the square root of 16?", new String[]{"2", "4", "6", "8"}, 1, "Correct, the square root of 16 is 4."));
                questions.add(new StuddyBuddyQuestion("Solve: 15 - 7", new String[]{"6", "7", "8", "9"}, 2, "Correct, 15 minus 7 is 8."));
                questions.add(new StuddyBuddyQuestion("What is 9 × 3?", new String[]{"24", "26", "27", "30"}, 2, "Correct, 9 times 3 is 27."));
                break;

            case "reading":
                // ✅ Adds Reading-related questions to the list
                questions.add(new StuddyBuddyQuestion("Who wrote '1984'?", new String[]{"George Orwell", "J.K. Rowling", "Mark Twain", "Jane Austen"}, 0, "Correct, '1984' was written by George Orwell."));
                questions.add(new StuddyBuddyQuestion("What is the main idea of a story called?", new String[]{"Plot", "Theme", "Conflict", "Setting"}, 1, "Correct, the theme represents the main idea of a story."));
                questions.add(new StuddyBuddyQuestion("What do we call words that sound alike but have different meanings?", new String[]{"Homophones", "Synonyms", "Antonyms", "Adjectives"}, 0, "Correct, homophones are words that sound alike but have different meanings."));
                questions.add(new StuddyBuddyQuestion("What is a noun?", new String[]{"A person, place, or thing", "An action word", "A describing word", "A feeling"}, 0, "Correct, a noun is a person, place, or thing."));
                questions.add(new StuddyBuddyQuestion("What is an autobiography?", new String[]{"A book about someone's life", "A book about science", "A fictional story", "A book about animals"}, 0, "Correct, an autobiography is a book written about someone's life by themselves."));
                break;

            case "science":
                // ✅ Adds Science-related questions to the list
                questions.add(new StuddyBuddyQuestion("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, 0, "Correct, H2O is the chemical symbol for water."));
                questions.add(new StuddyBuddyQuestion("What planet is closest to the Sun?", new String[]{"Venus", "Earth", "Mars", "Mercury"}, 3, "Correct, Mercury is the closest planet to the Sun."));
                questions.add(new StuddyBuddyQuestion("What gas do plants need to perform photosynthesis?", new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"}, 2, "Correct, plants require carbon dioxide for photosynthesis."));
                questions.add(new StuddyBuddyQuestion("What is the powerhouse of the cell?", new String[]{"Nucleus", "Ribosome", "Mitochondria", "Cell membrane"}, 2, "Correct, the mitochondria is known as the powerhouse of the cell."));
                questions.add(new StuddyBuddyQuestion("What force keeps us on the ground?", new String[]{"Magnetism", "Friction", "Gravity", "Inertia"}, 2, "Correct, gravity is the force that keeps us grounded."));
                break;

            case "government":
                // ✅ Adds Government-related questions to the list
                questions.add(new StuddyBuddyQuestion("What is the supreme law of the United States?", new String[]{"Bill of Rights", "The Constitution", "Declaration of Independence", "Articles of Confederation"}, 1, "Correct, the Constitution is the supreme law of the United States."));
                questions.add(new StuddyBuddyQuestion("Who is the Commander in Chief of the military?", new String[]{"The President", "The Vice President", "The Speaker of the House", "The Chief Justice"}, 0, "Correct, the President is the Commander in Chief of the military."));
                questions.add(new StuddyBuddyQuestion("How many U.S. Senators are there?", new String[]{"50", "100", "150", "200"}, 1, "Correct, there are 100 U.S. Senators, two per state."));
                questions.add(new StuddyBuddyQuestion("What are the first ten amendments to the Constitution called?", new String[]{"The Bill of Rights", "The Articles of Confederation", "The Federalist Papers", "The Amendments"}, 0, "Correct, the first ten amendments are called the Bill of Rights."));
                questions.add(new StuddyBuddyQuestion("Who was the first President of the United States?", new String[]{"Abraham Lincoln", "Thomas Jefferson", "George Washington", "John Adams"}, 2, "Correct, George Washington was the first U.S. President."));
                break;

            default:
                // ✅ If an invalid subject is given, default to Math
                loadQuestionsBySubject("math");
                break;
        }
    }

    // ✅ Retrieve a question by index
    public StuddyBuddyQuestion getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index); // ✅ Return the question at the given index
        }
        return null; // ✅ Prevents index out-of-bounds errors
    }

    // ✅ Get the total number of questions
    public int getSize() {
        return questions.size(); // ✅ Returns the number of questions in the current subject
    }
}

