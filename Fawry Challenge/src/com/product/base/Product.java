package com.product.base;

public abstract class Product
{

    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) throws ProductException {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ProductException {

        //length of name is assumed here
        DataChecker.check(name, 1, 30);

        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws ProductException {

        //assuming that we are not selling very expensive products
        DataChecker.check(price, 0.1, 1e8);

        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws ProductException {

        //assuming that we have limited storage area
        DataChecker.check(quantity, 0, 1000);

        this.quantity = quantity;
    }
}
