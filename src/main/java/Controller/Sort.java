package Controller;

import Models.Product;

import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort {

    private static String currentSort;

    public static void setCurrentSort(String currentSort) {
        Sort.currentSort = currentSort;
    }

    public static String getCurrentSort() {
        return currentSort;
    }

    public static void sortByTime() {
        Collections.sort(DataBase.sortedOrFilteredProduct, new SortByTime());
    }

    public static void sortByScore() {
        Collections.sort(DataBase.sortedOrFilteredProduct, new SortByScore());
    }

    public static void sortByView() {
        Collections.sort(DataBase.sortedOrFilteredProduct, new SortByView());
    }

    public static void disableSort() {
        currentSort = "view";
        sort();
    }

    static class SortByView implements Comparator<Product> {
        public int compare(Product a, Product b) {
            return -a.getNumberOfView() + b.getNumberOfView();
        }
    }

    static class SortByScore implements Comparator<Product> {
        public int compare(Product a, Product b) {
            double value = b.getAverageScore() - a.getAverageScore();
            if (value > 1)
                return 1;
            if (value < 1)
                return -1;
            return 0;
        }
    }

    static class SortByTime implements Comparator<Product> {
        public int compare(Product a, Product b) {
            return (int)(-a.getProductDate().getTime() + b.getProductDate().getTime());
        }
    }

    public static void sort() {
        if (currentSort.matches("(?i)view")) {
            sortByView();
        } else if (currentSort.matches("(?i)time")) {
            sortByTime();
        } else if (currentSort.matches("(?i)score")) {
            sortByScore();
        }else{
            sortByView();
        }
    }

    public static ArrayList<String> getSortedProducts() {
        ArrayList<String> sortedProducts = new ArrayList<String>();
        for (Product product : DataBase.sortedOrFilteredProduct) {
            sortedProducts.add(product.getName());
        }
        return sortedProducts;
    }

    public static ArrayList<String> getAvailableSorts() {
        ArrayList<String> availableSorts = new ArrayList<String>();
        availableSorts.add("sort by time");
        availableSorts.add("sort by score");
        availableSorts.add("sort by view");
        return availableSorts;
    }

}
