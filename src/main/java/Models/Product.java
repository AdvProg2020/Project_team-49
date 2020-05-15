package Models;

import Models.User.Seller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Product implements Serializable {
    private long productId;
    private String name;
    private String brand;
    private ArrayList<Double> price;
    private ArrayList<Seller> allSellers;
    private ArrayList<Integer> availableItems;
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

    public Product(String name, String brand, double price, String explanation, Category parentCategory, Seller seller, int remainingItems) {
        this.allSellers = new ArrayList<>();
        this.allSellers.add(seller);
        this.availableItems = new ArrayList<>();
        this.availableItems.add(remainingItems);
        this.brand = brand;
        this.addItem(seller, remainingItems);
        this.defaultSeller = seller;
        this.allComments = new ArrayList<>();
        this.explanation = explanation;
        this.status = ProductStatus.REVIEWFORMAKE;
        this.parentCategory = parentCategory;
        this.name = name;
        this.price = new ArrayList<>();
        this.price.add(price);
        this.productDate = new Date();
    }

    public Product(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public Product(double averageScore) {
        this.averageScore = averageScore;
    }

    public int remainingProductForSeller(Seller seller) {
        for (int i = 0; i < allSellers.size(); i++) {
            if (allSellers.get(i).equals(seller)) {
                return availableItems.get(i);
            }
        }
        return 0;
    }

    public ArrayList<Seller> getAllSellers() {
        return allSellers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setPrice(double price, Seller seller) {
        for (int i = 0; i < allSellers.size(); i++) {
            if (allSellers.get(i).equals(seller)) {
                this.price.set(i, price);
            }
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int remainingItems() {
        int sum = 0;
        for (Integer availableItem : availableItems) {
            sum += availableItem;
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

    public double getPrice(Seller seller) {
        for (int i = 0; i < allSellers.size(); i++) {
            if (allSellers.get(i).equals(seller)) {
                return price.get(i);
            }
        }
        return 0;
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

    public void resetAverageScore() {
        double average = 0;
        for (Score allScore : allScores) {
            average += allScore.getScore();
        }
        this.averageScore = (average / allScores.size());
    }

    public ArrayList<Comment> getAllComments() {
        return allComments;
    }

    public void addScore(Score score) {
        this.allScores.add(score);
    }

    public void addItem(Seller seller, int remainingItems) {
        if (!allSellers.contains(seller)) {
            allSellers.add(seller);
            availableItems.add(remainingItems);
        } else {
            availableItems.set(allSellers.indexOf(seller), availableItems.get(allSellers.indexOf(seller)) + remainingItems);
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
        for (Seller seller : allSellers) {
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
        return availableItems.get(allSellers.indexOf(seller));
    }

    public ArrayList<String> getAllSellerName() {
        ArrayList<String> allSellerName = new ArrayList<String>();
        for (Seller seller : allSellers) {
            allSellerName.add(seller.getCompanyName());
        }
        return allSellerName;
    }
}

enum ProductStatus implements Serializable {
    REVIEWFORMAKE,
    REVIEWFOREDIT,
    ACCEPTED
}
