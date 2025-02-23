import org.junit.Before;
import org.junit.Test;

import laihung.junit.Calculator;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVCalculatorTest {

    private Calculator calc;
    private List<TestData> testData;

    @Before
    public void setUp() {
        calc = new Calculator();
        testData = readCSV();
        // Đảm bảo file CSV tồn tại và có dữ liệu, nếu không fail test
        assertFalse("No data found in CSV or file not found", testData.isEmpty());
    }

    @Test
    public void testCalculatorOperations() {
        for (TestData data : testData) {
            double result = 0;
            String operationMessage = " for " + data.a + " and " + data.b;
            
            if (Math.abs(data.result - (data.a + data.b)) < 0.0001) { // Sử dụng delta để so sánh double
                result = calc.add(data.a, data.b);
                assertEquals("Add test failed" + operationMessage,
                        data.result, result, 0.0001);
            } else if (Math.abs(data.result - (data.a - data.b)) < 0.0001) {
                result = calc.subtract(data.a, data.b);
                assertEquals("Subtract test failed" + operationMessage,
                        data.result, result, 0.0001);
            } else if (Math.abs(data.result - (data.a * data.b)) < 0.0001) {
                result = calc.multiply(data.a, data.b);
                assertEquals("Multiply test failed" + operationMessage,
                        data.result, result, 0.0001);
            } else if (Math.abs(data.result - (data.a / data.b)) < 0.0001) {
                result = calc.divide(data.a, data.b);
                assertEquals("Divide test failed" + operationMessage,
                        data.result, result, 0.0001);
            } else {
                fail("No matching operation found for result " + data.result + operationMessage);
            }
        }
    }

    private List<TestData> readCSV() {
        List<TestData> dataList = new ArrayList<>();
        String filePath = "src/main/resources/calculator_results.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Bỏ qua header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) { // Đảm bảo dòng có đủ 3 cột
                    TestData data = new TestData(
                        Double.parseDouble(values[0].trim()),
                        Double.parseDouble(values[1].trim()),
                        Double.parseDouble(values[2].trim())
                    );
                    dataList.add(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in CSV file: " + e.getMessage(), e);
        }
        return dataList;
    }

    // Class nội bộ để lưu dữ liệu từ CSV
    private static class TestData {
        double a, b, result;

        TestData(double a, double b, double result) {
            this.a = a;
            this.b = b;
            this.result = result;
        }
    }
}