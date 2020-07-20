package Bank.Model;

import java.io.Serializable;

public class Receipt {
    private Token token;
    private String type;
    private String receiptType;
    private double money;
    private String sourceID;
    private String destinationID;
    private String description;

    public Receipt(Token token, String type, String receiptType, double money, String sourceID, String destinationID, String description) {
        this.token = token;
        this.type = type;
        this.receiptType = receiptType;
        this.money = money;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.description = description;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

