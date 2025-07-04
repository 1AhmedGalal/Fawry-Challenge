package com.delivery;

import com.product.actual.*;
import com.product.base.Product;
import com.product.base.ProductException;

import java.util.ArrayList;
import java.util.Calendar;

class Shop {

    // I Am Assuming This Class Will be Used by Customers Only
    // So, I didn't Add a add product that should be used by vendors

    private ArrayList<Product> shopProducts;

    public Shop()
    {
        shopProducts = new ArrayList<>();
    }

    public void loadAvailableProducts() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Now Adding Hardcoded Values Simulating A Shop Available Products");
        System.out.println("Normally This Would Be A Good Place For A DB Query but I am Keeping it Simple :)");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.AUGUST, 15);

        Calendar calendar2 = Calendar.getInstance();
        calendar.set(2023, Calendar.AUGUST, 15);

        try {
            calendar.set(2026, Calendar.JANUARY, 1);
            shopProducts.add(new Biscuits("Molto", 10.0, 50, calendar2.getTime()));
            calendar.set(2026, Calendar.JUNE, 1);
            shopProducts.add(new Biscuits("Ulker", 8.0, 30, calendar.getTime()));

            calendar.set(2025, Calendar.SEPTEMBER, 15);
            shopProducts.add(new Cheese("Domty", 25.0, 20, 0.5, calendar.getTime()));
            calendar.set(2025, Calendar.DECEMBER, 10);
            shopProducts.add(new Cheese("Maraa'y", 30.0, 15, 0.4, calendar.getTime()));

            shopProducts.add(new Mobile("Samsung", 18000.0, 10, 0.3));
            shopProducts.add(new Mobile("Xiaomi", 12000.0, 1, 0.32));

            calendar.set(2026, Calendar.JANUARY, 15);
            shopProducts.add(new MobileScratchCard("Vodafone", 10.0, 100, calendar.getTime()));
            calendar.set(2026, Calendar.FEBRUARY, 10);
            shopProducts.add(new MobileScratchCard("Etisalat", 25.0, 80, calendar.getTime()));

            shopProducts.add(new TV("LG", 12500.0, 5, 7.5));
            shopProducts.add(new TV("Tornado", 8000.0, 6, 6.2));

        } catch (ProductException e) {
            System.err.println("Error adding products: " + e.getMessage());
        }
    }

    public void displayShopProducts() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Available Products in the Shop :");

        int cnt = 1;
        for (Product product : shopProducts) {

            System.out.println("\t [Product" + cnt +"]");
            System.out.println("Name     : " + product.getName());
            System.out.println("Price    : " + product.getPrice());
            System.out.println("Quantity : " + product.getQuantity());
            System.out.println("\n\n\n");

            cnt++;
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public Product takeProduct(int index, int takenQuantity) throws ServiceException {

        if(!(index >= 1 && index <= shopProducts.size()))
            throw new ServiceException("Please Choose A Valid Product");

        Product product = shopProducts.get(index - 1);

        try {
            product.setQuantity(product.getQuantity() - takenQuantity);
        }
        catch (Exception e) {
            throw new ServiceException("Sorry! No Enough Stock");
        }

        return product;
    }

}
