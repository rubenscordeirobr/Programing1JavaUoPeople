import java.util.ArrayList;
import java.util.Random;

public class StockPriceAnalysis {

    /**
     * Method to calculate the average stock price using a traditional `for` loop.
     * 
     * @param stockPrices An array of floats representing the stock prices over a
     *                    period.
     * @return The average stock price as a float.
     */
    public static float calculateAveragePrice(float[] stockPrices) {

        // validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Check if the array is empty
        if (stockPrices.length == 0) {
            return 0;
        }

        float sum = 0;
        // Using a traditional `for` loop to sum up the stock prices
        for (int i = 0; i < stockPrices.length; i++) {
            sum += stockPrices[i];
        }

        // Calculate and return the average price by dividing the sum by the number of
        // elements
        return sum / stockPrices.length;
    }

    /**
     * Method to find the maximum stock price using a `while` loop.
     * 
     * @param stockPrices An array of floats representing the stock prices.
     * @return The highest stock price as a float.
     */
    public static float findMaximumPrice(float[] stockPrices) {

        // validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Check if the array is empty
        if (stockPrices.length == 0) {
            return 0;
        }

        int index = 0;
        float maxPrice = stockPrices[0]; // Initialize maxPrice with the first element

        // Using a `while` loop to find the maximum value
        while (index < stockPrices.length) {
            if (stockPrices[index] > maxPrice) {
                maxPrice = stockPrices[index];
            }
            index++;
        }

        // Return the maximum price found
        return maxPrice;
    }

    /**
     * Method to count the occurrences of a specific stock price using a `for-each`
     * loop.
     * 
     * @param stockPrices An array of floats representing the stock prices.
     * @param targetPrice The price whose occurrences need to be counted.
     * @return The number of occurrences of the target price as an integer.
     */
    public static int countOccurrences(float[] stockPrices, float targetPrice) {

        // validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // here we don't need to check if the array is empty

        int count = 0;

        // Using a `for-each` loop to count occurrences of the target price
        for (float price : stockPrices) {
            if (price == targetPrice) {
                count++;
            }
        }

        // Return the count of occurrences
        return count;
    }

    /**
     * Method to compute the cumulative sum of stock prices using a `do-while` loop.
     * 
     * @param stockPrices An ArrayList of Float representing the stock prices.
     * @return An ArrayList of Float containing the cumulative sums.
     */
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> stockPrices) {

        // validate if the array is null
        if (stockPrices == null) {
            throw new IllegalArgumentException("stockPrices cannot be null");
        }

        // Check if the array is empty
        if (stockPrices.isEmpty()) {
            return new ArrayList<>();
        }

        // Create a new ArrayList to store the cumulative sums
        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float sum = 0;
        int index = 0;

        // Using a `do-while` loop to calculate the cumulative sum
        do {
            sum += stockPrices.get(index);
            cumulativeSum.add(sum);
            index++;
        } while (index < stockPrices.size());

        // Return the list containing the cumulative sums
        return cumulativeSum;
    }

    public static void main(String[] args) {

        // Target stock price to count occurrences
        float targetPrice = 125.0f;

        // Array of stock prices for a 10-day period
        float[] stockPricesArray = generateRandomStockPrices(10, 100.0f, 150.0f, targetPrice);

        // Converting the array to an ArrayList for the cumulative sum calculation
        ArrayList<Float> stockPricesList = new ArrayList<>();
        for (float price : stockPricesArray) {
            stockPricesList.add(price);
        }

        // 1. Calculate and display the average stock price using a traditional `for`
        // loop
        System.out.println(String.format( "Average Stock Price: %.2f", calculateAveragePrice(stockPricesArray)));

        // 2. Find and display the maximum stock price using a `while` loop
        System.out.println(String.format("Maximum Stock Price: %.2f", findMaximumPrice(stockPricesArray)));

        // 3. Count occurrences of a specific stock price using a `for-each` loop
        System.out.println(String.format("Occurrences of %.2f: %d", targetPrice, countOccurrences(stockPricesArray, targetPrice)));

        // 4. Compute and display the cumulative sum of stock prices using a `do-while`
        // loop
        System.out.println("Cumulative Sum of Stock Prices: ");
        for (float price : computeCumulativeSum(stockPricesList)) {
            System.out.print(String.format("%.2f, ", price));
        }
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
    public static float[] generateRandomStockPrices(int size, float min, float max, float targetPrice) {

        // Validate the input parameters
        Random random = new Random();

        // Create an array to store the randomly generated stock prices
        float[] stockPrices = new float[size];

        // Generate random stock prices within the specified range
        for (int i = 0; i < size; i++) {
            float randomPrice = min + random.nextFloat() * (max - min);
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
