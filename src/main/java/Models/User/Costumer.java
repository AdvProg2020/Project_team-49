package Models.User;

import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Costumer extends User implements Serializable {
    private Cart cart;
    private ArrayList<BuyLog> buyHistory;
    private ArrayList<DiscountCode> discountCodes;
    private double credit;

    public Costumer(String username, String firstName, String lastName, String eMail, long phoneNumber, String password) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
        this.cart = new Cart();
        this.buyHistory = new ArrayList<BuyLog>();
        this.discountCodes = new ArrayList<DiscountCode>();
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

    public Cart getCart() {
        return cart;
    }

    public BuyLog getBuyLogById(Long Id) {
        for (BuyLog buyLog : buyHistory) {
            if (buyLog.getLogId() == Id) {
                return buyLog;
            }
        }
        return null;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void addProductToCart(Product product,Seller seller,int count) {

    }

    public void addBuyLog(BuyLog buyLog) {

    }

    public void addDiscountCode(DiscountCode discountCode) {

    }

    @Override
    public String getType() {
        return "Costumer";
    }
}
