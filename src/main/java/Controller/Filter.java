package Controller;

import Models.Product;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Filter {

    private static boolean isItFilteredByName = false;
    private static boolean isItFilteredByCategory = false;
    private static boolean isItFilteredByPrice = false;
    private static boolean isItFilteredByAvailability = false;
    private static boolean isItFilteredByBrand = false;
    private static boolean isItFilteredByOffs = false;
    private static ArrayList<String> selectedBrands = new ArrayList<String>();
    private static double minPrice = -1;
    private static double maxPrice = -1;
    private static String name = "";
    private static String categoryName = "";
    private static ArrayList<String> availableBrands = new ArrayList<>();

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

    public static double getMinPrice() {
        return minPrice;
    }

    public static double getMaxPrice() {
        return maxPrice;
    }

    public static String getName() {
        return name;
    }

    public static ArrayList<String> getAvailableBrands() {
        return availableBrands;
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

    public static void setMinPrice(double minPrice) {
        Filter.minPrice = minPrice;
    }

    public static void setMaxPrice(double maxPrice) {
        Filter.maxPrice = maxPrice;
    }

    public static void setName(String name) {
        Filter.name = name;
    }

    public static void setCategoryName(String categoryName) {
        Filter.categoryName = categoryName;
    }

    public static void addBrand(String brand) {
        for (String selectedBrand : selectedBrands) {
            if (selectedBrand.toLowerCase().equals(brand.toLowerCase())) {
                return;
            }
        }
        selectedBrands.add(brand);
    }

    public static void filterByName(String name) {
        setName(name);
        isItFilteredByName = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;

        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if (!product.getName().toLowerCase().startsWith(name.toLowerCase())) {
                temp.add(product);
            }
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
        return;
    }

    public static void filterByCategory(String categoryName) {
        setCategoryName(categoryName);
        isItFilteredByCategory = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if (!product.getParentCategory().getName().toLowerCase().startsWith(categoryName.toLowerCase())) {
                temp.add(product);
            }
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
        return;
    }

    public static void filterByPrice(double minPrice, double maxPrice) {
        setMaxPrice(maxPrice);
        setMinPrice(minPrice);
        isItFilteredByPrice = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if (!(product.getPrice() >= minPrice && product.getPrice() <= maxPrice)) {
                temp.add(product);
            }
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
        return;
    }

    public static void filterByAvailability() {
        isItFilteredByAvailability = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if ((product.remainingItems() == 0)) {
                temp.add(product);
            }
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
        return;
    }

    public static void filterByBrand(String brandName) {
        addBrand(brandName);
        isItFilteredByBrand = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        main:
        for (Product product : DataBase.sortedOrFilteredProduct) {
            for (String selectedBrand : selectedBrands) {
                if (selectedBrand.toLowerCase().equals(product.getBrand().toLowerCase())) {
                    continue main;
                }
            }
            temp.add(product);
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
        return;
    }

    public static void filterByOffs() {
        isItFilteredByOffs = true;
        if (DataBase.sortedOrFilteredProduct.size() == 0)
            return;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if (!(product.getDoesItHaveDiscount())) {
                temp.add(product);
            }
        }
        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
        updateAvailableBrands();
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
        if (isItFilteredByAvailability)
            currentFilters.add("Availability filter is on");
        if (isItFilteredByBrand)
            currentFilters.add("Brand filter is on");
        if (isItFilteredByCategory)
            currentFilters.add("Category filter is on");
        if (isItFilteredByName)
            currentFilters.add("Name filter is on");
        if (isItFilteredByPrice)
            currentFilters.add("Price filter is on");
        if (isItFilteredByOffs)
            currentFilters.add("Off filter is on");
        if (currentFilters.size() == 0) {
            currentFilters.add("there are no filters on");
        }
        return currentFilters;
    }

    public static void disableNameFilter() {
        name = "";
        isItFilteredByName = false;
        filter();
    }

    public static void disableOffsFilter() {
        isItFilteredByOffs = false;
        filter();
    }

    public static void disablePriceFilter() {
        minPrice = -1;
        maxPrice = -1;
        isItFilteredByPrice = false;
        filter();
    }

    public static void disableCategoryFilter() {
        categoryName = "";
        isItFilteredByCategory = false;
        filter();
    }

    public static void disableAvailabilityFilter() {
        isItFilteredByAvailability = false;
        filter();
    }

    public static void disableBrandFilter(String brand) {
        int i = 0;
        for (String selectedBrand : selectedBrands) {
            if (selectedBrand.toLowerCase().equals(brand.toLowerCase())) {
                i = selectedBrands.indexOf(selectedBrand);
                break;
            }
        }
        selectedBrands.remove(i);
        if (selectedBrands.size() == 0) {
            isItFilteredByBrand = false;
        }
        filter();
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
        categoryName = "";
        minPrice = -1;
        maxPrice = -1;
        selectedBrands.clear();
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        updateAvailableBrands();
        Sort.sort();
    }

    public static void filter() {
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        if (isItFilteredByName)
            filterByName(name);
        if (isItFilteredByBrand) {
            for (String selectedBrand : selectedBrands) {
                filterByBrand(selectedBrand);
            }
        }
        if (isItFilteredByOffs)
            filterByOffs();
        if (isItFilteredByPrice)
            filterByPrice(minPrice, maxPrice);
        if (isItFilteredByCategory)
            filterByCategory(categoryName);
        if (isItFilteredByAvailability)
            filterByAvailability();
        updateAvailableBrands();
    }

    public static ArrayList<String> updateAvailableBrands() {
        availableBrands.clear();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            availableBrands.add(product.getBrand());
        }
        return availableBrands;
    }

}