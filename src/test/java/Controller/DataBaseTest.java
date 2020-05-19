package Controller;

import org.apache.commons.io.FileUtils;

import Models.Category;
import Models.Product;
import Models.User.Request.EditProductRequest;
import Models.User.Request.Request;
import Models.User.User;
import Models.User.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import static Controller.DataBase.*;
import static org.junit.Assert.*;

public class DataBaseTest {
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "amiriI");
    static Category mother = new Category("xx", null, null);
    static Category father = new Category("xxlx", null, null);
    static Category phone = new Category("mobile", "", mother);
    static Category makeup = new Category("aaaa", "", father);
    static Category shirt = new Category("nike", "", father);
    static Category car = new Category("bmw", "", father);
    static Category child = new Category("child", null, car);
    static Product a = new Product("phone", "apple", 1000.0, "nothing", phone, seller1, 1000);
    static Product b = new Product("plakolang", "iran", 10.0, "nothing", car, seller1, 0);
    static Product c = new Product("S9", "samsung", 7000.0, "nothing", phone, seller1, 1000);
    static Product d = new Product("yaghe7", "nikooTanPoosh", 5.0, "nothing", shirt, seller1, 1000);
    static Product e = new Product("poster", "LMV", 500.0, "nothing", makeup, seller1, 0);
    static Costumer costumer1 = new Costumer("alim1379", "ali", "mehrabani", "alim1379@gmail.com", 1288888, "alialiali", 0);
    static Costumer costumer2 = new Costumer("alireza_hr", "alireza", "heidari", "alireza@gmail.com", 1777, "alirezaalireza", 0);
    static Costumer costumer3 = new Costumer("hamid_Tala", "hamid", "yaghobi", "yaghobi@gmail.com", 12338, "hamidhamid", 0);
    static Manager manager1 = new Manager("izadiii", "mohammad", "izadi", "izadi@gmail.com", 12, "izadizad");
    static Manager manager2 = new Manager("AbaM", "jj", "abam", "abam@gmail.com", 13, "abamiad");


    public void initialise() {
        reset();
        costumer1.setCredit(1.1);
        allCategories.clear();
        allProducts.clear();
        allUsers.clear();
        phone.addProduct(a);
        phone.addProduct(c);
        car.addProduct(b);
        shirt.addProduct(d);
        makeup.addProduct(e);

        allProducts.add(a);
        allProducts.add(b);
        allProducts.add(c);
        allProducts.add(d);
        allProducts.add(e);

        allUsers.add(costumer1);
        allUsers.add(seller1);
        allUsers.add(costumer2);
        allUsers.add(manager1);
        allUsers.add(costumer3);
        allUsers.add(manager2);

        a.setProductId(1);
        b.setProductId(2);
        c.setProductId(3);
        d.setProductId(4);
        e.setProductId(5);



        allCategories.clear();

        allCategories.add(phone);
        allCategories.add(makeup);
        allCategories.add(mother);
        allCategories.add(car);
        allCategories.add(shirt);
        allCategories.add(father);
        allCategories.add(child);
    }

    public void clearResources() {
        File dir = new File("src/main/resources/DataBase");
        File[] listFiles = dir.listFiles();
        for (File file : listFiles) {
            file.delete();
        }
        dir.delete();
    }

    public void reset(){
        allActiveRequests.clear();
        allCategories.clear();
        allProducts.clear();
        allUsers.clear();
        allDiscountCodes.clear();
        allAvailableSorting.clear();
        allAvailableFilters.clear();
    }

    public void makeResources() {
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
        } catch (Exception e) {
            System.out.println("not exist");
        }
    }


    public void deleteOrMake() {
        Path path = Paths.get("src/main/resources/DataBase");
        if (Files.exists(path)) {
            clearResources();
        } else
            makeResources();
    }

    @Test
    public void TestRemoveCategory() {
        initialise();
        removeCategory("xxlx");
        assertEquals(allCategories.size(), 2);
        assertEquals(allProducts.size(), 5);
    }

    @Test
    public void TestExceptions() {

        allCategories.clear();
        allProducts.clear();
        allUsers.clear();
        allActiveRequests.clear();
        answeredRequests.clear();
        allDiscountCodes.clear();

        File file = new File("src/main/resources/DataBase");
        try {
            FileUtils.cleanDirectory(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        loadAllData();
        assertEquals(0, allUsers.size());
        saveAllData();
        loadAllData();

        File file2 = new File("src/main/resources");
        try {
            FileUtils.cleanDirectory(file2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        file2.delete();
        dataBaseEnd();
        dataBaseRun();
        assertEquals(0, allProducts.size());
        assertEquals(0, allActiveRequests.size());
    }


    @Test
    public void TestGetUserByUserName() {
        initialise();
        User user = null;
        user = getUserByUsername("ooo");
        assertEquals(null, user);
        user = getUserByUsername("Abam");
        assertEquals(manager2, user);
        user = getUserByUsername("amiri77");
        assertEquals(seller1, user);
    }

    @Test
    public void TestSaveAndLoadAllCategories() {
        initialise();
        saveAllCategories();
        allCategories.clear();
        loadAllCategories();
        assertEquals(allCategories.size(), 7);
        assertEquals("mobile", allCategories.get(0).getName());
        allCategories.clear();
        loadAllCategories();
        loadAllCategories();
        assertEquals(allCategories.get(1).getParentCategory().getName(), "xxlx");
        assertEquals(7, allCategories.size());

        allCategories.clear();
        saveAllCategories();
        allCategories.clear();
        loadAllCategories();
        assertEquals(0, allCategories.size());

    }

    @Test
    public void TestSaveAndLoadAllProducts() {
        initialise();
        saveAllProducts();
        allProducts.clear();
//        loadAllProducts();
//        assertEquals(allProducts.size(), 5);
//        assertEquals("phone", allProducts.get(0).getName());
//        allProducts.clear();
//        loadAllProducts();
//        loadAllProducts();
//        assertEquals(allProducts.get(4).getBrand(), "LMV");
//        assertEquals(5, allProducts.size());
//        allProducts.clear();
//        saveAllProducts();
//        loadAllProducts();
//        assertEquals(0, allProducts.size());
    }

    @Test
    public void TestSaveAndLoadAllUsers() {

        initialise();
        assertEquals(6, allUsers.size());
        saveAllUsers();
        allUsers.clear();
        loadAllUsers();
        assertEquals(6, allUsers.size());
        assertEquals("alim1379", allUsers.get(0).getUsername());
        Costumer costumer = (Costumer) allUsers.get(0);
        assertEquals(1.1, costumer.getCredit(), 0.0001);
        assertEquals("Seller", allUsers.get(1).getType());
        assertEquals("Manager", allUsers.get(5).getType());
        assertEquals("Costumer", allUsers.get(4).getType());

        allUsers.clear();
        saveAllUsers();
        loadAllUsers();
        assertEquals(0, allUsers.size());

    }

    @Test
    public void TestSaveAndLoadAllData() {
        initialise();
        saveAllData();

        allUsers.clear();
        allProducts.clear();
        allCategories.clear();
        loadAllData();

        assertEquals(6, allUsers.size());
        assertEquals("alim1379", allUsers.get(0).getUsername());
        Costumer costumer = (Costumer) allUsers.get(0);
        assertEquals(1.1, costumer.getCredit(), 0.0001);
        assertEquals("Seller", allUsers.get(1).getType());
        assertEquals("Manager", allUsers.get(5).getType());
        assertEquals("Costumer", allUsers.get(4).getType());

        assertEquals(allProducts.size(), 5);
        assertEquals("phone", allProducts.get(0).getName());
        assertEquals(allProducts.get(4).getBrand(), "LMV");
        assertEquals(5, allProducts.size());

        assertEquals(allCategories.size(), 7);
        assertEquals("mobile", allCategories.get(0).getName());
        assertEquals(allCategories.get(1).getParentCategory().getName(), "xxlx");
        assertEquals(7, allCategories.size());

    }

    @Test
    public void TestIsProductInThisCategory() {
        initialise();
        assertTrue(isProductInThisCategory("xx", a));
        assertFalse(isProductInThisCategory("xxlx", a));
    }

    @Test
    public void TestIsThereAnyCategoryWithName() {
        initialise();
        assertFalse(isThereAnyCategoryWithName("akusdafkas"));
        assertTrue(isThereAnyCategoryWithName("xxlx"));
    }

    @Test
    public void TestGetCategoryByName() {
        initialise();
        Category category = null;
        category = getCategoryByName("xx");
        assertEquals(mother, category);
        category = getCategoryByName("asdfsafas");
        assertEquals(null, category);
    }

    @Test
    public void TestAddCategory() {
        initialise();
        Category category = null;
        category = new Category("ali", null, mother);
        addCategory(category);

        assertEquals(8, allCategories.size());
    }

    @Test
    public void TestGetProductById() {
        initialise();
        assertEquals(getProductById(1), a);
        assertEquals(getProductById(333) , null);
    }

    @Test
    public void TestAddNewUser() {
        initialise();
        allUsers.remove(manager2);
        allUsers.remove(seller1);
        addNewUser(seller1);
        assertEquals(5, allUsers.size());
    }

    @Test
    public void clearDataBase() throws IOException {
        allCategories.clear();
        allProducts.clear();
        allUsers.clear();
        allActiveRequests.clear();
        answeredRequests.clear();
        allDiscountCodes.clear();
        File file = new File("src/main/resources/DataBase");
        try {
            FileUtils.cleanDirectory(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

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
        assertEquals(0, allProducts.size());
        assertEquals(0, activeRequestsFile.length(), .0001);
    }

    @Test
    public void TestSetRandomDiscountCode() {
        initialise();
        referenceTime = 0;
        setRandomDiscountCode();
        assertEquals(1, referenceTime);
        referenceTime = 50000;
        assertEquals(50000, referenceTime);
    }

    @Test
    public void TestStartProgram() {
        initialise();
        saveAllData();
        allProducts.clear();
        dataBaseRun();
        assertEquals(allProducts.size(), 5);
    }

    @Test
    public void TestRemoveUser() {
        initialise();
        removeUser("alireza_hr79");
        assertEquals(allUsers.size(), 6);
        removeUser("alireza_hr");
        assertEquals(allUsers.size(), 5);
        reset();
    }

}
