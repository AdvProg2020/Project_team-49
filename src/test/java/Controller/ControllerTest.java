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
    static Product a = new Product("phone", "apple", 1000.0, "nothing", phone, null, 1000);
    static Product b = new Product("plakolang", "iran", 10.0, "nothing", car, null, 0);
    static Product c = new Product("S9", "samsung", 7000.0, "nothing", phone, null, 1000);
    static Product d = new Product("yaghe7", "nikooTanPoosh", 5.0, "nothing", shirt, null, 1000);
    static Product e = new Product("poster", "LMV", 500.0, "nothing", makeup, null, 0);
    static Costumer costumer1 = new Costumer("alim1379", "ali", "mehrabani", "alim1379@gmail.com", 1288888, "alialiali", 0);
    static Costumer costumer2 = new Costumer("alireza_hr", "alireza", "heidari", "alireza@gmail.com", 1777, "alirezaalireza", 0);
    static Costumer costumer3 = new Costumer("hamid_Tala", "hamid", "yaghobi", "yaghobi@gmail.com", 12338, "hamidhamid", 0);
    static Manager manager1 = new Manager("izadiii", "mohammad", "izadi", "izadi@gmail.com", 12, "izadizad");
    static Manager manager2 = new Manager("AbaM", "jj", "abam", "abam@gmail.com", 13, "abamiad");
    static Seller seller1 = new Seller("amiri77", "amir", "amiri", "amiri@gmail.com", 222, "sdfsafa", "nike");

    public void initialise() {
        seller1.setCredit(320.2);
        costumer1.setCredit(22.1);
    }


    @Test
    public void TestEditField() {
        //?????????????????????????????
        //?????????????????????????????
        //?????????????????????????????
        currentUser = new Guest();
        editField("alireza", "heidari");
        assertEquals("heidari", currentUser.getFirstName());
        assertEquals("heidari", currentUser.getLastName());
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
        // we should edit return content of this method
        String type = "costumer";
        ArrayList<String> infos = new ArrayList<>();
        infos.add("alirezahr79");
        infos.add("aa a  a");
        infos.add("alireza");
        infos.add("heidari");
        infos.add("heidari@gmail.com");
        infos.add("8dd55");
        assertEquals("invalid password", createAccount(infos, type));
        infos.set(1, "aaa");
        assertEquals("invalid phone number", createAccount(infos, type));
        infos.set(5, "4332434");
        assertEquals("account created", createAccount(infos, type));
        type = "manager";
        assertEquals("account created", createAccount(infos, type));
        type = "seller";
        infos.add("nike");
        assertEquals("account created", createAccount(infos, type));
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
        assertEquals(22.1 , getBalance() , .001);
        setCurrentUser(seller1);
        assertEquals(320.2 , getBalance() , .001);
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
}


