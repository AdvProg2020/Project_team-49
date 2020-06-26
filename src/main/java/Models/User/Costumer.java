package Models.User;

import Controller.DataBase;
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


    public Costumer(String username, String firstName, String lastName, String eMail, long phoneNumber, String password, double credit) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
        this.cart = new Cart();
        this.buyHistory = new ArrayList<BuyLog>();
        this.discountCodes = new ArrayList<DiscountCode>();
        this.credit = credit;
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

    public DiscountCode getDiscountCodeById(String Id) {
        for (DiscountCode discountCode : discountCodes) {
            if (discountCode.getDiscountId().equals(Id)) {
                return discountCode;
            }
        }
        return null;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

    public void addCredit(Double credit) {
        this.credit += credit;
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
        cart.addProduct(product, seller, count);
    }

    public void addBuyLog(BuyLog buyLog) {
        this.buyHistory.add(buyLog);
    }

    public void addDiscountCode(DiscountCode discountCode) {
//        DataBase.getAllDiscountCodes().add(discountCode);
        this.discountCodes.add(discountCode);
    }

    public void removeDiscountCode(DiscountCode discountCode) {
        discountCodes.remove(discountCode);
    }

    @Override
    public String getType() {
        return "Costumer";
    }
}
