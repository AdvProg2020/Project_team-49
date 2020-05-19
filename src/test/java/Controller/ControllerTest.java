package Controller;


import Models.Category;
import Models.Product;
import Models.User.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static Controller.DataBase.*;
import static Controller.Controller.*;
import static org.junit.Assert.*;

public class ControllerTest {
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
        reset();
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
    public void TestEditField() {
        currentUser =seller1 ;
        editField("first name","hamidreza");
        assertEquals("hamidreza",currentUser.getFirstName());

        editField("last name","hamidreza");
        assertEquals("hamidreza",currentUser.getLastName());

        editField("phone number","9123");
        assertEquals(9123,currentUser.getPhoneNumber());

        Assert.assertEquals("invalid new phone number",Controller.editField("phone number","asdasd"));

        editField("email","email@as.com");
        assertEquals("email@as.com",currentUser.getEMail());

        editField("email","email@as.com");
        Assert.assertEquals("invalid new email",Controller.editField("email","asds.com"));

        editField("password","123");
        assertEquals("123",currentUser.getPassword());

        Assert.assertEquals("invalid new password",Controller.editField("password","ads asd"));

        assertEquals("company edited",Controller.editField("company","mihan"));

        assertEquals("invalid field",Controller.editField("compaasdsadny","mihan"));

    }

    @Test
    public void TestGetCurrentUserType() {
        currentUser = new Guest();
        assertEquals("Guest", getCurrentUserType());
        currentUser = manager1;
        assertEquals("Manager", getCurrentUserType());
    }

    @Test
    public void TestSetCurrentUserType() {
        setCurrentUser(manager1);
        assertEquals("Manager", getCurrentUserType());
        setCurrentUser(seller1);
        assertEquals("Seller", getCurrentUserType());
    }

    @Test
    public void TestGetHasHeadManager() {
        setHasHeadManager(true);
        assertTrue(getHasHeadManager());
        setHasHeadManager(false);
        assertFalse(getHasHeadManager());
    }

    @Test
    public void TestCreateAccount() {
//        // we should edit return content of this method
//        String type = "costumer";
//        ArrayList<String> infos = new ArrayList<>();
//        infos.add("alirezahr79");
//        infos.add("alireza");
//        infos.add("yaghobi");
//        infos.add("heidari@gmail.com");
//        infos.add("9123");
//        infos.add("8d d55");
//        assertEquals("invalid password", createAccount(infos, type));
//        infos.set(1, "aaa");
//        assertEquals("invalid phone number", createAccount(infos, type));
//        infos.set(5, "4332434");
//        assertEquals("account created", createAccount(infos, type));
//        type = "manager";
//        assertEquals("account created", createAccount(infos, type));
//        type = "seller";
//        infos.add("nike");
//        assertEquals("account created", createAccount(infos, type));
    }

    @Test
    public void TestLoginAccount() {
        // valid boodn username check shavad
        assertEquals("login successful", loginAccount("alim1379"));
    }

    @Test
    public void TestGetBalance() {
        initialise();
        setCurrentUser(costumer1);
        assertEquals(1.1 , getBalance() , .001);
        setCurrentUser(seller1);
        assertEquals(0 , getBalance() , .001);
    }

    @Test
    public void TestLogout() {
        logout();
        assertEquals("Guest" , currentUser.getType());
    }

    @Test
    public void TestGetPersonalInfo() {
        currentUser = costumer1;
        ArrayList<String> infos = new ArrayList<>();
        infos.add("alim1379");
        infos.add("ali");
        infos.add("mehrabani");
        infos.add("alim1379@gmail.com");
        infos.add("1288888");
        infos.add("alialiali");
        assertEquals(infos , getPersonalInfo());
    }

    @Test
    public void TestIsPasswordCorrect(){
        initialise();
        currentUser =seller1 ;
        editField("password","123");
        assertTrue(Controller.isPasswordCorrect("123","amiri77"));
        assertFalse(Controller.isPasswordCorrect("123223","amiri77"));
    }

    @Test
    public void TestSetCurrentUser(){
        initialise();
        currentUser=seller1;
    }

    @Test
    public void TestGetCurrentUserSpecifications(){
        initialise();
        currentUser=seller1;
        Assert.assertEquals("amiri77,amir,amiri,amiri@gmail.com,3,amiriI,Seller",Controller.getCurrentUserSpecifications());
    }

    @Test
    public void TestHasUserWithUsername(){
        initialise();
        Assert.assertTrue(Controller.hasUserWithUsername("amiri77"));
        Assert.assertFalse(Controller.hasUserWithUsername("amiri77asd"));
    }

    @Test
    public void TestAddToCart(){
        initialise();
        currentUser=costumer1;
        Controller.addToCart(a,seller1,1);
        ArrayList<Product> exceptedCart=new ArrayList<Product>();
        exceptedCart.add(a);
        Assert.assertEquals(exceptedCart,costumer1.getCart().getProducts());
        Controller.addToCart(b,seller1,1);
        exceptedCart.add(b);
        Assert.assertEquals(exceptedCart,costumer1.getCart().getProducts());
        Controller.addToCart(c,seller1,1);
        Controller.addToCart(d,seller1,1);
        exceptedCart.add(c);
        exceptedCart.add(d);
        Assert.assertEquals(exceptedCart,costumer1.getCart().getProducts());
        Guest test=new Guest();
        currentUser=test;
        Controller.addToCart(a,seller1,1);
        ArrayList<Product> exceptedCartGuest=new ArrayList<Product>();
        exceptedCartGuest.add(a);
        Assert.assertEquals(exceptedCartGuest,test.getCart().getProducts());
        Controller.addToCart(b,seller1,1);
        exceptedCartGuest.add(b);
        Assert.assertEquals(exceptedCartGuest,test.getCart().getProducts());
        Controller.addToCart(c,seller1,1);
        Controller.addToCart(d,seller1,1);
        exceptedCartGuest.add(c);
        exceptedCartGuest.add(d);
        Assert.assertEquals(exceptedCartGuest,test.getCart().getProducts());
    }

    @Test
    public void TestGetProductById(){
        initialise();
        Assert.assertEquals(a,Controller.getProductById(1111));
        reset();
    }
}


