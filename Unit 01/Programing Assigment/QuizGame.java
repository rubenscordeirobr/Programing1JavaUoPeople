/*
 * Brazil Quiz Game
 * This program is a quiz game that asks the user questions about Brazil.
 */
public class QuizGame {

    // Data Initialization
    // Questions
    public static String[] questions = {
            "In which year did Brazil gain independence?",
            "Who was the first President of Brazil?",
            "What is the capital of Brazil?",
            "In which year did Brazil host the FIFA World Cup for the first time?",
            "How many times has Brazil won the FIFA World Cup?",
    };

    // Answers
    public static String[][] options = {
            { "1822", "1824", "1826", "1828" },
            { "Getúlio Vargas", "Deodoro da Fonseca", "Juscelino Kubitschek", "Tancredo Neves" },
            { "Rio de Janeiro", "São Paulo", "Salvador", "Brasília" },
            { "1950", "1954", "1958", "1962" },
            { "3", "4", "5", "6" },
    };

    // Correct Answers
    public static int[] correctIndexAnswers = { 0, 1, 3, 0, 2 };

    /*
     * This mis the main method that starts the quiz game that is executed when the
     * program is run.
     */
    public static void main(String[] args) {
        // Introduction
        System.out.println("Welcome to the Brazil Quiz Game!");

        // Call the quiz method to start the game
        initializeQuiz();
    }

    /*
     * This method will initialize the quiz game and ask the user questions.
     */
    public static void initializeQuiz() {

        // Initialize the score
        int score = 0;

        // Loop through the questions
        for (int i = 0; i < questions.length; i++) {
            // Print the question
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);

            // Print the options
            for (int j = 0; j < options[i].length; j++) {

                char letter = getLetter(j);
                System.out.println(letter + ". " + options[i][j]);
            }

            // Get the user's answer
            int userAnswerIndex = getUserAnswerIndex();

            // Check if the answer is correct
            if (userAnswerIndex == correctIndexAnswers[i]) {
                // Print the success message
                printSuccessMessage("Correct Answer! You got 1 point.");
                // Increment the score
                score++;
            } else {
                // Print the failure message
                printFailureMessage("Wrong Answer! The correct answer is: " + options[i][correctIndexAnswers[i]]);
            }
        }

        // Calculate the percentage
        int percentage = (score * 100) / questions.length;

        // Print the final score
        System.out.println("\nYour final score is: " + score + "/" + questions.length + " (" + (int) percentage + "%)");
        System.out.println("Thank you for playing the Brazil Quiz Game!");
    }

    /*
     * This method gets the letter based oB the index.
     * @param index The index of the letter
     * @return The letter
     */
    public static char getLetter(int index) {
        switch (index) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            default:
                throw new IllegalArgumentException("Invalid index");
        }
    }

    /*
     * This method gets the user's answer index.
     * @return The index of the user's answer
     */
    public static int getUserAnswerIndex() {

        // Get the user's answer
        System.out.print("Enter your answer: (A, B, C, D), or (Q) to quit: ");
        String userAnswer = System.console().readLine();

        // Check if the user wants to quit
        if (userAnswer.equalsIgnoreCase("Q")) {
            System.out.println("You have quit the game.");
            System.out.println("Thank you for playing the Brazil Quiz Game!");
            System.exit(0);
        }

        // convert the user's answer to uppercase
        if (!isValidAnswer(userAnswer)) {
            // Print the failure message
            printFailureMessage("Invalid answer. Please enter a valid answer.");
            return getUserAnswerIndex();
        }

        // Convert the letter to an index
        int userAnswerIndex = getIndex(userAnswer);

        // Check if the index is valid
        if (userAnswerIndex == -1) {
            System.out.println("Invalid answer. Please enter a valid answer.");
            return getUserAnswerIndex();
        }

        return userAnswerIndex;
    }

    /*
     * This method check if the user's answer is valid.
     * @param userAnswer The user's answer
     */
    public static boolean isValidAnswer(String userAnswer) {

        if (userAnswer.length() != 1) {
            return false;
        }
        return userAnswer.equalsIgnoreCase("A") ||
                userAnswer.equalsIgnoreCase("B") ||
                userAnswer.equalsIgnoreCase("C") ||
                userAnswer.equalsIgnoreCase("D");
    }

    /*
     * This method gets the index of the letter.
     * @param letter The letter to get the index
     * @return The index of the letter
     */
    public static int getIndex(String letter) {
        switch (letter) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                return -1;
        }
    }

    /*
     * This method prints the success message to the console.
     * @param message The message to be printed in green color
     */
    public static void printSuccessMessage(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    /*
     * This method prints the failure message to the console.
     * @param message The message to be printed in red color
     */
    public static void printFailureMessage(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }
}
