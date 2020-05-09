package Controller;

import Models.Log.SellLog;
import Models.Product;
import Models.User.Manager;
import Models.User.Request.AddProductRequest;
import Models.User.Seller;
import View.View;

import java.util.ArrayList;

public class SellerAreaController {

    //check beshe
    public static String removeProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            Seller seller = (Seller) Controller.currentUser;
            seller.removeProduct(productId);
            DataBase.removeProduct(productId);
            return "product removed";
        }
    }

    //kamel nist
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
        return seller.getCompanyName();
    }

    public static ArrayList<String> viewSellerProducts() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getProductsForSale();
        return null;
    }

    //logs mitone kamel tar beshe
    public static ArrayList<String> viewSalesHistory() {
        Seller seller = (Seller) Controller.currentUser;
        ArrayList<SellLog> salesHistory = seller.getSellHistory();
        ArrayList<String> logs = new ArrayList<>();
        for (SellLog sellLog : salesHistory) {
            logs.add(sellLog.getLogId() + " " +
                    sellLog.getLogDate() + " " +
                    sellLog.getBuyerName() + " " +
                    sellLog.getReceivedAmount() + " " +
                    sellLog.getReducedAmountForOff());
        }
        return logs;
    }

    public static String viewProductBuyers(long productId) {
        DataBase.getProductById(productId);
        return "";
    }

    public static void editProduct(String field, String newContent, long productId) {
        DataBase.getProductById(productId);
    }

    //halate vojod dashtan product dar nazar gerefte nashode
    public static void addProduct(ArrayList<String> productInfo) {
        if (!productInfo.get(2).matches("(\\d+)(.?)(\\d*)")) {
            View.printString("invalid price");
        } else if (DataBase.getCategoryByName(productInfo.get(4)) == null) {
            View.printString("invalid category");
        } else if (!productInfo.get(5).matches("\\d+")) {
            View.printString("invalid number of items");
        } else {
            Manager.addRequest(new AddProductRequest(new Product(productInfo.get(0)
                    , productInfo.get(1)
                    , Double.parseDouble(productInfo.get(2))
                    , productInfo.get(3)
                    , DataBase.getCategoryByName(productInfo.get(4))
                    , (Seller) Controller.currentUser
                    , Integer.parseInt(productInfo.get(5)))));
            View.printString("request sent");
        }
    }

    public static ArrayList<String> showOffs() {
        Seller seller = (Seller) Controller.currentUser;
        seller.getOffs();
        return null;
    }
}
