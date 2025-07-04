import com.customer.Customer;
import com.delivery.Cart;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void checkout(Cart cart, Customer customer){

        System.out.println("Trying To Take An Expired Product");
        try{
            cart.add(1, 1);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Trying To Take Some");
        cart.add(9, 10);
    }

    public static void main(String[] args) {

        try {

            Customer customer = new Customer(1e5);
            Cart cart = new Cart();
            checkout(cart, customer);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}