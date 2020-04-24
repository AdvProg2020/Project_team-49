package Controller;

import Models.User.Seller;

import java.util.ArrayList;

public class SellerAreaController {

    public static void removeProduct(long productId) {
        Seller seller = (Seller) Controller.currentUser;
        seller.removeProduct(productId);
        DataBase.removeProduct(productId);
    }

    public static ArrayList<String> showCategories() {
        DataBase.allCategories.get(0);
        return null;
    }

    public static String viewOff(long offId) {
        Seller seller = (Seller) Controller.currentUser;
        seller.getOffById(1);
        return "";
    }

    public static void editOff(String field, String newContent, long offId) {
        Seller seller = (Seller) Controller.currentUser;
        seller.getOffById(1);
    }

    public static void addOff(String[] info) {
        Seller seller = (Seller) Controller.currentUser;
        seller.addOff(null);
    }

    public static String viewCompanyInfo() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getCompanyName();
        return "";
    }

    public static ArrayList<String> viewSellerProducts() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getProductsForSale();
        return null;
    }

    public static String viewSalesHistory() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getSellHistory();
        return "";
    }

    public static String viewProductBuyers(long productId) {
        DataBase.getProductById(productId);
        return "";
    }

    public static void editProduct(String field, String newContent, long productId) {
        DataBase.getProductById(productId);
    }

    public static void addProduct(String[] info) {

    }

    public static ArrayList<String> showOffs() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getOffs();
        return null;
    }
}
