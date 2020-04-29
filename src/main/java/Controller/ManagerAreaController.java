package Controller;

import Models.DiscountCode;
import Models.User.Manager;
import Models.User.Seller;
import Models.User.User;

import javax.xml.crypto.Data;
import java.util.ArrayList;

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

    public static void createDiscountCode(String[] info) {
        DiscountCode.addDiscountCode(null);
    }

    public static void editDiscountCode(long code) {

    }

    public static void removeDiscountCode(long code) {

    }

    public static String requestDetails(long requestID) {
        return "";
    }

    public static void acceptRequest(long requestID) {
        Manager manager = (Manager) Controller.currentUser;
        manager.answerRequest("accept", 1);
    }

    public static void declineRequest(long requestID) {
        Manager manager = (Manager) Controller.currentUser;
        manager.answerRequest("decline", 1);
    }

    public static void editCategory(String name) {
        DataBase.getCategoryByName(name);
    }

    public static void addCategory(String name) {
        DataBase.addCategory(name);
    }

    public static void removeCategory(String name) {
        DataBase.removeCategory("");
    }

    public static String viewDiscountCode(long discountCode) {
        return "";
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
