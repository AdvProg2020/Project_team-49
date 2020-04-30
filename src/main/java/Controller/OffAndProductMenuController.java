package Controller;

import Models.Category;
import Models.Comment;
import Models.Product;
import Models.User.Seller;
import View.Menu.OffsAndProductsMenu.Comments;

import static Controller.DataBase.*;
import java.util.ArrayList;



public class OffAndProductMenuController {

    private ArrayList<Product> SortedProduct=new ArrayList<Product>();

    //kheili khari


    //agar bekham az in estefade konam bayad hamash static beshe ya to Menu tarif beshe.

    public static ArrayList<String> getName(){
        ArrayList<String> productName=new ArrayList<String>();
        for (Product product : allProducts) {
            productName.add(product.getName());
        }
        return productName;
    }

    public static ArrayList<String> getCurentName(){
        ArrayList<String> productName=new ArrayList<String>();
        for (Product product : sortedOrFilteredProduct) {
            productName.add(product.getName());
        }
        return productName;
    }

    public static ArrayList<Long> getId(){
        ArrayList<Long> productId=new ArrayList<Long>();
        for (Product product : allProducts) {
            productId.add(product.getProductId());
        }
        return productId;
    }

    public static ArrayList<Long> getCurrentId(){
        ArrayList<Long> productId=new ArrayList<Long>();
        for (Product product : sortedOrFilteredProduct) {
            productId.add(product.getProductId());
        }
        return productId;
    }

    public static ArrayList<Double> getOffPercentage(){
        ArrayList<Double> productOffPercentage=new ArrayList<Double>();
        for (Product product : allProducts) {
            productOffPercentage.add(product.getOffPercentage());
        }
        return productOffPercentage;
    }

    public static ArrayList<Double> getCurrentOffPercentage(){
        ArrayList<Double> productOffPercentage=new ArrayList<Double>();
        for (Product product : sortedOrFilteredProduct) {
            productOffPercentage.add(product.getOffPercentage());
        }
        return productOffPercentage;
    }

    public static ArrayList<Double> getPrice(){
        ArrayList<Double> productPrice=new ArrayList<Double>();
        for (Product product : allProducts) {
            productPrice.add(product.getPrice());
        }
        return productPrice;
    }

    public static ArrayList<Double> getCurrentPrice(){
        ArrayList<Double> productPrice=new ArrayList<Double>();
        for (Product product : sortedOrFilteredProduct) {
            productPrice.add(product.getPrice());
        }
        return productPrice;
    }

    public static ArrayList<Boolean> doesItOff(){
        ArrayList<Boolean> isIfOff=new ArrayList<Boolean>();
        for (Product product : allProducts) {
            isIfOff.add(product.getDoesItHaveOff());
        }
        return isIfOff;
    }

    public static ArrayList<Boolean> doesCurrentOff(){
        ArrayList<Boolean> isIfOff=new ArrayList<Boolean>();
        for (Product product : sortedOrFilteredProduct) {
            isIfOff.add(product.getDoesItHaveOff());
        }
        return isIfOff;
    }

    public static ArrayList<String> getAllAvailableFilters() {
        return allAvailableFilters;
    }

    public static ArrayList<Category> getAllCategory() {
        return allCategories;
    }

    //
    public static ArrayList<Product> filtering(String filter,String Type) {
        Filter.filterByBrand("");
        // va baghie.

        //bayad havasam bashe shayad chnta filter yoho On bokone.
        return null;
    }

    //
    public static void disableFilter(String filter) {
        //
    }

    public static ArrayList<String> getAllAvailableSorting() {
        return allAvailableSorting;
    }

    //
    public static ArrayList<Product> sorting(String sort) {
        //va baghie
        return null;
    }

    public static ArrayList<String> getCurrentSort() {
        return null;
    }

    //
    public static void disableSort(String sort) {
        Filter.disableAvailabilityFilter();
        //va baghie.
    }

    //Test ino chi kar konam?
    public static void addCommentsById(long productId, String title, String content) {
        Controller.getProductById(productId).addAComment(new Comment(Controller.currentUser,Controller.getProductById(productId),title,content));
    }

    public static ArrayList<String> getCategoriesName(){
        ArrayList<String> categoryName=new ArrayList<String>();
        for (Category category : allCategories) {
            categoryName.add(category.getName());
        }
        return categoryName;
    }

    public static void addToCartById(long productId, boolean commenSeller,String sellerUserName){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                if (commenSeller){
                    Controller.addToCart(product,product.getCommenSeller());
                }else
                Controller.addToCart(product,product.getSellerByUserName(sellerUserName));
            }
        }
    }

    public static boolean isCurrentUserGuestOrUser(){
        if (Controller.currentUser.getType().equals("Guest")||Controller.currentUser.getType().equals("Costumer"))
            return true;
        return false;
    }
}
