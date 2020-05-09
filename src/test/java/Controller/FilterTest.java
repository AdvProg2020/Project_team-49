package Controller;

import Models.Category;
import Models.Product;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;


import static Controller.Filter.*;
import static Controller.DataBase.*;
import static Controller.Sort.*;

public class FilterTest {
    static ArrayList<Product> temp = new ArrayList<>();
    Category mother = new Category("xx", null , null);
    Category father = new Category("xxlx", null , null);
    Category phone = new Category("mobile", "" , null);
    Category makeup = new Category("aaaa", "",  null);
    Category shirt = new Category("nike", "" , null);
    Category car = new Category("bmw", "" , null);
    Category child = new Category("child" , null , null);
    final Product a = new Product("phone", 1112, "apple", 1000.0, "nothing", phone, null, 1000);
    final Product b = new Product("plakolang", 11212, "iran", 10.0, "nothing", car, null, 0);
    final Product c = new Product("S9", 1112, "samsung", 7000.0, "nothing", phone, null, 1000);
    final Product d = new Product("yaghe7", 1112, "nikooTanPoosh", 5.0, "nothing", shirt, null, 1000);
    final Product e = new Product("poster", 1112, "LMV", 500.0, "nothing", makeup, null, 0);

    public void addProductsToList(ArrayList<Product> list) {
        father.setParentCategory(null);
        mother.setParentCategory(null);
        phone.setParentCategory(mother);
        makeup.setParentCategory(father);
        shirt.setParentCategory(father);
        car.setParentCategory(father);
        father.addSubCategory(makeup);
        father.addSubCategory(shirt);
        father.addSubCategory(car);
        mother.addSubCategory(phone);
        car.addSubCategory(child);
        child.setParentCategory(car);
        allCategories.add(phone);
        allCategories.add(makeup);
        allCategories.add(mother);
        allCategories.add(car);
        allCategories.add(shirt);
        allCategories.add(father);
        a.setDoesItHaveDiscount(true);
        b.setDoesItHaveDiscount(true);
        c.setDoesItHaveDiscount(true);
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
        Assert.assertEquals(true, isIsItFilteredByName());
    }

    @Test
    public void TestIsItFilteredByBrand() {
        setIsItFilteredByBrand(true);
        Assert.assertEquals(true, isIsItFilteredByBrand());
    }

    @Test
    public void TestIsItFilteredByAvailability() {
        setIsItFilteredByAvailability(false);
        Assert.assertEquals(false, isIsItFilteredByAvailability());
    }

    @Test
    public void TestIsItFilteredByCategory() {
        setIsItFilteredByCategory(false);
        Assert.assertEquals(false, isIsItFilteredByCategory());
    }

    @Test
    public void TestIsItFilteredByOffs() {
        setIsItFilteredByOffs(true);
        Assert.assertEquals(true, isIsItFilteredByOffs());
    }

    @Test
    public void TestIsItFilteredByPrice() {
        setIsItFilteredByPrice(false);
        Assert.assertEquals(false, isIsItFilteredByPrice());
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
        Assert.assertEquals(selectedBrands, getSelectedBrands());
    }

    @Test
    public void TestGetName() {
        setName("alireza");
        Assert.assertEquals("alireza", getName());
    }

    @Test
    public void TestGetMinPrice() {
        setMinPrice(1000);
        Assert.assertEquals(1000, getMinPrice(), 0.00001);
    }

    @Test
    public void TestGetMaxPrice() {
        setMaxPrice(2000);
        Assert.assertEquals(2000, getMaxPrice(), 0.00001);
    }

    @Test
    public void TestGetCategoryName() {
        setCategoryName("alireza");
        Assert.assertEquals("alireza", getCategoryName());
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
        Assert.assertEquals(expected, getAvailableBrands());

    }

