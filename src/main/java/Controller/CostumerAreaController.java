package Controller;


import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Log.SellLog;
import Models.Off;
import Models.Product;
import Models.Score;
import Models.User.Cart;
import Models.User.Costumer;
import Models.User.Guest;
import Models.User.Seller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CostumerAreaController {

    public static ArrayList<String> viewCostumerDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (DiscountCode discountCode : ((Costumer) Controller.currentUser).getDiscountCodes()) {
            String info = discountCode.getDiscountId();
            info += "," + formatter.format(discountCode.getStartDate()) + "," + formatter.format(discountCode.getEndDate());
            info += "," + discountCode.getDiscountPercent() + "," + discountCode.getMaximumDiscountAmount();
            info += "," + discountCode.getDiscountCount() + "," + discountCode.getUsageCount();
            discountCodes.add(info);
        }
        return discountCodes;
    }

    public static ArrayList<String> showProducts() {
        ArrayList<String> products = new ArrayList<>();
        Cart cart = ((Costumer) Controller.currentUser).getCart();
        for (int i = 0; i < cart.getProducts().size(); i++) {
            String info = cart.getProducts().get(i).getName() + "," + cart.getProducts().get(i).getProductId();
            info += "," + cart.getProducts().get(i).getBrand() + "," + cart.getProducts().get(i).getPrice(cart.getProducts().get(i).getDefaultSeller());
            info += "," + cart.getItemsByProductId(cart.getProducts().get(i).getProductId(), cart.getSellers().get(i)) + "," + cart.getSellers().get(i);
            products.add(info);
        }
        return products;
    }

    public static String IncreaseOrDecreaseProduct(long productId, int count, String sellerUsername) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        }
        if (DataBase.getUserByUsername(sellerUsername) == null) {
            return "seller not exist in users";
        }
        if (!DataBase.getUserByUsername(sellerUsername).getType().equals("Seller")) {
            return "seller not exist in sellers";
        }
        Cart cart = ((Costumer) Controller.currentUser).getCart();
        if (!cart.getProducts().contains(DataBase.getProductById(productId))) {
            return "product is not in the cart";
        }
        Seller seller = (Seller) DataBase.getUserByUsername(sellerUsername);
        for (int i = 0; i < cart.getProducts().size(); i++) {
            if (cart.getSellers().get(i).equals(seller)) {
                Controller.addToCart(DataBase.getProductById(productId), seller, count);
                if (count == 1) {
                    return "increased";
                } else {
                    return "decreased";
                }
            }
        }
        return "product is not in the cart for this seller";
    }

    public static double getTotalPrice() {
        Cart cart = ((Costumer) Controller.currentUser).getCart();
        double totalPrice = 0;
        for (int i = 0; i < cart.getProducts().size(); i++) {
            totalPrice += (cart.getProducts().get(i).getPrice(cart.getSellers().get(i)) * cart.getItemsByProductId(cart.getProducts().get(i).getProductId(), cart.getSellers().get(i)));
        }
        return totalPrice;
    }

    public static String finishPayment(ArrayList<String> receiverInfo) {
        Costumer costumer = (Costumer) Controller.currentUser;
        Cart cart = costumer.getCart();
        Set<Seller> sellers = new HashSet<>(cart.getSellers());
        int discount = 0;
        if (!receiverInfo.get(2).equals("no")) {
            discount = costumer.getDiscountCodeById(receiverInfo.get(2)).getDiscountPercent();
        }
        if ((getTotalPrice() * (1 - discount / 100)) > costumer.getCredit()) {
            return "your credit is not enough";
        }
        for (Seller seller : sellers) {
            double paidAmount = 0;
            double reducedAmountForOff = 0;
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < cart.getSellers().size(); i++) {
                if (cart.getSellers().get(i).equals(seller)) {
                    paidAmount += (cart.getProducts().get(i).getPrice(seller) * cart.getItemsByProductId(cart.getProducts().get(i).getProductId(), seller));
                    products.add(cart.getProducts().get(i));
                }
            }
            for (Product product : products) {
                int offPercent = 0;
                for (Off off : seller.getOffs()) {
                    if (off.getProducts().contains(product)) {
                        offPercent = off.getOffAmount();
                    }
                }
                reducedAmountForOff += (product.getPrice(seller) / (1 - offPercent / 100)) - (product.getPrice(seller));
            }
            costumer.addBuyLog(new BuyLog(paidAmount * (1 - discount / 100), discount, products
                    , seller.getUsername(), DataBase.getLogId(), new Date()));
            seller.addSellLog(new SellLog(paidAmount, reducedAmountForOff, products, costumer.getUsername(), DataBase.getLogId(), new Date()));
            seller.addCredit(getTotalPrice());
        }
        String answer = "payment done";
        if (getTotalPrice() > 1000000) {
            answer += "\ngift discount code activated for your account";
            ArrayList<Costumer> allowed = new ArrayList<>();
            allowed.add(costumer);
            costumer.addDiscountCode(new DiscountCode("monthly gift"
                    , new Date(), new Date(2592000000L + new Date().getTime()), 10
                    , 50000, 2, allowed));
        }
        costumer.addCredit((getTotalPrice() * -1) * (1 - (discount / 100)));
        costumer.setCart(new Cart());
        return answer;
    }

    public static boolean hasDiscountCode(String discountCode) {
        if (((Costumer) Controller.currentUser).getDiscountCodeById(discountCode) == null) {
            return false;
        }
        return true;
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        BuyLog order = ((Costumer) Controller.currentUser).getBuyLogById(Id);
        String info = "" + order.getLogId() + "," + formatter.format(order.getLogDate());
        info += "," + order.getSellerName() + "," + order.getPaidAmount();
        info += "," + order.getReceiveStatus() + ",";
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

    public static String increaseCredit(Double credit) {
        ((Costumer) Controller.currentUser).addCredit(credit);
        return "credit increased";
    }
}
