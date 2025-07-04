package com.delivery;

import com.product.base.Product;

public class CartItem {

    private int quantityBought;
    private Product product;
    private double totalPrice;

    public CartItem(int quantityBought, Product product, double totalPrice) {
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
