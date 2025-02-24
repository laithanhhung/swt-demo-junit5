package laihung.junit;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String filePath = "src/main/resources/calculator_results.csv";

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append("a,b,result\n");

            // Write sample data
            writer.append("2,3,5\n")         // 2 + 3 = 5
                  .append("5,3,2\n")         // 5 - 3 = 2
                  .append("4,3,12\n")        // 4 * 3 = 12
                  .append("6,2,3.0\n");      // 6 / 2 = 3.0

            System.out.println("CSV file created at: " + filePath);
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }
}