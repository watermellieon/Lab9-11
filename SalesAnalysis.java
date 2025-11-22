import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SalesAnalysis class processes sales data from a file for a month (4 weeks).
 * It calculates weekly totals, average daily sales, total monthly sales,
 * average weekly sales, and identifies the weeks with the highest and lowest sales.
 * 
 * @author Ellie Russo
 * @version 1.0
 * @since 11/16/2025
 */
public class SalesAnalysis {

    // Array to store total sales for each week
    private double[] weeklyNumber;

    // Input file path containing sales data
    private String inputFile;

    // Tracks which week is being processed
    private int lineNumber = 0;

    // Number of weeks in a month (constant) 
    public static final int WEEKS_IN_MONTH = 4;

    /**
     * Constructor for SalesAnalysis.
     * Initializes the input file and weekly sales array.
     * 
     * @param inputFile The path to the sales data file
     */
    public SalesAnalysis(String inputFile) {
        this.inputFile = inputFile;
        weeklyNumber = new double[WEEKS_IN_MONTH];
    }

    /**
     * Reads the sales data file line by line.
     * Splits each line by commas and passes the array to setArrayElement().
     * Handles FileNotFoundException if the file does not exist.
     */
    public void processFile() {
        try {
            File file = new File(inputFile);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine() && lineNumber < WEEKS_IN_MONTH) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");
                setArrayElement(tokens);   // Matches assignment requirement
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
        }
    }

    /**
     * Calculates the total sales for a week from the String array of daily sales.
     * Converts each string to double and sums the values.
     * Adds the total to the weekly sales array and increments lineNumber.
     * 
     * @param inArray The array of daily sales as strings
     * @throws NumberFormatException if any string cannot be converted to a double
     */
    private void setArrayElement(String[] inArray) {
        double total = 0;

        // Enhanced for-loop required by assignment
        for (String s : inArray) {
            total += Double.parseDouble(s);
        }

        weeklyNumber[lineNumber] = total;
        lineNumber++;
    }

    /**
     * Writes the sales report to the console.
     * Includes weekly totals, average daily sales, total monthly sales,
     * average weekly sales, and weeks with the highest and lowest sales.
     */
    public void writeOutput() {
        double totalSales = 0;
        double minSales = weeklyNumber[0], maxSales = weeklyNumber[0];
        int minWeek = 1, maxWeek = 1;

        for (int i = 0; i < weeklyNumber.length; i++) {
            double weekly = weeklyNumber[i];
            totalSales += weekly;

            if (weekly < minSales) { minSales = weekly; minWeek = i + 1; }
            if (weekly > maxSales) { maxSales = weekly; maxWeek = i + 1; }

            System.out.printf("Week%d Info\n", i + 1);
            System.out.printf("Total Sales: $%,.2f\n", weekly);
            System.out.printf("Avg Daily Sales for Week: $%,.2f\n\n", weekly / 7);
        }

        System.out.printf("Total Sales for all Weeks: $%,.2f\n", totalSales);
        System.out.printf("Avg Weekly Sales: $%,.2f\n", totalSales / WEEKS_IN_MONTH);
        System.out.printf("Week%d had the highest amount of sales\n", maxWeek);
        System.out.printf("Week%d had the lowest amount of sales\n", minWeek);
    }
}