package com.product.actual;

import com.product.base.DataChecker;
import com.product.base.Product;
import com.product.base.ProductException;
import com.product.base.Shippable;

public class TV extends Product implements Shippable {

    private double weight;

    public TV(String name, double price, int quantity, double weight) throws ProductException {
        super(name, price, quantity);
        setWeight(weight);
    }

    public void setWeight(double weight) throws ProductException {

        //assuming ranges
        DataChecker.check(weight, 0.1, 5000);

        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
