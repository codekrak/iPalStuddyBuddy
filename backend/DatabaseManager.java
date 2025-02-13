import com.robotemi.sdk.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private static List<Question> quiz;
    private static int currentQuestionIndex = 0;
    private static Robot robot;  // Robot instance for interaction

    public static void main(String[] args) {
        // Initialize the quiz questions
        quiz = new ArrayList<>();
        quiz.add(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                2 // Paris is correct (index 2)
        ));
        quiz.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                1 // Mars is correct (index 1)
        ));
        quiz.add(new Question(
                "Who wrote 'To Kill a Mockingbird'?",
                new String[]{"Harper Lee", "J.K. Rowling", "Mark Twain", "Ernest Hemingway"},
                0 // Harper Lee is correct (index 0)
        ));

        robot = Robot.getInstance(); // Get the iPal Robot instance
        startQuiz();
    }

    private static void startQuiz() {
        displayQuestion();
    }

    private static void displayQuestion() {
        if (currentQuestionIndex < quiz.size()) {
            Question q = quiz.get(currentQuestionIndex);
            StringBuilder questionText = new StringBuilder("Question: " + q.question + "\n");

            for (int i = 0; i < q.options.length; i++) {
                questionText.append((i + 1)).append(". ").append(q.options[i]).append("\n");
            }

            // Display question on robot screen
            robot.speak(questionText.toString());
            robot.showToast(questionText.toString());

            // Wait for user input
            getUserAnswer();
        } else {
            robot.speak("Quiz finished! Thank you for playing.");
            robot.showToast("Quiz finished! Thank you for playing.");
        }
    }

    private static void getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        robot.speak("Please enter the number of your answer.");
        robot.showToast("Please enter the number of your answer.");

        int userAnswer = scanner.nextInt() - 1; // Convert to zero-based index

        checkAnswer(userAnswer);
    }

    private static void checkAnswer(int userAnswer) {
        Question q = quiz.get(currentQuestionIndex);

        if (userAnswer == q.correctAnswerIndex) {
            robot.speak("Correct! Good job.");
            robot.showToast("Correct! ✅");
        } else {
            robot.speak("Incorrect. The correct answer was " + q.options[q.correctAnswerIndex]);
            robot.showToast("Incorrect ❌. Correct answer: " + q.options[q.correctAnswerIndex]);
        }

        // Move to the next question
        currentQuestionIndex++;
        displayQuestion();
    }
}
