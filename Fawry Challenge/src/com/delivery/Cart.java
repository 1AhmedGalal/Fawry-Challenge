package com.delivery;

import com.product.base.Expirable;
import com.product.base.Product;

import java.util.ArrayList;
import java.util.Date;

public class Cart {

    private ArrayList<String> cartErrors;
    private ArrayList<CartItem> cartItems;
    private Shop shop;

    public Cart(Shop shop) {
        this.shop = shop;
        this.cartErrors = new ArrayList<>();
        this.cartItems = new ArrayList<>();
    }

    public void add(int index, int takenQuantity) throws ServiceException {

        Product product = shop.getProductDetails(index);

        try {
            int actualTakenQuantity = shop.takeProduct(index, takenQuantity);

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
