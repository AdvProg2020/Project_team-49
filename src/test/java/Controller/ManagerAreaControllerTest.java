package Controller;

import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Request.AddProductRequest;
import Models.User.Request.Request;
import Models.User.Seller;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static Controller.DataBase.*;
import static Controller.DataBase.allCategories;

public class ManagerAreaControllerTest {
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
    static ArrayList<Costumer> forDiscount=new ArrayList<Costumer>();
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

        allActiveRequests.add(new AddProductRequest(a));
        allActiveRequests.add(new AddProductRequest(b));
        allActiveRequests.add(new AddProductRequest(c));

        forDiscount.add(costumer1);
        forDiscount.add(costumer2);
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

    @Test
    public void TestDeleteUser(){
        initialise();
        Assert.assertEquals("user deleted", ManagerAreaController.deleteUser("amiri77"));
    }

    @Test
    public void TestDeleteProduct(){
        initialise();
        Assert.assertEquals("product not exist", ManagerAreaController.deleteProduct(123123));
        Assert.assertEquals("product removed", ManagerAreaController.deleteProduct(1111));
    }

    @Test
    public void TestCreateDiscountCode(){
        initialise();
        //
    }

    @Test
    public void TestRemoveDiscountCode(){
        initialise();
        DiscountCode discountCode =new DiscountCode("123Dis",null,null,20,5000,4,forDiscount);
        allDiscountCodes.add(discountCode);
        Assert.assertEquals("discount code not exist",ManagerAreaController.removeDiscountCode("1"));
        Assert.assertEquals("discount code removed",ManagerAreaController.removeDiscountCode("123Dis"));
    }

    @Test
    public void TestRequestDetails(){
        Assert.assertEquals("request not exist",ManagerAreaController.requestDetails(123123));
        Assert.assertEquals("",ManagerAreaController.requestDetails(2));

    }

    @Test
    public void TestAcceptRequest(){
        initialise();
        Assert.assertEquals("request not exist",ManagerAreaController.acceptRequest(123123));
        Assert.assertEquals("request 1 accepted",ManagerAreaController.acceptRequest(1));

    }

    @Test
    public void TestDeclineRequest(){
        initialise();
        Assert.assertEquals("request 3 declined",ManagerAreaController.declineRequest(3));
        Assert.assertEquals("request not exist",ManagerAreaController.declineRequest(123123));
    }

    @Test
    public void TestAddCategory(){
        initialise();
//        ManagerAreaController.addCategory()
    }

    @Test
    public void TestRemoveCategory(){
        initialise();
        Assert.assertEquals("category not exist",ManagerAreaController.removeCategory("asdasdasd"));
        Assert.assertEquals("mobile category removed",ManagerAreaController.removeCategory("mobile"));

    }

    @Test
    public void TestViewDiscountCode(){
        //
    }

    @Test
    public void TestShowAllUsers(){
        initialise();
        ArrayList<String> test=new ArrayList<String >();
        test.add("alim1379,Costumer,ali,mehrabani,alim1379@gmail.com,1288888");
        test.add("amiri77,Seller,amir,amiri,amiri@gmail.com,3");
        test.add("alireza_hr,Costumer,alireza,heidari,alireza@gmail.com,1777");
        test.add("izadiii,Manager,mohammad,izadi,izadi@gmail.com,12");
        test.add("hamid_Tala,Costumer,hamid,yaghobi,yaghobi@gmail.com,12338");
        test.add("AbaM,Manager,jj,abam,abam@gmail.com,13");
        Assert.assertEquals(test,ManagerAreaController.showAllUsers());
    }

    @Test
    public void TestShowAllProducts(){
        initialise();
        ArrayList<String> test=new ArrayList<String >();
        test.add("phone,1111,apple,1000.0,0.0,nothing");
        test.add("plakolang,2222,iran,10.0,0.0,nothing");
        test.add("S9,3333,samsung,7000.0,0.0,nothing");
        test.add("yaghe7,4444,nikooTanPoosh,5.0,0.0,nothing");
        test.add("poster,5555,LMV,500.0,0.0,nothing");
        Assert.assertEquals(test,ManagerAreaController.showAllProducts());
    }

    @Test
    public void TestShowALlDiscountCode(){
        initialise();
        ArrayList<String> test=new ArrayList<String >();
        DiscountCode discountCode =new DiscountCode("123Dis",null,null,20,5000,4,forDiscount);
        allDiscountCodes.add(discountCode);
        //in datesh ro che konam?
//        test.add("123Dis,null,null,20,5000,4");
//        Assert.assertEquals(test,ManagerAreaController.showDiscountCodes());
    }

    @Test
    public void TestEditDiscountCode(){
        initialise();
        DiscountCode discountCode =new DiscountCode("123Dis",null,null,20,5000,4,forDiscount);
        allDiscountCodes.add(discountCode);
        Assert.assertEquals("field edited",ManagerAreaController.editDiscountCode("123Dis","percent","12"));
        Assert.assertEquals("invalid percent",ManagerAreaController.editDiscountCode("123Dis","percent","asd"));
        Assert.assertEquals("invalid amount",ManagerAreaController.editDiscountCode("123Dis","maximum amount","asd"));
        Assert.assertEquals("discount code not exist",ManagerAreaController.editDiscountCode("121233Dis","percent","12"));

    }

    @Test
    public void TestShowRequests(){
//        initialise();
        allActiveRequests.clear();
        initialise();
        ArrayList<String> test=new ArrayList<String >();
        test.add("1,Add Product");
        test.add("2,Add Product");
        test.add("3,Add Product");
        Assert.assertEquals(test,ManagerAreaController.showRequests());
    }

    @Test
    public void TestChangeUserType(){
        //
    }

    @Test
    public void TestShowCategories(){
        initialise();
        ArrayList<String> test=new ArrayList<String >();
        test.add("mobile,,xx");
        test.add("aaaa,,xxlx");
        test.add("xx,null,null");
        test.add("bmw,,xxlx");
        test.add("nike,,xxlx");
        test.add("xxlx,null,null");
        test.add("child,null,bmw");
        Assert.assertEquals(test,ManagerAreaController.showCategories());
    }

    @Test
    public void TestEditCategory(){
        initialise();
        Assert.assertEquals("category not exist",ManagerAreaController.editCategory("asdasd","asd","asda"));
        Assert.assertEquals("field edited",ManagerAreaController.editCategory("xx","attribute","asda"));
        Assert.assertEquals("invalid new name",ManagerAreaController.editCategory("xx","name","moblie asd"));
        Assert.assertEquals("category exist with this new name",ManagerAreaController.editCategory("xx","name","mobile"));
        Assert.assertEquals("field edited",ManagerAreaController.editCategory("xx","name","mobileee"));
        reset();
    }
}
