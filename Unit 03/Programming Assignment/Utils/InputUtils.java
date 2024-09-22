package Utils;

/**
 * The InputUtils class provides utility methods for handling user input.
 * It includes methods for retrieving integer and string input from the user,
 * with basic error handling and validation.
 */
public class InputUtils {

    /**
     * Prompts the user with a message and retrieves an integer input.
     * If the input is invalid, the method will prompt the user again until a valid integer is entered.
     *
     * @param message the message to display to the user.
     * @return the integer input from the user.
     */
    public static int getInt(String message) {
        try {
            String input = getString(message);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            PrintUtils.printFail("Invalid input, please enter a valid integer.");
            return getInt(message); // Retry until valid input is provided.
        }
    }

    /**
     * Prompts the user with a message and retrieves a string input.
     *
     * @param message the message to display to the user.
     * @return the string input from the user.
     */
    public static String getString(String message) {
        return getString(message, false);
    }

    /**
     * Prompts the user with a message and retrieves a string input, with an option to require the input.
     * If the input is required and the user provides an empty input, they will be prompted again.
     * Users can also quit the application by typing 'q' or 'quit'.
     *
     * @param message    the message to display to the user.
     * @param isRequired if true, the input is required and the user will be re-prompted on empty input.
     * @return the string input from the user.
     */
    public static String getString(String message, boolean isRequired) {
        PrintUtils.printQuestion(message);
        String input = System.console().readLine();

        // Handle quitting the system.
        if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
            System.out.println("Goodbye! Thank you for using the Student Management System.");
            System.exit(0);
        }

        // Handle required input.
        if (isRequired && input.isEmpty()) {
            PrintUtils.printFail("Input is required, please try again.");
            return getString(message, isRequired); // Retry until valid input is provided.
        }

        return input;
    }
}
