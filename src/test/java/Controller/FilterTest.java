package Controller;

import Models.Category;
import Models.Product;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


import static Controller.Filter.*;
import static Controller.DataBase.*;
import static Controller.Sort.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FilterTest {
    static ArrayList<Product> temp = new ArrayList<>();
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

    public void initialise() {
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

        a.setDoesItHaveDiscount(true);
        b.setDoesItHaveDiscount(true);
        c.setDoesItHaveDiscount(true);
    }

    public void addProductsToList(ArrayList<Product> list) {
        list.clear();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
    }


    @Test
    public void TestIsItFilteredByName() {
        setIsItFilteredByName(true);
        Assert.assertTrue(isIsItFilteredByName());
    }

    @Test
    public void TestIsItFilteredByBrand() {
        setIsItFilteredByBrand(true);
        Assert.assertTrue(isIsItFilteredByBrand());
    }

    @Test
    public void TestIsItFilteredByAvailability() {
        setIsItFilteredByAvailability(false);
        Assert.assertFalse(isIsItFilteredByAvailability());
    }

    @Test
    public void TestIsItFilteredByCategory() {
        setIsItFilteredByCategory(false);
        Assert.assertFalse(isIsItFilteredByCategory());
    }

    @Test
    public void TestIsItFilteredByOffs() {
        setIsItFilteredByOffs(true);
        Assert.assertTrue(isIsItFilteredByOffs());
    }

    @Test
    public void TestIsItFilteredByPrice() {
        setIsItFilteredByPrice(false);
        Assert.assertFalse(isIsItFilteredByPrice());
    }

    @Test
    public void TestSelectedBrands() {
        restartFilters();
        addBrand("adidas");
        addBrand("nike");
        addBrand("apple");
        addBrand("Apple");
        addBrand("LG");
        ArrayList<String> selectedBrands = new ArrayList<>();
        selectedBrands.add("adidas");
        selectedBrands.add("nike");
        selectedBrands.add("apple");
        selectedBrands.add("LG");
        assertEquals(selectedBrands, getSelectedBrands());
    }

    @Test
    public void TestGetName() {
        setName("alireza");
        assertEquals("alireza", getName());
    }

    @Test
    public void TestGetMinPrice() {
        setMinPrice(1000);
        assertEquals(1000, getMinPrice(), 0.00001);
    }

    @Test
    public void TestGetMaxPrice() {
        setMaxPrice(2000);
        assertEquals(2000, getMaxPrice(), 0.00001);
    }

    @Test
    public void TestGetCategoryName() {
        setCategoryName("alireza");
        assertEquals("alireza", getCategoryName());
    }

    @Test
    public void TestGetAvailableBrands() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        sortedOrFilteredProduct.remove(a);
        sortedOrFilteredProduct.remove(b);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("samsung");
        expected.add("nikooTanPoosh");
        expected.add("LMV");
        updateAvailableBrands();
        assertEquals(expected, getAvailableBrands());

    }

    @Test
    public void TestFilterByName() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        assertEquals(5, sortedOrFilteredProduct.size());
        filterByName("P");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(e);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        filterByName("asdf");
        expected.clear();
        assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(b);
        expected.add(a);
        expected.add(b);
        filterByName("P");
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByCategory() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByCategory("mobile");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        expected.add(a);
        expected.add(c);
        filterByCategory("MOBILE");
        assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        addProductsToList(sortedOrFilteredProduct);
        filterByCategory("xx");
        expected.add(a);
        expected.add(c);
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByPrice() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByPrice(200.0, 1100.0);
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(e);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByPrice(100.0, 200.0);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        filterByPrice(100000.0, 2000000.0);
        assertEquals(expected, sortedOrFilteredProduct);
        addProductsToList(sortedOrFilteredProduct);
        filterByPrice(0.0, 100000.0);
        addProductsToList(expected);
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByAvailability() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByAvailability();
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        expected.add(d);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByAvailability();
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(d);
        expected.add(a);
        expected.add(c);
        expected.add(d);
        filterByAvailability();
        assertEquals(expected, sortedOrFilteredProduct);

    }

    @Test
    public void TestFilterByOffs() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByOffs();
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByOffs();
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(b);
        sortedOrFilteredProduct.add(c);
        expected.add(a);
        expected.add(b);
        expected.add(c);
        filterByOffs();
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByBrands() {
        initialise();
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        getSelectedBrands().clear();
        addBrand("lmv");
        addBrand("SamSung");
        ArrayList<String> brandsExpected = new ArrayList<>();
        brandsExpected.add("lmv");
        brandsExpected.add("SamSung");
        assertEquals(brandsExpected, getSelectedBrands());
        filterByBrand("IRan");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(b);
        expected.add(c);
        expected.add(e);
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByBrand("m");
        assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        getSelectedBrands().clear();
        addBrand("aPPLE");
        filterByBrand("apple");
        expected.add(a);
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void testGetAvailableFilters() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Filter by Brand -brand-");
        expected.add("Filter by Price -minPrice- -maxPrice");
        expected.add("Filter by Name -name-");
        expected.add("Filter by Category -categoryName-");
        expected.add("Filter by Off");
        expected.add("Filter by Availability");
        assertEquals(expected, getAvailableFilters());
    }

    @Test
    public void testShowCurrentFilters() {
        setIsItFilteredByAvailability(true);
        setIsItFilteredByOffs(true);
        setIsItFilteredByName(true);
        setIsItFilteredByPrice(true);
        setIsItFilteredByCategory(true);
        setIsItFilteredByBrand(true);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Availability filter is on");
        expected.add("Brand filter is on");
        expected.add("Category filter is on");
        expected.add("Name filter is on");
        expected.add("Price filter is on");
        expected.add("Off filter is on");
        assertEquals(expected, showCurrentFilters());
        restartFilters();
        expected.clear();
        expected.add("there are no filters on");
        assertEquals(expected, showCurrentFilters());
    }

    @Test
    public void TestDisableNameFilter() {
        initialise();
        disableNameFilter();
        assertFalse(isIsItFilteredByName());
        assertEquals("", getName());
    }

    @Test
    public void TestDisableOffsFilter() {
        initialise();
        disableOffsFilter();
        Assert.assertFalse(isIsItFilteredByOffs());
    }

    @Test
    public void TestDisableCategoryFilter() {
        initialise();
        disableCategoryFilter();
        Assert.assertFalse(isIsItFilteredByCategory());
        assertEquals("", getCategoryName());
    }

    @Test
    public void TestDisablePriceFilter() {
        initialise();
        disablePriceFilter();
        Assert.assertFalse(isIsItFilteredByPrice());
        assertEquals(-1.0, getMinPrice(), 0.0001);
        assertEquals(-1.0, getMaxPrice(), 0.0001);
    }

    @Test
    public void TestDisableAvailabilityFilter() {
        initialise();
        disableAvailabilityFilter();
        assertFalse(isIsItFilteredByAvailability());
    }

    @Test
    public void TestDisableBrandFilter() {
        initialise();
        ArrayList<String> expected = new ArrayList<>();
        getSelectedBrands().clear();
        disableBrandFilter("apple");
        assertEquals(expected, getSelectedBrands());
        addBrand("apple");
        addBrand("apple");
        addBrand("iran");
        disableBrandFilter("USA");
        expected.add("apple");
        expected.add("iran");
        assertEquals(expected, getSelectedBrands());
        disableBrandFilter("apple");
        expected.remove("apple");
        assertEquals(expected, getSelectedBrands());
    }

    @Test
    public void TestFilter() {
        initialise();
        sortedOrFilteredProduct.clear();
        restartFilters();
        setIsItFilteredByBrand(true);
        setIsItFilteredByName(true);
        setIsItFilteredByCategory(true);
        setIsItFilteredByAvailability(true);
        setIsItFilteredByOffs(true);
        setIsItFilteredByPrice(true);
        addBrand("adidas");
        filter();
        ArrayList<Product> expected = new ArrayList<>();
        assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestShowSubCategories() {
        initialise();
        addProductsToList(sortedOrFilteredProduct);
        setCategoryName("");
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        expected.add(mother.getName());
        expected.add(father.getName());
        actual = showSubCategories();
        assertEquals(expected, actual);
        expected.clear();
        expected.add("child");
        setCategoryName("bmw");
        actual = showSubCategories();
        assertEquals(expected, actual);
    }

}
