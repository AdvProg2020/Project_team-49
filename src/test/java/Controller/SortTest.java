package Controller;

import Models.Product;
import org.junit.Assert;
import org.junit.Test;

import Models.Category;
import Models.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static Controller.Sort.*;

import java.util.ArrayList;

import static Controller.Sort.*;
import static Controller.Sort.getAvailableSorts;

public class SortTest {

    @Test
    public void testSortByTime() {
        setCurrentSort("time");
        Product a = new Product("phone", 1112, "apple", 1000.0, "nothing", null, null, 1000);
        for (long i = 0; i < 100000000; i++) ;
        Product b = new Product("plakolang", 11212, "iran", 10.0, "nothing", null, null, 0);
        for (long i = 0; i < 100000000; i++) ;
        Product c = new Product("S9", 1112, "samsung", 7000.0, "nothing", null, null, 1000);
        for (long i = 0; i < 100000000; i++) ;
        Product d = new Product("yaghe7", 1112, "nikooTanPoosh", 5.0, "nothing", null, null, 1000);
        for (long i = 0; i < 100000000; i++) ;
        Product e = new Product("poster", 1112, "LMV", 500.0, "nothing", null, null, 0);
        for (long i = 0; i < 100000000; i++) ;
        Product f = new Product("towelr", 152, "LllV", 2500.0, "nothing", null, null, 0);
        DataBase.allProducts.clear();
        DataBase.sortedOrFilteredProduct.clear();
        DataBase.allProducts.add(a);
        DataBase.allProducts.add(b);
        DataBase.allProducts.add(c);
        DataBase.allProducts.add(d);
        DataBase.allProducts.add(e);
        DataBase.allProducts.add(f);
        DataBase.sortedOrFilteredProduct.addAll(DataBase.allProducts);
        sort();
        ArrayList<Product> actual = DataBase.sortedOrFilteredProduct;
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(f);
        expected.add(e);
        expected.add(d);
        expected.add(c);
        expected.add(b);
        expected.add(a);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortByViews() {
        setCurrentSort("view");
        Product a = new Product("phone", 1112, "apple", 1000.0, "nothing", null, null, 1000);
        Product b = new Product("plakolang", 11212, "iran", 10.0, "nothing", null, null, 0);
        Product c = new Product("S9", 1112, "samsung", 7000.0, "nothing", null, null, 1000);
        Product d = new Product("yaghe7", 1112, "nikooTanPoosh", 5.0, "nothing", null, null, 1000);
        Product e = new Product("poster", 1112, "LMV", 500.0, "nothing", null, null, 0);
        Product f = new Product("towelr", 152, "LllV", 2500.0, "nothing", null, null, 0);
        a.setNumberOfView(1000);
        b.setNumberOfView(500);
        c.setNumberOfView(200);
        d.setNumberOfView(100);
        e.setNumberOfView(90);
        f.setNumberOfView(90);
        DataBase.allProducts.clear();
        DataBase.sortedOrFilteredProduct.clear();
        DataBase.allProducts.add(a);
        DataBase.allProducts.add(b);
        DataBase.allProducts.add(c);
        DataBase.allProducts.add(d);
        DataBase.allProducts.add(e);
        DataBase.allProducts.add(f);
        DataBase.sortedOrFilteredProduct.addAll(DataBase.allProducts);
        sort();
        ArrayList<Product> actual = DataBase.sortedOrFilteredProduct;
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        expected.add(d);
        expected.add(e);
        expected.add(f);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortByScore() {
        setCurrentSort("score");
        Product a = new Product("phone", 1112, "apple", 1000.0, "nothing", null, null, 1000);
        Product b = new Product("plakolang", 11212, "iran", 10.0, "nothing", null, null, 0);
        Product c = new Product("S9", 1112, "samsung", 7000.0, "nothing", null, null, 1000);
        Product d = new Product("yaghe7", 1112, "nikooTanPoosh", 5.0, "nothing", null, null, 1000);
        Product e = new Product("poster", 1112, "LMV", 500.0, "nothing", null, null, 0);
        Product f = new Product("towelr", 152, "LllV", 2500.0, "nothing", null, null, 0);
        a.setAverageScore(4.99);
        b.setAverageScore(4.8);
        c.setAverageScore(4.82);
        d.setAverageScore(4.7);
        e.setAverageScore(4.6);
        f.setAverageScore(4.6);
        DataBase.allProducts.clear();
        DataBase.sortedOrFilteredProduct.clear();
        DataBase.allProducts.add(a);
        DataBase.allProducts.add(b);
        DataBase.allProducts.add(c);
        DataBase.allProducts.add(d);
        DataBase.allProducts.add(e);
        DataBase.allProducts.add(f);
        DataBase.sortedOrFilteredProduct.addAll(DataBase.allProducts);
        sort();
        ArrayList<Product> actual = DataBase.sortedOrFilteredProduct;
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        expected.add(b);
        expected.add(d);
        expected.add(e);
        expected.add(f);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentSort() {
        setCurrentSort("ali");
        String actual = getCurrentSort();
        Assert.assertEquals("ali", actual);
    }

    @Test
    public void testDisableSort() {
        disableSort();
        Assert.assertEquals("view", getCurrentSort());

    }

    @Test
    public void testGetAvailableSorts() {
        ArrayList<String> actual = new ArrayList<>();
        actual.add("sort by time");
        actual.add("sort by score");
        actual.add("sort by view");
        ArrayList<String> expected = getAvailableSorts();
        Assert.assertEquals(expected, actual);
    }
}


