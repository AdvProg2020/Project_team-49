package Controller;

import Models.Category;
import Models.Comment;
import Models.Product;
import Models.Score;
import Models.User.Guest;
import Models.User.Manager;
import Models.User.Seller;
import Models.User.User;
import View.View;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import javafx.scene.control.Control;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class Server {
    public static HashMap<String, User> onlineUsers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        DataBase.dataBaseRun();
        new ServerImp().run();
    }

    static class ServerImp {
        public void run() throws IOException {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket clientSocket;
                clientSocket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                new ClientHandler(clientSocket, dataOutputStream, dataInputStream).start();
            }
        }
    }

    static class ClientHandler extends Thread {

        private Socket socket;
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private ED ed;

        public ClientHandler(Socket socket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
            this.socket = socket;
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            ed = new ED();
        }

        private void handleClient() {
            try {
                byte[] key = ed.getKey().getEncoded();
                for (int i = 0; i < key.length; i++) {
                    if (i % 2 == 0) {
                        key[i] = (byte) (key[i] - 1);
                    } else {
                        key[i] = (byte) (key[i] + 1);
                    }
                }
                dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(key));
                dataOutputStream.flush();
                while (true) {
                    String command = ed.decrypt(dataInputStream.readUTF());
                    if (command.startsWith("setCategoriesInMainBar")) {
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getAllCategories()));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("clickedOnACategoryOnMainBar")) {
                        Filter.restartFilters();
                        Controller.setDoesItOffPage(false);
                        Filter.filterByCategory(ed.decrypt(dataInputStream.readUTF()));
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("goToOffsAndDiscountsPageFromMainBar")) {
                        Filter.restartFilters();
                        Controller.setDoesItOffPage(true);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equalsIgnoreCase("getSubCategories")){
                        String rawCategories="";
                        for (String showSubCategory : Filter.showSubCategories()) {
                            rawCategories.concat(showSubCategory);
                            rawCategories.concat("!@");
                        }
                        rawCategories=rawCategories.substring(0,rawCategories.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawCategories));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("cancelSong")){
                        Controller.cancelSong();
                        dataOutputStream.writeUTF(ed.encrypt("songCanceled"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("startSong")){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Controller.startSong(command.split("!@")[1]);
                            }
                        }).start();
                        dataOutputStream.writeUTF(ed.encrypt("songStarted"));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getIsDoesItOffPage")){
                        if (Controller.isDoesItOffPage()){
                            dataOutputStream.writeUTF(ed.encrypt("true"));
                            dataOutputStream.flush();
                        }else {
                            dataOutputStream.writeUTF(ed.encrypt("false"));
                            dataOutputStream.flush();
                        }
                    }
                    if (command.startsWith("getHowMuchLeftForThisPage")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        int returnValue=Controller.getHowMuchLeftForThisPage(counter);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(returnValue)));
                        dataOutputStream.flush();
                    }

                    if (command.startsWith("getProductImageForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (String s : Controller.getProductImageForFxml(counter)) {
                            rawInput.concat(s);
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductPriceForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Double aDouble : Controller.getProductPriceForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductNameForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (String s : Controller.getProductNameForFxml(counter)) {
                            rawInput.concat(s);
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductScoreForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Double aDouble : Controller.getProductScoreForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffRemainForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Double aDouble : Controller.getProductOffRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductIdForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Long aDouble : Controller.getProductIdForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getOffForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Boolean aDouble : Controller.getOffForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffPercentForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (int aDouble : Controller.getProductOffPercentForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductRemainForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (int aDouble : Controller.getProductRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getAvailableBrands")){
                        String rawInput="";
                        for (String availableBrand : Filter.getAvailableBrands()) {
                            rawInput.concat(availableBrand);
                            rawInput.concat("!@");
                        }
                        rawInput=rawInput.substring(0,rawInput.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("disableBrandFilterByName")){
                        Filter.disableBrandFilter(command.split("!@")[1]);
                    }
                    if (command.startsWith("filterBy")){
                        if (command.split("!@")[1].equalsIgnoreCase("Category")){
                            Filter.filterByCategory(command.split("!@")[2]);
                            continue;
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Brand")){
                            Filter.setIsItFilteredByBrand(true);
                            Filter.addBrand(command.split("!@")[2]);
                            Filter.filter();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Name")){
                            Filter.filterByName(command.split("!@")[2]);
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Off")){
                            Filter.filterByOffs();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Availability")){
                            Filter.filterByAvailability();
                        }
                        dataOutputStream.writeUTF("");
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("disableFilter")){
                        if (command.split("!@")[1].equalsIgnoreCase("Name")){
                            Filter.disableNameFilter();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Off")){
                            Filter.disableOffsFilter();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Availability")){
                            Filter.disableAvailabilityFilter();
                        }
                        dataOutputStream.writeUTF("");
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("setDoesItOffPage")){
                        if (command.split("!@")[1].equalsIgnoreCase("true")){
                            Controller.setDoesItOffPage(true);
                        }else {
                            Controller.setDoesItOffPage(false);
                        }
                        dataOutputStream.writeUTF("");
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getAllPageNumber")){
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getAllPageNumber())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("SortBy")){
                        if (command.split("!@")[1].equalsIgnoreCase("disable")){
                            Sort.disableSort();
                            dataOutputStream.writeUTF("");
                            dataOutputStream.flush();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("View")){
                            Sort.sortByView();
                            dataOutputStream.writeUTF("");
                            dataOutputStream.flush();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Time")){
                            Sort.sortByTime();
                            dataOutputStream.writeUTF("");
                            dataOutputStream.flush();
                        }
                        if (command.split("!@")[1].equalsIgnoreCase("Score")){
                            Sort.sortByScore();
                            dataOutputStream.writeUTF("");
                            dataOutputStream.flush();
                        }
                    }
                    if (command.equalsIgnoreCase("priceFiltering")){
                        double min=Double.parseDouble(command.split("!@")[1]);
                        double max=Double.parseDouble(command.split("!@")[2]);
                        Filter.disablePriceFilter();

                        Filter.setIsItFilteredByPrice(true);
                        Filter.setMinPrice(min);
                        Filter.setMaxPrice(max);
                        Filter.filter();
                        dataOutputStream.writeUTF("");
                    }
                    if (command.equalsIgnoreCase("setCurrentUserForProductPage")){
                        DataBase.getAllUsers().get(0);
                        dataOutputStream.writeUTF("");
                    }
                    if (command.startsWith("checkScoreBuyer")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        for (Score score : Controller.getProductById(productId).getAllScores()) {
                            if (score.getBuyer().equals(Controller.getCurrentUser())) {
                                dataOutputStream.writeUTF(ed.encrypt("true"));
                                dataOutputStream.flush();
                            }
                        }
                    }
                    if (command.startsWith("getScoreAfterCheckScoreBuyer")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        for (Score score : Controller.getProductById(productId).getAllScores()) {
                            if (score.getBuyer().equals(Controller.getCurrentUser())) {
                                dataOutputStream.writeUTF(ed.encrypt(String.valueOf(score.getScore())));
                                dataOutputStream.flush();
                            }
                        }
                    }
                    if (command.startsWith("getDoesItHaveDiscount")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getDoesItHaveDiscount())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getDoesItHaveOff")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getDoesItHaveOff())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductAllSellerById")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getAllSellers().size())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getSellerOfProductByIdCompanyName")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        int index=Integer.parseInt(command.split("!@")[2]);
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getProductById(productId).getAllSellers().get(index).getCompanyName()));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getSellerOfProductByIdCompanyName")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String userName=command.split("!@")[2];
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getRemainingItemsForSeller(Controller.getProductById(productId).getSellerByUsername(userName)))));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductSellerNameByIndex")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        int index=Integer.parseInt(command.split("!@")[2]);
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getProductById(productId).getAllSellers().get(index).getUsername()));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductPriceBySellerUserName")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String userName=command.split("!@")[2];
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getPrice(Controller.getProductById(productId).getSellerByUsername(userName)))));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductDiscountPercentage")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getDiscountPercentage())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductParentCategory")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getProductById(productId).getParentCategory().getName()));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductCommentsSize")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).getAllComments().size())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getRemainingItems")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getProductById(productId).remainingItems())));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductAllParentCategories")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        ArrayList<String> address = new ArrayList<>();
                        Product product=Controller.getProductById(productId);
                        address.add(product.getName());
                        Category category = product.getParentCategory();
                        while (category != null) {
                            address.add(category.getName());
                            category = category.getParentCategory();
                        }
                        for (String s : address) {
                            output=output.concat(s);
                            output=output.concat("!@");
                        }
                        output=output.substring(0,output.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getStrangeInfoForProductPage")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        Product product=Controller.getProductById(productId);
                        int one=0;
                        int two=0;
                        int three=0;
                        int four=0;
                        int five=0;
                        for (Score score : product.getAllScores()) {
                            double n = score.getScore();
                            if (n >= 0 && n < 1) {
                                one++;
                            } else if (n >= 1 && n < 2) {
                                two++;
                            } else if (n >= 2 && n < 3) {
                                three++;
                            } else if (n >= 3 && n < 4) {
                                four++;
                            } else if (n >= 4 && n <= 5) {
                                five++;
                            }
                        }
                        String output=one+"!@"+two+"!@"+three+"!@"+four+"!@"+five;
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("isCurrentUserManagerOrSeller")){
                        if (Controller.currentUser instanceof Manager || Controller.getCurrentUser() instanceof Seller){
                            dataOutputStream.writeUTF(ed.encrypt("true"));
                            dataOutputStream.flush();
                        }
                        dataOutputStream.writeUTF(ed.encrypt("false"));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("addToCart")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String sellerUserName=command.split("!@")[2];
                        int count=Integer.parseInt(command.split("!@")[3]);
                        Controller.addToCart(Controller.getProductById(productId),Controller.getProductById(productId).getSellerByUsername(sellerUserName),count);
                        dataOutputStream.writeUTF(ed.encrypt(""));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("isCurrentUserManagerOrGuest")){
                        if (Controller.currentUser instanceof Manager || Controller.getCurrentUser() instanceof Guest){
                            dataOutputStream.writeUTF(ed.encrypt("true"));
                            dataOutputStream.flush();
                        }
                        dataOutputStream.writeUTF(ed.encrypt("false"));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getProductInfoForProductPage")){
                        Product product=Controller.getSelectedProduct();
                        String output=product.getName();
                        output=output.concat("!@");
                        output=output.concat(product.getImageAddress());
                        output=output.concat("!@");
                        output=output.concat(String.valueOf(product.getProductId()));
                        output=output.concat("!@");
                        output=output.concat(String.valueOf(product.remainingItems()));
                        output=output.concat("!@");
                        output=output.concat(product.getBrand());
                        output=output.concat("!@");
                        output=output.concat(product.getExplanation());
                        output=output.concat("!@");
                        output=output.concat(String.valueOf(product.getAllScores().size()));
                        output=output.concat("!@");
                        output=output.concat(String.valueOf(product.getAverageScore()));
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("rateTheProduct")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        double score=Double.parseDouble(command.split("!@")[2]);
                        CostumerAreaController.rate(productId, score);
                        dataOutputStream.writeUTF(ed.encrypt(""));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("canRate")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String outPut="false";
                        if (CostumerAreaController.canRate(productId)){
                            outPut="true";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(outPut));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("isCurrentUserGuest")){
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(Controller.getCurrentUserType().equalsIgnoreCase("guest"))));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("addComment")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String title=command.split("!@")[2];
                        String comment=command.split("!@")[3];
                        OffAndProductMenuController.addCommentsById(productId, title, comment);
                        dataOutputStream.writeUTF(ed.encrypt(""));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCommentsNoted")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        for (Comment comment : Controller.getProductById(productId).getAllComments()) {
                            output=output.concat(comment.getNote());
                            output=output.concat("!@");
                        }
                        output=output.substring(0,output.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCommentsUserNames")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        for (Comment comment : Controller.getProductById(productId).getAllComments()) {
                            output=output.concat(comment.getUserWhoComment().getUsername());
                            output=output.concat("!@");
                        }
                        output=output.substring(0,output.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCommentsIsUserBought")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        for (Comment comment : Controller.getProductById(productId).getAllComments()) {
                            output=output.concat(String.valueOf(comment.isUserBuyThisProduct()));
                            output=output.concat("!@");
                        }
                        output=output.substring(0,output.length()-2);
                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getCurrentUserUserName")){
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getCurrentUser().getUsername()));
                        dataOutputStream.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            handleClient();
        }

        class ED {
            private Cipher ecipher;
            private Cipher dcipher;
            private SecretKey key;

            public ED() {
                try {
                    key = KeyGenerator.getInstance("DES").generateKey();
                    ecipher = Cipher.getInstance("DES");
                    dcipher = Cipher.getInstance("DES");
                    ecipher.init(Cipher.ENCRYPT_MODE, key);
                    dcipher.init(Cipher.DECRYPT_MODE, key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public Cipher getEcipher() {
                return ecipher;
            }

            public Cipher getDcipher() {
                return dcipher;
            }

            public SecretKey getKey() {
                return key;
            }

            public String encrypt(String str) {
                try {
                    byte[] utf8 = str.getBytes("UTF8");
                    byte[] enc = ecipher.doFinal(utf8);
                    enc = BASE64EncoderStream.encode(enc);
                    return new String(enc);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public String decrypt(String str) {
                try {
                    byte[] dec = BASE64DecoderStream.decode(str.getBytes());
                    byte[] utf8 = dcipher.doFinal(dec);
                    return new String(utf8, "UTF8");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            public String generateToken() {
                try {
                    boolean flag = true;
                    while (true) {
                        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
                        if (onlineUsers.keySet().contains(key.toString())) flag = false;
                        if (!flag) {
                            break;
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                return key.toString();
            }
        }
    }
}
