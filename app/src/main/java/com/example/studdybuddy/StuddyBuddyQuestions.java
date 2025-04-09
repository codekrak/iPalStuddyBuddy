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
                questions.add(new StuddyBuddyQuestion("What is 7 × 8?", new String[]{"54", "56", "58", "64"}, 1, "Correct, 7 times 8 is 56."));
                questions.add(new StuddyBuddyQuestion("What is 25% of 80?", new String[]{"10", "15", "20", "25"}, 2, "Correct, 25% of 80 is 20."));
                questions.add(new StuddyBuddyQuestion("What is the area of a rectangle with sides 4 and 6?", new String[]{"10", "16", "24", "30"}, 2, "Correct, area is 4 × 6 = 24."));
                questions.add(new StuddyBuddyQuestion("What is 3 to the power of 2?", new String[]{"6", "8", "9", "12"}, 2, "Correct, 3² = 9."));
                questions.add(new StuddyBuddyQuestion("What is the next prime number after 7?", new String[]{"8", "9", "10", "11"}, 3, "Correct, the next prime after 7 is 11."));
                questions.add(new StuddyBuddyQuestion("Solve: 100 - 37", new String[]{"63", "67", "73", "83"}, 0, "Correct, 100 - 37 is 63."));
                questions.add(new StuddyBuddyQuestion("What is 1/2 + 1/4?", new String[]{"3/4", "2/4", "1/4", "1"}, 0, "Correct, 1/2 + 1/4 = 3/4."));
                questions.add(new StuddyBuddyQuestion("What is the perimeter of a square with side 5?", new String[]{"10", "15", "20", "25"}, 2, "Correct, perimeter is 4 × 5 = 20."));
                questions.add(new StuddyBuddyQuestion("What is 90 divided by 9?", new String[]{"9", "8", "10", "11"}, 0, "Correct, 90 ÷ 9 = 10."));
                questions.add(new StuddyBuddyQuestion("Which number is a multiple of both 3 and 5?", new String[]{"10", "12", "15", "18"}, 2, "Correct, 15 is a multiple of both 3 and 5."));
                break;

            case "reading":
                // ✅ Adds Reading-related questions to the list
                questions.add(new StuddyBuddyQuestion("What is the author's purpose if they want to persuade?", new String[]{"To entertain", "To inform", "To convince", "To describe"}, 2, "Correct, 'to persuade' means to convince the reader."));
                questions.add(new StuddyBuddyQuestion("What is a synonym for 'happy'?", new String[]{"Sad", "Joyful", "Angry", "Tired"}, 1, "Correct, 'joyful' is a synonym for 'happy'."));
                questions.add(new StuddyBuddyQuestion("What point of view uses 'I' and 'we'?", new String[]{"First-person", "Second-person", "Third-person", "Narrator"}, 0, "Correct, 'I' and 'we' indicate first-person point of view."));
                questions.add(new StuddyBuddyQuestion("What is a theme?", new String[]{"The setting", "The characters", "The plot", "The message"}, 3, "Correct, theme is the central message of the story."));
                questions.add(new StuddyBuddyQuestion("What do we call a comparison using 'like' or 'as'?", new String[]{"Simile", "Metaphor", "Hyperbole", "Alliteration"}, 0, "Correct, a simile uses 'like' or 'as'."));
                questions.add(new StuddyBuddyQuestion("Which of the following is a genre?", new String[]{"Fiction", "Plot", "Theme", "Simile"}, 0, "Correct, fiction is a literary genre."));
                questions.add(new StuddyBuddyQuestion("What is the conflict in a story?", new String[]{"The setting", "The problem", "The resolution", "The climax"}, 1, "Correct, the conflict is the problem in the story."));
                questions.add(new StuddyBuddyQuestion("What is an antonym for 'difficult'?", new String[]{"Hard", "Simple", "Tough", "Tricky"}, 1, "Correct, 'simple' is an antonym for 'difficult'."));
                questions.add(new StuddyBuddyQuestion("What is the main character called?", new String[]{"Protagonist", "Antagonist", "Narrator", "Author"}, 0, "Correct, the protagonist is the main character."));
                questions.add(new StuddyBuddyQuestion("What is the climax of a story?", new String[]{"The introduction", "The turning point", "The setting", "The ending"}, 1, "Correct, the climax is the turning point of the story."));
                break;

            case "science":
                // ✅ Adds Science-related questions to the list
                questions.add(new StuddyBuddyQuestion("What planet do we live on?", new String[]{"Mars", "Venus", "Earth", "Jupiter"}, 2, "Correct, we live on Earth."));
                questions.add(new StuddyBuddyQuestion("What part of the plant absorbs water?", new String[]{"Roots", "Stem", "Leaves", "Flower"}, 0, "Correct, roots absorb water from the soil."));
                questions.add(new StuddyBuddyQuestion("What gas do humans need to breathe?", new String[]{"Oxygen", "Carbon dioxide", "Hydrogen", "Nitrogen"}, 0, "Correct, humans need oxygen."));
                questions.add(new StuddyBuddyQuestion("What is the boiling point of water?", new String[]{"90°C", "100°C", "110°C", "120°C"}, 1, "Correct, water boils at 100 degrees Celsius."));
                questions.add(new StuddyBuddyQuestion("Which organ pumps blood?", new String[]{"Lungs", "Brain", "Heart", "Liver"}, 2, "Correct, the heart pumps blood."));
                questions.add(new StuddyBuddyQuestion("Which of these is a state of matter?", new String[]{"Solid", "Wave", "Energy", "Gravity"}, 0, "Correct, solid is a state of matter."));
                questions.add(new StuddyBuddyQuestion("What is the center of an atom called?", new String[]{"Electron", "Proton", "Nucleus", "Molecule"}, 2, "Correct, the nucleus is the atom’s center."));
                questions.add(new StuddyBuddyQuestion("What do we call animals that eat only plants?", new String[]{"Carnivores", "Herbivores", "Omnivores", "Scavengers"}, 1, "Correct, herbivores eat only plants."));
                questions.add(new StuddyBuddyQuestion("What is Earth's natural satellite?", new String[]{"Mars", "The Moon", "The Sun", "Venus"}, 1, "Correct, the Moon is Earth's natural satellite."));
                questions.add(new StuddyBuddyQuestion("What causes day and night?", new String[]{"Moon phases", "Earth's rotation", "Seasons", "Tides"}, 1, "Correct, day and night are caused by Earth’s rotation."));
                break;

            case "government":
                // ✅ Adds Government-related questions to the list
                questions.add(new StuddyBuddyQuestion("How many branches of government are there?", new String[]{"2", "3", "4", "5"}, 1, "Correct, there are 3 branches of government."));
                questions.add(new StuddyBuddyQuestion("Who makes federal laws?", new String[]{"The President", "Congress", "The Supreme Court", "Governors"}, 1, "Correct, Congress makes federal laws."));
                questions.add(new StuddyBuddyQuestion("What are the first ten amendments called?", new String[]{"The Constitution", "Bill of Rights", "Declaration", "Federalist Papers"}, 1, "Correct, the first ten amendments are the Bill of Rights."));
                questions.add(new StuddyBuddyQuestion("What is the highest court in the U.S.?", new String[]{"Federal Court", "State Court", "Supreme Court", "Appeals Court"}, 2, "Correct, the Supreme Court is the highest."));
                questions.add(new StuddyBuddyQuestion("Who was the main author of the Declaration of Independence?", new String[]{"George Washington", "Thomas Jefferson", "John Adams", "Ben Franklin"}, 1, "Correct, Thomas Jefferson was the main author."));
                questions.add(new StuddyBuddyQuestion("How many states are in the U.S.?", new String[]{"48", "49", "50", "52"}, 2, "Correct, there are 50 states."));
                questions.add(new StuddyBuddyQuestion("What is the capital of the United States?", new String[]{"New York", "Los Angeles", "Washington, D.C.", "Chicago"}, 2, "Correct, Washington, D.C. is the capital."));
                questions.add(new StuddyBuddyQuestion("What is the rule of law?", new String[]{"Leaders follow the law", "The President is above the law", "Only Congress follows laws", "Judges create laws"}, 0, "Correct, everyone must follow the law."));
                questions.add(new StuddyBuddyQuestion("Who signs bills into laws?", new String[]{"Congress", "The President", "Supreme Court", "Governors"}, 1, "Correct, the President signs bills into law."));
                questions.add(new StuddyBuddyQuestion("What does the judicial branch do?", new String[]{"Make laws", "Enforce laws", "Interpret laws", "Veto laws"}, 2, "Correct, the judicial branch interprets laws."));
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

