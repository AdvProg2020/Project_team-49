package Controller;

import Models.User.User;
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
                    String kham = dataInputStream.readUTF();
                    System.out.println(kham);
                    String command = ed.decrypt(kham);
                    if (command.startsWith("setCategoriesInMainBar")) {
                        dataOutputStream.writeUTF(ed.encrypt(Controller.getAllCategories()));
                        dataOutputStream.flush();
                        continue;
                    }
                    if (command.equals("clickedOnACategoryOnMainBar")) {
                        Filter.restartFilters();
                        Controller.setDoesItOffPage(false);
                        String category = ed.decrypt(dataInputStream.readUTF());
                        Filter.filterByCategory(category);
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
                    if (command.equalsIgnoreCase("getSubCategories")) {
                        String rawCategories = "";
                        for (String showSubCategory : Filter.showSubCategories()) {
                            rawCategories = rawCategories.concat(showSubCategory);
                            rawCategories = rawCategories.concat("!@");
                        }
                        if (rawCategories.length() != 0)
                            rawCategories = rawCategories.substring(0, rawCategories.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawCategories));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("cancelSong")) {
                        Controller.cancelSong();
                        dataOutputStream.writeUTF(ed.encrypt("songCanceled"));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("startSong")) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Controller.startSong(command.split("!@")[1]);
                            }
                        }).start();
                        dataOutputStream.writeUTF(ed.encrypt("songStarted"));
                        dataOutputStream.flush();
                    }
                    if (command.equalsIgnoreCase("getIsDoesItOffPage")) {
                        if (Controller.isDoesItOffPage()) {
                            dataOutputStream.writeUTF(ed.encrypt("true"));
                            dataOutputStream.flush();
                        } else {
                            dataOutputStream.writeUTF(ed.encrypt("false"));
                            dataOutputStream.flush();
                        }
                    }
                    if (command.startsWith("getHowMuchLeftForThisPage")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        int returnValue = Controller.getHowMuchLeftForThisPage(counter);
                        dataOutputStream.writeUTF(ed.encrypt(String.valueOf(returnValue)));
                        dataOutputStream.flush();
                    }

                    if (command.startsWith("getProductImageForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (String s : Controller.getProductImageForFxml(counter)) {
                            rawInput.concat(s);
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductPriceForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (Double aDouble : Controller.getProductPriceForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductNameForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (String s : Controller.getProductNameForFxml(counter)) {
                            rawInput.concat(s);
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductScoreForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (Double aDouble : Controller.getProductScoreForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffRemainForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (Double aDouble : Controller.getProductOffRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductIdForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (Long aDouble : Controller.getProductIdForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getOffForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (Boolean aDouble : Controller.getOffForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductOffPercentForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (int aDouble : Controller.getProductOffPercentForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
                        dataOutputStream.writeUTF(ed.encrypt(rawInput));
                        dataOutputStream.flush();
                    }
                    if (command.startsWith("getProductRemainForFxml")) {
                        long counter = Long.parseLong(command.split("!@")[1]);
                        String rawInput = "";
                        for (int aDouble : Controller.getProductRemainForFxml(counter)) {
                            rawInput.concat(String.valueOf(aDouble));
                            rawInput.concat("!@");
                        }
                        rawInput = rawInput.substring(0, rawInput.length() - 2);
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
