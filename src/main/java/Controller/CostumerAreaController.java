package Controller;


import java.util.ArrayList;

public class CostumerAreaController {

    public static String viewCostumerDiscountCodes() {
        return "";
    }

    public static String showProducts() {
        return "";
    }

    public static void removeFromCart(long ProductId) {
    }

    public static double getTotalPrice() {
        return 0;
    }

    public static void finishPayment(ArrayList<String> receiverInfo) {

    }

    public static void rateProduct(long productId, int score) {
        DataBase.getProductById(productId).setAverageScore(DataBase.getProductById(productId).getAverageScore());
    }
}
