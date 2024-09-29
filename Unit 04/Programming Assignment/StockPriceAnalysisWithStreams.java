import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Random;

public class StockPriceAnalysisWithStreams {

    /**
     * Method to calculate the average stock price using Java Streams.
     * 
     * @param stockPrices An array of doubles representing the stock prices.
     * @return The average stock price as a double.
     */
    public static double calculateAveragePriceUsingStream(double[] stockPrices) {

        // Validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Use Arrays.stream() to create a stream of stock prices, then use .average()
        // to calculate the average
        return Arrays.stream(stockPrices)
                .average()
                .orElse(0.0); // Return 0.0 if the array is empty
    }

    /**
     * Method to find the maximum stock price using Java Streams.
     * 
     * @param stockPrices An array of doubles representing the stock prices.
     * @return The highest stock price as a double.
     */
    public static double findMaximumPriceUsingStream(double[] stockPrices) {

        // Validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Use Arrays.stream() to create a stream and .max() to find the maximum price
        return Arrays.stream(stockPrices)
                .max()
                .orElse(0.0); // Return 0.0 if the array is empty
    }

    /**
     * Method to count occurrences of a specific stock price using Java Streams.
     * 
     * @param stockPrices An array of doubles representing the stock prices.
     * @param targetPrice The price to be counted.
     * @return The number of occurrences of the target price as a long.
     */
    public static long countOccurrencesUsingStream(double[] stockPrices, double targetPrice) {

        // Validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Use .filter() to create a stream of prices matching the target price and
        // .count() to count them
        return Arrays.stream(stockPrices).filter(price -> price == targetPrice).count();
    }

    /**
     * Method to compute the cumulative sum of stock prices using Java Streams.
     * 
     * @param stockPrices A List of Double representing the stock prices.
     * @return A List of Double containing the cumulative sums.
     */
    public static ArrayList<Double> computeCumulativeSumUsingStream(ArrayList<Double> stockPrices) {

        // Validate if the list is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Create a mutable array to hold the running sum, then map each price to the
        // cumulative sum
        final double[] runningSum = { 0 };
        return stockPrices.stream()
                .map(price -> runningSum[0] += price)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {

        // Target price to count occurrences
        double targetPrice = 125.0;

        // Array of stock prices for a 10-day period
        double[] stockPricesArray = generateRandomStockPrices(10, 100.0, 150.0, targetPrice);

        // Converting the array to a List for the cumulative sum calculation
        ArrayList<Double> stockPricesList = Arrays.stream(stockPricesArray)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        // 1. Calculate and display the average stock price using Streams
        System.out.println(String.format("Average Stock Price (Stream): %.2f",
                calculateAveragePriceUsingStream(stockPricesArray)));

        // 2. Find and display the maximum stock price using Streams
        System.out.println(String.format("Maximum Stock Price (Stream): %.2f",
                findMaximumPriceUsingStream(stockPricesArray)));

        // 3. Count occurrences of a specific stock price and display the result using
        System.out.println(String.format("Occurrences of %.2f (Stream): %d",
                targetPrice, countOccurrencesUsingStream(stockPricesArray, targetPrice)));

        // 4. Compute and display the cumulative sum of stock prices using Streams

        ArrayList<Double> cumulativeStockPrices = computeCumulativeSumUsingStream(stockPricesList);
        String cumulativeStockPricesFormatted = cumulativeStockPrices.stream()
                .map(price -> String.format("%.2f", price))
                .collect(Collectors.joining(", "));

        System.out.println(
                "Cumulative Sum of Stock Prices (Stream): " + cumulativeStockPricesFormatted);
    }

    /**
     * Method to generate random stock prices.
     * 
     * @param size        The number of random stock prices to generate.
     * @param min         The minimum possible stock price.
     * @param max         The maximum possible stock price.
     * @param targetPrice The price target to repeat in the array.
     * @return An array of randomly generated stock prices.
     */
    public static double[] generateRandomStockPrices(int size, double min, double max, double targetPrice) {

        // Validate the input parameters
        Random random = new Random();

        // Create an array to store the randomly generated stock prices
        double[] stockPrices = new double[size];

        // Generate random stock prices within the specified range
        for (int i = 0; i < size; i++) {
            double randomPrice = min + random.nextDouble() * (max - min);
            stockPrices[i] = Math.round(randomPrice * 100.0f) / 100.0f; // Round to 2 decimal places
        }

        // Repeat the specified price at random indices
        int randomCount = 1 + random.nextInt(3);
        for (int i = 0; i < randomCount; i++) {

            int randomIndex = random.nextInt(size);
            stockPrices[randomIndex] = targetPrice;
        }

        return stockPrices;
    }
}
