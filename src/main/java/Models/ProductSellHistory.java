package Models;

public class ProductSellHistory {
    private String productName;
    private String productId;
    private String seller;
    private String buyer;
    private int count;

    public ProductSellHistory(String productName, String productId, String seller, String buyer, int count) {
        this.productName = productName;
        this.productId = productId;
        this.seller = seller;
        this.buyer = buyer;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public String getSeller() {
        return seller;
    }

    public String getBuyer() {
        return buyer;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return productName + "!@" +
                productId + "!@" +
                seller + "!@" +
                buyer + "!@" +
                count;
    }
}
