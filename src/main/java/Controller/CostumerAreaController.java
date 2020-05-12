package Controller;


import Models.DiscountCode;
import Models.Score;
import Models.User.Costumer;

import java.util.ArrayList;

public class CostumerAreaController {

    public static ArrayList<String> viewCostumerDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        for (DiscountCode discountCode : ((Costumer) Controller.currentUser).getDiscountCodes()) {
            String info = discountCode.getDiscountId();
            info += " " + discountCode.getStartDate() + " " + discountCode.getEndDate();
            info += " " + discountCode.getDiscountPercent() + " " + discountCode.getMaximumDiscountAmount();
            info += " " + discountCode.getDiscountCount() + " " + discountCode.getUsageCount();
            discountCodes.add(info);
        }
        return discountCodes;
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

    public static boolean hasOrderWithId(Long Id) {
        if (((Costumer) Controller.currentUser).getBuyLogById(Id) == null) {
            return false;
        }
        return true;
    }

    public static String rateProduct(long productId, int score, long logId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        }
        if (!((Costumer) Controller.currentUser).getBuyLogById(logId).getBoughtProduct().contains(DataBase.getProductById(productId))) {
            return "product not bought";
        }
        if ((score > 5) || (score <= 0)) {
            return "invalid score";
        }
        DataBase.getProductById(productId).addScore(new Score(Controller.currentUser, score, DataBase.getProductById(productId)));
        DataBase.getProductById(productId).resetAverageScore();
        return "product rated";
    }
}
