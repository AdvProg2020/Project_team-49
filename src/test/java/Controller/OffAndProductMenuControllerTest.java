package Controller;

import Models.Category;
import Models.Product;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Seller;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;

import static Controller.DataBase.*;
import static Controller.DataBase.allCategories;
import static Controller.OffAndProductMenuController.*;
import static Controller.ShowProductDetail.*;
import static org.junit.Assert.*;

public class OffAndProductMenuControllerTest {
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
    public void TestGetName() {
        initialise();
        ArrayList<String> productsName = new ArrayList<>();
        productsName.add("phone");
        productsName.add("plakolang");
        productsName.add("S9");
        productsName.add("yaghe7");
        productsName.add("poster");
        assertEquals(productsName, getName());
    }

    @Test
    public void TestGetCurrentName() {
        initialise();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(e);
        ArrayList<String> productsName = new ArrayList<>();
        productsName.add("phone");
        productsName.add("S9");
        productsName.add("poster");
        assertEquals(productsName, getCurrentName());
    }

    @Test
    public void TestGetCurrentId() {
        // integer midadam ghabool nemikard
        initialise();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(e);
        ArrayList<Long> productsId = new ArrayList<>();
        productsId.add(Long.parseLong("1111"));
        productsId.add(Long.parseLong("3333"));
        productsId.add(Long.parseLong("5555"));
        assertEquals(productsId, getCurrentId());
    }

    @Test
    public void TestGetCurrentOffPercentage() {
        // difference of off discount code and discount percentage ????
        initialise();

        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(d);
        sortedOrFilteredProduct.add(e);
        ArrayList<Double> productsDiscount = new ArrayList<>();
        productsDiscount.add(0.65);
        productsDiscount.add(0.75);
        productsDiscount.add(0.0);
        productsDiscount.add(0.85);
        assertEquals(productsDiscount, getCurrentOffPercentage());
    }
    //// get price product chera injoorie?/

    @Test
    public void TestDoesCurrentOff() {
        initialise();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(d);
        sortedOrFilteredProduct.add(e);
        ArrayList<Boolean> productsOff = new ArrayList<>();
        productsOff.add(true);
        productsOff.add(true);
        productsOff.add(false);
        productsOff.add(true);
        assertEquals(productsOff, doesCurrentOff());
    }

    @Test
    public void TestGetAllAvailableFilters(){
        ArrayList<String> availableFilters = new ArrayList<String>();
        availableFilters.add("Filter by Brand -brand-");
        availableFilters.add("Filter by Price -minPrice- -maxPrice");
        availableFilters.add("Filter by Name -name-");
        availableFilters.add("Filter by Category -categoryName-");
        availableFilters.add("Filter by Off");
        availableFilters.add("Filter by Availability");
        assertEquals(availableFilters , getAllAvailableFilters());
    }

    @Test
    public void TestGetCurrentPrice(){
        initialise();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(b);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(d);
        sortedOrFilteredProduct.add(e);
        ArrayList<Double> priceExcepted=new ArrayList<Double>();
        priceExcepted.add( 1000.0);
        priceExcepted.add( 10.0);
        priceExcepted.add( 7000.0);
        priceExcepted.add( 5.0);
        priceExcepted.add( 500.0);
        assertEquals(priceExcepted,OffAndProductMenuController.getCurrentPrice());

    }

    @Test
    public void TestGetCategoriesName(){
        initialise();
        ArrayList<String> exceptedCategoriesName=new ArrayList<String>();
        exceptedCategoriesName.add("xx");
        exceptedCategoriesName.add("xxlx");
        Assert.assertEquals(exceptedCategoriesName,OffAndProductMenuController.getCategoriesName());
    }

    @Test
    public void TestGetAllAvailableSorting(){
        initialise();
        allAvailableSorting.add("view");
        allAvailableSorting.add("time");
        allAvailableSorting.add("score");
        ArrayList<String> exceptedSorting=new ArrayList<String>();
        exceptedSorting.add("view");
        exceptedSorting.add("time");
        exceptedSorting.add("score");
        Assert.assertEquals(exceptedSorting,OffAndProductMenuController.getAllAvailableSorting());
    }

