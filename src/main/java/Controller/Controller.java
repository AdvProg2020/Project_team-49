package Controller;

import Bank.View.BankServer;
import Models.Category;
import Models.Off;
import Models.OffStatus;
import Models.Product;

import Models.User.*;
import javafx.geometry.Insets;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javax.print.attribute.standard.MediaPrintableArea;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static Controller.DataBase.*;


public class Controller {
    public static User currentUser = new Guest();
    private static boolean hasHeadManager;
    private static Product selectedProduct;

    private static boolean doesItOffPage;
    private static ArrayList<Long> compareIds = new ArrayList<>();

    public static void setCompareIds(ArrayList<Long> compareIds) {
        Controller.compareIds = compareIds;
    }

    public static ArrayList<Long> getCompareIds() {
        return compareIds;
    }

    public static void setDoesItOffPage(boolean doesItOffPage) {
        Controller.doesItOffPage = doesItOffPage;
    }

    public static boolean isDoesItOffPage() {
        return doesItOffPage;
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isHasHeadManager() {
        return hasHeadManager;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public static void setSelectedProduct(Product selectedProduct) {
        Controller.selectedProduct = selectedProduct;
    }

    //new content shart dare?
    public static String editField(String field, String newContent) {
        if (field.equalsIgnoreCase("first name")) {
            currentUser.setFirstName(newContent);
            return "first name edited";
        } else if (field.equalsIgnoreCase("last name")) {
            currentUser.setLastName(newContent);
            return "last name edited";
        } else if (field.equalsIgnoreCase("email")) {
            if (!newContent.matches("(\\S+)@(\\S+)")) {
                return "invalid new email";
            }
            currentUser.setEMail(newContent);
            return "Email edited";
        } else if (field.equalsIgnoreCase("phone number")) {
            if (!newContent.matches("\\d+")) {
                return "invalid new phone number";
            }
            currentUser.setPhoneNumber(Long.parseLong(newContent));
        } else if (field.equalsIgnoreCase("password")) {
            if (!newContent.matches("^\\w+$")) {
                return "invalid new password";
            }
            currentUser.setPassword(newContent);
            return "password edited";
        } else if (field.equalsIgnoreCase("company") && currentUser.getType().equalsIgnoreCase("Seller")) {
            ((Seller) currentUser).setCompanyName(newContent);
            return "company edited";
        }
        return "invalid field";
    }

    public static String getCurrentUserType() {
        return currentUser.getType();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    //bayad hazf beshe
    public static Product getProductById(long productId) {
        return DataBase.getProductById(productId);
    }

    public static boolean getHasHeadManager() {
        return hasHeadManager;
    }

    public static boolean isPasswordCorrect(String password, String username) {
        if (DataBase.getUserByUsername(username) == null) {
            return false;
        }
        if (DataBase.getUserByUsername(username).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static void setHasHeadManager(boolean hasHeadManager) {
        Controller.hasHeadManager = hasHeadManager;
    }

    //kamel nist
    public static String getCurrentUserSpecifications() {
        return ManagerAreaController.viewUser(currentUser.getUsername());
    }

    public static String createAccount(ArrayList<String> info, String type) {
        //login beshe ya na
        if (type.toLowerCase().equals("costumer"))
            DataBase.addNewUser(new Costumer(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1), Double.parseDouble(info.get(6))));

        if (type.toLowerCase().equals("seller"))
            DataBase.addNewUser(new Seller(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1), info.get(6)));

        if (type.toLowerCase().equals("manager")) {
            hasHeadManager = true;
            DataBase.addNewUser(new Manager(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1)));
        }
        if (type.toLowerCase().equalsIgnoreCase("support")) {
            DataBase.addNewUser(new Support(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1)));
        }
        return "account created";
    }

    public static String loginAccount(String username) {
        if (DataBase.getUserByUsername(username).getType().equals("Costumer")) {
            if (((Guest) currentUser).getCart().getProducts().size() != 0) {
                ((Costumer) DataBase.getUserByUsername(username)).setCart(((Guest) currentUser).getCart());
            }
        }
        setCurrentUser(DataBase.getUserByUsername(username));
        return "login successful";
    }

    public static double getBalance() {
        if (currentUser.getType().equals("Costumer")) {
            return ((Costumer) currentUser).getCredit();
        } else {
            return ((Seller) currentUser).getCredit();
        }
    }

    //bayad hazf beshe
    public static boolean hasUserWithUsername(String username) {
        if (DataBase.getUserByUsername(username) == null) {
            return false;
        }
        return true;
    }

    public static String addToCart(Product product, Seller seller, int count) {
        if (product.remainingProductForSeller(seller) < count) {
            return "available items for this seller is less than " + count + " (" + product.remainingProductForSeller(seller) + ")";
        }
        if (currentUser.getType().equalsIgnoreCase("Guest")) {
            ((Guest) currentUser).addProductToCart(product, seller, count);
        } else {
            ((Costumer) currentUser).addProductToCart(product, seller, count);
        }
        return "product added";
    }

    public static void addToCartWithToken(User user, Product product, Seller seller, int count) {
        if (product.remainingProductForSeller(seller) < count) {
            return;
        }
        if (user.getType().equalsIgnoreCase("Guest")) {
            ((Guest) user).addProductToCart(product, seller, count);
        } else {
            ((Costumer) user).addProductToCart(product, seller, count);
        }
    }

    public static void logout() {
        setCurrentUser(new Guest());
    }

    public static ArrayList<String> getPersonalInfo() {
        ArrayList<String> info = new ArrayList<String>();
        info.add(currentUser.getUsername());
        info.add(currentUser.getFirstName());
        info.add(currentUser.getLastName());
        info.add(currentUser.getEMail());
        info.add(String.valueOf(currentUser.getPhoneNumber()));
        info.add(currentUser.getPassword());
        return info;
    }

    public static int getHowMuchLeftForThisPage(long start) {
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            return 20;
        } else {
            return (int) (DataBase.sortedOrFilteredProduct.size() - start);
        }
    }

