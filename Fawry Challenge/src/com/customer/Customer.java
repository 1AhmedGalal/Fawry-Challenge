package com.customer;

public class Customer {
    private double balance;

    public Customer(double balance) throws Exception {
        setBalance(balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws Exception {

        //assuming max balance is 1e6
        if(balance < 0.1 || balance > 1e6)
            throw new Exception("Invalid Range");

        this.balance = balance;
    }
}
