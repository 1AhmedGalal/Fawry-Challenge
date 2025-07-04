package com.product.actual;

import com.product.base.*;

public class Mobile extends Product implements Shippable {

    private double weight;

    public Mobile(String name, double price, int quantity, double weight) throws ProductException {
        super(name, price, quantity);
        setWeight(weight);
    }

    public void setWeight(double weight) throws ProductException {
        DataChecker.check(weight, 0.1, 5);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
