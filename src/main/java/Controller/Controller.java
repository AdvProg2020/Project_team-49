package Controller;

import Models.Product;

import Models.User.Guest;
import Models.User.Seller;
import Models.User.User;


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

    public static Product getProductById(long productId) {
        return DataBase.getProductById(productId);
    }

    public static boolean isPasswordCorrect(String password) {
        return false;
    }

    public static String getCurrentUserSpecifications() {
        return "";
    }

    public static String createAccount(String[] info) {
        DataBase.addNewUser(null);
        return "";
    }

    public static String loginAccount(String username) {
        DataBase.getUserByUsername(username);
        setCurrentUser(null);
        return "";
    }

    public static double getBalance() {

        return 0;
    }

    public static boolean hasUserWithUsername(String username) {
        return false;
    }

    public static void addToCart(Product product, Seller seller) {


    }

    public static void logout() {
        setCurrentUser(new Guest());
    }

    public static boolean hasDiscountCode(String code) {
        return false;
    }

    public static boolean isCurrentUserBoughtProductById(long productId){
        return false;
    }
}
