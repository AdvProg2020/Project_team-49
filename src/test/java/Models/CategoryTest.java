package Models;

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

public class CategoryTest {

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


    @Test
    public void TestGetSubProducts(){
        shirt.addSubCategory(phone);
        assertEquals(1 , shirt.getSubCategories().size());
        shirt.removeSubCategory(phone);
    }

    @Test
    public void TestAddSubProducts(){
        shirt.addProduct(a);
        shirt.addProduct(b);
        assertEquals(2 , shirt.getSubProducts().size());
        shirt.removeProduct(a);
        shirt.removeProduct(b);

    }

    @Test
    public void TestShowSubCategories(){
        assertEquals(null , shirt.showCategories());
    }

    @Test
    public void TestRemoveSubCategory(){
        shirt.addSubCategory(phone);
        shirt.addSubCategory(makeup);
        shirt.removeSubCategory(phone);
        assertEquals(1 , shirt.getSubCategories().size());
        shirt.removeSubCategory(makeup);
    }

    @Test
    public void TestSetSpecialAttributes(){
        shirt.setSpecialAttributes("ali");
        assertEquals("ali" , shirt.getSpecialAttributes());
    }

    @Test
    public void TestSetName(){
        shirt.setName("ali");
        assertEquals("ali" , shirt.getName());
    }

    @Test
    public void TestSetSubProducts(){
        ArrayList<Product> someProducts = new ArrayList<>();
        someProducts.add(a);
        someProducts.add(b);
        someProducts.add(e);
        shirt.getSubProducts().clear();
        shirt.setSubProducts(someProducts);
        assertEquals(3 , shirt.getSubProducts().size());
        shirt.getSubProducts().clear();
    }


}
