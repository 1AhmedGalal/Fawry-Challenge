package com.delivery.cart;

import com.delivery.ServiceException;
import com.delivery.shop.Shop;
import com.product.base.Expirable;
import com.product.base.Product;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    private ArrayList<String> cartErrors;
    private ArrayList<CartItem> cartItems;

    public Cart() {
        this.cartErrors = new ArrayList<>();
        this.cartItems = new ArrayList<>();
    }

    private int addProductHelper(Product product, int takenQuantity) throws Exception{
        int finalTaken = takenQuantity;

        try {
            product.setQuantity(product.getQuantity() - takenQuantity);
        }
        catch (Exception e) {
            if(product.getQuantity() == 0)
                throw new ServiceException("Sorry! Out Of Stock for " + product.getName());
            else{

                finalTaken = product.getQuantity();

                try{
                    product.setQuantity(0);
                } catch (Exception e2){
                    throw new ServiceException("Unexpected Error!!!!");
                }

            }
        }


        return finalTaken;
    }

    public void add(Product product, int takenQuantity) throws ServiceException {

        try {
            int actualTakenQuantity = addProductHelper(product, takenQuantity);

            if(actualTakenQuantity < takenQuantity)
                cartErrors.add("Couldn't Take " + takenQuantity + " from " + product.getName() + " so you got only " + actualTakenQuantity);

            takenQuantity = actualTakenQuantity;

        } catch (Exception e){
            cartErrors.add(e.getMessage());
            return;
        }

        if(product instanceof Expirable){
            Date expiringDate = ((Expirable) product).getExpiringDate();
            Date now = new Date();

            if (expiringDate.before(now)) {
                cartErrors.add(product.getName() + " has already expired");
                return;
            }
        }

        if(takenQuantity == 0)
            return;

        CartItem cartItem = new CartItem(takenQuantity, product, product.getPrice() * takenQuantity);
        cartItems.add(cartItem);
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public ArrayList<String> getCartErrors() {
        return cartErrors;
    }

}
