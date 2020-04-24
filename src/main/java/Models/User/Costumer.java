package Models.User;

import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class Costumer extends User {
    private HashMap<Product, Integer> cart;
    private ArrayList<BuyLog> buyHistory;
    private ArrayList<DiscountCode> discountCodes;
    private double credit;

    public Costumer(String username, String firstName, String lastName, String eMail, long phoneNumber, String password) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
        this.cart = new HashMap<>();
        this.buyHistory = new ArrayList<>();
        this.discountCodes = new ArrayList<>();
        this.credit = 0;
    }

    public ArrayList<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public double getCredit() {
        return credit;
    }

    public ArrayList<BuyLog> getBuyHistory() {
        return buyHistory;
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void addProductToCart(Product product) {

    }

    public void addBuyLog(BuyLog buyLog) {

    }

    public void addDiscountCode(DiscountCode discountCode) {

    }

    @Override
    public String getType() {
        return "Consumer";
    }
}
