package utils;

/**
 * The InputUtils class provides utility methods for handling user input from
 * the console. It includes methods to retrieve integers, doubles, and strings,
 * with error handling and validation.
 */
public class InputUtils {

    /**
     * Prompts the user for input and attempts to parse it as an integer.
     * If the input is invalid, the user is prompted to try again.
     * 
     * @param message The message to display to the user when asking for input.
     * @return The integer value input by the user.
     */
    public static int getInt(String message) {
        try {
            String input = getString(message);
            return Integer.parseInt(input);
        } catch (Exception e) {
            PrintUtils.printFail("Invalid input, please try again");
            return getInt(message);  // Recursively ask for input until valid
        }
    }

    /**
     * Prompts the user for input and attempts to parse it as a double.
     * If the input is invalid, the user is prompted to try again.
     * 
     * @param message     The message to display to the user when asking for input.
     * @param isRequired  Whether input is required (cannot be empty).
     * @return The double value input by the user.
     */
    public static double getDouble(String message, boolean isRequired) {
        try {
            String input = getString(message, isRequired);
            return Double.parseDouble(input);
        } catch (Exception e) {
            PrintUtils.printFail("Invalid input, please try again");
            return getDouble(message, isRequired);  // Recursively ask for input until valid
        }
    }

    /**
     * Prompts the user for a string input and returns it.
     * This method does not require the input to be non-empty.
     * 
     * @param message The message to display to the user when asking for input.
     * @return The string value input by the user.
     */
    public static String getString(String message) {
        return getString(message, false);
    }

    /**
     * Prompts the user for a string input and returns it.
     * If input is required and the user provides an empty input, the user is
     * prompted to try again.
     * 
     * @param message     The message to display to the user when asking for input.
     * @param isRequired  Whether input is required (cannot be empty).
     * @return The string value input by the user.
     */
    public static String getString(String message, boolean isRequired) {
        PrintUtils.printPrompt(message);
        String input = System.console().readLine();

        // Check if the user wants to quit the program
        if (input.toLowerCase().equals("q") || input.toLowerCase().equals("quit")) {
            System.out.println("Goodbye!, thank you for using Student Management System");
            System.exit(0);
        }

        // If input is required and the user provides an empty input, prompt again
        if (isRequired && input.isEmpty()) {
            PrintUtils.printFail("Input is required, please try again");
            return getString(message, isRequired);  // Recursively ask for input until valid
        }

        return input;
    }
}
