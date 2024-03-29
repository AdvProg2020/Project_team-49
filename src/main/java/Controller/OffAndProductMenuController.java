package Controller;

import Models.Comment;
import Models.Product;
import Models.User.Seller;
import Models.User.User;

import static Controller.DataBase.*;
import java.util.ArrayList;
import java.util.Date;

public class OffAndProductMenuController {

    private ArrayList<Product> SortedProduct=new ArrayList<Product>();

    public static ArrayList<String> getName(){
        ArrayList<String> productName=new ArrayList<String>();
        for (Product product : allProducts) {
            productName.add(product.getName());
        }
        return productName;
    }
    public static ArrayList<String> getCurrentName(){
        ArrayList<String> productName=new ArrayList<String>();
        for (Product product : sortedOrFilteredProduct) {
            productName.add(product.getName());
        }
        return productName;
    }
    public static ArrayList<Long> getCurrentId(){
        ArrayList<Long> productId=new ArrayList<Long>();
        for (Product product : sortedOrFilteredProduct) {
            productId.add(product.getProductId());
        }
        return productId;
    }
    public static ArrayList<Double> getCurrentOffPercentage(){
        ArrayList<Double> productOffPercentage=new ArrayList<Double>();
        for (Product product : sortedOrFilteredProduct) {
            productOffPercentage.add(product.getOffPercentage());
        }
        return productOffPercentage;
    }
    public static ArrayList<Double> getCurrentPrice(){
        ArrayList<Double> productPrice=new ArrayList<Double>();
        for (Product product : sortedOrFilteredProduct) {
            productPrice.add(product.getPrice(product.getDefaultSeller()));
        }
        return productPrice;
    }
    public static ArrayList<Boolean> doesCurrentOff(){
        ArrayList<Boolean> isIfOff=new ArrayList<Boolean>();
        for (Product product : sortedOrFilteredProduct) {
            isIfOff.add(product.getDoesItHaveOff());
        }
        return isIfOff;
    }
    public static ArrayList<String> getAllAvailableFilters() {
        return Filter.getAvailableFilters();
    }
    public static ArrayList<String> getCategoriesName(){
        return Filter.showSubCategories();
    }
    public static ArrayList<String> getAllAvailableSorting() {
        return allAvailableSorting;
    }
    public static ArrayList<String> getAllSellerOfProductWithId(long productId){
        return Controller.getProductById(productId).getAllSellerName();
    }
    public static String getCurrentSort() {
        return Sort.getCurrentSort();
    }
    public static void sorting(String sort) {
        if (sort.equalsIgnoreCase("View")){
            Sort.sortByView();
            Sort.setCurrentSort("View");
            return;
        }
        if (sort.equalsIgnoreCase("Time")){
            Sort.sortByTime();
            Sort.setCurrentSort("Time");
            return;
        }
        if (sort.equalsIgnoreCase("Score")){
            Sort.sortByScore();
            Sort.setCurrentSort("Score");
        }
    }
    public static void disableSort() {
        Sort.disableSort();
    }
    public static void addCommentsById(long productId, String title, String content) {
        Controller.getProductById(productId).addAComment(new Comment(Controller.currentUser,Controller.getProductById(productId),title,content));
    }

    public static void addNewCommentsById(long productId, String title, String content, User user) {
        Controller.getProductById(productId).addAComment(new Comment(user,Controller.getProductById(productId),title,content));
    }
    public static void addToCartById(long productId, boolean commonSeller,String sellerUserName,int count){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                if (commonSeller){
                    Controller.addToCart(product,product.getDefaultSeller(),count);
                }else
                Controller.addToCart(product,product.getSellerByUsername(sellerUserName),count);
            }
        }
    }
    public static boolean checkRemainCountForBuy(long productId,String userName,int count){
        Seller seller;
        if (userName==null){
            seller=Controller.getProductById(productId).getDefaultSeller();
        }else {
            seller=Controller.getProductById(productId).getSellerByUsername(userName);
        }

        if (Controller.getProductById(productId).getRemainingItemsForSeller(seller)<count){
            return false;
        }
        return true;
    }
    public static boolean isCurrentUserGuestOrUser(){
        if (Controller.currentUser.getType().equals("Guest")||Controller.currentUser.getType().equals("Costumer"))
            return true;
        return false;
    }
    public static boolean checkSortingInput(String sort){
        if (sort.equalsIgnoreCase("View")||sort.equalsIgnoreCase("Time")||sort.equalsIgnoreCase("Score")){
            return true;
        }
        return false;
    }
    public static boolean checkFilteringByBrand(String brand){
        Filter.updateAvailableBrands();
        for (String availableBrand : Filter.getAvailableBrands()) {
            if (availableBrand.equalsIgnoreCase(brand)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkFilteringByCategory(String category){
        for (String subCategory : Filter.showSubCategories()) {
            if (subCategory.equalsIgnoreCase(category)){
                return true;
            }
        }
        return false;
    }
    public static boolean isProductWithId(long productId){
        for (Product allProduct : allProducts) {
            if (allProduct.getProductId()==productId){
                return true;
            }
        }
        return false;
    }
    public static boolean isSellerWithNameForProduct(long productId,String sellerName){
        for (String seller : Controller.getProductById(productId).getAllSellerName()) {
            if (seller.equalsIgnoreCase(sellerName)){
                return true;
            }
        }
        return false;
    }
    public static void clearAndRestoreProduct(){
        Filter.restartFilters();
    }
    public static void increaseView(long productId){
        for (Product product : allProducts) {
            if (product.getProductId()==productId){
                product.setNumberOfView(product.getNumberOfView()+1);
            }
        }
    }

    public static double  getOffTimeLeftById(Product product){
        Date date=product.getOff().getEndDate();
        Date today=new Date();
        return (double) ((date.getTime()-today.getTime())/1000/60/60/24);
    }
}
