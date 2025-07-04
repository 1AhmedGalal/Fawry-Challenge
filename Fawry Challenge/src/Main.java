import com.customer.Customer;
import com.delivery.Cart;
import com.delivery.ShippingService;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void checkout(Cart cart){

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Errors Made By User While Adding To Cart: ");
        cart.printErrors();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        ShippingService shippingService = cart.getShippingService();
        shippingService.deliverAll();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        ArrayList<String> receipt = cart.getReceiptItems();
        for(String item : receipt)
            System.out.println(item);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        double subtotal = cart.getSubtotal();
        double shippingFees = cart.getShippingFees();
        double totalPaid = subtotal + shippingFees;
        System.out.println("subtotal = " + subtotal);
        System.out.println("shipping Fees = " + shippingFees);
        System.out.println("Total Paid = " + totalPaid);
        System.out.println("Remaining Customer Balance = " + cart.getCustomer().getBalance());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


    }

    public static void main(String[] args) {

        try {

            Customer customer = new Customer(222615.25);
            Cart cart = new Cart(customer);

            cart.add(1, 1);
            cart.add(2, 50);
            cart.add(2, 1);
            cart.add(9, 1);
            cart.add(10, 1);
            cart.add(7, 2);
            
            checkout(cart);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}