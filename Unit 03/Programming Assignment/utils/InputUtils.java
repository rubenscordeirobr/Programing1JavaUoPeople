package utils;

public class InputUtils {

    public static int getInt(String message) {

        try {
            String input = getString(message);
            return Integer.parseInt(input);

        } catch (Exception e) {
            PrintUtils.printFail("Invalid input, please try again");
            return getInt(message);
        }
    }

    public static double getDouble(String message, boolean isRequired) {

        try {
            String input = getString(message, isRequired);
            return Double.parseDouble(input);

        } catch (Exception e) {
            PrintUtils.printFail("Invalid input, please try again");
            return getDouble(message, isRequired);
        }
    }

    public static String getString(String message) {
        return getString(message, false);
    }

    public static String getString(String message, boolean isRequired) {

        PrintUtils.printPrompt(message);
        String input = System.console().readLine();

        if (input.toLowerCase().equals("q") || input.toLowerCase().equals("quit")) {
            System.out.println("Goodbye!, thank you for using Student Management System");
            System.exit(0);
        }

        if (isRequired && input.isEmpty()) {
            PrintUtils.printFail("Input is required, please try again");
            return getString(message, isRequired);
        }

        return input;
    }

}


