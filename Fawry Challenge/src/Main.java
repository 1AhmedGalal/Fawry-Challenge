import com.customer.Customer;
import com.delivery.cart.Cart;
import com.delivery.cart.CartItem;
import com.delivery.shipping.ShippingService;
import com.delivery.shop.Shop;
import com.product.base.Product;
import com.product.base.Shippable;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //I am Assuming that shipping each gram costs this constant value :)
    static final double SHIPPING_PRICE_PER_GRAM = 100.64;

    static class PaidMoney{
        double subtotal = 0;
        double shippingFees = 0;

        public void addToSubTotal(double amount){
            subtotal += amount;
        }

        public void addSTohippingFees(double amount){
            shippingFees += amount;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public double getShippingFees() {
            return shippingFees;
        }
    }

    private static void checkUserBalance(ArrayList<String> errors, ArrayList<CartItem> cartItems, Customer customer, PaidMoney paidMoney, ArrayList<Shippable> shippables){
        if(cartItems.isEmpty())
            errors.add("Cart IS EMPTY!!");

        int idx = 0;
        while(idx < cartItems.size()){
            var item = cartItems.get(idx++);

            try{
                customer.setBalance(customer.getBalance() - item.getTotalPrice());

                if(item.getProduct() instanceof Shippable){
                    double currentShippingFee = ((Shippable) item.getProduct()).getWeight() * SHIPPING_PRICE_PER_GRAM;
                    customer.setBalance(customer.getBalance() - currentShippingFee);
                    paidMoney.addSTohippingFees(currentShippingFee);
                    shippables.add((Shippable) item.getProduct());
                }

                paidMoney.addToSubTotal(item.getTotalPrice());

            }
            catch (Exception e){

                errors.add("Customer Can't Buy " + item.getQuantityBought() + " Items from " + item.getProduct().getName()
                        + " Because Customer Has " + customer.getBalance() +" Now, And That Item Needs More");

                cartItems.remove(item);
                --idx;

            }
        }
    }

    public static void checkout(Cart cart, Customer customer) {

        //---------------------------------------------------

        //initial values
        ArrayList<CartItem> cartItems = cart.getCartItems();
        ArrayList<String> errors = cart.getCartErrors();
        PaidMoney paidMoney = new PaidMoney();
        ArrayList<Shippable> shippables = new ArrayList<>();

        //---------------------------------------------------

        System.out.println("Errors Made By User While Adding To Cart: ");

        checkUserBalance(errors, cartItems, customer, paidMoney, shippables);

        if(!errors.isEmpty()){
            for(String error : errors)
                System.out.println(error);
        }else {
            System.out.println("No Errors Found");
        }


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        ShippingService shippingService = new ShippingService(shippables);
        shippingService.performShipping();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Receipt: ");

        for(var item : cartItems) {
            System.out.println(item.toString());
        }
        System.out.println();

        double totalPaid = paidMoney.getSubtotal() + paidMoney.getShippingFees();
        System.out.println("subtotal = " + paidMoney.getSubtotal());
        System.out.println("shipping Fees = " + paidMoney.getShippingFees());
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

            Cart cart = new Cart();

            Product product = shop.getProduct(1);
            cart.add(product, 1);

            product = shop.getProduct(2);
            cart.add(product, 50);

            product = shop.getProduct(2);
            cart.add(product, 3);

            product = shop.getProduct(9);
            cart.add(product, 1);

            product = shop.getProduct(10);
            cart.add(product, 1);

            product = shop.getProduct(7);
            cart.add(product, 2);

            product = shop.getProduct(8);
            cart.add(product, 3);

            checkout(cart, customer);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
