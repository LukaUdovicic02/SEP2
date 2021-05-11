package model;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;

    public Balance(){
        this.balance = new BigDecimal("0.0");
    }

    public Balance(int balance){
        this.balance = new BigDecimal(balance+".0");
    }

    public void add(double amount){
        balance = balance.add(new BigDecimal(amount));
    }

    public void withDraw(double amount){
        if (amount<0){
            throw new IllegalArgumentException("Enter a positive value");
        }
        if ((balance.doubleValue()-amount)<0){
            throw new IllegalArgumentException("Can not withdraw below zero");
        }
        balance = balance.subtract(new BigDecimal(amount));
    }

    public double getBalance(){
        return balance.doubleValue();
    }
}
