import com.robotemi.sdk.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Instantiate the QuizManager
        QuizManager quizManager = new QuizManager();

        // Initialize quiz data
        quizManager.initializeQuizData();

        // Simulate selecting a subject (e.g., "Math")
        String selectedSubject = "Math";
        System.out.println("Selected Subject: " + selectedSubject);
=
        // Set the quiz based on the selected subject
        QuizManager.currentQuiz = QuizManager.quizData.get(selectedSubject);
        QuizManager.currentQuestionIndex = 0;
        QuizManager.score = 0;

        // Start the quiz
        quizManager.startQuiz();

        // Simulate answering questions
        for (QuizManager.Question question : QuizManager.currentQuiz) {
            System.out.println("Question: " + question.question);

            // Simulating user selecting the correct answer
            String correctAnswer = question.options[question.correctAnswerIndex];
            System.out.println("User Answer (Correct): " + correctAnswer);
            quizManager.checkAnswer(correctAnswer);

            // Simulating user selecting a wrong answer
            String wrongAnswer = question.options[(question.correctAnswerIndex + 1) % question.options.length];
            System.out.println("User Answer (Wrong): " + wrongAnswer);
            quizManager.checkAnswer(wrongAnswer);
        }

        // Display the final score
        quizManager.displayFinalScore();
    }
}
