package Models;

import Models.User.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Product {
    private long productId;
    private String name;
    private String brand;
    private double price;
    private HashMap<Seller, Integer> allSellers;
    private Seller defaultSeller;
    private Category parentCategory;
    private String explanation;
    private double averageScore;
    private ArrayList<Score> allScores;
    private ArrayList<Comment> allComments;

    private ProductStatus status;

    private Off off;
    private boolean doesItHaveOff;
    private double offPercentage;

    private DiscountCode discountCode;
    private boolean doesItHaveDiscount;
    private double discountPercentage;
    private int numberOfView;
    private Date productDate;

    public Product(String name, long productId, String brand, double price, String explanation, Category parentCategory, Seller seller, int remainingItems) {
        this.allSellers = new HashMap<Seller, Integer>();
        this.brand = brand;
        this.addItem(seller, remainingItems);
        this.defaultSeller = seller;
        this.allComments = new ArrayList<>();
        this.explanation = explanation;
        this.status = ProductStatus.REVIEWFORMAKE;
    }

    public int remainingProductForSeller(Seller seller) {
        for (Seller eachSeller : allSellers.keySet()) {
            if (eachSeller.equals(seller)) {
                return allSellers.get(eachSeller);
            }
        }
        return 0;
    }

    public int remainingItems() {
        int sum = 0;
        for (Seller seller : allSellers.keySet()) {
            sum += allSellers.get(seller);
        }
        return sum;
    }

    public void setStatus(String status) {
        if (status.equals("accepted")) {
            this.status = ProductStatus.ACCEPTED;
        }
        if (status.equals("edit")) {
            this.status = ProductStatus.REVIEWFOREDIT;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void addAComment(Comment comment) {
        this.allComments.add(comment);
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public String getExplanation() {
        return explanation;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public void addScore(Score score) {
        this.allScores.add(score);
    }

    public void addItem(Seller seller, int remainingItems) {
        if (!allSellers.containsKey(seller)) {
            allSellers.put(seller, remainingItems);
        } else {
            allSellers.replace(seller, allSellers.get(seller) + remainingItems);
        }
    }

    public void setOffPercentage(double discountPercentage) {
        this.offPercentage = discountPercentage;
    }

    public void setDoesItHaveOff(boolean doesItHaveOff) {
        this.doesItHaveOff = doesItHaveOff;
    }

    public void setOff(Off off) {
        this.off = off;
    }

    public Off getOff() {
        return off;
    }

    public double getOffPercentage() {
        return offPercentage;
    }

    public boolean getDoesItHaveOff() {
        return doesItHaveOff;
    }


    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public void setDoesItHaveDiscount(boolean doesItHaveDiscount) {
        this.doesItHaveDiscount = doesItHaveDiscount;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public boolean getDoesItHaveDiscount() {
        return doesItHaveDiscount;
    }


    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public int getNumberOfView() {
        return numberOfView;
    }

    public Date getProductDate() {
        return productDate;
    }

    public Seller getSellerByUsername(String username) {
        for (Seller seller : allSellers.keySet()) {
            if (seller.getUsername().equals(username)) {
                return seller;
            }
        }
        return null;
    }

    public Seller getDefaultSeller() {
        return defaultSeller;
    }

    public int getRemainingItemsForSeller(Seller seller) {
        return allSellers.get(seller);
    }

    public ArrayList<String> getAllSellerName(){
        ArrayList<String> allSellerName=new ArrayList<String>();
        for (Seller seller : allSellers.keySet()) {
            allSellerName.add(seller.getCompanyName());
        }
        return allSellerName;
    }
}

enum ProductStatus {
    REVIEWFORMAKE,
    REVIEWFOREDIT,
    ACCEPTED
}
