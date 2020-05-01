package Controller;


import Models.Category;
import Models.Product;
import Models.User.User;
import View.View;

import java.io.*;
import java.util.ArrayList;
import java.util.ArrayList;

public class DataBase {
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;

    static ArrayList<User> allUsers = new ArrayList<User>();
    static ArrayList<Product> allProducts = new ArrayList<Product>();
    static ArrayList<Category> allCategories = new ArrayList<Category>();
    static ArrayList<String> allAvailableFilters = new ArrayList<String>();
    static ArrayList<String> allAvailableSorting = new ArrayList<String>();
    static ArrayList<Product> sortedOrFilteredProduct = new ArrayList<Product>();

    public static User getUserByUsername(String username) {
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

    public static Category getCategoryWithName(String name) {
        for (Category category : allCategories) {
            if (category.getName().toLowerCase().equals(name.toLowerCase())) {
                return category;
            }
        }
        return null;
    }


    public static void addNewUser(User user) {

    }

    public static void removeUser(String username) {
        allUsers.remove(getUserByUsername(username));
    }

    public static void addNewProduct(Product product) {

    }

    public static void removeProduct(long productId) {

    }

    public static void removeCategory(String name) {

    }

    public static void addCategory(String name) {

    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if(category.getName().equals(name.toLowerCase())){
                return category;
            }
        }
        return null;
    }

    public static boolean isThereAnyCategoryWithName(String name){
        for (Category category : allCategories) {
            if(category.getName().equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static boolean isProductInThisCategory(String categoryName , Product product){
        Category category = product.getParentCategory();
        while (category != null){
            if(category.getName().equalsIgnoreCase(categoryName)){
                return true;
            }
            category = category.getParentCategory();
        }
        return false;
    }

    public static void startProgram() throws IOException, ClassNotFoundException {
        File resourcesDir = new File("src/main/resources/DataBase");
        if (!resourcesDir.exists()) {
            try {
                resourcesDir.mkdir();
            } catch (Exception e) {
                System.out.println("sorry we can't create resources directory");
                System.exit(0);
            }
        }
        File dataBaseDir = new File("src/main/resources/DataBase");
        if (!dataBaseDir.exists()) {
            try {
                dataBaseDir.mkdir();
                File productsFile = new File("src/main/resources/DataBase/products.txt");
                productsFile.createNewFile();
                File usersFile = new File("src/main/resources/DataBase/users.txt");
                productsFile.createNewFile();
                File categoriesFile = new File("src/main/resources/DataBase/categories.txt");
                productsFile.createNewFile();
                return;
            } catch (Exception e) {
                System.out.println("sorry we can't create DataBase directory");
                System.exit(0);
            }
        }
        loadAllData();
        allAvailableFilters = Filter.getAvailableFilters();
        allAvailableSorting = Sort.getAvailableSorts();
        View.run();
    }

    public static void endProgram() throws IOException, ClassNotFoundException {
        saveAllData();
        System.exit(0);
    }

    public static void loadAllData() throws IOException, ClassNotFoundException {
        loadAllProducts();
        loadAllCategories();
        loadAllUsers();
    }

    public static void loadAllProducts() throws IOException, ClassNotFoundException {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/products.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allProducts = (ArrayList<Product>) objectInputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/products");
        }
    }

    public static void loadAllUsers() throws IOException, ClassNotFoundException {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/users.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allUsers = (ArrayList<User>) objectInputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/users");
        }
    }

    public static void loadAllCategories() throws IOException, ClassNotFoundException {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/categories.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allCategories = (ArrayList<Category>) objectInputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/categories");
        }
    }

    public static void saveAllData() throws IOException, ClassNotFoundException {
        saveAllProducts();
        saveAllCategories();
        saveAllUsers();
    }

    public static void saveAllProducts() throws IOException, ClassNotFoundException {
        try{
            outputStream = new FileOutputStream("src/main/resources/DataBase/products.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allProducts);
            objectOutputStream.close();
            outputStream.close();
        }
        catch (Exception e){
            System.out.println("can't save products");
        }
    }

    public static void saveAllUsers() throws IOException, ClassNotFoundException {
        try{
            outputStream = new FileOutputStream("src/main/resources/DataBase/users.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allUsers);
            objectOutputStream.close();
            outputStream.close();
        }
        catch (Exception e){
            System.out.println("can't save users");
        }
    }

    public static void saveAllCategories() throws IOException, ClassNotFoundException {
        try{
            outputStream = new FileOutputStream("src/main/resources/DataBase/categories.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allCategories);
            objectOutputStream.close();
            outputStream.close();
        }
        catch (Exception e){
            System.out.println("can't save categories");
        }
    }
}
