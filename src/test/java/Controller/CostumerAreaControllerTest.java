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

import static Controller.Controller.*;
import static Controller.CostumerAreaController.*;
import static Controller.DataBase.*;
import static org.junit.Assert.*;

public class CostumerAreaControllerTest {
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

        a.setProductId(1);
        b.setProductId(2);
        c.setProductId(3);
        d.setProductId(4);
        e.setProductId(5);

        allCategories.clear();
        father.getSubCategories().clear();
        mother.getSubCategories().clear();
        car.getSubCategories().clear();
        father.addSubCategory(makeup);
        father.addSubCategory(shirt);
        father.addSubCategory(car);
        mother.addSubCategory(phone);
        car.addSubCategory(child);
        mother.addProduct(a);
        phone.addProduct(c);
        father.addProduct(b);

        allCategories.add(phone);
        allCategories.add(makeup);
        allCategories.add(mother);
        allCategories.add(car);
        allCategories.add(shirt);
        allCategories.add(father);
        allCategories.add(child);
    }


    @Test
    public void TestShowProducts() {
        initialise();
        currentUser = costumer1;

        costumer1.addProductToCart(a, seller1, 50);
        costumer1.addProductToCart(b, seller1, 1);

        ArrayList<String> infos = new ArrayList<>();
        infos.add(a.getName() + " " + a.getProductId() + " " + a.getBrand() + a.getPrice(a.getDefaultSeller()) + 50 + "seller1");
        infos.add(b.getName() + " " + b.getProductId() + " " + b.getBrand() + b.getPrice(b.getDefaultSeller()) + 1 + "seller1");
//        assertEquals(infos , showProducts());
    }
}
