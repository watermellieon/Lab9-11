import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * SalesAnalysis class processes sales data for a month (4 weeks).
 * Calculates weekly totals, average daily sales, total monthly sales,
 * average weekly sales, and identifies the weeks with highest/lowest sales.
 * 
 * Author: Ellie Russo
 * Version: 1.0
 * Since: 11/22/2025
 */
public class SalesAnalysis {

    // Number of weeks in a month
    public static final int WEEKS_IN_MONTH = 4;

    // Fields
    private double[] weeklyNumber;
    private String inputFile;
    private int lineNumber = 0;

    /**
     * Constructor
     * @param inputFile path to the sales data file
     */
    public SalesAnalysis(String inputFile) {
        this.inputFile = inputFile;
        weeklyNumber = new double[WEEKS_IN_MONTH];
    }

    /**
     * Processes the sales data file
     * @throws IOException if file cannot be read
     */
    public void processFile() throws IOException {
        File file = new File(inputFile);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine() && lineNumber < WEEKS_IN_MONTH) {
            String line = sc.nextLine();
            String[] tokens = line.split(",");
            setArrayElement(tokens);
        }

        sc.close();
    }

    /**
     * Totals a week's sales from String array and stores in weeklyNumber
     * @param inArray array of daily sales as strings
     */
    private void setArrayElement(String[] inArray) {
        double total = 0;

        // Enhanced for-loop
        for (String s : inArray) {
            total += Double.parseDouble(s);
        }

        weeklyNumber[lineNumber] = total;
        lineNumber++;
    }

    /**
     * Outputs sales report to console
     */
    public void writeOutput() {
        double totalSales = 0;
        double minSales = weeklyNumber[0];
        double maxSales = weeklyNumber[0];
        int minWeek = 1;
        int maxWeek = 1;

        for (int i = 0; i < weeklyNumber.length; i++) {
            double weekly = weeklyNumber[i];
            totalSales += weekly;

            if (weekly < minSales) {
                minSales = weekly;
                minWeek = i + 1;
            }
            if (weekly > maxSales) {
                maxSales = weekly;
                maxWeek = i + 1;
            }

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