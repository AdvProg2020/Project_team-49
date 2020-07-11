package Controller;

import Models.Log.SellLog;
import Models.Off;
import Models.OffStatus;
import Models.Product;
import Models.ProductStatus;
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
import java.util.Locale;

public class SellerAreaController {

    public static String removeProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            Seller seller = (Seller) Controller.currentUser;
            if (DataBase.getProductById(productId).getAllSellers().size() == 1) {
                DataBase.removeProduct(productId);
            } else {
                seller.removeProduct(productId);
                DataBase.getProductById(productId).removeSeller(seller);
            }
            return "product removed";
        }
    }

    //parameter ha ro mishe bishtear kard
    public static String viewOff(long offId) {
        Seller seller = (Seller) Controller.currentUser;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (seller.getOffById(offId) == null) {
            return "off not exist";
        } else {
            String info = "";
            info += seller.getOffById(offId).getOffId() + ",";
            info += formatter.format(seller.getOffById(offId).getStartDate()) + ",";
            info += formatter.format(seller.getOffById(offId).getEndDate()) + ",";
            info += seller.getOffById(offId).getOffAmount();
            return info;
        }
    }

    //kamel nist (invalid field bayad bere!)
    public static String editOff(String field, String newContent, long offId) {
        Seller seller = (Seller) Controller.currentUser;
        if (seller.getOffById(offId) == null) {
            return "off not exist";
        } else if (field.toLowerCase().equals("end date")) {
            Manager.addRequest(new EditOffRequest("end date", newContent, seller.getOffById(offId)));
            return "request sent";
        } else if (field.toLowerCase().equals("amount")) {
            if (!newContent.matches("\\d+")) {
                return "invalid new content";
            }
            Manager.addRequest(new EditOffRequest("percent", newContent, seller.getOffById(offId)));
            return "request sent";
        }
        return "invalid field";
    }

    //kamel nist
    public static String addOff(ArrayList<String> info) {
        Seller seller = (Seller) Controller.currentUser;
        ArrayList<Product> products = new ArrayList<>();
        for (String Id : info.get(0).split("\\s")) {
            if (!Id.matches("\\d+")) {
                return "invalid product Id";
            }
            if (seller.getProductById(Long.parseLong(Id)) == null) {
                return "product not exist";
            }
            products.add(DataBase.getProductById(Long.parseLong(Id)));
        }
        if (!info.get(3).matches("\\d+")) {
            return "invalid percentage";
        }
        if (Integer.parseInt(info.get(3)) >= 100) {
            return "invalid percentage";
        }
        Date dateE;
        Date dateS;
        try {
            dateS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(info.get(1));
        } catch (Exception ParseException) {
            return "invalid start date";
        }
        try {
            dateE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH).parse(info.get(2));
        } catch (Exception ParseException) {
            return "invalid end date";
        }
        Manager.addRequest(new AddOffRequest(new Off(products, OffStatus.inProgressToBuild, dateS, dateE, Integer.parseInt(info.get(3))), (Seller) Controller.currentUser));
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
            String info = product.getName() + "," + product.getProductId();
            info += "," + product.getBrand() + "," + product.getPrice(product.getDefaultSeller());
            info += "," + product.getAverageScore() + "," + product.getExplanation();
            products.add(info);
        }
        return products;
    }

    //logs mitone kamel tar beshe
    public static ArrayList<String> viewSalesHistory() {
        Seller seller = (Seller) Controller.currentUser;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<SellLog> salesHistory = seller.getSellHistory();
        ArrayList<String> logs = new ArrayList<>();
        for (SellLog sellLog : salesHistory) {
            logs.add(sellLog.getLogId() + "," +
                    formatter.format(sellLog.getLogDate()) + "," +
                    sellLog.getBuyerName() + "," +
                    sellLog.getReceivedAmount() + "," +
                    sellLog.getReducedAmountForOff());
        }
        return logs;
    }

    public static ArrayList<String> getAvailableProductsForOff(String sellerUsername) {
        Seller seller = (Seller) DataBase.getUserByUsername(sellerUsername);
        ArrayList<String> productsName = new ArrayList<>();
        boolean isAvailable = true;
        for (Product product : seller.getProductsForSale()) {
            isAvailable = true;
            for (Off off : seller.getOffs()) {
                for (Product offProduct : off.getProducts()) {
                    if (offProduct.getProductId() == product.getProductId()) {
                        isAvailable = false;
                        break;
                    }
                }
                if (!isAvailable) {
                    break;
                }
            }
            if (isAvailable) {
                productsName.add(product.getName() + "_" + product.getProductId());
            }
        }
        return productsName;
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

    public static boolean hasProductWithName(String name) {
        for (Product allProduct : DataBase.getAllProducts()) {
            if (allProduct.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String editProduct(String field, String newContent, long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else if (field.toLowerCase().equals("name")) {
            Manager.addRequest(new EditProductRequest("name", newContent, DataBase.getProductById(productId), (Seller) Controller.currentUser));
        } else if (field.toLowerCase().equals("brand")) {
            Manager.addRequest(new EditProductRequest("brand", newContent, DataBase.getProductById(productId), (Seller) Controller.currentUser));
        } else if (field.toLowerCase().equals("price")) {
            if (!newContent.matches("(\\d+)(\\.?)(\\d*)")) {
                return "invalid new content";
            }
            Manager.addRequest(new EditProductRequest("price", newContent, DataBase.getProductById(productId), (Seller) Controller.currentUser));
        } else if (field.toLowerCase().equals("explanation")) {
            Manager.addRequest(new EditProductRequest("explanation", newContent, DataBase.getProductById(productId), (Seller) Controller.currentUser));
        }
        DataBase.getProductById(productId).setStatus("edit");
        return "request sent";
    }

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
                    , Integer.parseInt(productInfo.get(5)), productInfo.get(6)) ));
            return "request sent";
        }
    }

    //check parameters
    public static ArrayList<String> showOffs() {
        Seller seller = (Seller) Controller.currentUser;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<String> offs = new ArrayList<>();
        for (Off off : seller.getOffs()) {
            String info = off.getOffId() + "," + off.getOffAmount();
            info += "," + formatter.format(off.getStartDate()) + "," + formatter.format(off.getEndDate());
            offs.add(info);
        }
        return offs;
    }

    public static String viewProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            String info = DataBase.getProductById(productId).getName();
            info += "," + DataBase.getProductById(productId).getProductId();
            info += "," + DataBase.getProductById(productId).getBrand();
            info += "," + DataBase.getProductById(productId).getExplanation();
            return info;
        }
    }
}
