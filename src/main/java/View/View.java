package View;

import Controller.Controller;
import Controller.DataBase;
import Models.Product;
import Models.User.User;
import View.Menu.MainMenu;
import View.Menu.Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static Controller.DataBase.getProductById;


public class View extends Application {

    public View() {
    }

    @Override
    public void start(Stage stage) throws Exception {
//        cartAndBuyScene = new Scene( FXMLLoader.load(getClass().getClassLoader().getResource("fxml/cartAndBuyPage.fxml")));
//        Controller.setCurrentUser(getAllUsers().get(5));
//        Product product = getProductById(1);
//        Controller.setSelectedProduct(product);
        Pane mainMenu = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainPage.fxml"));
        Pane mainBar = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainBar.fxml"));
        Controller.setInnerPaneForColor((Pane) ((ScrollPane) mainMenu.getChildren().get(0)).getContent());
        ScrollPane scrollPane = (ScrollPane) mainMenu.getChildren().get(0);
        scrollPane.setPrefHeight(800);
        mainMenu.getChildren().add(mainBar);
        if(Controller.getHasHeadManager()){
            Controller.setCurrentPane(mainMenu);
        }else{
            Controller.setLastPane(mainMenu);
            Pane register = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RegisterMenu.fxml"));
            Controller.setCurrentPane(register);
        }
//        Controller.setCurrentPane(mainMenu);
        Scene scene = new Scene(Controller.getCurrentPane());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
    }

    private void closeProgram() {
        DataBase.endProgram();
    }

    private ArrayList<User> getAllUsers() {
        return DataBase.getAllUsers();
    }

    public void run() {
        this.launch();
        Menu.setScanner(new Scanner(System.in));
        new MainMenu().run("");
    }

    public static void printAllProduct(ArrayList<Long> productsId,
                                       ArrayList<String> productName,
                                       ArrayList<Double> productPrice,
                                       ArrayList<Double> offPercentage,
                                       ArrayList<Boolean> doesItHaveOff) {
        System.out.println("All Products:");
        for (int i = 1; i <= productsId.size(); i++) {
            String off = "";
            if (doesItHaveOff.get(i - 1)) {
                off = "%OFF%" + offPercentage.get(i - 1) + "%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n", i, productName.get(i - 1), productPrice.get(i - 1), off, productsId.get(i - 1));
        }
    }

