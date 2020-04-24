package Models.User;

import Models.Log.SellLog;
import Models.Off;
import Models.Product;

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<SellLog> sellHistory;
    private ArrayList<Product> productsForSale;
    private ArrayList<Off> offs;
    private String companyName;
    private double credit;

    public Seller(String username, String firstName, String lastName, String eMail, long phoneNumber, String password, String companyName) {
        super(username, firstName, lastName, eMail, phoneNumber, password);
        this.sellHistory = new ArrayList<>();
        this.productsForSale = new ArrayList<>();
        this.offs = new ArrayList<>();
        this.companyName = companyName;
        this.credit = 0;
    }

    public Off getOffById(long ID) {return null;}

    public ArrayList<SellLog> getSellHistory() {
        return sellHistory;
    }

    public ArrayList<Product> getProductsForSale() {
        return productsForSale;
    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void addProduct(Product product) {

    }

    public void removeProduct(long productId) {

    }

    public void addOff(Off off) {

    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    @Override
    public String getType() {
        return "Seller";
    }
}
