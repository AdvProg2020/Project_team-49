package Controller;

import Models.Category;
import Models.Log.SellLog;
import Models.Off;
import Models.Product;
import Models.User.Costumer;
import Models.User.Manager;
import Models.User.Seller;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static Controller.Controller.currentUser;
import static Controller.DataBase.*;
import static Controller.DataBase.allCategories;
import static Controller.SellerAreaController.*;
import static Models.OffStatus.confirmed;
import static org.junit.Assert.*;

public class SellerAreaControllerTest {
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "amiriI");
    static Seller seller2 = new Seller("roozbeh", "amir", "amiri", "amiri@gmail.com", 3, "amiri", "rooZ");

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
    static Off off1 = new Off(allProducts, confirmed, null, null, 30);

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

        allCategories.add(phone);
        allCategories.add(makeup);
        allCategories.add(mother);
        allCategories.add(car);
        allCategories.add(shirt);
        allCategories.add(father);
        allCategories.add(child);
    }

    @Test
    public void TestViewProduct() {
        initialise();
        assertEquals("product not exist", viewProduct(33));

        a.setExplanation("good products!");
        assertEquals("phone,1,apple,good products!", viewProduct(1));
    }

    @Test
    public void TestShowOffs() {
        initialise();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        currentUser = seller1;
        seller1.addOff(off1);
        off1.setOffId(1);
        Date dateTest = new Date();
        dateTest.setTime(10);
        off1.setStartDate(dateTest);
        dateTest.setTime(20);
        off1.setEndDate(dateTest);
        ArrayList<String> offsTest = new ArrayList<>();
        offsTest.add("" + off1.getOffId() + "," + off1.getOffAmount() + "," + formatter.format(off1.getStartDate()) + "," + formatter.format(off1.getEndDate()));
        assertEquals(offsTest, showOffs());
    }

    @Test
    public void TestAddProduct() {
        currentUser = seller1;
        initialise();
        a.setExplanation("good products!");
        ArrayList<String> infoTest = new ArrayList<>();
        infoTest.add("adsf");
        infoTest.add("adsf");
        infoTest.add("sfe43");
        infoTest.add("adsf");
        infoTest.add("adsf");
        infoTest.add("sfe43");
        assertEquals("invalid price", addProduct(infoTest));
        infoTest.set(2, "1344");
        assertEquals("invalid category", addProduct(infoTest));
        infoTest.set(4, "xx");
        assertEquals("invalid number of items", addProduct(infoTest));
        infoTest.set(5, "23");
        assertEquals("request sent", addProduct(infoTest));
    }

    @Test
    public void TestEditProduct() {
        initialise();
        currentUser = seller1;
        a.setExplanation("adfasf(((#*($&R");
        assertEquals("product not exist", editProduct("asdf", "asdf", 222));
        assertEquals("request sent", editProduct("nAme", "asdfsd", 1));
        assertEquals("request sent", editProduct("braND", "asdfsd", 1));
        assertEquals("request sent", editProduct("Price", "320", 1));
        assertEquals("invalid new content", editProduct("Price", "32d0", 1));
        assertEquals("request sent", editProduct("exPlanation", "320", 1));
    }

    @Test
    public void TestSellAndBuyLogs() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        initialise();
        Date d = new Date();
        d.setTime(20);
        currentUser = seller1;
        ArrayList<Product> soldProducts = new ArrayList<>();
        soldProducts.add(a);
        SellLog sellLog1 = new SellLog(100, 0, soldProducts, "amiri77", 2, d);
        seller1.addSellLog(sellLog1);
        assertEquals("product not exist", viewProductBuyers(22));
        assertEquals("amiri77, ", viewProductBuyers(1));
        ArrayList<String> logsTest = new ArrayList<>();
        logsTest.add("2," + formatter.format(sellLog1.getLogDate()) + ",amiri77,100.0,0.0");
        assertEquals(logsTest, viewSalesHistory());
        seller1.addProduct(b);
        b.setExplanation("good One");
        logsTest.clear();
        logsTest.add(b.getName() + "," + b.getProductId() + "," + b.getBrand() + "," + b.getPrice(seller1) + "," + b.getAverageScore() + "," + b.getExplanation());
        assertEquals(logsTest, viewSellerProducts());
        assertEquals("amiriI", viewCompanyInfo());
    }

    @Test
    public void TestRemoveProduct() {
        initialise();
        currentUser = seller1;
        assertEquals("product not exist", SellerAreaController.removeProduct(120L));
        assertEquals( "product removed", SellerAreaController.removeProduct(3L));

        d.addItem(seller2 , 100 ,2 );
        d.addItem(seller1 , 30 , 333);
        seller1.addProduct(d);
        seller2.addProduct(d);
        assertEquals( "product removed", SellerAreaController.removeProduct(4L));


    }
}
