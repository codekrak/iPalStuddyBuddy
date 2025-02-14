import com.robotemi.sdk.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizManager {

    static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        public Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private static Map<String, List<Question>> quizData;
    private static List<Question> currentQuiz;
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static Robot robot;

    public static void main(String[] args) {
        // Initialize quiz data
        quizData = new HashMap<>();

        quizData.put("Math", new ArrayList<>());
        quizData.get("Math").add(new Question("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, 2));
        quizData.get("Math").add(new Question("What is 12 ÷ 4?", new String[]{"2", "3", "4", "5"}, 1));
        quizData.get("Math").add(new Question("What is the square root of 16?", new String[]{"2", "4", "6", "8"}, 1));
        quizData.get("Math").add(new Question("Solve: 9 x 9", new String[]{"72", "81", "90", "99"}, 1));
        quizData.get("Math").add(new Question("What is 15% of 200?", new String[]{"20", "25", "30", "35"}, 2));

        quizData.put("Reading", new ArrayList<>());
        quizData.get("Reading").add(new Question("Who wrote '1984'?", new String[]{"George Orwell", "J.K. Rowling", "Mark Twain", "Jane Austen"}, 0));
        quizData.get("Reading").add(new Question("What is the main idea of a story called?", new String[]{"Plot", "Theme", "Conflict", "Setting"}, 1));
        quizData.get("Reading").add(new Question("A synonym for 'happy' is?", new String[]{"Sad", "Angry", "Joyful", "Bored"}, 2));
        quizData.get("Reading").add(new Question("What do we call the time and place of a story?", new String[]{"Plot", "Theme", "Setting", "Conflict"}, 2));
        quizData.get("Reading").add(new Question("Which is a non-fiction book?", new String[]{"Harry Potter", "The Great Gatsby", "A Science Textbook", "Lord of the Rings"}, 2));

        quizData.put("Science", new ArrayList<>());
        quizData.get("Science").add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, 0));
        quizData.get("Science").add(new Question("What planet is closest to the Sun?", new String[]{"Venus", "Earth", "Mars", "Mercury"}, 3));
        quizData.get("Science").add(new Question("Which gas do plants absorb from the air?", new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"}, 2));
        quizData.get("Science").add(new Question("How many bones are in the adult human body?", new String[]{"206", "208", "210", "215"}, 0));
        quizData.get("Science").add(new Question("What type of energy is produced by the Sun?", new String[]{"Kinetic", "Solar", "Nuclear", "Chemical"}, 1));

        quizData.put("Government", new ArrayList<>());
        quizData.get("Government").add(new Question("What is the supreme law of the United States?", new String[]{"The Bill of Rights", "The Constitution", "The Declaration of Independence", "The Articles of Confederation"}, 1));
        quizData.get("Government").add(new Question("Who is the Commander in Chief of the military?", new String[]{"The President", "The Vice President", "The Speaker of the House", "The Chief Justice"}, 0));
        quizData.get("Government").add(new Question("How many U.S. Senators are there?", new String[]{"50", "100", "435", "200"}, 1));
        quizData.get("Government").add(new Question("What are the three branches of government?", new String[]{"Legislative, Executive, Judicial", "Congress, President, Supreme Court", "Democrats, Republicans, Independents", "Federal, State, Local"}, 0));
        quizData.get("Government").add(new Question("Who was the first President of the United States?", new String[]{"Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"}, 1));

        robot = Robot.getInstance(); // Get iPal Robot instance

        // Start by asking the user to select a subject
        selectSubject();
    }

    private static void selectSubject() {
        robot.speak("Please select a subject: Math, Reading, Science, or Government.");
        robot.showNotification("Select a Subject", new ArrayList<>(quizData.keySet()), new Robot.OnSdkCallback() {
            @Override
            public void onResult(String selectedSubject) {
                if (quizData.containsKey(selectedSubject)) {
                    currentQuiz = quizData.get(selectedSubject);
                    currentQuestionIndex = 0;
                    score = 0; // Reset score for new quiz
                    startQuiz();
                } else {
                    robot.speak("Invalid selection. Please try again.");
                    selectSubject();
                }
            }
        });
    }

    private static void startQuiz() {
        displayQuestion();
    }

    private static void displayQuestion() {
        if (currentQuestionIndex < currentQuiz.size()) {
            Question q = currentQuiz.get(currentQuestionIndex);
            StringBuilder questionText = new StringBuilder("Question: " + q.question + "\n");

            List<String> answerChoices = new ArrayList<>();
            for (String option : q.options) {
                answerChoices.add(option);
            }

            // Speak question and display options as buttons
            robot.speak(questionText.toString());
            robot.showNotification(q.question, answerChoices, new Robot.OnSdkCallback() {
                @Override
                public void onResult(String selectedAnswer) {
                    checkAnswer(selectedAnswer);
                }
            });

        } else {
            displayFinalScore();
        }
    }

    private static void checkAnswer(String selectedAnswer) {
        Question q = currentQuiz.get(currentQuestionIndex);
        if (selectedAnswer.equals(q.options[q.correctAnswerIndex])) {
            robot.speak("Correct! Good job.");
            robot.showToast("✅ Correct!");
            score++; // Increase score on correct answer
        } else {
            robot.speak("Incorrect. The correct answer was " + q.options[q.correctAnswerIndex]);
            robot.showToast("❌ Incorrect. Correct answer: " + q.options[q.correctAnswerIndex]);
        }

        // Move to next question
        currentQuestionIndex++;
        displayQuestion();
    }

    private static void displayFinalScore() {
        robot.speak("Quiz finished! Your final score is " + score + " out of " + currentQuiz.size());
        robot.showToast("🏆 Final Score: " + score + "/" + currentQuiz.size());

        selectSubject(); // Restart the quiz selection
    }
}
