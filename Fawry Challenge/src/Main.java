import com.customer.Customer;
import com.delivery.Cart;
import com.delivery.CartItem;
import com.delivery.ShippingService;
import com.delivery.Shop;
import com.product.base.Shippable;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static ArrayList<CartItem> cartItems = null;
    static double subtotal = 0;
    static double shippingFees = 0;
    static final double SHIPPING_PRICE = 100.64;

    public static void checkout(Cart cart, Customer customer) {

        System.out.println("Errors Made By User While Adding To Cart: ");

        var errors = cart.getCartErrors();
        cartItems = cart.getCartItems();

        if(cartItems.isEmpty())
            errors.add("Cart IS EMPTY!!");

        int idx = 0;
        while(idx < cartItems.size()){
            var item = cartItems.get(idx++);

            try{
                customer.setBalance(customer.getBalance() - item.getTotalPrice());
                if(item.getProduct() instanceof Shippable)
                    customer.setBalance(customer.getBalance() - ((Shippable) item.getProduct()).getWeight() * SHIPPING_PRICE);
            }
            catch (Exception e){

                errors.add("Customer Can't Buy " + item.getQuantityBought() + " Items from " + item.getProduct().getName()
                            + " Because Customer Has " + customer.getBalance() +" Now, And That Item Needs More");

                cartItems.remove(item);
                --idx;

            }
        }

        if(!errors.isEmpty())
            for(String error : errors)
                System.out.println(error);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        ArrayList<Shippable> shippables = new ArrayList<>();
        for(var item : cartItems)
        {
            subtotal += item.getTotalPrice();

            if(item.getProduct() instanceof Shippable){
                shippingFees += ((Shippable) item.getProduct()).getWeight() * SHIPPING_PRICE * item.getQuantityBought();
                shippables.add((Shippable) item.getProduct());
            }
        }

        ShippingService shippingService = new ShippingService(shippables);
        shippingService.performShipping();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Receipt: ");

        for(var item : cartItems) {
            System.out.println(item.toString());
        }
        System.out.println();

        double totalPaid = subtotal + shippingFees;
        System.out.println("subtotal = " + subtotal);
        System.out.println("shipping Fees = " + shippingFees);
        System.out.println("Total Paid = " + totalPaid);
        System.out.println("Remaining Customer Balance = " + customer.getBalance());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {

        try {

            Customer customer = new Customer(213999);

            Shop shop = new Shop();
            shop.loadAvailableProducts();
            shop.displayShopProducts();

            Cart cart = new Cart(shop);
            cart.add(1, 1);
            cart.add(2, 50);
            cart.add(2, 1);
            cart.add(9, 1);
            cart.add(10, 1);
            cart.add(7, 2);
            cart.add(8, 3);

            checkout(cart, customer);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
