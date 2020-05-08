package Controller;

import Models.DiscountCode;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Seller;
import Models.User.User;
import View.View;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerAreaController {

    public static String viewUser(String username) {
        User user = DataBase.getUserByUsername(username);
        String info = username + " ";
        info += user.getFirstName() + " ";
        info += user.getLastName() + " ";
        info += user.getEMail() + " ";
        info += user.getPhoneNumber() + " ";
        if (user.getType().equals("Seller")) {
            info += ((Seller) user).getCompanyName() + " ";
        }
        info += user.getType() + " ";
        return info;
    }

    public static void deleteUser(String username) {
        DataBase.removeUser(username);
    }

    public static void changeUserType(String username, String newType) {

    }

    public static String deleteProduct(long productId) {
        if (DataBase.getProductById(productId) == null) {
            return "product not exist";
        } else {
            DataBase.removeProduct(productId);
            return "product removed";
        }
    }

    public static String createDiscountCode(ArrayList<String> info) {
        ArrayList<String> allowedCostumersNames = new ArrayList<String>(Arrays.asList(info.get(6).split("\\s")));
        ArrayList<Costumer> allowedCostumers = new ArrayList<Costumer>();
        for (String allowedCostumersName : allowedCostumersNames) {
            if (DataBase.getUserByUsername(allowedCostumersName) == null) {
                return "invalid costumer name";
            }
            allowedCostumers.add((Costumer) DataBase.getUserByUsername(allowedCostumersName));
        }
        DiscountCode.addDiscountCode(new DiscountCode(info.get(0),
                Date.valueOf(info.get(1)),
                Date.valueOf(info.get(2)),
                Integer.parseInt(info.get(3)),
                Long.parseLong(info.get(4)),
                Integer.parseInt(info.get(5)),
                allowedCostumers));
        return "discount code created";
    }

    public static void editDiscountCode(String code) {

    }

    public static void removeDiscountCode(String code) {
        if (Manager.getDiscountCodeById(code) == null) {
            View.printString("discount code not exist");
        } else {
            Manager.removeDiscountCode(code);
            View.printString("discount code removed");
        }
    }

    public static void requestDetails(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            View.printString(Manager.getRequestById(requestId).getType());
            View.printString(Manager.getRequestById(requestId).toString());
        }
    }

    public static void acceptRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            Manager.answerRequest("accept", requestId);
            View.printString("request" + requestId + "accepted");
        }
    }

    public static void declineRequest(long requestId) {
        if (Manager.getRequestById(requestId) == null) {
            View.printString("request not exist");
        } else {
            Manager.answerRequest("decline", requestId);
            View.printString("request" + requestId + "declined");
        }
    }

    public static void editCategory(String name, String field, String newContent) {
        DataBase.getCategoryByName(name);
    }

    public static void addCategory(String name) {
        DataBase.addCategory(name);
    }

    public static void removeCategory(String name) {
        if (DataBase.getCategoryByName(name) == null) {
            View.printString("category not exist");
        } else {
            DataBase.removeCategory(name);
            View.printString(name + "category removed");
        }
    }

    public static void viewDiscountCode(String code) {
        if (Manager.getDiscountCodeById(code) == null) {
            View.printString("discount code not exist");
        } else {
            ArrayList<String> info = new ArrayList<>();
            DiscountCode discountCode = Manager.getDiscountCodeById(code);
            info.add(code);
            info.add(discountCode.getStartDate().toString());
            info.add(discountCode.getEndDate().toString());
            info.add(String.valueOf(discountCode.getDiscountPercent()));
            info.add(String.valueOf(discountCode.getMaximumDiscountAmount()));
            info.add(String.valueOf(discountCode.getDiscountCount()));
            info.add(discountCode.getAllowedCostumers().toString());
            View.printArrayList(info);
        }
    }

    public static ArrayList<String> showAllProducts() {
        DataBase.allProducts.get(1);
        return null;
    }

    public static ArrayList<String> showRequests() {
        Manager manager = (Manager) Controller.currentUser;
        manager.getAllActiveRequests();
        return null;
    }

    public static ArrayList<String> showAllUsers() {
        DataBase.allUsers.get(0);
        return null;
    }
}
