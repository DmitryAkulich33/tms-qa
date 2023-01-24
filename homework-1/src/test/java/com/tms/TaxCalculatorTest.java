package com.tms;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TaxCalculatorTest {
    private TaxCalculator taxCalculator = new TaxCalculator();

    @DataProvider(name = "salaries")
    public static Object[][] salaries() {
        return new Double[][]{{0.00, 0.00}, {1000.00, 130.00}, {10000.00, 2000.00}, {100000.00, 30000.00}};
    }

    @Test(dataProvider = "salaries")
    public void test_valid_salary(double salary, double expected) {
        assertEquals(expected, taxCalculator.calculateTax(salary));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = TaxCalculator.ERROR_MESSAGE)
    public void test_invalid_salary() {
        taxCalculator.calculateTax(-100.00);
    }
}