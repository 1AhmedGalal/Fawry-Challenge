package com.delivery;

import com.product.base.Product;
import com.product.base.Shippable;

import java.util.ArrayList;

public class ShippingService
{
    private ArrayList<Shippable> shippables;

    public ShippingService(ArrayList<Shippable> shippables) {
        this.shippables = shippables;
    }

    // I was originally trying to print here, but I am trying to do separation of concern
    public void performShipping() {
        double totalWeight = 0;

        if(!shippables.isEmpty()){
            for (Shippable shippable : shippables){
                System.out.println("Shipping: " + shippable.getName() + " [Weight = " + shippable.getWeight() + "g]");
                totalWeight += shippable.getWeight();
                System.out.println();
            }

            System.out.println("Total Delivered Weight = " + totalWeight / 1000.0 + "kg");
            System.out.println("All Products Are Delivered :)");
        }

        System.out.println("NOTHING TO BE DELIVERED!");

    }
}
