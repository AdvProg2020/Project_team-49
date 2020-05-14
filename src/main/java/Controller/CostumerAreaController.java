package Controller;


import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Product;
import Models.Score;
import Models.User.Cart;
import Models.User.Costumer;
import Models.User.Guest;

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

    public static ArrayList<String> showProducts() {
        ArrayList<String> products = new ArrayList<>();
        Cart cart = ((Costumer) Controller.currentUser).getCart();
        for (Product product : cart.getProducts()) {
            String info = product.getName() + " " + product.getProductId();
            info += " " + product.getBrand() + " " + product.getPrice();
            info += " " + cart.getItemsByProductId(product.getProductId()) + " " + cart.getSellerByProductId(product.getProductId());
            products.add(info);
        }
        return products;
    }

    public static String IncreaseOrDecreaseProduct(long productId, int count) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        }
        Cart cart;
        if (Controller.currentUser.getType().equals("guest")) {
            cart = ((Guest) Controller.currentUser).getCart();
        } else {
            cart = ((Costumer) Controller.currentUser).getCart();
        }
        if (!cart.getProducts().contains(DataBase.getProductById(productId))) {
            return "product is not in the cart";
        }
        Controller.addToCart(DataBase.getProductById(productId), cart.getSellerByProductId(productId), count);
        if (count == 1) {
            return "increased";
        } else {
            return "decreased";
        }
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

    public static ArrayList<String> getOrders() {
        ArrayList<String> orders = new ArrayList<>();
        for (BuyLog buyLog : ((Costumer) Controller.currentUser).getBuyHistory()) {
            orders.add(getOrderInfoById(buyLog.getLogId()));
        }
        return orders;
    }

    public static String getOrderInfoById(long Id) {
        BuyLog order = ((Costumer) Controller.currentUser).getBuyLogById(Id);
        String info = "" + order.getLogId();
        info += " " + order.getSellerName() + " " + order.getPaidAmount();
        info += " " + order.getReceiveStatus() + " ";
        for (Product product : order.getBoughtProduct()) {
            info += product.getName() + "_" + product.getProductId() + "/";
        }
        return info;
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
