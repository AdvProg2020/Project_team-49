package Controller;

import Models.Comment;
import Models.Product;

import java.util.ArrayList;

import static Controller.DataBase.*;

public class ShowProductDetail {
    public static String getName(long productId){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                return product.getName();
            }
        }
        return null;
    }
    public static Double getOffPercentage(long productId){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                return product.getOffPercentage();
            }
        }
        return null;
    }
    public static String getExplanation(long productId){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                return product.getExplanation();
            }
        }
        return null;
    }
    public static Double getPrice(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()) {
                return product.getPrice(product.getDefaultSeller());
            }
        }
        return null;
    }
    public static String getCategory(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()) {
                return product.getParentCategory().getName();
            }
        }
        return null;
    }
    public static Double getAverageScore(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()) {
                return product.getAverageScore();
            }
        }
        return null;
    }
    public static ArrayList<String> getAllSeller(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()) {
                return product.getAllSellerName();
            }
        }
        return null;
    }
    public static ArrayList<Double> getAllSellerPrice(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()){
                return product.getAllSellerPrice();
            }
        }
        return null;
    }
    public static int getRemainedNumber(long productId){
        for (Product product : allProducts) {
            if (productId==product.getProductId()) {
                return product.remainingItems();
            }
        }
        return 0;
    }
    public static ArrayList<String> getTitleOfComment(long productId){
        ArrayList<String> titles=new ArrayList<String>();
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                for (Comment allComment : product.getAllComments()) {
                    titles.add(allComment.getTitle());
                }
            }
        }
        return titles;
    }
    public static ArrayList<String> getNoteOfComment(long productId){
        ArrayList<String> notes=new ArrayList<String>();
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                for (Comment allComment : product.getAllComments()) {
                    notes.add(allComment.getNote());
                }
            }
        }
        return notes;
    }
    public static ArrayList<String> getUserOfComment(long productId){
        ArrayList<String> users=new ArrayList<String>();
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                for (Comment allComment : product.getAllComments()) {
                    users.add(allComment.getUserWhoComment().getUsername());
                }
            }
        }
        return users;
    }
}
