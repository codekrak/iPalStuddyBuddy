package com.example.trivia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaQuestions {
    List<TriviaQuestion> questions = new ArrayList<>();
    Random random = new Random();

    // List to hold all the questionSet1 and answers
    public TriviaQuestions() {
        int randomNumber = random.nextInt(100) % 3;

        if (randomNumber == 0) {
            // Math Questions
            questions.add(new TriviaQuestion("What is 5 + 3?", "8", "Correct, 5 + 3 equals 8."));
            questions.add(new TriviaQuestion("What is 12 ÷ 4?", "3", "Correct, 12 divided by 4 is 3."));
            questions.add(new TriviaQuestion("What is the square root of 16?", "4", "Correct, the square root of 16 is 4."));
            questions.add(new TriviaQuestion("Solve: 15 - 7", "8", "Correct, 15 minus 7 is 8."));
            questions.add(new TriviaQuestion("What is 9 × 3?", "27", "Correct, 9 times 3 is 27."));
        } else if (randomNumber == 1) {
            // Reading Questions
            questions.add(new TriviaQuestion("Who wrote '1984'?", "George Orwell", "Correct, '1984' was written by George Orwell."));
            questions.add(new TriviaQuestion("What is the main idea of a story called?", "Theme", "Correct, the theme represents the main idea of a story."));
            questions.add(new TriviaQuestion("What do we call words that sound alike but have different meanings?", "Homophones", "Correct, homophones are words that sound alike but have different meanings."));
            questions.add(new TriviaQuestion("What is a noun?", "A person, place, or thing", "Correct, a noun is a person, place, or thing."));
            questions.add(new TriviaQuestion("What is an autobiography?", "A book about someone's life", "Correct, an autobiography is a book written about someone's life by themselves."));
        } else if (randomNumber == 2) {
            // Science Questions
            questions.add(new TriviaQuestion("What is the chemical symbol for water?", "H2O", "Correct, H2O is the chemical symbol for water."));
            questions.add(new TriviaQuestion("What planet is closest to the Sun?", "Mercury", "Correct, Mercury is the closest planet to the Sun."));
            questions.add(new TriviaQuestion("What gas do plants need to perform photosynthesis?", "Carbon Dioxide", "Correct, plants require carbon dioxide for photosynthesis."));
            questions.add(new TriviaQuestion("What is the powerhouse of the cell?", "Mitochondria", "Correct, the mitochondria is known as the powerhouse of the cell."));
            questions.add(new TriviaQuestion("What force keeps us on the ground?", "Gravity", "Correct, gravity is the force that keeps us grounded."));
        } else {
            // Government Questions
            questions.add(new TriviaQuestion("What is the supreme law of the United States?", "The Constitution", "Correct, the Constitution is the supreme law of the United States."));
            questions.add(new TriviaQuestion("Who is the Commander in Chief of the military?", "The President", "Correct, the President is the Commander in Chief of the military."));
            questions.add(new TriviaQuestion("How many U.S. Senators are there?", "100", "Correct, there are 100 U.S. Senators, two per state."));
            questions.add(new TriviaQuestion("What are the first ten amendments to the Constitution called?", "The Bill of Rights", "Correct, the first ten amendments are called the Bill of Rights."));
            questions.add(new TriviaQuestion("Who was the first President of the United States?", "George Washington", "Correct, George Washington was the first U.S. President."));
        }

    }


    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public TriviaQuestion getQuestion(int index) { return questions.get(index); }

    public int getSize() {
        return questions.size();
    }
}

