package Controller;

import java.util.ArrayList;

public class Filter {

    private static boolean isItFilteredByName = false;
    private static boolean isItFilteredByCategory = false;
    private static boolean isItFilteredByPrice = false;
    private static boolean isItFilteredByAvailability = false;
    private static boolean isItFilteredByBrand = false;
    private static boolean isItFilteredByOffs = false;
    private static ArrayList<String> selectedBrands = new ArrayList<>();

    private static int minPrice = -1;
    private static int maxPrice = -1;
    private static String name = "";
    private static String categoryName = "";
    private static int availability = -1;

    public static void filterByName(String name) {
        return;
    }

    public static void filterByCategory(String categoryName) {
        return;
    }

    public static void filterByPrice(double min, double max) {
        return;
    }

    public static void filterByAvailability(int number) {
        return;
    }

    public static void filterByBrand(String brandName) {
        return;
    }

    public static void filterByOffs() {
        return;
    }

    public static ArrayList<String> showAvailableFilters() {
        return null;
    }

    public static ArrayList<String> showCurrentFilters() {
        return null;
    }

    public static void disableNameFilter() {

    }

    public static void disableOffsFilter() {

    }

    public static void disablePriceFilter() {

    }

    public static void disableCategoryFilter() {

    }

    public static void disableAvailabilityFilter() {

    }

    public static void disableBrandFilter() {

    }

    public static void restartFilters() {

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

    public static ArrayList<String> showAvailableSorts() {
        ArrayList<String> availableFilters = new ArrayList<>();
        return availableFilters;
    }


    public static ArrayList<String> getCurrentFilter() {
        return Filter.showCurrentFilters();
    }

    public static void findProductByName(String productName){
         //
    }

}