    @Test
    public void TestGetAllSellerOfProductWithId(){
        initialise();
        ArrayList<String> excepted=new ArrayList<String>();
        excepted.add("amiriI");
        Assert.assertEquals(excepted,OffAndProductMenuController.getAllSellerOfProductWithId(1111));
    }

    @Test
    public void TestGetCurrentSort(){
        OffAndProductMenuController.sorting("time");
        Assert.assertEquals("Time",OffAndProductMenuController.getCurrentSort());
    }

    @Test
    public void TestSorting(){
        OffAndProductMenuController.sorting("time");
        Assert.assertEquals("Time",OffAndProductMenuController.getCurrentSort());
        OffAndProductMenuController.sorting("score");
        Assert.assertEquals("Score",OffAndProductMenuController.getCurrentSort());
        OffAndProductMenuController.sorting("view");
        Assert.assertEquals("View",OffAndProductMenuController.getCurrentSort());
    }

    @Test
    public void TestDisableSort(){
        OffAndProductMenuController.sorting("time");
        OffAndProductMenuController.disableSort();
        Assert.assertEquals("view",OffAndProductMenuController.getCurrentSort());
    }

    @Test
    public void TestAddCommentById(){
        initialise();
        OffAndProductMenuController.addCommentsById(1111,"title","content");
    }

    @Test
    public void TestAddToCartById(){
        initialise();
        OffAndProductMenuController.addToCartById(1111,true,null,1);
        OffAndProductMenuController.addToCartById(1111,false,"amiriI",1);
    }

    @Test
    public void TestCheckRemainCountForBuy(){
        initialise();
        OffAndProductMenuController.checkRemainCountForBuy(1111,null,1);
        OffAndProductMenuController.checkRemainCountForBuy(1111,"amiri77",1);
        OffAndProductMenuController.checkRemainCountForBuy(1111,"amiri77",10000);
    }

    @Test
    public void TestIsCurrentUserGuestOrUser(){
        Assert.assertTrue(OffAndProductMenuController.isCurrentUserGuestOrUser());
        Controller.currentUser=seller1;
        Assert.assertFalse(OffAndProductMenuController.isCurrentUserGuestOrUser());
    }

    @Test
    public void TestCheckSortingInput(){
        Assert.assertTrue(OffAndProductMenuController.checkSortingInput("View"));
        Assert.assertFalse(OffAndProductMenuController.checkSortingInput("Viewww"));
    }

    @Test
    public void TestCheckFilteringByBrand(){
        initialise();
        Assert.assertTrue(OffAndProductMenuController.checkFilteringByBrand("apple"));
        Assert.assertFalse(OffAndProductMenuController.checkFilteringByBrand("bennnz"));
    }

    @Test
    public void TestCheckFilteringByCategory(){
        initialise();
        Assert.assertFalse(OffAndProductMenuController.checkFilteringByCategory("asdas"));
        Assert.assertTrue(OffAndProductMenuController.checkFilteringByCategory("xx"));
    }

    @Test
    public void TestIsProductWithId(){
        Assert.assertFalse(OffAndProductMenuController.isProductWithId(1111));
        initialise();
        Assert.assertTrue(OffAndProductMenuController.isProductWithId(1111));
        Assert.assertFalse(OffAndProductMenuController.isProductWithId(11131));
    }

    @Test
    public void TestIsSellerWithNameForProduct(){
        initialise();
        Assert.assertTrue(OffAndProductMenuController.isSellerWithNameForProduct(1111,"amiriI"));
        Assert.assertFalse(OffAndProductMenuController.isSellerWithNameForProduct(1111,"asd"));
    }

    @Test
    public void TestClearAndRestoreProduct(){
        initialise();
        OffAndProductMenuController.clearAndRestoreProduct();
        Assert.assertEquals(sortedOrFilteredProduct,allProducts);
    }

    @Test
    public void TestIncreaseView(){
        initialise();
        OffAndProductMenuController.increaseView(1111);
        Assert.assertEquals(1,a.getNumberOfView());
    }
}
