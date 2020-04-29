package Controller;

import Models.Product;

import Models.User.*;

import java.util.ArrayList;


public class Controller {
    public static User currentUser;
    private static boolean hasHeadManager;

    public Controller() {
        this.currentUser = new Guest();
        this.hasHeadManager = false;
    }

    public static String editField(String field, String newContent) {
        currentUser.setFirstName(newContent);
        currentUser.setLastName(newContent);
        return "";
    }

    public static String getCurrentUserType() {
        return currentUser.getType();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static String getProductById(long productId) {
        DataBase.getProductById(productId);
        return null;
    }

    public static boolean hasHeadManager() {
        return hasHeadManager;
    }

    public static boolean isPasswordCorrect(String password) {
        return false;
    }

    public static String getCurrentUserSpecifications() {
        return "";
    }

    public static String createAccount(ArrayList<String> info, String type) {
        //login beshe ya na
        if (type.toLowerCase().equals("costumer")) {
            DataBase.addNewUser(new Costumer(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1)));
        }
        if (type.toLowerCase().equals("seller")) {
            DataBase.addNewUser(new Seller(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1), info.get(6)));
        }
        if (type.toLowerCase().equals("manager")) {
            hasHeadManager = true;
            DataBase.addNewUser(new Manager(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1)));
        }
        return "account created";
    }

    public static String loginAccount(String username) {
        setCurrentUser(DataBase.getUserByUsername(username));
        return "login successful";
    }

    public static double getBalance() {

        return 0;
    }

    public static boolean hasUserWithUsername(String username) {
        return false;
    }

    public static void addToCart(Product product) {

        //check beshe.
    }

    public static void logout() {
        setCurrentUser(new Guest());
    }

    public static boolean hasDiscountCode(String code) {
        return false;
    }

    public static ArrayList<String> getPersonalInfo() {
        ArrayList<String> info = new ArrayList<>();
        info.add(currentUser.getUsername());
        info.add(currentUser.getFirstName());
        info.add(currentUser.getLastName());
        info.add(currentUser.getEMail());
        info.add(String.valueOf(currentUser.getPhoneNumber()));
        info.add(currentUser.getPassword());
        return info;
    }
}
