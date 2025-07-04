package com.delivery;

import com.product.base.Expirable;
import com.product.base.Product;
import com.product.base.Shippable;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    private ArrayList<Product> boughtProducts;
    private Shop shop;
    private double subtotal;
    private double shippingFees;

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public Cart() {

        this.shop = new Shop();
        this.shop.loadAvailableProducts();
        this.shop.displayShopProducts();

        this.boughtProducts = new ArrayList<>();
        this.subtotal = 0.0;
        this.shippingFees = 0.0;
    }

    public void add(int index, int takenQuantity) throws ServiceException {

        //checks if it is enough or not
        Product product = shop.takeProduct(index, takenQuantity);

        if(product instanceof Expirable){
            Date expiringDate = ((Expirable) product).getExpiringDate();
            Date now = new Date();

            if (expiringDate.before(now)) {
                throw new ServiceException(product.getName() + " has already expired on ");
            }
        }

        double tmpSubTotal = product.getPrice() * takenQuantity;
        double tmpFee = 0;

        //this is assumed fee of 100 EGP on each unit of weight
        if(product instanceof Shippable)
            tmpFee += ((Shippable) product).getWeight() * 100.0;

        subtotal += tmpSubTotal;
        shippingFees += tmpFee;
    }

    public ShippingService shipProducts(){

        ArrayList<Shippable> shippables = new ArrayList<>();

        for(Product product : boughtProducts){
            if(product instanceof Shippable){
                shippables.add((Shippable) product);
            }
        }

        return new ShippingService(shippables);

    }
}
