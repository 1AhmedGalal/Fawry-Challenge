package com.delivery;

import com.customer.Customer;
import com.product.base.Expirable;
import com.product.base.Product;
import com.product.base.Shippable;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    private ArrayList<String> errors;

    private ArrayList<Product> boughtProducts;
    private ArrayList<String> receiptItems;

    private Shop shop;
    private double subtotal;
    private double shippingFees;
    private Customer customer;

    public double getSubtotal() {
        return subtotal;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public Cart(Customer costumer) {
        this.shop = new Shop();
        this.shop.loadAvailableProducts();
        this.shop.displayShopProducts();
        this.errors = new ArrayList<>();
        this.boughtProducts = new ArrayList<>();
        this.receiptItems = new ArrayList<>();
        this.subtotal = 0.0;
        this.shippingFees = 0.0;
        this.customer = costumer;
    }

    public void add(int index, int takenQuantity) throws ServiceException {

        Product product = shop.getProductDetails(index);

        try {
            int actualTakenQuantity = shop.takeProduct(index, takenQuantity);

            if(actualTakenQuantity < takenQuantity)
                errors.add("Couldn't Take " + takenQuantity + " from " + product.getName() + " so you got only " + actualTakenQuantity);

            takenQuantity = actualTakenQuantity;

        } catch (Exception e){
            errors.add(e.getMessage());
            return;
        }

        if(product instanceof Expirable){
            Date expiringDate = ((Expirable) product).getExpiringDate();
            Date now = new Date();

            if (expiringDate.before(now)) {
                errors.add(product.getName() + " has already expired");
                return;
            }
        }

        double tmpSubTotal = product.getPrice() * takenQuantity;
        double tmpFee = 0.0;

        //this is assumed fee of 100 EGP on each unit of weight
        if(product instanceof Shippable)
            tmpFee += ((Shippable) product).getWeight() * 100.0;

        try{
            customer.setBalance(customer.getBalance() - tmpSubTotal - tmpFee);
        }
        catch (Exception e){
            errors.add("Customer Doesn't Have Enough Money To Buy " + takenQuantity + " units from "+ product.getName());
            return;
        }

        subtotal += tmpSubTotal;
        shippingFees += tmpFee;

        boughtProducts.add(product);
        receiptItems.add(takenQuantity + "x\t" + product.getName() + "\t" + product.getPrice() * takenQuantity);
    }

    public ShippingService getShippingService(){

        ArrayList<Shippable> shippables = new ArrayList<>();

        for(Product product : boughtProducts){
            if(product instanceof Shippable){
                shippables.add((Shippable) product);
            }
        }

        return new ShippingService(shippables);

    }

    public ArrayList<String> getReceiptItems() {
        return receiptItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void printErrors(){
        if(errors.isEmpty())
            return;

        for(String error : errors)
            System.out.println(error);
    }
}
