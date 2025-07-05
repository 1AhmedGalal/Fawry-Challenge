package com.delivery.cart;

import com.product.base.Product;

public class CartItem {

    private int quantityBought;
    private Product product;
    private double totalPrice;

    CartItem(int quantityBought, Product product, double totalPrice) {
        //no need to validate here bcz I know I will get them in a valid state from Cart
        //and this class constructor is visible in this package only :)
        this.quantityBought = quantityBought;
        this.product = product;
        this.totalPrice = totalPrice;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return
                "x" + quantityBought +
                "\t" + product.getName() + '\t' +
                        totalPrice;
    }
}