    @Test
    public void TestFilterByName() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByName("p");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(e);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        filterByName("asdf");
        expected.clear();
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(b);
        expected.add(a);
        expected.add(b);
        filterByName("P");
        Assert.assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByCategory() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByCategory("mobile");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        expected.add(a);
        expected.add(c);
        filterByCategory("MOBILE");
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        expected.clear();
        sortedOrFilteredProduct.clear();
        addProductsToList(sortedOrFilteredProduct);
        filterByCategory("xx");
        expected.add(a);
        expected.add(c);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByPrice() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByPrice(200.0, 1100.0);
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(e);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByPrice(100.0, 200.0);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        filterByPrice(100000.0, 2000000.0);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        addProductsToList(sortedOrFilteredProduct);
        filterByPrice(0.0, 100000.0);
        addProductsToList(expected);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByAvailability() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByAvailability();
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        expected.add(d);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByAvailability();
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(c);
        sortedOrFilteredProduct.add(d);
        expected.add(a);
        expected.add(c);
        expected.add(d);
        filterByAvailability();
        Assert.assertEquals(expected, sortedOrFilteredProduct);

    }

    @Test
    public void TestFilterByOffs() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        filterByOffs();
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByOffs();
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        sortedOrFilteredProduct.add(b);
        sortedOrFilteredProduct.add(c);
        expected.add(a);
        expected.add(b);
        expected.add(c);
        filterByOffs();
        Assert.assertEquals(expected, sortedOrFilteredProduct);
    }

    @Test
    public void TestFilterByBrands() {
        addProductsToList(allProducts);
        addProductsToList(sortedOrFilteredProduct);
        getSelectedBrands().clear();
        addBrand("lmv");
        addBrand("SamSung");
        ArrayList<String> brandsExpected = new ArrayList<>();
        brandsExpected.add("lmv");
        brandsExpected.add("SamSung");
        Assert.assertEquals(brandsExpected, getSelectedBrands());
        filterByBrand("IRan");
        ArrayList<Product> expected = new ArrayList<>();
        expected.add(b);
        expected.add(c);
        expected.add(e);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.clear();
        expected.clear();
        filterByBrand("m");
        Assert.assertEquals(expected, sortedOrFilteredProduct);
        sortedOrFilteredProduct.add(a);
        getSelectedBrands().clear();
        addBrand("aPPLE");
        filterByBrand("apple");
        expected.add(a);
        Assert.assertEquals(expected, sortedOrFilteredProduct);
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
        Assert.assertEquals(expected, getAvailableFilters());
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
        Assert.assertEquals(expected, showCurrentFilters());
        restartFilters();
        expected.clear();
        expected.add("there are no filters on");
        Assert.assertEquals(expected, showCurrentFilters());
    }

    @Test
    public void TestDisableNameFilter() {
        disableNameFilter();
        Assert.assertEquals(false, isIsItFilteredByName());
        Assert.assertEquals("", getName());
    }

    @Test
    public void TestDisableOffsFilter() {
        disableOffsFilter();
        Assert.assertEquals(false, isIsItFilteredByOffs());
    }

    @Test
    public void TestDisableCategoryFilter() {
        disableCategoryFilter();
        Assert.assertEquals(false, isIsItFilteredByCategory());
        Assert.assertEquals("", getCategoryName());
    }

    @Test
    public void TestDisablePriceFilter() {
        disablePriceFilter();
        Assert.assertEquals(false, isIsItFilteredByPrice());
        Assert.assertEquals(-1.0, getMinPrice(), 0.0001);
        Assert.assertEquals(-1.0, getMaxPrice(), 0.0001);
    }

    @Test
    public void TestDisableAvailabilityFilter() {
        disableAvailabilityFilter();
        Assert.assertEquals(false, isIsItFilteredByAvailability());
    }

    @Test
    public void TestDisableBrandFilter() {
        ArrayList<String> expected = new ArrayList<>();
        getSelectedBrands().clear();
        disableBrandFilter("apple");
        Assert.assertEquals(expected, getSelectedBrands());
        addBrand("apple");
        addBrand("apple");
        addBrand("iran");
        disableBrandFilter("USA");
        expected.add("apple");
        expected.add("iran");
        Assert.assertEquals(expected, getSelectedBrands());
        disableBrandFilter("apple");
        expected.remove("apple");
        Assert.assertEquals(expected, getSelectedBrands());
    }

    @Test
    public void TestFilter() {
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
        Assert.assertEquals(expected , sortedOrFilteredProduct);
    }

    @Test
    public void TestShowSubCategories() {
        addProductsToList(sortedOrFilteredProduct);
        setCategoryName("");
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        expected.add("xx");
        expected.add("xxlx");
        actual = showSubCategories();
        Assert.assertEquals(expected ,actual );
        expected.clear();
        expected.add("child");
        setCategoryName("bmw");
        actual = showSubCategories();
        Assert.assertEquals(expected , actual);
    }

}
