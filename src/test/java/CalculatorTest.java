import org.junit.Test;

import laihung.junit.Calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    public void testDividePositiveNumbers() {
        double result = calculator.divide(10.0, 2.0);
        assertEquals(5.0, result, 0.0001); // So sánh với delta 0.0001 cho độ chính xác double
    }

    @Test
    public void testDivideNegativeNumbers() {
        double result = calculator.divide(-10.0, -2.0);
        assertEquals(5.0, result, 0.0001); // (-10) / (-2) = 5
    }

    @Test
    public void testDividePositiveByNegative() {
        double result = calculator.divide(10.0, -2.0);
        assertEquals(-5.0, result, 0.0001); // 10 / (-2) = -5
    }

    @Test
    public void testDivideNegativeByPositive() {
        double result = calculator.divide(-10.0, 2.0);
        assertEquals(-5.0, result, 0.0001); // (-10) / 2 = -5
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZero() {
        calculator.divide(5.0, 0.0); // Nên ném IllegalArgumentException
    }

    @Test
    public void testDivideByOne() {
        double result = calculator.divide(10.0, 1.0);
        assertEquals(10.0, result, 0.0001); // 10 / 1 = 10
    }

    @Test
    public void testDivideLargeNumbers() {
        double result = calculator.divide(Double.MAX_VALUE, 2.0);
        assertEquals(Double.MAX_VALUE / 2.0, result, 0.0001); // Kiểm tra số rất lớn
    }

    @Test
    public void testDivideSmallNumbers() {
        double result = calculator.divide(0.0001, 2.0);
        assertEquals(0.00005, result, 0.0001); // Kiểm tra số rất nhỏ
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideByZeroPositiveNumerator() {
        calculator.divide(5.0, 0.0); // Nên ném IllegalArgumentException
    }

     @Test
    public void test_decimal_calculations_precision() {
        double result = calculator.add(0.1, 0.2);
        assertEquals(0.3, result, 0.0001);
    }

    @Test
    public void test_max_integer_values() {
        int maxValue = Integer.MAX_VALUE; // Giá trị lớn nhất của kiểu int: 2,147,483,647
        double result = calculator.add(maxValue, 0); // Gọi phương thức add với 2 tham số
        assertEquals(maxValue, result, 0.0001); // So sánh với độ chính xác thập phân
    }
    
    @Test
    public void test_negative_numbers() {
        double result = calculator.add(-5, -10);
        assertEquals(-15.0, result, 0.0001); // So sánh với delta cho double
    }
}