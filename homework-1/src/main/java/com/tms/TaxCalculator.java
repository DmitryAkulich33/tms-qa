package com.tms;

public class TaxCalculator {
    public static final String ERROR_MESSAGE = "Salary less than 0";

    public double calculateTax(double salary) {
        validateSalary(salary);

        if (salary < 10000) {
            return salary * 0.13;
        } else if (salary >= 10000 && salary < 50000) {
            return salary * 0.20;
        } else {
            return salary * 0.30;
        }
    }

    private void validateSalary(double salary) {
        if (salary < 0) {
            throw new InvalidSalaryException(ERROR_MESSAGE);
        }
    }
}
