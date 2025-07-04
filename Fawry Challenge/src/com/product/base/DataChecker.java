package com.product.base;
import java.util.Date;

public final class DataChecker {

    public static void check(double number, double start, double end) throws ProductException
    {
        if(Double.isNaN(number) || number < start || number > end)
            throw new ProductException("Value must be between [" + start + '-' + end + "]");
    }

    public static void check(int number, int start, int end) throws ProductException
    {
        if(number < start || number > end)
            throw new ProductException("Value must be between [" + start + '-' + end + "]");
    }

    public static void check(String str, int minSize, int maxSize) throws ProductException
    {
        if(str == null || str.length() > maxSize || str.length() < minSize)
            throw new ProductException("String must have between [" + minSize + '-' + maxSize + "] characters");
    }

    public static void check(Date date, Date maxDate) throws ProductException {
        long now = new Date().getTime();
        if (date == null || date.getTime() > maxDate.getTime())
            throw new ProductException("Date must be between today and " + maxDate);
    }

}
