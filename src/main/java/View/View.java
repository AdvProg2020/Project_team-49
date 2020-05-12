package View;

import View.Menu.MainMenu;
import View.Menu.Menu;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    public View() {

    }
    public static void run() {
        Menu.setScanner(new Scanner(System.in));
        new MainMenu().run("");
    }

    public static void printAllProduct(ArrayList<Long> productsId,
                                       ArrayList<String> productName,
                                       ArrayList<Double> productPrice,
                                       ArrayList<Double> offPercentage,
                                       ArrayList<Boolean> doesItHaveOff) {
        for (int i = 0; i <productsId.size() ; i++) {
            String off="";
            if (doesItHaveOff.get(i)){
                off="%OFF%"+offPercentage.get(i)+"%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n",i,productName.get(i),productPrice.get(i),off,productsId.get(i));
        }
    }

    public static void printAllOffProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage) {
        for (int i = 0; i <productsId.size() ; i++) {
            String off="%OFF%"+offPercentage.get(i)+"%";
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n",i,productName.get(i),productPrice.get(i),off,productsId.get(i));
        }
    }


    //duplicate shode alan vali be in khater hastesh ke shayad bekhaim bad az filter shodan joor digee neshon bedim
    public static void printFilteredProduct(ArrayList<Long> productsId,
                                            ArrayList<String> productName,
                                            ArrayList<Double> productPrice,
                                            ArrayList<Double> offPercentage,
                                            ArrayList<Boolean> doesItHaveOff) {
        System.out.println("Filtered Product");
        for (int i = 0; i <productsId.size() ; i++) {
            String off="";
            if (doesItHaveOff.get(i)){
                off="%OFF%"+offPercentage.get(i)+"%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n",i,productName.get(i),productPrice.get(i),off,productsId.get(i));
        }
    }

    public static void printCurrentFilter(ArrayList<String> filters) {
        System.out.println("Current Filter's are:");
        for (int i = 0; i < filters.size(); i++) {
            System.out.println(i+". "+filters.get(i));
        }
    }

    //duplicate shode alan vali be in khater hastesh ke shayad bekhaim bad az filter shodan joor digee neshon bedim
    public static void printSortedProduct(ArrayList<Long> productsId,
                                          ArrayList<String> productName,
                                          ArrayList<Double> productPrice,
                                          ArrayList<Double> offPercentage,
                                          ArrayList<Boolean> doesItHaveOff) {
        System.out.println("Sorted Product");
        for (int i = 0; i <productsId.size() ; i++) {
            String off="";
            if (doesItHaveOff.get(i)){
                off="%OFF%"+offPercentage.get(i)+"%";
            }
            System.out.printf("%d. %-30s  %-9.2f $  %-15s Id:%d\n",i,productName.get(i),productPrice.get(i),off,productsId.get(i));
        }
    }

    public static void printCurrentSort(ArrayList<String> sort){
        System.out.println("Current Sort's are");
        for (int i = 0; i < sort.size(); i++) {
            System.out.println(i+". "+sort.get(i));
        }
    }
    //in ba printAllProduct fargh dare ya na?

    public static void printCategories(ArrayList<String> categoriesName) {
        System.out.println("Available Categories are:");
        for (int i = 0; i < categoriesName.size(); i++) {
            System.out.println(i+". "+categoriesName.get(i));
        }
    }

    public static void printAvailableFilters(ArrayList<String> filters) {
        System.out.println("Available Filters are:");
        for (int i = 0; i < filters.size(); i++) {
            System.out.println(i+". "+filters.get(i));
        }
    }

    public static void printAvailableSorting(ArrayList<String> sorting) {
        System.out.println("Available Sort's are:");
        for (int i = 0; i < sorting.size(); i++) {
            System.out.println(i+". "+sorting.get(i));
        }
    }

    public static void printProductSummery(Long productId,String productName,
                                           double offPercentage,String explanation,
                                           double productPrice,String category,
                                           double productAverageScore) {
        String offDetail="";
        if (offPercentage==0){
            offDetail="This product is not on OFF(。﹏。*)";
        }else{
            offDetail="This product is on OFF("+offPercentage+"%)";
        }
        System.out.println(productName+" INFO:"+"\n"+
                "Product Id: "+productId+"\n"+
                "Price: "+productPrice+"\n"+
                offDetail+"\n"+
                "Score: "+productAverageScore+"\n"+
                "Category: "+category+"\n"+
                "More Detail:\n"+explanation);
    }

    public static void printAttributes(Long productId,String productName,
                                       double offPercentage,String explanation,
                                       double productPrice,String category,
                                       double productAverageScore,ArrayList<String> allSeller,
                                       int remainedNumber) {
        String offDetail="";
        if (offPercentage==0){
            offDetail="This product is not on OFF(。﹏。*)";
        }else{
            offDetail="This product is on OFF("+offPercentage+"%)";
        }
        System.out.println(productName+" INFO:"+"\n"+
                "Product Id: "+productId+"\n"+
                "Count of Remained:"+remainedNumber+"\n"+
                "Price: "+productPrice+"\n"+
                offDetail+"\n"+
                "Score: "+productAverageScore+"\n"+
                "Category: "+category+"\n"+
                "More Detail:\n"+explanation);
        System.out.println("you can buy this Product from:");
        for (int i = 0; i < allSeller.size(); i++) {
            System.out.println(" ~"+i+". "+allSeller.get(i));
        }
    }

    public static void printCompareProduct(String firstProductName, double firstOffPercentage,
                                           String firstExplanation, double firstProductPrice,
                                           double firstProductAverageScore,

                                           String secondProductName, double secondOffPercentage,
                                           String secondExplanation, double secondProductPrice,
                                           double secondProductAverageScore) {
        System.out.println("~~~~~~~~ Comparing "+firstProductName+"Vs. "+secondProductName+" ~~~~~~~~");
        String firstProductOffDetail="";
        String secondProductOffDetail="";
        if (firstOffPercentage!=0){
            firstProductOffDetail="~OFF("+firstOffPercentage+")";
        }
        if (secondOffPercentage!=0){
            secondProductOffDetail="~OFF("+secondOffPercentage+")";
        }
        System.out.printf("%-30s %-12.2f $ %-20s Score: %-10.2f Detail:%s\n",firstProductName,firstProductPrice,firstProductOffDetail,firstProductAverageScore,firstExplanation);
        System.out.printf("%-30s %-12.2f $ %-20s Score: %-10.2f Detail:%s\n",secondProductName,secondProductPrice,secondProductOffDetail,secondProductAverageScore,secondExplanation);
    }

    public static void printAvailableBrand(ArrayList<String> availableBrand){
        System.out.println("Available Brand's are:");
        for (int i = 0; i < availableBrand.size(); i++) {
            System.out.println(i+". "+availableBrand.get(i));
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

    public static void printOnlyUserOrGuestCanBuyProduct(){
        System.out.println("Only User Or Guest Can Buy Product");
    }

    public static void printAddToCardSuccessfullyDone(){
        System.out.println("Add to Card Successfully Done");
    }

    public static void printProductsPage(){
        System.out.println("Products Page:\n"+
                "1. Show All Product\n"+
                "2. Show Categories\n"+
                "3. Filtering Menu \n"+
                "4. Sorting Menu \n"+
                "5. Show Product\n"+
                "6. Log In\n"+
                "7. Log Out");
    }

    public static void printOffsPage(){
        System.out.println("Offs Page:\n"+
                "1. Show All Product\n"+
                "2. Show Categories\n"+
                "3. Filtering Menu \n"+
                "4. Sorting Menu \n"+
                "5. Show Product\n"+
                "6. Log In\n"+
                "7. Log Out");
    }

    public static void printCommentsMenu(){
        System.out.println("Comment Menu Instruction:\n"+
                "1. Add Comment\n"+
                "2. Log In\n"+
                "3. Log out\n"+
                "4. Help\n"+
                "5. back");
    }

    public static void printDigestMenu(){
        System.out.println("Digest Menu Instruction:\n"+
                "1. Add To Cart\n"+
                "2. Select Seller\n"+
                "3. Log In\n"+
                "4. Log Out\n"+
                "5. Help\n"+
                "6. Back");
    }

    public static void printFilteringMenu(){
        System.out.println("Filtering Menu Instruction:\n"+
                "1. Show Available Filters\n"+
                "2. Filter\n"+
                "3. Current Filter\n"+
                "4. Disable Filter\n"+
                "5. Search For [sth]\n"+
                "6. Log In\n"+
                "7. Log Out\n"+
                "8. Help\n"+
                "9. Back\n");
    }

    public static void printSortingMenu(){
        System.out.println("Sorting Menu Instruction\n" +
                "1. Show Available Sort\n" +
                "2. Sort\n" +
                "3. Current Sort\n" +
                "4. Disable Sort\n" +
                "5. Log In\n" +
                "6. Log Out\n" +
                "7. Help\n" +
                "8. Back");
    }
    public static void printShowProductMenu(){
        System.out.println("Show Product Instruction:\n" +
                "1. Digest\n" +
                "2. Attribute\n" +
                "3. Compare [productID]\n" +
                "4. Comments\n" +
                "5. Log In\n" +
                "6. Log Out\n" +
                "7. Help\n" +
                "8. Back");
    }
}
