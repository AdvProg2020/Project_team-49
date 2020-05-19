package Controller;

import Models.Category;
import Models.Comment;
import Models.Product;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Seller;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static Controller.DataBase.*;
import static Controller.DataBase.allCategories;

public class ShowProductDetailTest {
    static Category mother = new Category("xx", null, null);
    static Category father = new Category("xxlx", null, null);
    static Category phone = new Category("mobile", "", mother);
    static Category makeup = new Category("aaaa", "", father);
    static Category shirt = new Category("nike", "", father);
    static Category car = new Category("bmw", "", father);
    static Category child = new Category("child", null, car);
    static Costumer costumer1 = new Costumer("alim1379", "ali", "mehrabani", "alim1379@gmail.com", 1288888, "alialiali", 0);
    static Costumer costumer2 = new Costumer("alireza_hr", "alireza", "heidari", "alireza@gmail.com", 1777, "alirezaalireza", 0);
    static Costumer costumer3 = new Costumer("hamid_Tala", "hamid", "yaghobi", "yaghobi@gmail.com", 12338, "hamidhamid", 0);
    static Manager manager1 = new Manager("izadiii", "mohammad", "izadi", "izadi@gmail.com", 12, "izadizad");
    static Manager manager2 = new Manager("AbaM", "jj", "abam", "abam@gmail.com", 13, "abamiad");
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "amiriI");
    static Product a = new Product("phone", "apple", 1000.0, "nothing", phone, seller1, 1000);
    static Product b = new Product("plakolang", "iran", 10.0, "nothing", car, seller1, 0);
    static Product c = new Product("S9", "samsung", 7000.0, "nothing", phone, seller1, 1000);
    static Product d = new Product("yaghe7", "nikooTanPoosh", 5.0, "nothing", shirt, seller1, 1000);
    static Product e = new Product("poster", "LMV", 500.0, "nothing", makeup, seller1, 0);

    public void initialise() {
        costumer1.setCredit(1.1);
        allCategories.clear();
        allProducts.clear();
        allUsers.clear();
        sortedOrFilteredProduct.clear();

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

        a.setProductId(1111);
        b.setProductId(2222);
        c.setProductId(3333);
        d.setProductId(4444);
        e.setProductId(5555);

        a.setDiscountPercentage(0.65);
        c.setDiscountPercentage(0.75);
        e.setDiscountPercentage(0.85);

        a.setOffPercentage(.65);
        c.setOffPercentage(.75);
        e.setOffPercentage(.85);

        a.setDoesItHaveOff(true);
        c.setDoesItHaveOff(true);
        e.setDoesItHaveOff(true);
    }

    @Test
    public void TestGetName(){
        initialise();
        Assert.assertEquals("phone", ShowProductDetail.getName(1111));
        Assert.assertNull(ShowProductDetail.getName(123123));
    }

    @Test
    public void TestGetOffPercentage(){
        initialise();
        Assert.assertNull(ShowProductDetail.getOffPercentage(1231234));
        Assert.assertEquals(0.65,ShowProductDetail.getOffPercentage(1111),0);
    }

    @Test
    public void TestGetExplanation(){
        initialise();
        Assert.assertEquals("nothing",ShowProductDetail.getExplanation(1111));
        Assert.assertNull(ShowProductDetail.getExplanation(123123));
    }

    @Test
    public void TestGetPrice(){
        initialise();
        Assert.assertEquals(1000.0,ShowProductDetail.getPrice(1111),0);
        Assert.assertNull(ShowProductDetail.getPrice(123233123));
    }

    @Test
    public void TestGetCategory(){
        initialise();
        Assert.assertEquals("mobile",ShowProductDetail.getCategory(1111));
        Assert.assertNull(ShowProductDetail.getCategory(123233123));
    }

    @Test
    public void TestGetAverageScore(){
        initialise();
        ShowProductDetail.getAverageScore(1111);
        Assert.assertEquals(0.0,ShowProductDetail.getAverageScore(1111),0);
        Assert.assertNull(ShowProductDetail.getAverageScore(123233123));
    }

    @Test
    public void TestGetArrayListInformation(){
        initialise();
        ArrayList<String> allSellerName=new ArrayList<String>();
        ArrayList<Double> allSellerPrice=new ArrayList<Double>();
        int remained;
        ArrayList<String> titleOfComment=new ArrayList<String>();
        ArrayList<String> noteOfComment=new ArrayList<String>();
        ArrayList<String> userOfComment=new ArrayList<String>();
        allSellerName.add("amiriI");
        allSellerPrice.add(1000.0);
        remained=1000;
        Assert.assertEquals(allSellerName,ShowProductDetail.getAllSeller(1111));
        Assert.assertNull(ShowProductDetail.getAllSeller(1111231));
        Assert.assertEquals(allSellerPrice,ShowProductDetail.getAllSellerPrice(1111));
        Assert.assertNull(ShowProductDetail.getAllSellerPrice(1111231));
        Assert.assertEquals(remained,ShowProductDetail.getRemainedNumber(1111));
        Assert.assertEquals(0,ShowProductDetail.getRemainedNumber(1111231));
        a.addAComment(new Comment(seller1,a,"title","note"));
        titleOfComment.add("title");
        noteOfComment.add("note");
        userOfComment.add("amiri77");
        Assert.assertEquals(titleOfComment,ShowProductDetail.getTitleOfComment(1111));
        Assert.assertEquals(noteOfComment,ShowProductDetail.getNoteOfComment(1111));
        Assert.assertEquals(userOfComment,ShowProductDetail.getUserOfComment(1111));
    }
}
