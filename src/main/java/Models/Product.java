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

    private enum productStatus {REVIEWFORMAKE, REVIEWFOREDIT, ACCEPTED}

    private Off off;
    private boolean doesItHaveOff;
    private double offPercentage;

    private DiscountCode discountCode;
    private boolean doesItHaveDiscount;
    private double discountPercentage;
    private int numberOfView;
    private Date addProductDate;

    public Product(String name, long productId, String brand, double price, String explanation, Category parentCategory, Seller seller, int remainingItems) {
        allSellers = new HashMap<Seller, Integer>();
        this.brand = brand;
        this.addItem(seller, remainingItems);
        this.defaultSeller = seller;
        allComments = new ArrayList<Comment>();
        this.explanation = explanation;
        addProductDate = new Date();
        this.name = name;
        this.numberOfView = 0;
        this.productId = productId;
        this.price = price;
        this.parentCategory = parentCategory;
        this.off = null;
        this.offPercentage = 0;
        this.doesItHaveOff = false;
        this.discountCode = null;
        this.discountPercentage = 0;
        this.doesItHaveDiscount = false;
        this.averageScore = 0;
        this.allScores = new ArrayList<Score>();
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

    public Date getAddProductDate() {
        return addProductDate;
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
}
