package Controller;

import Models.Category;
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
    private static String productName = "";
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
        return productName;
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
        Filter.productName = name;
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
        return;
    }

    public static void filterByCategory(String categoryName) {
        setCategoryName(categoryName);
        isItFilteredByCategory = true;
        ArrayList<Product> temp = new ArrayList<Product>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            if(!DataBase.isProductInThisCategory(categoryName , product)){
                temp.add(product);
            }
        }

        if (temp.size() == 0)
            return;

        for (Product product : temp) {
            DataBase.sortedOrFilteredProduct.remove(product);
        }
        temp.clear();
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
        return;
    }

    public static ArrayList<String> getAvailableFilters() {
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
        productName = "";
        isItFilteredByName = false;
        filter();
    }

    public static void disableOffsFilter() {
        isItFilteredByOffs = false;
        filter();
    }

    public static void disablePriceFilter() {
        minPrice = -1.0;
        maxPrice = -1.0;
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
        int i = -10;
        if(selectedBrands.size() == 0) {
            isItFilteredByBrand = false;
            return;
        }
        for (String selectedBrand : selectedBrands) {
            if (selectedBrand.toLowerCase().equals(brand.toLowerCase())) {
                i = selectedBrands.indexOf(selectedBrand);
                break;
            }
        }
        if(i == -10)
            return;
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
        productName = "";
        categoryName = "";
        minPrice = -1;
        maxPrice = -1;
        selectedBrands.clear();
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        Sort.sort();
    }

    public static void filter() {
        DataBase.sortedOrFilteredProduct.clear();
        for (Product product : DataBase.allProducts) {
            DataBase.sortedOrFilteredProduct.add(product);
        }
        if (isItFilteredByName)
            filterByName(productName);
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
    }

    public static ArrayList<String> updateAvailableBrands() {
        availableBrands.clear();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            availableBrands.add(product.getBrand());
        }
        return availableBrands;
    }

    public static ArrayList<String> showSubCategories(){
        ArrayList<String> subCategories = new ArrayList<>();

        for (Category category : DataBase.allCategories) {
            if(category.getName().toLowerCase().equals(categoryName.toLowerCase())){
                for (Category subCategory : category.getSubCategories()) {
                    subCategories.add(subCategory.getName());
                }
            }
        }
        if(subCategories.size() == 0 && categoryName.equals("")){
            for (Category category : DataBase.allCategories) {
                if(category.getParentCategory() == null){
                    subCategories.add(category.getName());
                }
            }
        }
        return subCategories;
    }

}