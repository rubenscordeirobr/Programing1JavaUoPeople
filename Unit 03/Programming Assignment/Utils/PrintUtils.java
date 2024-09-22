package Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The PrintUtils class provides utility methods for printing messages with different formats and colors.
 * This class supports printing error messages in red, success messages in green, and questions in yellow.
 */
public class PrintUtils {

    /**
     * Prints a failure message in red text.
     *
     * @param message the message to print.
     */
    public static void printFail(String message) {
        System.out.println("\u001B[31m" + message + "\u001B[0m"); // Red color
    }

    /**
     * Prints a success message in green text.
     *
     * @param message the message to print.
     */
    public static void printSuccess(String message) {
        System.out.println("\u001B[32m" + message + "\u001B[0m"); // Green color
    }

    /**
     * Prints a question message in yellow text.
     *
     * @param message the message to print.
     */
    public static void printQuestion(String message) {
        System.out.println("\u001B[33m" + message + "\u001B[0m"); // Yellow color
    }

    /**
     * Prints the given text line by line.
     *
     * @param text the text to print. Each line in the text will be printed on a new line.
     */
    public static void print(String text) {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(text.split("\n")));
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
