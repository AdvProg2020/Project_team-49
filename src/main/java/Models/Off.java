package Models;

import Models.OffStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Off  implements Serializable {
    private long offId;
    private ArrayList<Product> products;
    private OffStatus offStatus;
    private Date startDate;
    private Date endDate;
    private int offAmount;

    public Off(ArrayList<Product> products, OffStatus offStatus, Date startDate, Date endDate, int offAmount) {
        this.offId = 0;
        this.products = products;
        this.offStatus = offStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.offAmount = offAmount;
    }
//    all setter and getter

    public void setOffId(long offId) {
        this.offId = offId;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setOffAmount(int offAmount) {
        this.offAmount = offAmount;
    }

    public void setOffStatus(OffStatus offStatus) {
        this.offStatus = offStatus;
    }

    public long getOffId() {
        return offId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public OffStatus getOffStatus() {
        return offStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getOffAmount() {
        return offAmount;
    }

//    add new product to Off
    public void addProduct(Product product){
        this.products.add(product);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String products = "";
        boolean test = false;
        for (Product product : this.products) {
            if (test) {
                products += "@#";
            }
            products += product.getProductId();
            test = true;
        }
        return offId + "!@"
                + offStatus + "!@"
                + formatter.format(startDate) + "!@"
                + formatter.format(endDate) + "!@"
                + offAmount + "!@"
                + products;
    }
}
