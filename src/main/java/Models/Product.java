package Models;

import Models.User.Seller;

import java.util.ArrayList;
import java.util.Date;

public class Product {
    private long productId;
    private String name;
    private ArrayList<String> allBrands;
    private double price;
    private ArrayList<Seller> allSellers;
    private int remainedNumber;
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

    public Product(String name, long productId, String brand, double price, String explanation, Category parentCategory, Seller seller) {
        allBrands = new ArrayList<>();
        allSellers = new ArrayList<>();
        allBrands.add(brand);
        this.addSeller(seller);
        allComments = new ArrayList<>();
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
        this.allScores = new ArrayList<>();
    }

    public void setRemainedNumber(int remainedNumber) {
        this.remainedNumber = remainedNumber;
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

    public int getRemainedNumber() {
        return remainedNumber;
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

    public void addSeller(Seller seller) {
        if (!allSellers.contains(seller)) {
            allSellers.add(seller);
        }
    }

    public void addBrand(String brand) {
        allBrands.add(brand);
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

    public ArrayList<String> getAllSellersName() {
        ArrayList<String>allSellerName=new ArrayList<String>();
        for (Seller seller : allSellers) {
            allSellerName.add(seller.getUsername());
        }
        return allSellerName;
    }
}
