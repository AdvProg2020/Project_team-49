package Models.User;

import Models.Log.SellLog;
import Models.Off;
import Models.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller extends User implements Serializable {
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

    public Off getOffById(long Id) {return null;}

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

    public Product getProductById(Long Id) {
        for (Product product : productsForSale) {
            if (product.getProductId() == Id) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {

    }

    public void removeProduct(long productId) {
        for (Product product : productsForSale) {
            if (product.getProductId() == productId) {
                productsForSale.remove(product);
                break;
            }
        }
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
