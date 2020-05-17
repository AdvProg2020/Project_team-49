package Controller;


import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.User.Request.Request;
import Models.User.Seller;
import Models.User.User;
import View.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ArrayList;

public class DataBase {
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;
    static ArrayList<User> allUsers = new ArrayList<User>();
    static ArrayList<Product> allProducts = new ArrayList<Product>();
    static ArrayList<Product> sortedOrFilteredProduct = new ArrayList<Product>();
    static ArrayList<Category> allCategories = new ArrayList<Category>();
    static ArrayList<String> allAvailableFilters = new ArrayList<String>();
    static ArrayList<String> allAvailableSorting = new ArrayList<String>();
    public static long createdProductsCount = 0;

    public static ArrayList<Request> allActiveRequests = new ArrayList<>();
    public static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();
    public static ArrayList<Request> answeredRequests = new ArrayList<>();

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equalsIgnoreCase(username)) {
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
        for (Product allProduct : allProducts) {
            if (!allProduct.getName().equals(product.getName())) {
                continue;
            }
            if (!allProduct.getBrand().equals(product.getBrand())) {
                continue;
            }
            allProduct.addItem(product.getDefaultSeller(), product.remainingItems(), product.getPrice(product.getDefaultSeller()));
            product.getDefaultSeller().addProduct(allProduct);
            return;
        }
        createdProductsCount++;
        product.setProductId(createdProductsCount);
        allProducts.add(product);
        product.getDefaultSeller().addProduct(product);
    }

    public static void removeProduct(long productId) {
        for (Seller seller : getProductById(productId).getAllSellers()) {
            seller.removeProduct(getProductById(productId));
        }
        allProducts.remove(getProductById(productId));
    }

    //kamel nist
    public static void removeCategory(String name) {
        //bayad kol derakht ro remove kard

        allCategories.remove(getCategoryByName(name));
    }

    public static void addCategory(Category category) {
        allCategories.add(category);
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public static boolean isThereAnyCategoryWithName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProductInThisCategory(String categoryName, Product product) {
        Category category = product.getParentCategory();
        while (category != null) {
            if (category.getName().toLowerCase().equals(categoryName.toLowerCase())) {
                return true;
            }
            category = category.getParentCategory();
        }
        return false;
    }

    public static void startProgram() {
        Path resourcesPath = Paths.get("src/main/resources");
        if (!Files.exists(resourcesPath)) {
            try {
                File resourcesDir = new File("src/main/resources");
                resourcesDir.mkdir();
            } catch (Exception e) {
                System.out.println("sorry we can't create resources directory");
                System.exit(0);
            }
        }
        Path dataBasePath = Paths.get("src/main/resources/DataBase");
        if (!Files.exists(dataBasePath)) {
            try {
                File dataBaseDir = new File("src/main/resources/DataBase");
                dataBaseDir.mkdir();
                File productsFile = new File("src/main/resources/DataBase/products.txt");
                productsFile.createNewFile();
                File usersFile = new File("src/main/resources/DataBase/users.txt");
                usersFile.createNewFile();
                File categoriesFile = new File("src/main/resources/DataBase/categories.txt");
                categoriesFile.createNewFile();
                File activeRequestsFile = new File("src/main/resources/DataBase/activeRequests.txt");
                productsFile.createNewFile();
                File answeredRequestsFile = new File("src/main/resources/DataBase/answeredRequests.txt");
                usersFile.createNewFile();
                File discountCodesFile = new File("src/main/resources/DataBase/discountCodes.txt");
                categoriesFile.createNewFile();

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

    public static void endProgram() {
        saveAllData();
        System.exit(0);
    }

    public static void loadAllData() {
        loadAllProducts();
        loadAllCategories();
        loadAllUsers();
        loadAllActiveRequests();
        loadAllAnsweredRequests();
        loadAllDiscountCodes();
    }

    public static void loadAllAnsweredRequests() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/answeredRequests.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            answeredRequests = (ArrayList<Request>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/answeredRequests");
        }
    }

    public static void loadAllActiveRequests() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/activeRequests.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allActiveRequests = (ArrayList<Request>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/activeRequests");
        }
    }

    public static void loadAllDiscountCodes() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/discountCodes.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allDiscountCodes = (ArrayList<DiscountCode>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/discountCodes");
        }
    }

    public static void loadAllProducts() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/products.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allProducts = (ArrayList<Product>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/products");
        }
    }

    public static void loadAllUsers() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/users.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allUsers = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/users");
        }
    }

    public static void loadAllCategories() {
        try {
            inputStream = new FileInputStream("src/main/resources/DataBase/categories.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allCategories = (ArrayList<Category>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/categories");
        }
    }

    public static void saveAllData() {
        saveAllProducts();
        saveAllCategories();
        saveAllUsers();
        saveAllActiveRequests();
        saveAllAnsweredRequests();
        saveAllDiscountCodes();
    }

    public static void saveAllProducts() {
        try {
            outputStream = new FileOutputStream("src/main/resources/DataBase/products.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allProducts);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save products");
        }
    }

    public static void saveAllUsers() {
        try {
            outputStream = new FileOutputStream("src/main/resources/DataBase/users.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allUsers);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save users");
        }
    }

    public static void saveAllCategories() {
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/DataBase/categories.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allCategories);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save categories");
        }
    }

    //    private static ArrayList<Request> allActiveRequests = new ArrayList<>();
//    private static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();
//    private static ArrayList<Request> answeredRequests = new ArrayList<>();
    public static void saveAllActiveRequests() {
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/DataBase/activeRequests.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allActiveRequests);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save active requests");
        }
    }

    public static void saveAllDiscountCodes() {
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/DataBase/discountCodes.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allDiscountCodes);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save discount codes");
        }
    }

    public static void saveAllAnsweredRequests() {
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/DataBase/answeredRequests.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(answeredRequests);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save answered requests");
        }
    }
//
}
