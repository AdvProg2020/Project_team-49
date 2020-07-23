package Controller;

import Models.Category;
import Models.DiscountCode;
import Models.Log.BuyLog;
import Models.Log.SellLog;
import Models.Off;
import Models.Product;
import Models.User.Costumer;
import Models.User.Request.Request;
import Models.User.Seller;
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
import java.awt.*;
import java.awt.image.BandedSampleModel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
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
            ServerSocket serverSocket = new ServerSocket(5678);
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
                    if (command.startsWith("hasHeadManager")) {
                        String answer = "";
                        if (Controller.getHasHeadManager()) {
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }

                    if (command.startsWith("goToBankServer")) {
                        doBankServerConnection();
                        continue;
                    }

                    if (command.startsWith("setCategoriesInMainBar")) {
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getAllCategories()));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.startsWith("hasUserWithUsername")) {
                        String answer = "";
                        if (Controller.hasUserWithUsername(command.split("!@")[1])) {
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.startsWith("isPasswordCorrect")) {
                        String password = command.split("!@")[2];
                        String username = command.split("!@")[1];
                        String answer = "";
                        if (Controller.isPasswordCorrect(password, username)) {
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.startsWith("loginAccount")) {
                        Controller.loginAccount(command.split("!@")[1]);
                        String token = ed.generateToken();
                        onlineUsers.put(token, Controller.currentUser);
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getCurrentUserType() + "!@" + token));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.startsWith("createAccount")) {
                        String[] info = command.split("!@");
                        ArrayList<String> accountInfo = new ArrayList<>();
                        for (int i = 1; i < info.length - 1; i++) {
                            accountInfo.add(info[i]);
                        }
                        Controller.createAccount(accountInfo, info[info.length - 1]);
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
                            rawCategories=rawCategories.concat(showSubCategory);
                            rawCategories=rawCategories.concat("!@");
                        }
                        if (!rawCategories.isEmpty()) {
                            rawCategories = rawCategories.substring(0, rawCategories.length() - 2);
                        }
                        dataOutputStream.writeUTF(ed.encrypt(rawCategories));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("cancelSong")){
                        Controller.cancelSong();
                        dataOutputStream.writeUTF(ed.encrypt("songCanceled"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("startSong")){
                        String comm=command;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String path=comm.split("!@")[1];
//                                Controller.startSong(path);
                            }
                        }).start();

