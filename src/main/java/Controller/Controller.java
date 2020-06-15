package Controller;

import Models.Product;

import Models.User.*;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;


public class Controller {
    public static User currentUser = new Guest();
    private static boolean hasHeadManager = false;

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
        if (!info.get(1).matches("\\w+"))
            return "invalid password";

        if (!info.get(4).matches("(\\S+)@(\\S+)")) {
            return "invalid Email";
        }
        if (!info.get(5).matches("\\d+"))
            return "invalid phone number";

        if (type.toLowerCase().equals("costumer"))
            DataBase.addNewUser(new Costumer(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1), Double.parseDouble(info.get(6))));

        if (type.toLowerCase().equals("seller"))
            DataBase.addNewUser(new Seller(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1), info.get(6)));

        if (type.toLowerCase().equals("manager")) {
            hasHeadManager = true;
            DataBase.addNewUser(new Manager(info.get(0), info.get(2), info.get(3), info.get(4), Long.parseLong(info.get(5)), info.get(1)));
        }
        return "account created";
    }

    public static String loginAccount(String username) {
        if (DataBase.getUserByUsername(username).getType().equals("Costumer")) {
            ((Costumer) DataBase.getUserByUsername(username)).setCart(((Guest) currentUser).getCart());
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

    public static int getHowMuchLeftForThisPage(long start){
        if (DataBase.allProducts.size()-start>=20){
            return 20;
        }else {
            return (int)(DataBase.allProducts.size()-start);
        }
    }

    public static ArrayList<String> getProductNameForFxml(long start){
        int counter=0;
        ArrayList<String> returnValue=new ArrayList<String>();
        if (DataBase.allProducts.size()-start>=20){
            counter=20;
        }else{
            counter=DataBase.allProducts.size()-(int)start;
        }
        for (int i = (int)start; i < (int)start+counter; i++) {
            returnValue.add(DataBase.allProducts.get(i).getName());
        }
        return returnValue;
    }

    public static ArrayList<String> getProductImageForFxml(long start){
        int counter=0;
        ArrayList<String> returnValue=new ArrayList<String>();
        if (DataBase.allProducts.size()-start>=20){
            counter=20;
        }else{
            counter=DataBase.allProducts.size()-(int)start;
        }
        for (int i = (int)start; i < (int)start+counter; i++) {
            returnValue.add(DataBase.allProducts.get(i).getImageAddress());
        }
        return returnValue;
    }

    public static ArrayList<Double> getProductPriceForFxml(long start){
        int counter=0;
        ArrayList<Double> returnValue=new ArrayList<Double>();
        if (DataBase.allProducts.size()-start>=20){
            counter=20;
        }else{
            counter=DataBase.allProducts.size()-(int)start;
        }
        for (int i = (int)start; i < (int)start+counter; i++) {
            Product product= DataBase.allProducts.get(i);
            returnValue.add(product.getPrice(product.getDefaultSeller()));
        }
        return returnValue;
    }
}
