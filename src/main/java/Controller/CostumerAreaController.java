package Controller;


import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Log.SellLog;
import Models.Product;
import Models.Score;
import Models.User.Cart;
import Models.User.Costumer;
import Models.User.Guest;
import Models.User.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
            info += " " + product.getBrand() + " " + product.getPrice(product.getDefaultSeller());
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

    //kamel nist
    public static double getTotalPrice() {
        return 0;
    }

    //kamel nist (log ID va off handle nashode va kam shodan credit)
    public static String finishPayment(ArrayList<String> receiverInfo) {
        Costumer costumer = (Costumer) Controller.currentUser;
        Set<Seller> sellers = new HashSet<>();
        Cart cart = costumer.getCart();
        for (Product product : cart.getProducts()) {
            sellers.add(cart.getSellerByProductId(product.getProductId()));
        }
        int discount = 0;
        if (!receiverInfo.get(2).equals("no")) {
            discount = costumer.getDiscountCodeById(receiverInfo.get(2)).getDiscountPercent();
        }
        for (Seller seller : sellers) {
            double paidAmount = 0;
            ArrayList<Product> products = new ArrayList<>();
            for (Product product : cart.getProducts()) {
                if (cart.getSellerByProductId(product.getProductId()).equals(seller)) {
                    paidAmount += (product.getPrice(seller) * cart.getItemsByProductId(product.getProductId()));
                    products.add(product);
                }
            }
            costumer.addBuyLog(new BuyLog(paidAmount * (1 - discount), discount, products, seller.getUsername(), 1, new Date()));
            seller.addSellLog(new SellLog(paidAmount, 1, products, costumer.getUsername(), 1, new Date()));
        }
        //costumer.addCredit((getTotalPrice() * -1));
        costumer.setCart(new Cart());
        return "payment done";
    }

    public static boolean hasDiscountCode(String discountCode) {

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

    public static String increaseCredit(long credit) {
        ((Costumer) Controller.currentUser).addCredit(credit);
        return "credit increased";
    }
}
