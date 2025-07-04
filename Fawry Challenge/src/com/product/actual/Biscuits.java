package com.product.actual;

import com.product.base.*;

import java.util.Calendar;
import java.util.Date;

public class Biscuits extends Product implements Shippable, Expirable {

    private Date expiringDate;
    private double weight;

    public Biscuits(String name, double price, int quantity, Date expiringDate, double weight) throws ProductException {
        super(name, price, quantity);
        setExpiringDate(expiringDate);
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws ProductException {
        DataChecker.check(weight, 0.1, 10);
        this.weight = weight;
    }


    public void setExpiringDate(Date expiringDate) throws ProductException {

        //assuming max date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        Date maxDate = calendar.getTime();
        DataChecker.check(expiringDate, maxDate);

        this.expiringDate = expiringDate;
    }

    @Override
    public Date getExpiringDate() {
        return expiringDate;
    }
}
