package Controller;

import Models.Log.SellLog;
import Models.Off;
import Models.OffStatus;
import Models.Product;
import Models.User.Manager;
import Models.User.Request.AddOffRequest;
import Models.User.Request.AddProductRequest;
import Models.User.Request.EditOffRequest;
import Models.User.Request.EditProductRequest;
import Models.User.Seller;
import View.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    //parameter ha ro mishe bishtear kard
    public static String viewOff(long offId) {
        Seller seller = (Seller) Controller.currentUser;
        if (seller.getOffById(offId) == null) {
            return "off not exist";
        } else {
            String info = "";
            info += seller.getOffById(offId).getOffId() + " ";
            info += seller.getOffById(offId).getStartDate() + " ";
            info += seller.getOffById(offId).getEndDate() + " ";
            info += seller.getOffById(offId).getOffAmount();
            return info;
        }
    }

    //kamel nist
    public static String editOff(String field, String newContent, long offId) {
        Seller seller = (Seller) Controller.currentUser;
        if (seller.getOffById(offId) == null) {
            return "off not exist";
        } else if (field.toLowerCase().equals("enddate")) {
            Manager.addRequest(new EditOffRequest("endDate", newContent, seller.getOffById(offId)));
            return "request sent";
        } else if (field.toLowerCase().equals("amount")) {
            if (!newContent.matches("\\d+")) {
                return "invalid new content";
            }
            Manager.addRequest(new EditOffRequest("amount", newContent, seller.getOffById(offId)));
            return "request sent";
        }
        return "invalid field";
    }

    //kamel nist
    public static String addOff(ArrayList<String> info) throws ParseException {
        Seller seller = (Seller) Controller.currentUser;
        ArrayList<Product> products = new ArrayList<>();
        for (String Id : info.get(0).split("\\s")) {
            if (!Id.matches("\\d+")) {
                return "invalid Id";
            }
            if (seller.getProductById(Long.parseLong(Id)) == null) {
                return "product not exist";
            }
            products.add(DataBase.getProductById(Long.parseLong(Id)));
        }
        if (!info.get(1).matches("date structure")) {
            return "invalid end date";
        }
        if (!info.get(2).matches("\\d+")) {
            return "invalid percentage";
        }
        if (Integer.parseInt(info.get(2)) >= 100) {
            return "invalid percentage";
        }
        Manager.addRequest(new AddOffRequest(new Off(products, OffStatus.inProgressToBuild, new Date(), new SimpleDateFormat("date structure").parse(info.get(1)), Integer.parseInt(info.get(2))), (Seller) Controller.currentUser));
        return "request sent";
    }

    public static String viewCompanyInfo() {
        Seller seller = (Seller) Controller.currentUser;
        return seller.getCompanyName();
    }

    public static ArrayList<String> viewSellerProducts() {
        Seller seller = (Seller) Controller.currentUser;
        ArrayList<String> products = new ArrayList<>();
        for (Product product : seller.getProductsForSale()) {
            String info = product.getName() + " " + product.getProductId();
            info += " " + product.getBrand() + " " + product.getPrice();
            info += " " + product.getAverageScore() + " " + product.getExplanation();
            products.add(info);
        }
        return products;
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
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            String buyers = "";
            for (SellLog sellLog : ((Seller) Controller.currentUser).getSellHistory()) {
                if (sellLog.getSoldProduct().contains(DataBase.getProductById(productId))) {
                    buyers += sellLog.getBuyerName() + ", ";
                }
            }
            return buyers;
        }
    }

    public static String editProduct(String field, String newContent, long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else if (field.toLowerCase().equals("name")) {
            Manager.addRequest(new EditProductRequest("name", newContent, DataBase.getProductById(productId)));
        } else if (field.toLowerCase().equals("brand")) {
            Manager.addRequest(new EditProductRequest("brand", newContent, DataBase.getProductById(productId)));
        } else if (field.toLowerCase().equals("price")) {
            if (!newContent.matches("(\\d+)(\\.?)(\\d*)")) {
                return "invalid new content";
            }
            Manager.addRequest(new EditProductRequest("price", newContent, DataBase.getProductById(productId)));
        } else if (field.toLowerCase().equals("explanation")) {
            Manager.addRequest(new EditProductRequest("explanation", newContent, DataBase.getProductById(productId)));
        }
        return "request sent";
    }

    //halate vojod dashtan product dar nazar gerefte nashode
    public static String addProduct(ArrayList<String> productInfo) {
        if (!productInfo.get(2).matches("(\\d+)(\\.?)(\\d*)")) {
            return "invalid price";
        } else if (DataBase.getCategoryByName(productInfo.get(4)) == null) {
            return "invalid category";
        } else if (!productInfo.get(5).matches("\\d+")) {
            return "invalid number of items";
        } else {
            Manager.addRequest(new AddProductRequest(new Product(productInfo.get(0)
                    , productInfo.get(1)
                    , Double.parseDouble(productInfo.get(2))
                    , productInfo.get(3)
                    , DataBase.getCategoryByName(productInfo.get(4))
                    , (Seller) Controller.currentUser
                    , Integer.parseInt(productInfo.get(5)))));
            return "request sent";
        }
    }

    //check parameters
    public static ArrayList<String> showOffs() {
        Seller seller = (Seller) Controller.currentUser;
        ArrayList<String> offs = new ArrayList<>();
        for (Off off : seller.getOffs()) {
            String info = off.getOffId() + " " + off.getOffAmount();
            info += " " + off.getStartDate() + " " + off.getEndDate();
            offs.add(info);
        }
        return offs;
    }

    public static String viewProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            String info = DataBase.getProductById(productId).getName();
            info += " " + DataBase.getProductById(productId).getProductId();
            info += " " + DataBase.getProductById(productId).getBrand();
            info += " " + DataBase.getProductById(productId).getExplanation();
            return info;
        }
    }
}
