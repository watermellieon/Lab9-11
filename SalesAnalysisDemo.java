import java.io.File;
import java.util.Scanner;

/**
 * SalesAnalysisDemo class prompts the user for a sales data file,
 * verifies the file exists, creates a SalesAnalysis object,
 * processes the file, and outputs the results.
 * 
 * @author Ellie Russo
 * @version 1.0
 * @since 11/16/2025
 */
public class SalesAnalysisDemo {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String fileName;
        File file;

        // Ask user for file until a valid one is provided
        do {
            System.out.print("Enter the path to the SalesData.txt file: ");
            fileName = keyboard.nextLine();
            file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File not found. Please try again.\n");
            }

        } while (!file.exists());

        // Create SalesAnalysis object and process file
        SalesAnalysis sa = new SalesAnalysis(fileName);
        sa.processFile();
        sa.writeOutput();

        keyboard.close();
    }
}