    public static ArrayList<String> getProductNameForFxml(long start) {
        int counter = 0;
        ArrayList<String> returnValue = new ArrayList<String>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).getName());
        }
        return returnValue;
    }

    public static ArrayList<String> getProductImageForFxml(long start) {
        int counter = 0;
        ArrayList<String> returnValue = new ArrayList<String>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).getImageAddress());
        }
        return returnValue;
    }

    public static ArrayList<Double> getProductPriceForFxml(long start) {
        int counter = 0;
        ArrayList<Double> returnValue = new ArrayList<Double>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            Product product = DataBase.sortedOrFilteredProduct.get(i);
            returnValue.add(product.getPrice(product.getDefaultSeller()));
        }
        return returnValue;
    }

    public static ArrayList<Long> getProductIdForFxml(long start) {
        int counter = 0;
        ArrayList<Long> returnValue = new ArrayList<Long>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).getProductId());
        }
        return returnValue;
    }

    public static ArrayList<Boolean> getOffForFxml(long start) {
        int counter = 0;
        ArrayList<Boolean> returnValue = new ArrayList<Boolean>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).getDoesItHaveOff());
        }
        return returnValue;
    }

    public static ArrayList<Double> getProductScoreForFxml(long start) {
        int counter = 0;
        ArrayList<Double> returnValue = new ArrayList<Double>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).gettttAverageScore());
        }
        return returnValue;
    }

    public static ArrayList<Integer> getProductRemainForFxml(long start) {
        int counter = 0;
        ArrayList<Integer> returnValue = new ArrayList<Integer>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            returnValue.add(DataBase.sortedOrFilteredProduct.get(i).remainingItems());
        }
        return returnValue;
    }

    public static ArrayList<Double> getProductOffRemainForFxml(long start) {
        int counter = 0;
        ArrayList<Double> returnValue = new ArrayList<Double>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            if (DataBase.sortedOrFilteredProduct.get(i).getDoesItHaveOff()) {
                returnValue.add(OffAndProductMenuController.getOffTimeLeftById(DataBase.sortedOrFilteredProduct.get(i)));
            }
            returnValue.add(-1.0);
        }
        return returnValue;
    }

    public static ArrayList<Integer> getProductOffPercentForFxml(long start) {
        int counter = 0;
        ArrayList<Integer> returnValue = new ArrayList<Integer>();
        if (DataBase.sortedOrFilteredProduct.size() - start >= 20) {
            counter = 20;
        } else {
            counter = DataBase.sortedOrFilteredProduct.size() - (int) start;
        }
        for (int i = (int) start; i < (int) start + counter; i++) {
            if (DataBase.sortedOrFilteredProduct.get(i).getDoesItHaveOff()) {
                returnValue.add(DataBase.sortedOrFilteredProduct.get(i).getOff().getOffAmount());
            }
            returnValue.add(-1);

        }
        return returnValue;
    }


    public static int getAllPageNumber() {
        if (sortedOrFilteredProduct.size() % 20 == 0) {
            return sortedOrFilteredProduct.size() / 20;
        }
        return sortedOrFilteredProduct.size() / 20 + 1;
    }

    public static void restartSortedOrFilteredProduct() {
        DataBase.sortedOrFilteredProduct.clear();
        DataBase.sortedOrFilteredProduct.addAll(allProducts);
    }

    public static void check() {
//        allProducts.get(2).setDoesItHaveOff(true);
//        Date start=new Date();
//        Date end=new Date(start.getTime()+10*3600*1000*24);
//        ArrayList<Product> arrayList=new ArrayList<Product>();
//        arrayList.add(Controller.getProductById(2));
//        Off off =new Off(arrayList, OffStatus.confirmed,start,end,20);
//        allProducts.get(2).setOff(off);
//        allProducts.get(2).setDoesItHaveOff(true);
//        allProducts.get(0).setAverageScore(0);
//        allProducts.get(1).setAverageScore(2.3);
//        allProducts.get(2).setAverageScore(0.2);
//        allProducts.get(3).setAverageScore(3.7);
//        allProducts.get(4).setAverageScore(4.5);
        Category category = new Category("bastani", "oof", null);
        allCategories.add(category);
        Seller seller = new Seller("ahmad", "por", "mor", "asd@gmail.com", 123123123, "123", "kale");
        Product product1 = new Product("khorma", "mihan", 1500, "joon", category, seller, 10, "");
        allProducts.add(product1);
        sortedOrFilteredProduct.add(product1);
        for (Product product : allProducts) {
            product.setImageAddress("./photos/MainMenu/commercials/xbox1.png");
        }
    }

    private static Media media;

    private static MediaPlayer mediaPlayer;

    public static void startSong(String file) {
        media = new Media(new File(file).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(100);
    }

    public static void cancelSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void resumeSong() {
//        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(100);
    }

    public static void startClickSound() {
        String file = "src/main/resources/Sound/ProductsMenu/click.mp3";
        Media sound = new Media(new File(file).toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();
    }

    public static String getAllCategories() {
        String allCategories = "";
        for (int i = 0; i < DataBase.getAllCategories().size(); i++) {
            allCategories = allCategories.concat(DataBase.getAllCategories().get(i).toString());
            if (i != DataBase.getAllCategories().size() - 1) {
                allCategories = allCategories.concat("#$");
            }
        }
        return allCategories;
    }

    public static void exitFromSite(String token) {
        Server.onlineUsers.remove(token);
    }
}