    public static void printAllOffProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage) {
        System.out.println("All Of Offs Products:");
        for (int i = 0; i < productsId.size(); i++) {
            String off = "%OFF%" + offPercentage.get(i) + "%";
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n", i, productName.get(i), productPrice.get(i), off, productsId.get(i));
        }
    }


    //duplicate shode alan vali be in khater hastesh ke shayad bekhaim bad az filter shodan joor digee neshon bedim
    public static void printFilteredProduct(ArrayList<Long> productsId,
                                            ArrayList<String> productName,
                                            ArrayList<Double> productPrice,
                                            ArrayList<Double> offPercentage,
                                            ArrayList<Boolean> doesItHaveOff) {
        System.out.println("Filtered Product:");
        for (int i = 0; i < productsId.size(); i++) {
            String off = "";
            if (doesItHaveOff.get(i)) {
                off = "%OFF%" + offPercentage.get(i) + "%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n", i, productName.get(i), productPrice.get(i), off, productsId.get(i));
        }
    }

    public static void printCurrentFilter(ArrayList<String> filters) {
        System.out.println("Current Filter's are:");
        for (int i = 1; i <= filters.size(); i++) {
            System.out.println(i + ". " + filters.get(i - 1));
        }
    }

    //duplicate shode alan vali be in khater hastesh ke shayad bekhaim bad az filter shodan joor digee neshon bedim
    public static void printSortedProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage,
                                          ArrayList<Boolean> doesItHaveOff) {
        System.out.println("Sorted Product:");
        for (int i = 0; i < productsId.size(); i++) {
            String off = "";
            if (doesItHaveOff.get(i)) {
                off = "%OFF%" + offPercentage.get(i) + "%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n", i, productName.get(i), productPrice.get(i), off, productsId.get(i));
        }
    }

    public static void printCurrentSort(String sort) {
        if (sort.isEmpty()) {
            System.out.println("There Is No Entered Sort");
            return;
        }
        System.out.println("Current Sort Is:\n" +
                sort);
    }
    //in ba printAllProduct fargh dare ya na?

    public static void printCategories(ArrayList<String> categoriesName) {
        System.out.println("Available Categories Are:");
        for (int i = 1; i <= categoriesName.size(); i++) {
            System.out.println(i + ". " + categoriesName.get(i - 1));
        }
    }

    public static void printAvailableFilters(ArrayList<String> filters) {
        System.out.println("Available Filters are:");
        for (int i = 1; i <= filters.size(); i++) {
            System.out.println(i + ". " + filters.get(i - 1));
        }
    }

    public static void printAvailableSorting(ArrayList<String> sorting) {
        System.out.println("Available Sort's are:");
        for (int i = 1; i <= sorting.size(); i++) {
            System.out.println(i + ". " + sorting.get(i - 1));
        }
    }

    public static void printProductSummery(Long productId, String productName,
                                           double offPercentage, String explanation,
                                           double productPrice, String category,
                                           double productAverageScore) {
        String offDetail = "";
        if (offPercentage == 0) {
            offDetail = "This product is not on OFF(。﹏。*)";
        } else {
            offDetail = "This product is on OFF(" + offPercentage + "%)";
        }
        System.out.println(productName + " INFO:" + "\n" +
                "Product Id: " + productId + "\n" +
                "Price: " + productPrice + "\n" +
                offDetail + "\n" +
                "Score: " + productAverageScore + "\n" +
                "Category: " + category + "\n" +
                "More Detail:\n" + explanation);
    }

    public static void printAttributes(Long productId, String productName,
                                       double offPercentage, String explanation,
                                       double productPrice, String category,
                                       double productAverageScore, ArrayList<String> allSeller,
                                       ArrayList<Double> allSellerPrice, int remainedNumber) {
        String offDetail = "";
        if (offPercentage == 0) {
            offDetail = "This product is not on OFF(。﹏。*)";
        } else {
            offDetail = "This product is on OFF(" + offPercentage + "%)";
        }
        System.out.println(productName + " INFO:" + "\n" +
                "Product Id: " + productId + "\n" +
                "Count of Remained:" + remainedNumber + "\n" +
                "Price: " + productPrice + "\n" +
                offDetail + "\n" +
                "Score: " + productAverageScore + "\n" +
                "Category: " + category + "\n" +
                "More Detail:\n" + explanation);
        System.out.println("you can buy this Product from:");
        for (int i = 1; i <= allSeller.size(); i++) {
            System.out.println(" ~" + i + ". " + allSeller.get(i - 1) + " With Price: " + allSellerPrice.get(i - 1));
        }
    }

    public static void printCompareProduct(String firstProductName, double firstOffPercentage,
                                           String firstExplanation, double firstProductPrice,
                                           double firstProductAverageScore,

                                           String secondProductName, double secondOffPercentage,
                                           String secondExplanation, double secondProductPrice,
                                           double secondProductAverageScore) {
        System.out.println("~~~~~~~~ Comparing " + firstProductName + " Vs. " + secondProductName + " ~~~~~~~~");
        String firstProductOffDetail = "";
        String secondProductOffDetail = "";
        if (firstOffPercentage != 0) {
            firstProductOffDetail = "~OFF(" + firstOffPercentage + ")";
        }
        if (secondOffPercentage != 0) {
            secondProductOffDetail = "~OFF(" + secondOffPercentage + ")";
        }
        System.out.printf("%-30s %-12.2f $ %-20s Score: %-10.2f Detail:%s\n", firstProductName, firstProductPrice, firstProductOffDetail, firstProductAverageScore, firstExplanation);
        System.out.printf("%-30s %-12.2f $ %-20s Score: %-10.2f Detail:%s\n", secondProductName, secondProductPrice, secondProductOffDetail, secondProductAverageScore, secondExplanation);
    }

    public static void printAvailableBrand(ArrayList<String> availableBrand) {
        System.out.println("Available Brand's are:");
        for (int i = 0; i < availableBrand.size(); i++) {
            System.out.println(i + ". " + availableBrand.get(i));
        }
    }


    //tavabee ke az alireza type khroji ro migiram.

    public static void printString(String string) {
        System.out.println(string);
    }

    public static void printArrayList(ArrayList<String> list) {
        for (String s : list) {
            printString(s);
        }
    }

    public static void printOnlyUserOrGuestCanBuyProduct() {
        System.out.println("Only User Or Guest Can Buy Product");
    }

    public static void printAddToCardSuccessfullyDone() {
        System.out.println("Add to Card Successfully Done");
    }

    public static void printProductsPage() {
//        System.out.println("Products Page:\n"+
//                "1. Show All Product\n"+
//                "2. Show Categories\n"+
//                "3. Filtering Menu \n"+
//                "4. Sorting Menu \n"+
//                "5. Show Product\n"+
//                "6. Log In\n"+
//                "7. Log Out");
        System.out.println("\"Products Page:\"");
    }

    public static void printOffsPage() {
//        System.out.println("Offs Page:\n"+
//                "1. Show All Product\n"+
//                "2. Show Categories\n"+
//                "3. Filtering Menu \n"+
//                "4. Sorting Menu \n"+
//                "5. Show Product\n"+
//                "6. Log In\n"+
//                "7. Log Out");
        System.out.println("\"Offs Page:\"");
    }

    public static void printCommentsMenu() {
        System.out.println("Comment Menu Instruction:\n" +
                "1. Add Comment\n" +
                "2. Show Comments\n" +
                "3. Log In\n" +
                "4. Log out\n" +
                "5. Help\n" +
                "6. back");
    }

    public static void printDigestMenu() {
        System.out.println("Digest Menu Instruction:\n" +
                "1. Add To Cart\n" +
                "2. Select Seller\n" +
                "3. Log In\n" +
                "4. Log Out\n" +
                "5. Help\n" +
                "6. Back");
    }

    public static void printFilteringMenu() {
        System.out.println("Filtering Menu Instruction:\n" +
                "1. Show Available Filters\n" +
                "2. Filter\n" +
                "3. Current Filter\n" +
                "4. Disable Filter\n" +
                "5. Search For [sth]\n" +
                "6. Log In\n" +
                "7. Log Out\n" +
                "8. Help\n" +
                "9. Back");
    }

    public static void printSortingMenu() {
        System.out.println("Sorting Menu Instruction\n" +
                "1. Show Available Sort\n" +
                "2. Sort\n" +
                "3. Current Sort\n" +
                "4. Disable Sort\n" +
                "5. Log In\n" +
                "6. Log Out\n" +
                "7. Help\n" +
                "8. Back\n" +
                "9. Exit");
    }

    public static void printShowProductMenu() {
        System.out.println("Show Product Instruction:\n" +
                "1. Digest\n" +
                "2. Attributes\n" +
                "3. Compare [productID]\n" +
                "4. Comments\n" +
                "5. Log In\n" +
                "6. Log Out\n" +
                "7. Help\n" +
                "8. Back\n" +
                "9. Exit");
    }

    public static void printFilterSthMenu() {
        System.out.println("Choose One Of these Filtering:\n" +
                "1. Name\n" +
                "2. Brand\n" +
                "3. Price\n" +
                "4. Availability\n" +
                "5. Off\n" +
                "6. Categories\n" +
                "7. Back");
    }

    public static void printAllSeller(ArrayList<String> seller) {
        System.out.println("Available Seller For This Price Are:");
        for (int i = 1; i <= seller.size(); i++) {
            System.out.println(i + ". " + seller.get(i - 1));
        }
    }

    public static void printComments(ArrayList<String> userName, ArrayList<String> title, ArrayList<String> note) {
        if (userName.isEmpty()) {
            System.out.println("There Is No Comments For This Product\n" +
                    "Be The First One Who Comment :)");
        }
        for (int i = 1; i <= userName.size(); i++) {
            if (userName.get(i - 1) == null) {
                System.out.println(i + ". UserName: Guest\n" +
                        " ~Title: " + title.get(i - 1) + "\n" +
                        " ~Note: " + note.get(i - 1));
            } else
                System.out.println(i + ". UserName: " + userName.get(i - 1) + "\n" +
                        " ~Title: " + title.get(i - 1) + "\n" +
                        " ~Note: " + note.get(i - 1));
        }
    }
}
