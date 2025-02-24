// Quiz data for each subject
const quizData = {
    Math: [
        { question: "What is 5 + 3?", options: ["6", "7", "8", "9"], correctAnswerIndex: 2 },
        { question: "What is 12 ÷ 4?", options: ["2", "3", "4", "5"], correctAnswerIndex: 1 },
        { question: "What is the square root of 16?", options: ["2", "4", "6", "8"], correctAnswerIndex: 1 },
        { question: "Solve: 9 x 9", options: ["72", "81", "90", "99"], correctAnswerIndex: 1 },
        { question: "What is 15% of 200?", options: ["20", "25", "30", "35"], correctAnswerIndex: 2 }
    ],
    Reading: [
        { question: "Who wrote '1984'?", options: ["George Orwell", "J.K. Rowling", "Mark Twain", "Jane Austen"], correctAnswerIndex: 0 },
        { question: "What is the main idea of a story called?", options: ["Plot", "Theme", "Conflict", "Setting"], correctAnswerIndex: 1 }
    ],
    Science: [
        { question: "What is the chemical symbol for water?", options: ["H2O", "O2", "CO2", "NaCl"], correctAnswerIndex: 0 },
        { question: "What planet is closest to the Sun?", options: ["Venus", "Earth", "Mars", "Mercury"], correctAnswerIndex: 3 }
    ],
    Government: [
        { question: "What is the supreme law of the United States?", options: ["The Bill of Rights", "The Constitution", "The Declaration of Independence", "The Articles of Confederation"], correctAnswerIndex: 1 },
        { question: "Who is the Commander in Chief of the military?", options: ["The President", "The Vice President", "The Speaker of the House", "The Chief Justice"], correctAnswerIndex: 0 }
    ]
};

let currentQuiz;
let currentQuestionIndex = 0;
let score = 0;

function navigateToWelcome() {
    window.location.href = '/';
}

// Start the quiz for the selected subject
function startQuiz(subject) {
    document.getElementById('subject-selection').style.display = 'none';
    document.getElementById('quiz-container').style.display = 'block';
    currentQuiz = quizData[subject];
    score = 0;
    currentQuestionIndex = 0;
    displayQuestion();
}

// Display the current question and options
function displayQuestion() {
    const question = currentQuiz[currentQuestionIndex];
    const questionTitle = document.getElementById('question-title');
    const optionsContainer = document.getElementById('options-container');

    questionTitle.innerHTML = question.question;
    optionsContainer.innerHTML = '';

    optionsContainer.classList.add('options-grid');
    question.options.forEach((option, index) => {
        const optionButton = document.createElement('button');
        optionButton.classList.add('btn', 'btn-primary', 'option-button');
        optionButton.textContent = option;
        optionButton.onclick = () => checkAnswer(index);
        optionsContainer.appendChild(optionButton);
    });

    document.getElementById('score-display').textContent = `Score: ${score}`;
}

// Check if the selected answer is correct
function checkAnswer(selectedIndex) {
    const question = currentQuiz[currentQuestionIndex];
    if (selectedIndex === question.correctAnswerIndex) {
        score++;
        alert("Correct! Good job!");
    } else {
        alert(`Incorrect. The correct answer was: ${question.options[question.correctAnswerIndex]}`);
    }

    currentQuestionIndex++;
    if (currentQuestionIndex < currentQuiz.length) {
        displayQuestion();
    } else {
        finishQuiz();
    }
}

// Show final score and return to subjects
function finishQuiz() {
    alert(`Quiz finished! Your final score is ${score} out of ${currentQuiz.length}`);
    goBackToSubjects();
}

// Return to subject selection
function goBackToSubjects() {
    document.getElementById('quiz-container').style.display = 'none';
    document.getElementById('subject-selection').style.display = 'block';
}

function navigateToSubject(subject) {
    createConfetti();
    const card = event.currentTarget;
    card.style.transform = 'scale(1.2) rotate(360deg)';
    card.style.transition = 'transform 0.5s ease-in-out';

    setTimeout(() => {
        alert('Coming soon: ' + subject.charAt(0).toUpperCase() + subject.slice(1) + ' learning module!');
        card.style.transform = 'scale(1) rotate(0deg)';
    }, 500);
}

function navigateToHome() {
    // Add a fun animation before navigation
    const button = document.querySelector('.start-button');
    if (button) {
        button.style.transform = 'scale(1.2) rotate(360deg)';
        button.style.transition = 'transform 0.5s ease-in-out';
    }

    // Add some confetti effect
    createConfetti();

    // Navigate to home page after animations
    setTimeout(() => {
        window.location.href = 'home.html';
    }, 1000);
}

function createConfetti() {
    const confettiContainer = document.createElement('div');
    confettiContainer.style.position = 'fixed';
    confettiContainer.style.top = '0';
    confettiContainer.style.left = '0';
    confettiContainer.style.width = '100%';
    confettiContainer.style.height = '100%';
    confettiContainer.style.pointerEvents = 'none';
    confettiContainer.style.zIndex = '1000';
    document.body.appendChild(confettiContainer);

    const emojis = ['🌟', '✨', '🎈', '🎉', '📚', '✏️', '🎨', '🔢'];

    for (let i = 0; i < 50; i++) {
        const confetti = document.createElement('div');
        confetti.textContent = emojis[Math.floor(Math.random() * emojis.length)];
        confetti.style.position = 'absolute';
        confetti.style.left = Math.random() * 100 + 'vw';
        confetti.style.fontSize = '24px';
        confetti.style.userSelect = 'none';
        confetti.style.transition = 'transform 1s ease-out, opacity 1s ease-out';

        const animation = confetti.animate([
            {
                transform: 'translateY(-20vh) rotate(0deg) scale(1)',
                opacity: 1
            },
            {
                transform: `translateY(100vh) rotate(${720 * Math.random()}deg) scale(0)`,
                opacity: 0
            }
        ], {
            duration: 1500 + Math.random() * 1000,
            easing: 'cubic-bezier(0.25, 0.46, 0.45, 0.94)'
        });

        confettiContainer.appendChild(confetti);
        animation.onfinish = () => confetti.remove();
    }

    setTimeout(() => confettiContainer.remove(), 2500);
}

// Add interactive elements
document.addEventListener('DOMContentLoaded', () => {
    const title = document.querySelector('.welcome-title');

    // Add random color changes to title on click
    if (title) {
        title.addEventListener('click', () => {
            const colors = ['#FFD862', '#FF9E9E', '#CD853F', '#2B2B2B'];
            title.style.color = colors[Math.floor(Math.random() * colors.length)];
        });
    }
});