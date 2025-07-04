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

    public void deliverAll() throws ServiceException {

        if(shippables.isEmpty()){
            throw new ServiceException("No Product To Ship!");
        }

        for (Shippable shippable : shippables){
            System.out.println("Shipping: " + shippable.getName());
        }

        shippables.clear();
        System.out.println("All Products Are Delivered");
    }
}
