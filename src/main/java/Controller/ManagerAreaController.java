package Controller;

import Models.DiscountCode;
import Models.User.Manager;

import java.util.ArrayList;

public class ManagerAreaController {

    public static String viewUser(String username) {
        return "";
    }

    public static void deleteUser(String username) {
    }

    public static void changeUserType(String username, String newType) {

    }

    public static void createManagerProfile(String[] info) {

    }

    public static void deleteProduct(long productID) {

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
