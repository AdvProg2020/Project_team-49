package Controller;


import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.User.Costumer;
import Models.User.Request.Request;
import Models.User.Seller;
import Models.User.User;
import View.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.*;
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
    public static ArrayList<Request> allActiveRequests = new ArrayList<>();
    public static ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>();
    public static ArrayList<Request> answeredRequests = new ArrayList<>();

    static ArrayList<String> allAvailableFilters = new ArrayList<String>();
    static ArrayList<String> allAvailableSorting = new ArrayList<String>();

    static long referenceTime;
    public static long createdProductsCount = 0;
    public static long createdOffsCount = 0;
    public static long createdLogs = 0;
    public static long createdRequests = 0;

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

//    private void setReferences(){
//        for (Product product : allProducts) {
//            Category category = product.getParentCategory();
//            ArrayList<Seller> sellers = product.getAllSellers();
//            for (Category allCategory : allCategories) {
//                if(allCategory.getName().equals(category) && allCategory.getSpecialAttributes().equals(category.getSpecialAttributes())){
//                    category = allCategory;
//                    break;
//                }
//            }
//            for (Seller seller : sellers) {
//                for (User allUser : allUsers) {
//                    if(allUser.getType().equalsIgnoreCase("seller")){
//                        Seller seller1 = (Seller) allUser;
//                        if(seller1.getEMail().equals(seller.getEMail()) && seller.getUsername().equals(seller1.getUsername()) &&
//                            seller.getCompanyName().equals(seller1.getCompanyName()) && seller.getFirstName().equals(seller1.getFirstName())){
//                            seller = seller1;
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
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

    public static long getLogId() {
        createdLogs++;
        return createdLogs;
    }

    public static long getRequestId() {
        createdRequests++;
        return createdRequests;
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
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.addAll(allProducts);
    }

    public static void removeProduct(long productId) {
        for (Seller seller : getProductById(productId).getAllSellers()) {
            seller.removeProduct(getProductById(productId));
        }
        allProducts.remove(getProductById(productId));
    }


    public static void removeCategory(String name) {
        Category category = getCategoryByName(name);
        if (category.getParentCategory() != null)
            category.getParentCategory().removeSubCategory(category);
        removeCategoryRecursive(category);
    }

    public static void removeCategoryRecursive(Category category) {
        System.out.println("category name : " + category.getName());
        allCategories.remove(category);
        for (Product subProduct : category.getSubProducts()) {
            System.out.println("   product name = " + subProduct.getName() + subProduct.getProductId() + "  in category " + category.getName());
            removeProduct(subProduct.getProductId());
        }

        for (Category subCategory : category.getSubCategories()) {
            removeCategoryRecursive(subCategory);
        }
        return;
    }


    public static void addCategory(Category category) {
        allCategories.add(category);
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equalsIgnoreCase(name))
                return category;
        }
        return null;
    }

    public static void addDiscountCode(DiscountCode discountCode) {
        for (Costumer allowedCostumer : discountCode.getAllowedCostumers()) {
            allowedCostumer.addDiscountCode(discountCode);
        }
        allDiscountCodes.add(discountCode);
        //؟
    }

    public static boolean isThereAnyCategoryWithName(String name) {
        for (Category category : allCategories) {
            if (category.getName().equalsIgnoreCase(name))
                return true;
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
        dataBaseRun();
        new View().run();
    }

    public static void dataBaseRun() {
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
                activeRequestsFile.createNewFile();
                File answeredRequestsFile = new File("src/main/resources/DataBase/answeredRequests.txt");
                answeredRequestsFile.createNewFile();
                File discountCodesFile = new File("src/main/resources/DataBase/discountCodes.txt");
                discountCodesFile.createNewFile();
                File longVariablesFile = new File("src/main/resources/DataBase/longVariables.txt");
                longVariablesFile.createNewFile();
                Date firstRunDate = new Date();
                referenceTime = (Long) firstRunDate.getTime() / 2592000000L;
                createdProductsCount = 0;
                allAvailableFilters = Filter.getAvailableFilters();
                allAvailableSorting = Sort.getAvailableSorts();
                return;
            } catch (Exception e) {
                System.out.println("sorry we can't create DataBase directory");
                System.exit(0);
            }
        }
        loadAllData();
        setRandomDiscountCode();
        allAvailableFilters = Filter.getAvailableFilters();
        allAvailableSorting = Sort.getAvailableSorts();
    }

    public static void endProgram() {
        dataBaseEnd();
        System.exit(0);
    }

    public static void dataBaseEnd() {
        saveAllData();
    }

    public static void loadAllData() {
        loadAllProducts();
        loadAllCategories();
        loadAllUsers();
        loadAllActiveRequests();
        loadAllAnsweredRequests();
        loadAllDiscountCodes();
        loadLongVariables();
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

    public static void loadLongVariables() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/DataBase/longVariables.txt");
            BufferedReader in = new BufferedReader(fileReader);
            String a = in.readLine();
            referenceTime = Long.parseLong(a);
            a = in.readLine();
            createdProductsCount = Long.parseLong(a);
            a = in.readLine();
            createdOffsCount = Long.parseLong(a);
            a = in.readLine();
            createdLogs = Long.parseLong(a);
            in.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("still no files in dataBase/longVariables");
        }
    }

    public static void saveAllData() {
        saveAllProducts();
        saveAllCategories();
        saveAllUsers();
        saveAllActiveRequests();
        saveAllAnsweredRequests();
        saveAllDiscountCodes();
        saveLongVariables();
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

    public static void saveLongVariables() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/DataBase/longVariables.txt");
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(String.valueOf(referenceTime));
            out.newLine();
            out.write(String.valueOf(createdProductsCount));
            out.newLine();
            out.write(String.valueOf(createdOffsCount));
            out.newLine();
            out.write(String.valueOf(createdLogs));
            out.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("can't save long Variables");
        }
    }

    //?????
    public static void setRandomDiscountCode() {  // for maximum 10 costumers
        long monthPeriod = 2592000000L;
        Date everyRunCurrentDate = new Date();
        if (everyRunCurrentDate.getTime() < referenceTime * monthPeriod)
            return;
        referenceTime++;
        giveDiscountCode(10, monthPeriod);
    }

    public static void giveDiscountCode(int n, long monthPeriod) {
        ArrayList<Costumer> costumers = new ArrayList<>();
        for (User user : allUsers) {
            if (user instanceof Costumer)
                costumers.add((Costumer) user);
        }
        ArrayList<Costumer> allowedCostumers = new ArrayList<>();
        if (costumers.size() < 10) {
            n = costumers.size();
        }
        Random random = new Random();
        ArrayList<Integer> used = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(n);
            boolean flag = false;
            while (!flag) {
                if (!used.contains(index)) {
                    flag = true;
                } else {
                    index = random.nextInt(n);
                }
            }
            used.add(index);
            allowedCostumers.add(costumers.get(index));
        }
        int i= 0;
        for (Costumer allowedCostumer : allowedCostumers) {

            allowedCostumer.addDiscountCode(new DiscountCode("monthly gift"
                    , new Date(), new Date(monthPeriod + new Date().getTime()), 10
                    , 50000, 2, allowedCostumers));
        }
        allowedCostumers.clear();
        used.clear();
        costumers.clear();
    }
}
