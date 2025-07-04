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
    public ArrayList<Shippable> getShippables() throws ServiceException {
        return shippables;
    }
}
