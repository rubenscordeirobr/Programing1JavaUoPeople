package studmgmtsys.utils;

/**
 * Utility class that provides common helper methods, such as safe integer parsing.
 */
public class Utils {

    /**
     * Tries to parse a string into an integer. If the string is not a valid integer, 
     * the method returns 0 instead of throwing an exception.
     * 
     * @param value the string to be parsed into an integer
     * @return the parsed integer, or 0 if the string is not a valid integer
     */
    public static int tryParseInt(String value) {
        try {
            // Attempt to parse the string as an integer
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // Return 0 if parsing fails due to a NumberFormatException
            return 0;
        }
    }
}
