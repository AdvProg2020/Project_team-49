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
        Product a = new Product();
        for (long i = 0; i < 10000000; i++) ;
        Product b = new Product();
        for (long i = 0; i < 10000000; i++) ;
        Product c = new Product();
        for (long i = 0; i < 10000000; i++) ;
        Product d = new Product();
        for (long i = 0; i < 10000000; i++) ;
        Product e = new Product();
        for (long i = 0; i < 10000000; i++) ;
        Product f = new Product();
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
        Product a = new Product(1000);
        Product b = new Product(500);
        Product c = new Product(200);
        Product d = new Product(100);
        Product e = new Product(90);
        Product f = new Product(10);
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
        Product a = new Product(4.99);
        Product b = new Product(4.80);
        Product c = new Product(4.82);
        Product d = new Product(4.7);
        Product e = new Product(4.6);
        Product f = new Product(4.6);
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


