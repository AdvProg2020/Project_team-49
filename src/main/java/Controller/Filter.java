package Controller;

import Models.Product;
import org.graalvm.compiler.code.DataSection;

import java.util.ArrayList;

public class Filter {

    private static boolean isItFilteredByName = false;
    private static boolean isItFilteredByCategory = false;
    private static boolean isItFilteredByPrice = false;
    private static boolean isItFilteredByAvailability = false;
    private static boolean isItFilteredByBrand = false;
    private static boolean isItFilteredByOffs = false;
    private static ArrayList<String> selectedBrands = new ArrayList<String>();
    private static int minPrice = -1;
    private static int maxPrice = -1;
    private static String name = "";
    private static String categoryName = "";

    public static boolean isIsItFilteredByName() {
        return isItFilteredByName;
    }

    public static boolean isIsItFilteredByCategory() {
        return isItFilteredByCategory;
    }

    public static boolean isIsItFilteredByPrice() {
        return isItFilteredByPrice;
    }

    public static boolean isIsItFilteredByAvailability() {
        return isItFilteredByAvailability;
    }

    public static boolean isIsItFilteredByBrand() {
        return isItFilteredByBrand;
    }

    public static boolean isIsItFilteredByOffs() {
        return isItFilteredByOffs;
    }

    public static ArrayList<String> getSelectedBrands() {
        return selectedBrands;
    }

    public static int getMinPrice() {
        return minPrice;
    }

    public static int getMaxPrice() {
        return maxPrice;
    }

    public static String getName() {
        return name;
    }

    public static String getCategoryName() {
        return categoryName;
    }

    public static void setIsItFilteredByName(boolean isItFilteredByName) {
        Filter.isItFilteredByName = isItFilteredByName;
    }

    public static void setIsItFilteredByCategory(boolean isItFilteredByCategory) {
        Filter.isItFilteredByCategory = isItFilteredByCategory;
    }

    public static void setIsItFilteredByPrice(boolean isItFilteredByPrice) {
        Filter.isItFilteredByPrice = isItFilteredByPrice;
    }

    public static void setIsItFilteredByAvailability(boolean isItFilteredByAvailability) {
        Filter.isItFilteredByAvailability = isItFilteredByAvailability;
    }

    public static void setIsItFilteredByBrand(boolean isItFilteredByBrand) {
        Filter.isItFilteredByBrand = isItFilteredByBrand;
    }

    public static void setIsItFilteredByOffs(boolean isItFilteredByOffs) {
        Filter.isItFilteredByOffs = isItFilteredByOffs;
    }

    public static void setMinPrice(int minPrice) {
        Filter.minPrice = minPrice;
    }

    public static void setMaxPrice(int maxPrice) {
        Filter.maxPrice = maxPrice;
    }

    public static void setName(String name) {
        Filter.name = name;
    }

    public static void setCategoryName(String categoryName) {
        Filter.categoryName = categoryName;
    }

    public static void addBrand(String brand){
        for (String selectedBrand : selectedBrands) {
            if(selectedBrand.toLowerCase().equals(brand.toLowerCase())){
                return;
            }
        }
        selectedBrands.add(brand);
    }

    public static void filterByName() {
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;

        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if(!product.getName().toLowerCase().startsWith(name.toLowerCase())){
                temp.add(product);
            }
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static void filterByCategory() {
        if(DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if(!product.getParentCategory().getName().toLowerCase().startsWith(categoryName.toLowerCase())){
                temp.add(product);
            }
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static void filterByPrice() {
        if(DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if(!(product.getPrice() >= minPrice && product.getPrice() <= maxPrice)){
                temp.add(product);
            }
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static void filterByAvailability() {
        if(DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if((product.getRemainedNumber() == 0)){
                temp.add(product);
            }
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static void filterByBrand(String brandName) {
        if(DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        main:
        for (Product product : DataBase.sortedOrFilteredProduct) {
            for (String brand : product.getAllBrands()) {
                if((brand.toLowerCase().equals(brandName.toLowerCase()))){
                    continue main;
                }
            }
            temp.add(product);
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static void filterByOffs() {
        if(DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if(!(product.getDoesItHaveDiscount())){
                temp.add(product);
            }
        }
        if(temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        return;
    }

    public static ArrayList<String> showAvailableFilters() {
        ArrayList<String> allAvailableFilters = new ArrayList<String>();
        allAvailableFilters.add("Filter by Brand -brand-");
        allAvailableFilters.add("Filter by Price -minPrice- -maxPrice");
        allAvailableFilters.add("Filter by Name -name-");
        allAvailableFilters.add("Filter by Category -categoryName-");
        allAvailableFilters.add("Filter by Off");
        allAvailableFilters.add("Filter by Availability");
        return allAvailableFilters;
    }

    public static ArrayList<String> showCurrentFilters() {
        ArrayList<String> currentFilters = new ArrayList<String>();
        if(isItFilteredByAvailability)
            currentFilters.add("Availability filter is on");
        if(isItFilteredByBrand)
            currentFilters.add("Brand filter is on");
        if(isItFilteredByCategory)
            currentFilters.add("Category filter is on");
        if(isItFilteredByName)
            currentFilters.add("Name filter is on");
        if(isItFilteredByPrice)
            currentFilters.add("Price filter is on");
        if(isItFilteredByOffs)
            currentFilters.add("Off filter is on");
        if(currentFilters.size() == 0){
            currentFilters.add("there are no filters on");
        }
        return currentFilters;
    }

    public static void disableNameFilter() {
        name = "";
        isItFilteredByName = false;
    }

    public static void disableOffsFilter() {
        isItFilteredByOffs = false;
    }

    public static void disablePriceFilter() {
        minPrice = -1;
        maxPrice = -1;
        isItFilteredByPrice = false;
    }

    public static void disableCategoryFilter() {
        categoryName = "";
        isItFilteredByCategory = false;
    }

    public static void disableAvailabilityFilter() {
        isItFilteredByAvailability = false;
    }

    public static void disableBrandFilter(String brand) {
        selectedBrands.remove(brand);
        if(selectedBrands.size() == 0){
            isItFilteredByBrand = false;
        }
    }

    public static void restartFilters() {
        isItFilteredByBrand = false;
        isItFilteredByAvailability = false;
        isItFilteredByCategory = false;
        isItFilteredByPrice = false;
        isItFilteredByOffs = false;
        isItFilteredByBrand = false;
        isItFilteredByName = false;
        name = "";
        categoryName=  "";
        minPrice = -1;
        maxPrice = -1;
        selectedBrands.clear();
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        Sort.sort();
    }

    public static boolean canDisableNameFilter() {

        return false;
    }

    public static boolean canDisableOffsFilter() {
        return false;
    }

    public static boolean canDisablePriceFilter() {
        return false;
    }

    public static boolean canDisableCategoryFilter() {
        return false;
    }

    public static boolean canDisableAvailabilityFilter() {
        return false;
    }

    public static boolean canDisableBrandFilter() {
        return false;
    }

    public static void filter(){
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        if(isItFilteredByName)
            filterByName();
        if(isItFilteredByBrand){
            for (String selectedBrand : selectedBrands) {
                filterByBrand(selectedBrand);
            }
        }
        if(isItFilteredByOffs)
            filterByOffs();
        if(isItFilteredByPrice)
            filterByPrice();
        if(isItFilteredByCategory)
            filterByCategory();
        if(isItFilteredByAvailability)
            filterByAvailability();
    }

    public static boolean isItFilter(String filterName){
        return false;
    }

    public static void findProductByName(String productName){
         //
    }

}