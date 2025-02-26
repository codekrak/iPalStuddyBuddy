import com.robotemi.sdk.Robot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Speech.SpeechManagerIntegration;

/**
 * Manages a quiz application that interacts with a robot.
 */
public class QuizManager {

    /**
     * Represents a quiz question with options and the correct answer index.
     */
    static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        /**
         * Constructs a new Question.
         *
         * @param question          The question text.
         * @param options           The answer options.
         * @param correctAnswerIndex The index of the correct answer in the options array.
         */
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

    /**
     * Main method to initialize quiz data and start the quiz.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        quizData = new HashMap<>();
        initializeQuizData();
        robot = Robot.getInstance();
        selectSubject();
    }

    /**
     * Initializes the quiz data with predefined questions for various subjects.
     */
    private static void initializeQuizData() {
        quizData.put("Math", new ArrayList<>());
        quizData.get("Math").add(new Question("What is 5 + 3?", new String[]{"6", "7", "8", "9"}, 2));
        quizData.get("Math").add(new Question("What is 12 ÷ 4?", new String[]{"2", "3", "4", "5"}, 1));
        quizData.get("Math").add(new Question("What is the square root of 16?", new String[]{"2", "4", "6", "8"}, 1));
        quizData.get("Math").add(new Question("Solve: 15 - 7", new String[]{"6", "7", "8", "9"}, 2));
        quizData.get("Math").add(new Question("What is 9 × 3?", new String[]{"24", "26", "27", "30"}, 2));

        quizData.put("Reading", new ArrayList<>());
        quizData.get("Reading").add(new Question("Who wrote '1984'?", new String[]{"George Orwell", "J.K. Rowling", "Mark Twain", "Jane Austen"}, 0));
        quizData.get("Reading").add(new Question("What is the main idea of a story called?", new String[]{"Plot", "Theme", "Conflict", "Setting"}, 1));
        quizData.get("Reading").add(new Question("What do we call words that sound alike but have different meanings?", new String[]{"Homophones", "Synonyms", "Antonyms", "Adjectives"}, 0));
        quizData.get("Reading").add(new Question("What is a noun?", new String[]{"A person, place, or thing", "An action word", "A describing word", "A feeling"}, 0));
        quizData.get("Reading").add(new Question("What is an autobiography?", new String[]{"A book about someone's life", "A book about science", "A fictional story", "A book about animals"}, 0));

        quizData.put("Science", new ArrayList<>());
        quizData.get("Science").add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "NaCl"}, 0));
        quizData.get("Science").add(new Question("What planet is closest to the Sun?", new String[]{"Venus", "Earth", "Mars", "Mercury"}, 3));
        quizData.get("Science").add(new Question("What gas do plants need to perform photosynthesis?", new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"}, 2));
        quizData.get("Science").add(new Question("What is the powerhouse of the cell?", new String[]{"Nucleus", "Ribosome", "Mitochondria", "Cell membrane"}, 2));
        quizData.get("Science").add(new Question("What force keeps us on the ground?", new String[]{"Magnetism", "Friction", "Gravity", "Inertia"}, 2));

        quizData.put("Government", new ArrayList<>());
        quizData.get("Government").add(new Question("What is the supreme law of the United States?", new String[]{"The Bill of Rights", "The Constitution", "The Declaration of Independence", "The Articles of Confederation"}, 1));
        quizData.get("Government").add(new Question("Who is the Commander in Chief of the military?", new String[]{"The President", "The Vice President", "The Speaker of the House", "The Chief Justice"}, 0));
        quizData.get("Government").add(new Question("How many U.S. Senators are there?", new String[]{"50", "100", "150", "200"}, 1));
        quizData.get("Government").add(new Question("What are the first ten amendments to the Constitution called?", new String[]{"The Bill of Rights", "The Articles of Confederation", "The Federalist Papers", "The Amendments"}, 0));
        quizData.get("Government").add(new Question("Who was the first President of the United States?", new String[]{"Abraham Lincoln", "Thomas Jefferson", "George Washington", "John Adams"}, 2));
    }

    /**
     * Prompts the user to select a quiz subject.
     */
    private static void selectSubject() {
        selection();
        robot.showNotification("Select a Subject", new ArrayList<>(quizData.keySet()), new Robot.OnSdkCallback() {
            @Override
            public void onResult(String selectedSubject) {
                if (quizData.containsKey(selectedSubject)) {
                    currentQuiz = quizData.get(selectedSubject);
                    currentQuestionIndex = 0;
                    score = 0;
                    startQuiz();
                } else {
                    invalidSelection();
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
            robot.speak("Question: " + q.question);
            enableSpeechRecognition();
        } else {
            displayFinalScore();
        }
    }

    private static void checkAnswer(String selectedAnswer) {
        Question q = currentQuiz.get(currentQuestionIndex);
        if (selectedAnswer.equals(q.options[q.correctAnswerIndex])) {
            correctAnswer();
            robot.showToast("✅ Correct!");
            score++;
        } else {
            incorrectAnswer();
            robot.showToast("❌ Incorrect. Correct answer: " + q.options[q.correctAnswerIndex]);
        }
        currentQuestionIndex++;
        displayQuestion();
    }

    private static void displayFinalScore() {
        finalScore();
        robot.showToast("🏆 Final Score: " + score + "/" + currentQuiz.size());
        selectSubject();
    }

    private static void enableSpeechRecognition() {
        if (robot != null) {
            answerAfterBeep();
            robot.startVoiceRecognition(spokenAnswer -> processSpokenAnswer(spokenAnswer));
        }
    }

    private static void processSpokenAnswer(String spokenAnswer) {
        Question q = currentQuiz.get(currentQuestionIndex);
        for (String option : q.options) {
            if (spokenAnswer.equalsIgnoreCase(option)) {
                checkAnswer(option);
                return;
            }
        }
        unableToProcess();
        enableSpeechRecognition();
    }

    private static void speak(String message) {
        if (speechManager != null && speechManager.getTtsEnable()) {
            speechManager.startSpeaking(message);
        } else if (robot != null) {
            robot.speak(message);
        }
    }

    private static void correctAnswer() {
        speak("Correct! Good job.");
    }

    private static void incorrectAnswer() {
        speak("Incorrect. Try again.");
    }

    private static void finalScore() {
        speak("Quiz finished! Your final score is " + score + " out of " + currentQuiz.size());
    }

    private static void selection() {
        speak("Please select a subject: Math, Reading, Science, or Government.");
    }

    private static void invalidSelection() {
        speak("Invalid selection. Please try again.");
    }

    private static void answerAfterBeep() {
        speak("Please say your answer after the beep.");
    }

    private static void unableToProcess() {
        speak("I didn't understand. Please try again.");
    }
}