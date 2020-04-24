package View;

import View.Menu.MainMenu;
import View.Menu.Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    public View() {

    }

    public static void printAllProduct(ArrayList<Long> productsId,
                                       ArrayList<String> productName,
                                       ArrayList<Double> productPrice,
                                       ArrayList<Double> offPercentage,
                                       ArrayList<Boolean> doesItHaveOff) {


        //
    }

    public static void printAllOffProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage) {
        //
    }

    //in ba printAllProduct fargh dare ya na?
    public static void printFilteredProduct(ArrayList<Long> productsId,
                                            ArrayList<String> productName,
                                            ArrayList<Double> productPrice,
                                            ArrayList<Double> offPercentage,
                                            ArrayList<Boolean> doesItHaveOff) {
        //
    }

    public static void printCurrentFilter(ArrayList<String> filters) {
        //
    }

    //in ba printAllProduct fargh dare ya na?
    public static void printSortedProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage,
                                          ArrayList<Boolean> doesItHaveOff) {
        //
    }

    public static void printCurrentSort(ArrayList<String> sort){
        //
    }
    //in ba printAllProduct fargh dare ya na?
    public static void printOffProduct(ArrayList<Long> productsId,
                                       ArrayList<String> productName,
                                       ArrayList<Double> productPrice,
                                       ArrayList<Double> offPercentage,
                                       ArrayList<Boolean> doesItHaveOff) {
        //
    }

    public static void printCategories(ArrayList<String> categoriesName) {
        //
    }

    public static void printAvailableFilters(ArrayList<String> filters) {
        //
    }

    public static void printAvailableSorting(ArrayList<String> sorting) {
        //
    }

    public static void printProductSummery(Long productId,String productName,
                                           double offPercentage,String explanation,
                                           double productPrice,String Category,
                                           double productAverageScore) {
        //
    }

    public static void printAttributes(Long productId,String productName,
                                       double offPercentage,String explanation,
                                       double productPrice,String Category,
                                       double productAverageScore,ArrayList<String> allSeller,
                                       int remainedNumber) {
        //
    }

    public static void printCompareProduct(String firstProductName, double firstOffPercentage,
                                           String firstExplanation, double firstProductPrice,
                                           double firstProductAverageScore,

                                           String secondProductName, double secondOffPercentage,
                                           String secondExplanation, double secondProductPrice,
                                           double secondProductAverageScore) {
        //
    }

    //tavabee ke az alireza type khroji ro migiram.

    public static void printString(String string) {
        System.out.println(string);
    }

    public static void run() {
        Menu.setScanner(new Scanner(System.in));
        new MainMenu().run("");
    }
}
