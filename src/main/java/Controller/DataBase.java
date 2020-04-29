package Controller;

import Models.Category;
import Models.Product;
import Models.User.User;

import java.util.ArrayList;

public class DataBase {
    static ArrayList<User> allUsers = new ArrayList<>();
    static ArrayList<Product> allProducts = new ArrayList<>();
    static ArrayList<Category> allCategories = new ArrayList<>();
    static ArrayList<String> allAvailableFilters = new ArrayList<>();
    static ArrayList<String> allAvailableSorting = new ArrayList<>();
    static ArrayList<Product> sortedOrFilteredProduct = new ArrayList<>();

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static Product getProductById(long productId) {
        for (Product product : allProducts) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static void addNewUser(User user) {
        allUsers.add(user);
    }

    public static void removeUser(String username) {
        allUsers.remove(getUserByUsername(username));
    }

    public static void addNewProduct(Product product) {

    }

    public static void removeProduct(long productId) {}

    public static void removeCategory(String name) {

    }

    public static void addCategory(String name) {

    }

    public static Category getCategoryByName(String name) {
        return null;
    }
}
