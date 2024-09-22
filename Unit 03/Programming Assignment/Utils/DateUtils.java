package Utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * The DateUtils class provides utility methods for working with dates,
 * including parsing, formatting, and calculating age based on a given birth date.
 */
public class DateUtils {

    private static DateTimeFormatter dateFormatter;

    /**
     * Returns a shared DateTimeFormatter instance used for date parsing and formatting.
     * This formatter supports both "MM/dd/yyyy" and "M/d/yyyy" formats.
     *
     * @return the DateTimeFormatter instance.
     */
    public static DateTimeFormatter getDateFormatter() {
        if (dateFormatter == null) {
            dateFormatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    .appendOptional(DateTimeFormatter.ofPattern("M/d/yyyy"))
                    .toFormatter();
        }
        return dateFormatter;
    }

    /**
     * Parses a string representation of a date into a LocalDate object.
     * The string must be in the format defined by the DateTimeFormatter.
     *
     * @param date the date string to parse.
     * @return the parsed LocalDate object.
     */
    public static LocalDate dateParser(String date) {
        DateTimeFormatter formatter = getDateFormatter();
        return LocalDate.parse(date, formatter);
    }

    /**
     * Calculates the age based on the provided birth date.
     *
     * @param birthDate the birth date of the individual.
     * @return the calculated age in years.
     */
    public static int calculateAge(LocalDate birthDate) {
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears();
    }

    /**
     * Attempts to parse a date string. If parsing fails, the provided default date is returned.
     * This method is useful when working with user input where date formats may vary.
     *
     * @param date        the date string to parse.
     * @param defaultDate the default LocalDate to return if parsing fails.
     * @return the parsed LocalDate object or the defaultDate if parsing fails.
     */
    public static LocalDate tryDateParser(String date, LocalDate defaultDate) {
        try {
            return dateParser(date);
        } catch (Exception e) {
            PrintUtils.printFail("Invalid date format " + date + ", using default date");
            return defaultDate;
        }
    }

    /**
     * Formats a LocalDate object into a string based on the DateTimeFormatter.
     *
     * @param date the LocalDate object to format.
     * @return the formatted date string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(getDateFormatter());
    }
}
