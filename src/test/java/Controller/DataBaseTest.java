package Controller;

import Models.Category;
import Models.Product;
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

    static Category mother = new Category("xx", null, null);
    static Category father = new Category("xxlx", null, null);
    static Category phone = new Category("mobile", "", mother);
    static Category makeup = new Category("aaaa", "", father);
    static Category shirt = new Category("nike", "", father);
    static Category car = new Category("bmw", "", father);
    static Category child = new Category("child", null, car);
    static Product a = new Product("phone", "apple", 1000.0, "nothing", phone, null, 1000);
    static Product b = new Product("plakolang", "iran", 10.0, "nothing", car, null, 0);
    static Product c = new Product("S9", "samsung", 7000.0, "nothing", phone, null, 1000);
    static Product d = new Product("yaghe7", "nikooTanPoosh", 5.0, "nothing", shirt, null, 1000);
    static Product e = new Product("poster", "LMV", 500.0, "nothing", makeup, null, 0);
    static Costumer costumer1 = new Costumer("alim1379", "ali", "mehrabani", "alim1379@gmail.com", 1288888, "alialiali");
    static Costumer costumer2 = new Costumer("alireza_hr", "alireza", "heidari", "alireza@gmail.com", 1777, "alirezaalireza");
    static Costumer costumer3 = new Costumer("hamid_Tala", "hamid", "yaghobi", "yaghobi@gmail.com", 12338, "hamidhamid");
    static Manager manager1 = new Manager("izadiii", "mohammad", "izadi", "izadi@gmail.com", 12, "izadizad");
    static Manager manager2 = new Manager("AbaM", "jj", "abam", "abam@gmail.com", 13, "abamiad");
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "amiriI");

    public void initialise() {
        costumer1.setCredit(1.1);
        allCategories.clear();
        allProducts.clear();
        allUsers.clear();

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


        allCategories.clear();
        father.getSubCategories().clear();
        mother.getSubCategories().clear();
        car.getSubCategories().clear();
        father.addSubCategory(makeup);
        father.addSubCategory(shirt);
        father.addSubCategory(car);
        mother.addSubCategory(phone);
        car.addSubCategory(child);

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

    public void makeResources() {
        try{
            File dataBaseDir = new File("src/main/resources/DataBase");
            dataBaseDir.mkdir();
            File productsFile = new File("src/main/resources/DataBase/products.txt");
            productsFile.createNewFile();
            File usersFile = new File("src/main/resources/DataBase/users.txt");
            usersFile.createNewFile();
            File categoriesFile = new File("src/main/resources/DataBase/categories.txt");
            categoriesFile.createNewFile();
        }catch (Exception e){
            System.out.println("not exist");
        }
    }

    public void deleteOrMake(){
        Path path = Paths.get("src/main/resources/DataBase");
        if(Files.exists(path)){
            clearResources();
        }else
            makeResources();
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
        makeResources();
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
        deleteOrMake();
        saveAllCategories();
        allCategories.clear();
        loadAllCategories();
        assertEquals( 0 , allCategories.size());
        deleteOrMake();
    }

    @Test
    public void TestSaveAndLoadAllProducts() {
        initialise();
        saveAllProducts();
        allProducts.clear();
        loadAllProducts();
        assertEquals(allProducts.size(), 5);
        assertEquals("phone", allProducts.get(0).getName());
        assertEquals(10.0, allProducts.get(1).getPrice(allProducts.get(1).getDefaultSeller()), 0.0001);
        allProducts.clear();
        loadAllProducts();
        loadAllProducts();
        assertEquals(allProducts.get(4).getBrand(), "LMV");
        assertEquals(5, allProducts.size());
        deleteOrMake();
        allProducts.clear();
        saveAllProducts();
        loadAllProducts();
        assertEquals( 0 , allProducts.size());
        deleteOrMake();
    }

    @Test
    public void TestSaveAndLoadAllUsers() {
        initialise();
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
        deleteOrMake();
        allUsers.clear();
        saveAllUsers();
        loadAllUsers();
        assertEquals( 0 , allUsers.size());
        deleteOrMake();
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
        assertEquals(10.0, allProducts.get(1).getPrice(allProducts.get(1).getDefaultSeller()), 0.0001);
        assertEquals(allProducts.get(4).getBrand(), "LMV");
        assertEquals(5, allProducts.size());

        assertEquals(allCategories.size(), 7);
        assertEquals("mobile", allCategories.get(0).getName());
        assertEquals(allCategories.get(1).getParentCategory().getName(), "xxlx");
        assertEquals(7, allCategories.size());
        deleteOrMake();
        allUsers.clear();
        allProducts.clear();
        allCategories.clear();
        loadAllData();
        assertEquals(0, allProducts.size());
        assertEquals(0, allUsers.size());
        assertEquals(0, allCategories.size());
        deleteOrMake();
    }
}
