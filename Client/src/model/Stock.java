package model;

import java.io.Serializable;

/**
 * Stock class represents stock
 */


public class Stock implements Serializable {
    private String username;
    private String symbol;
    private int amount;
    private int price;

    /**
     * Constructor initialising the instance variables
     * @param symbol symbol of the company
     * @param username username of the user
     */

    public Stock(String symbol,String username) {
        this.symbol = symbol;
        this.username = username;
        this.amount = 0;
        this.price = 0;
    }

    /**
     * Constructor initialising the instance variables
     * @param symbol symbol of the company
     * @param username username of the user
     * @param amount amount of the stock
     */

    public Stock(String symbol,String username,int amount) {
        this.symbol = symbol;
        this.username = username;
        this.amount = amount;
        this.price = 0;
    }
    /**
     * setting price of stock
     */

    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * getting price of stock
     * @return getPrice
     */
    public int getPrice() {
        return price;
    }

    /**
     * getting the amount of stock
     * @return amount
     */

    public int getAmount() {
        return amount;
    }

    /**
     * sets the amount of stock
     * @param amount amount that is being set
     */

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * gets username
     * @return username
     */

    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     * @param username username that is being set
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets the symbol
     * @return symbol
     */

    public String getSymbol() {
        return symbol;
    }

    /**
     * sets the symbol
     * @param symbol symbol that is being set
     */

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * toString version of the stock
     * @return stock
     */

    @Override
    public String toString() {
        return "Stock{" +
                "username='" + username + '\'' +
                ", symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }
}