import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * SalesAnalysisDemo prompts the user for a sales data file,
 * checks if it exists, and runs the SalesAnalysis program.
 * 
 * Author: Ellie Russo
 * Version: 1.0
 * Since: 11/22/2025
 */
public class SalesAnalysisDemo {

    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String fileName;
        File file;

        // Prompt user until a valid file is entered
        do {
            System.out.print("Enter the path to the SalesData.txt file: ");
            fileName = keyboard.nextLine();
            file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File not found. Please try again.\n");
            }

        } while (!file.exists());

        // Create SalesAnalysis object and run methods
        SalesAnalysis sa = new SalesAnalysis(fileName);
        sa.processFile();
        sa.writeOutput();

        keyboard.close();
    }
}