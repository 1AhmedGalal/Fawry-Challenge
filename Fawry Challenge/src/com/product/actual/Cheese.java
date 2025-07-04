package com.product.actual;

import com.product.base.*;

import java.util.Calendar;
import java.util.Date;

public class Cheese extends Product implements Shippable, Expirable {

    private double weight;
    private Date expiringDate;

    public Cheese(String name, double price, int quantity, double weight, Date expiringDate) throws ProductException {
        super(name, price, quantity);
        setWeight(weight);
        setExpiringDate(expiringDate);
    }

    public void setWeight(double weight) throws ProductException {
        DataChecker.check(weight, 0.1, 10);
        this.weight = weight;
    }

    public void setExpiringDate(Date expiringDate) throws ProductException {
        //assuming max date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date maxDate = calendar.getTime();
        DataChecker.check(expiringDate, maxDate);
        
        this.expiringDate = expiringDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public Date getExpiringDate() {
        return expiringDate;
    }
}
