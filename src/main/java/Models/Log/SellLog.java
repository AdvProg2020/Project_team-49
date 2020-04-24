package Models.Log;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public class SellLog extends Log {
    private double receivedAmount;
    private double reducedAmountForSale;
    private ArrayList<Product> soldProduct = new ArrayList<Product>();
    private String buyerName;
    private DeliveryStatus deliveryStatus;

    public SellLog(double receivedAmount, double reducedAmountForSale, ArrayList<Product> soldProduct,
                   String buyerName,long logId, Date logDate) {
        this.receivedAmount = receivedAmount;
        this.reducedAmountForSale = reducedAmountForSale;
        this.soldProduct = soldProduct;
        this.buyerName = buyerName;
        this.deliveryStatus =DeliveryStatus.DELIVERY_IN_PROGRESS;
        this.logId = logId;
        this.logDate = logDate;
    }

    private void changeDeliveryStatus(DeliveryStatus newDeliveryStatus) {
        this.deliveryStatus = newDeliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public double getReceivedAmount() {
        return receivedAmount;
    }

    public double getReducedAmountForSale() {
        return reducedAmountForSale;
    }

    public ArrayList<Product> getSoldProduct() {
        return soldProduct;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
}