//                        dataOutputStream.writeUTF(ed.encypt("songStarted"));
//                        dataOutputStream.flush();
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

                    if (command.startsWith("getForFxmlProductImage")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (String s : Controller.getProductImageForFxml(counter)) {
                            rawInput=rawInput.concat(s);
                            rawInput=rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }
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
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }
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
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductScoreForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Double aDouble : Controller.getProductScoreForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffRemainForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Double aDouble : Controller.getProductOffRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductIdForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Long aDouble : Controller.getProductIdForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getOffForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (Boolean aDouble : Controller.getOffForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffPercentForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (int aDouble : Controller.getProductOffPercentForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductRemainForFxml")){
                        long counter=Long.parseLong(command.split("!@")[1]);
                        String rawInput="";
                        for (int aDouble : Controller.getProductRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getAvailableBrands")){
                        String rawInput="";
                        for (String availableBrand : Filter.getAvailableBrands()) {
                            rawInput.concat(availableBrand);
                            rawInput.concat("!@");
                        }
                        if (rawInput.split("!@").length>1) {
                            rawInput = rawInput.substring(0, rawInput.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
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
                    if (command.startsWith("getCurrentUser")) {
                        String token = command.split("!@")[1];
                        String user = "";
                        user += onlineUsers.get(token).getUsername() + "!@";
                        user += onlineUsers.get(token).getPassword() + "!@";
                        user += onlineUsers.get(token).getFirstName() + "!@";
                        user += onlineUsers.get(token).getLastName() + "!@";
                        user += onlineUsers.get(token).getEMail() + "!@";
                        user += String.valueOf(onlineUsers.get(token).getPhoneNumber()) + "!@";
                        if (onlineUsers.get(token).getType().equalsIgnoreCase("costumer")) {
                            user += String.valueOf(((Costumer) onlineUsers.get(token)).getCredit());
                        }
                        if (onlineUsers.get(token).getType().equalsIgnoreCase("seller")) {
                            user += String.valueOf(((Seller) onlineUsers.get(token)).getCredit()) + "!@";
                            user += ((Seller) onlineUsers.get(token)).getCompanyName();
                            String products = "";
                            boolean test = false;
                            for (Product product : ((Seller) onlineUsers.get(token)).getProductsForSale()) {
                                if (test) {
                                    products += "@#";
                                }
                                products += product.getProductId();
                                test = true;
                            }
                            user += products;
                        }
                        dataOutputStream.writeUTF(ed.encrypt(user));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.startsWith("getSellLogs")) {
                        Seller seller = (Seller) onlineUsers.get(command.split("!@")[1]);
                        String answer = "";
                        for (SellLog sellLog : seller.getSellHistory()) {
                            answer += sellLog.toString() + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getSellerOffs")) {
                        Seller seller = (Seller) onlineUsers.get(command.split("!@")[1]);
                        String answer = "";
                        for (Off off : seller.getOffs()) {
                            answer += off.toString() + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getSellerProduct")) {
                        Seller seller = (Seller) onlineUsers.get(command.split("!@")[1]);
                        String answer = "";
                        for (Product product : seller.getProductsForSale()) {
                            String info = "";
                            info += product.getProductId() + "!@";
                            info += product.getName() + "!@";
                            info += product.getBrand() + "!@";
                            info += product.getPrice(seller) + "!@";
                            info += product.getExplanation() + "!@";
                            info += product.getRemainingItemsForSeller(seller);
                            answer += info + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getBuyLogs")) {
                        Costumer costumer = (Costumer) onlineUsers.get(command.split("!@")[1]);
                        String answer = "";
                        for (BuyLog buyLog : costumer.getBuyHistory()) {
                            answer += buyLog.toString() + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCategories")) {
                        String answer = "";
                        for (Category allCategory : DataBase.getAllCategories()) {
                            String info = "";
                            info += allCategory.getName() + "!@";
                            info += allCategory.getSpecialAttributes()+ "!@";
                            //paretn cagetgori mitone null bashe
                            if (allCategory.getParentCategory()!=null) {
                                info += allCategory.getParentCategory().getName();
                            }
                            answer += info + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getAllUsers")) {
                        String answer = "";
                        for (User allUser : DataBase.getAllUsers()) {
                            String user = "";
                            user += allUser.getType() + "!@";
                            user += allUser.getUsername() + "!@";
                            user += allUser.getFirstName() + "!@";
                            user += allUser.getLastName() + "!@";
                            user += allUser.getEMail() + "!@";
                            user += allUser.getPhoneNumber();
                            if (allUser.getType().equalsIgnoreCase("seller")) {
                                user += "!@" + ((Seller) allUser).getCompanyName();
                            }
                            answer += user + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("deleteUser")) {
                        ManagerAreaController.deleteUser(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("removeCategory")) {
                        ManagerAreaController.removeCategory(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("editCategory")) {
                        String field = command.split("!@")[2];
                        ManagerAreaController.editCategory(command.split("!@")[1], field, command.split("!@")[3]);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getAllActiveRequests")) {
                        String answer = "";
                        for (Request allActiveRequest : DataBase.getAllActiveRequests()) {
                            answer += allActiveRequest.toString() + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("addCategory")) {
                        String name = command.split("!@")[1];
                        String attribute = command.split("!@")[2];
                        String parent = command.split("!@")[3];
                        if (parent.equalsIgnoreCase("null")) {
                            DataBase.addCategory(new Category(name, attribute, null));
                        } else {
                            DataBase.addCategory(new Category(name, attribute, DataBase.getCategoryByName(parent)));
                        }
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("isThereAnyCategoryWithName")) {
                        String answer = "";
                        if (command.split("!@").length>1){
                            if (DataBase.isThereAnyCategoryWithName(command.split("!@")[1])) {
                                answer = "true";
                            }
                        }
                        else {
                            answer = "false";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("answerRequest")) {
                        long id = Long.parseLong(command.split("!@")[2]);
                        if (command.split("!@")[1].equalsIgnoreCase("accept")) {
                            ManagerAreaController.acceptRequest(id);
                        } else {
                            ManagerAreaController.declineRequest(id);
                        }
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("logout")) {
                        String token = command.split("!@")[1];
                        onlineUsers.remove(token);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getDiscountCodes")) {
                        String answer = "";
                        for (DiscountCode allDiscountCode : DataBase.getAllDiscountCodes()) {
                            answer += allDiscountCode.toString() + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("removeDiscountCode")) {
                        ManagerAreaController.removeDiscountCode(command.split("!@")[1]);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getAvailableProductsForOff")) {
                        ArrayList<String> products = SellerAreaController.getAvailableProductsForOff(command.split("!@")[1]);
                        String answer = "";
                        for (String product : products) {
                            answer += product + "#$";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
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
                        if (output.split("!@").length>1) {
                            output = output.substring(0, output.length() - 2);
                        }
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
                        if (output.split("!@").length>1) {
                            output = output.substring(0, output.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCommentsUserNames")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        for (Comment comment : Controller.getProductById(productId).getAllComments()) {
                            output=output.concat(comment.getUserWhoComment().getUsername());
                            output=output.concat("!@");
                        }
                        if (output.split("!@").length>1) {
                            output = output.substring(0, output.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getCommentsIsUserBought")){
                        long productId=Long.parseLong(command.split("!@")[1]);
                        String output="";
                        for (Comment comment : Controller.getProductById(productId).getAllComments()) {
                            output=output.concat(String.valueOf(comment.isUserBuyThisProduct()));
                            output=output.concat("!@");
                        }
                        if (output.split("!@").length>1) {
                            output = output.substring(0, output.length() - 2);
                        }                        dataOutputStream.writeUTF(ed.encrypt(output));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getCurrentUserUserName")){
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getCurrentUser().getUsername()));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("addOff")) {
                        ArrayList<String> info = new ArrayList<String>(Arrays.asList(command.split("!@")));
                        info.remove(0);
                        SellerAreaController.addOff(info);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("editProduct")) {
                        String field = command.split("!@")[1];
                        String newContent = command.split("!@")[2];
                        long productId = Long.parseLong(command.split("!@")[3]);
                        SellerAreaController.editProduct(field, newContent, productId);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("hasProductWithName")) {
                        String answer = "";
                        if (SellerAreaController.hasProductWithName(command.split("!@")[1])) {
                            answer = "true";
                        } else {
                            answer = "false";
                        }
                        dataOutputStream.writeUTF(ed.encrypt(answer));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("removeProduct")) {
                        SellerAreaController.removeProduct(Long.parseLong(command.split("!@")[1]));
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("addProduct")) {
                        ArrayList<String> info = new ArrayList<>(Arrays.asList(command.split("!@")));
                        info.remove(0);
                        long remainingBytes = Long.parseLong(info.get(0));
                        info.remove(0);
                        String fileType = info.get(0);
                        info.remove(0);
                        dataOutputStream.writeUTF(ed.encrypt("done"));
                        dataOutputStream.flush();
                        String path = "src/main/resources/photos/productPhotos/APRI" + (DataBase.getCreatedRequests() + 1) + "." + fileType;
                        info.add(path);
                        File file = new File(path);
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        int readBytes = 0;
                        byte[] buffer = new byte[4096];
                        while (true) {
                            readBytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, remainingBytes));
                            remainingBytes -= readBytes;
                            fileOutputStream.write(buffer, 0, readBytes);
                            if (remainingBytes == 0) {
                                break;
                            }
                        }
                        dataInputStream.close();
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        SellerAreaController.addProduct(info);
                        fileOutputStream.close();
                    }
                    if (command.startsWith("getProductImage")) {
                        File file = new File(DataBase.getProductById(Long.parseLong(command.split("!@")[1])).getImageAddress());
                        dataOutputStream.writeUTF(ed.encrypt(file.getName() + "!@" + file.length()));
                        dataOutputStream.flush();
                        byte[] buffer = new byte[4096];
                        FileInputStream fileInputStream = new FileInputStream(file);
                        while (fileInputStream.read(buffer) > 0) {
                            dataOutputStream.write(buffer);
                        }
                        dataOutputStream.flush();
                        fileInputStream.close();
                    }
                    if (command.equalsIgnoreCase("clickSound")){
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Controller.startClickSound();
//                            }
//                        }).start();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doBankServerConnection() {
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 8080);
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                DataInputStream din = new DataInputStream(socket.getInputStream());

                while (true){
                    String command = ed.decrypt(dataInputStream.readUTF());
                    if(command.equals("isThereAnyAccountWithUsernameInBank")){
                        String username = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("isThereAnyAccountWithUsernameInBank");
                        dout.flush();
                        dout.writeUTF(username);
                        dout.flush();
                        String bankResponse = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(bankResponse));
                        dataOutputStream.flush();
                    }
                    if(command.equals("createAccountInBank")){
                        String message = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("createAccountInBank");
                        dout.flush();
                        dout.writeUTF(message);
                        dout.flush();
                        String bankResponse = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(bankResponse));
                        dataOutputStream.flush();
                    }
                    if(command.equals("isPasswordCorrectForBankAccount")){
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("createAccountInBank");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String bankResponse = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(bankResponse));
                        dataOutputStream.flush();
                    }
                    if(command.equals("getTokenInBank")){
                        String username = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("getTokenInBank");
                        dout.flush();
                        dout.writeUTF(username);
                        dout.flush();
                        String token = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(token));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("isTokenExpired")){
                        String bankToken = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("isTokenExpired");
                        dout.flush();
                        dout.writeUTF(bankToken);
                        dout.flush();
                        String bool = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(bool));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("getBankAccountInformation")){
                        String bankToken = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("getBankAccountInformation");
                        dout.flush();
                        dout.writeUTF(bankToken);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("isThereAnyBankAccountWithID")){
                        String accountID = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("isThereAnyBankAccountWithID");
                        dout.flush();
                        dout.writeUTF(accountID);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("createReceipt")){
                        String message = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("createReceipt");
                        dout.flush();
                        dout.writeUTF(message);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("isThereAnyReceiptWithID")){
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("isThereAnyReceiptWithID");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if(command.equals("getReceiptAndAccountDetailForPay")){
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("getReceiptAndAccountDetailForPay");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("payThisReceipt")) {
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("payThisReceipt");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("getReceiptsWithGivenType")) {
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("getReceiptsWithGivenType");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("getReceiptDetailsWithID")) {
                        String input = ed.decrypt(dataInputStream.readUTF());
                        dout.writeUTF("getReceiptDetailsWithID");
                        dout.flush();
                        dout.writeUTF(input);
                        dout.flush();
                        String response = din.readUTF();
                        dataOutputStream.writeUTF(ed.encrypt(response));
                        dataOutputStream.flush();
                        continue;
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
                        if (!onlineUsers.keySet().contains(key.toString())) flag = false;
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
