package utils;
/**
 * The PrintUtils class provides utility methods for printing formatted messages 
 * to the console. It includes methods for printing success, failure, and prompt
 * messages in different colors, as well as for printing multiline text.
 */
public class PrintUtils {

    /**
     * Prints a failure message in red to the console.
     * This method is typically used for error or failure messages.
     * 
     * @param message the message to print in red.
     */
    public static void printFail(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    /**
     * Prints a success message in green to the console.
     * This method is used to indicate successful operations or confirmations.
     * 
     * @param message the message to print in green.
     */
    public static void printSuccess(String message) {
        // Print message in green color to indicate success
        System.out.println("\u001B[32m" + message + "\u001B[0m");
    }

    /**
     * Prints a prompt message in yellow to the console.
     * This method is used for asking the user for input.
     * 
     * @param message the message to print in yellow.
     */
    public static void printPrompt(String message) {
        // Print message in yellow color to prompt the user
        System.out.print("\u001B[33m" + message + "\u001B[0m");
    }

    /**
     * Prints a warning message in yellow to the console.
     * 
     * @param message the message to print in yellow.
     */
    public static void printWaring(String message) {
        // Print message in yellow color to prompt the user
        System.out.println("\u001B[33m" + message + "\u001B[0m");
    }

     
}
