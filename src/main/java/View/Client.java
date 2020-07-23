package View;

import Controller.Controller;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class Client {
    private String bankToken;
    private String token;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ED ed;
    private String type;

    public Client() {
        this.bankToken = "";
        this.token = "";
        this.type = "guest";
        this.socket = null;
        this.dataInputStream = null;
        this.dataOutputStream = null;
        System.out.println("yea");
        View.setClient(this);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public String getToken() {
        return token;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public String getType() {
        return type;
    }


    public void run() {
        try {
            connectToServer();
            getGuestToken();
            View.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer() throws IOException {
        this.socket = new Socket("127.0.0.1", 5678);
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        String key = dataInputStream.readUTF();
        System.out.println(this.type);
        ed = new ED(key);
    }

    public void getGuestToken() throws IOException {
        dataOutputStream.writeUTF(ed.encrypt("getGuestToken"));
        dataOutputStream.flush();
        token = ed.decrypt(dataInputStream.readUTF());
    }

    public String setCategoriesInMainBar() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("setCategoriesInMainBar"));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasUserWithUsername(String username) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("hasUserWithUsername!@" + username));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
            if (answer.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void createAccount(ArrayList<String> accountInformation, String type) {
        String request = "createAccount!@";
        for (String s : accountInformation) {
            request += s + "!@";
        }
        request += type;
        try {
            dataOutputStream.writeUTF(ed.encrypt(request));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordCorrect(String password, String username) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("isPasswordCorrect!@" + username + "!@" + password));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
            if (answer.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void loginAccount(String username) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("loginAccount!@" + username + "!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        type = answer.split("!@")[0].toLowerCase();
        token = answer.split("!@")[1];
    }

    public void clickedOnACategoryOnMainBar(String category) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("clickedOnACategoryOnMainBar"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(category));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToOffsAndDiscountsPageFromMainBar() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("goToOffsAndDiscountsPageFromMainBar"));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSubCategories() {
        String rawCategories = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getSubCategories"));
            dataOutputStream.flush();
            rawCategories = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue = new ArrayList<String>();
        if (!rawCategories.isEmpty()) {
            for (int i = 0; i < rawCategories.split("!@").length; i++) {
                returnValue.add(rawCategories.split("!@")[i]);
            }
        }
        return returnValue;
    }

    public void startSong(String path) {
        cancelSong();
        String command = "startSong";
        command = command.concat("!@");
        command = command.concat(path);
//        try {
//            dataOutputStream.writeUTF(ed.encrypt(command));
//            dataOutputStream.flush();
//            dataInputStream.readUTF();
//        } catch (IOException e) {
//            System.out.println("song Cant Start");
//            e.printStackTrace();
//        }
    }

    public void cancelSong() {
        String command = "cancelSong";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            System.out.println("song cant Canceled");
            e.printStackTrace();
        }
    }

    public boolean getIsDoesItOffPage() {
        String command = "getIsDoesItOffPage";
        String rawGetIsItOffPage = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getIsDoesItOffPage"));
            dataOutputStream.flush();
            rawGetIsItOffPage = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            System.out.println("getIsDoesItOffPage problem");
            e.printStackTrace();
        }
        if (rawGetIsItOffPage.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }

    }

    public int getHowMuchLeftForThisPage(long counter) {
        String command = "getHowMuchLeftForThisPage";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        int returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Integer.parseInt(ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            System.out.println("getHowMuchLeftForThisPage problem");
            e.printStackTrace();
        }
        return returnValue;
    }

    public ArrayList<String> getForFxmlProductImage(long counter){
        String command="getForFxmlProductImage";
        command=command.concat("!@");
        command=command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                returnValue.add(s);
            }
        }
        return returnValue;
    }

    public ArrayList<Double> getProductPriceForFxml(long counter) {
        String command = "getProductPriceForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        int check=rawOutput.split("!@").length;
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                returnValue.add(Double.parseDouble(s));
            }
        }
        return returnValue;
    }

    public ArrayList<String> getProductNameForFxml(long counter) {
        String command = "getProductNameForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                returnValue.add(s);
            }
        }
        return returnValue;
    }

    public ArrayList<Double> getProductScoreForFxml(long counter) {
        String command = "getProductScoreForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {

            for (String s : rawOutput.split("!@")) {
                returnValue.add(Double.parseDouble(s));
            }
        }
        return returnValue;
    }

    public ArrayList<Double> getProductOffRemainForFxml(long counter) {
        String command = "getProductOffRemainForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {

            for (String s : rawOutput.split("!@")) {
                returnValue.add(Double.parseDouble(s));
            }
        }
        return returnValue;
    }

    public ArrayList<Long> getProductIdForFxml(long counter) {
        String command = "getProductIdForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Long> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                returnValue.add(Long.parseLong(s));
            }
        }
        return returnValue;
    }

    public ArrayList<Boolean> getOffForFxml(long counter) {
        String command = "getOffForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Boolean> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                if (s.equalsIgnoreCase("true")) {
                    returnValue.add(true);
                } else {
                    returnValue.add(false);
                }
            }
        }
        return returnValue;
    }

    public ArrayList<Integer> getProductOffPercentForFxml(long counter) {
        String command = "getProductOffPercentForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {

            for (String s : rawOutput.split("!@")) {
                returnValue.add(Integer.parseInt(s));
            }
        }
        return returnValue;
    }

    public ArrayList<Integer> getProductRemainForFxml(long counter) {
        String command = "getProductRemainForFxml";
        command = command.concat("!@");
        command = command.concat(String.valueOf(counter));
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {

            for (String s : rawOutput.split("!@")) {
                returnValue.add(Integer.parseInt(s));
            }
        }
        return returnValue;
    }

    public ArrayList<String> getCurrentUser() {
        ArrayList<String> user = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getCurrentUser!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] info = answer.split("!@");
        for (String s : info) {
            user.add(s);
        }
        return user;
    }

    public String setCostumerAreaAndCartButtons() {
        String type;
        try {
            dataOutputStream.writeUTF("setCostumerAreaAndCartButtons!@" + this.type);
            dataOutputStream.flush();
            type = dataInputStream.readUTF();
            return type;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasHeadManager() {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("hasHeadManager"));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void setUserInfo(String field, String content) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("set" + field + "!@" + token + "!@" + content));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSellLogs() {
        ArrayList<String> sellLogs = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getSellLogs!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!answer.equalsIgnoreCase("")) {
            sellLogs.addAll(Arrays.asList(answer.split("#\\$")));
        }
        return sellLogs;
    }

    public File getProductImage(long productId) {
        return null;
    }

    public ArrayList<String> getSellerOffs() {
        ArrayList<String> offs = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getSellerOffs!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!answer.equalsIgnoreCase("")) {
            offs.addAll(Arrays.asList(answer.split("#\\$")));
        }
        return offs;
    }

    public ArrayList<String> getSellerProducts() {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getSellerProduct!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> products = new ArrayList<>();
        if (!answer.equalsIgnoreCase("")) {
            products.addAll(Arrays.asList(answer.split("#\\$")));
        }
        return products;
    }

    public ArrayList<String> getBuyLogs() {
        ArrayList<String> buyLogs = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getBuyLogs!@" + token));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!answer.equalsIgnoreCase("")) {
            buyLogs.addAll(Arrays.asList(answer.split("#\\$")));
        }
        return buyLogs;
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getCategories"));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!answer.equalsIgnoreCase("")) {
            categories.addAll(Arrays.asList(answer.split("#\\$")));
        }
        return categories;
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> allUsers = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getAllUsers"));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        allUsers.addAll(Arrays.asList(answer.split("#\\$")));
        return allUsers;
    }

    public void deleteUser(String username) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("deleteUser!@" + username));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAvailableBrands() {
        String command = "getAvailableBrands";
        String rawOutput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue=new ArrayList<>();
        if (!rawOutput.isEmpty()) {
            for (String s : rawOutput.split("!@")) {
                returnValue.add(s);
            }
        }
        return returnValue;
    }

    public void removeCategory(String category) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("removeCategory!@" + category));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCategory(String category, String field, String content) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("editCategory!@" + category + "!@" + field + "!@" + content));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disableBrandFilterByName(String name) {
        String command = "disableBrandFilterByName";
        command = command.concat("!@");
        command = command.concat(name);
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getRequests() {
        ArrayList<String> requests = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getAllActiveRequests"));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        requests.addAll(Arrays.asList(answer.split("#\\$")));
        return requests;
    }

    public void filtering(String filterType, String name) {
        String command = "filterBy";
        command = command.concat("!@");
        command = command.concat(filterType);
        command = command.concat("!@");
        command = command.concat(name);
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(String name, String attribute, String parent) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("addCategory!@" + name + "!@" + attribute + "!@" + parent));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void priceFiltering(double min, double max) {
        String command = "priceFiltering";
        command = command.concat("!@");
        command = command.concat(String.valueOf(min));
        command = command.concat("!@");
        command = command.concat(String.valueOf(max));
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isThereAnyCategoryWithName(String name) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("isThereAnyCategoryWithName!@" + name));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void answerRequest(String answer, String Id) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("answerRequest!@" + answer + "!@" + Id));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disableFilter(String type) {
        String command = "disableFilter";
        command = command.concat("!@");
        command = command.concat(type);
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("logout!@" + token));
            dataOutputStream.flush();
            dataInputStream.readUTF();
            getGuestToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        type = "guest";
    }

    public void setDoesItOffPage(boolean setter) {
        String command = "setDoesItOffPage";
        command = command.concat("!@");
        command = command.concat(String.valueOf(setter));
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDiscountCodes() {
        ArrayList<String> discountCodes = new ArrayList<>();
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getDiscountCodes"));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        discountCodes.addAll(Arrays.asList(answer.split("#\\$")));
        return discountCodes;
    }

    public void removeDiscountCode(String discountId) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("removeDiscountCode!@" + discountId));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAllPageNumber() {
        String command = "getAllPageNumber";
        int number = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            number = Integer.parseInt(ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }

    public String[] getAvailableProductsForOff(String username) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getAvailableProductsForOff!@" + username));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.split("#\\$");
    }

    public void addOff(ArrayList<String> info) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("addOff!@" + info.get(0) + "!@" + info.get(1) + "!@" + info.get(2) + "!@" + info.get(3)));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToBankServer(boolean shouldOpenBankPage) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("goToBankServer"));
            dataOutputStream.flush();
            dataOutputStream.flush();

            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isThereAnyAccountWithUsernameInBank(String username) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("isThereAnyAccountWithUsernameInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(username));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void createAccountInBank(String message) { // not fucking complete
        try {
            dataOutputStream.writeUTF(ed.encrypt("createAccountInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(message));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean isPasswordCorrectForBankAccount(String password, String username) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("isPasswordCorrectForBankAccount"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(username + "!@" + password));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getTokenInBank(String username) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("getTokenInBank"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(username));
            dataOutputStream.flush();
            bankToken = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isTokenExpired() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("isTokenExpired"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String[] getBankAccountInformation() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("getBankAccountInformation"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            return response.split("!@");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isThereAnyBankAccountWithID(String id) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("isThereAnyBankAccountWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(id));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void createReceipt(String type, String money, String source, String destination, String description) {
        try {
            String message = type + "!@" + money + "!@" + source + "!@" + destination + "!@" + description;
            dataOutputStream.writeUTF(ed.encrypt("createReceipt"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(message));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isThereAnyReceiptWithID(String receiptID) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("isThereAnyReceiptWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            if (response.equals("false")) return false;
            if (response.equals("true")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String[] getReceiptAndAccountDetailForPay(String receiptID) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("getReceiptAndAccountDetailForPay"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            return response.split("!@");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void payThisReceipt(String receiptID) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("payThisReceipt"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void editProduct(String field, String content, long productId) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("editProduct!@" + field + "!@" + content + "!@" + productId));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasProductWithName(String name) {
        String answer = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("hasProductWithName!@" + name));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answer.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void removeProduct(long productId) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("removeProduct!@" + productId));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(ArrayList<String> info, File file, String fileType) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("addProduct!@" + file.length() + "!@" + fileType + "!@"
                    + info.get(0) + "!@" + info.get(1) + "!@" + info.get(2) + "!@" + info.get(3)
                    + "!@" + info.get(4) + "!@" + info.get(5)));
            dataOutputStream.flush();
            dataInputStream.readUTF();
            byte[] buffer = new byte[4096];
            FileInputStream fileInputStream = new FileInputStream(file);
            long readBytes = 0;
            while (fileInputStream.read(buffer) > 0) {
                dataOutputStream.write(buffer);
            }
            dataOutputStream.flush();
            connectToServer();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProductImageAddress(String productId) {
        String answer;
        String path = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getProductImage!@" + productId));
            dataOutputStream.flush();
            answer = ed.decrypt(dataInputStream.readUTF());
            path = "src/main/resources/photos/productPhotos/clientPhotos/" + answer.split("!@")[0];
            File file = new File(path);
            file.createNewFile();
            long remainingBytes = Long.parseLong(answer.split("!@")[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int readBytes = 0;
            byte[] buffer = new byte[4096];
            while ((readBytes = dataInputStream.read(buffer)) > 0) {
                remainingBytes -= readBytes;
                fileOutputStream.write(buffer, 0, readBytes);
            }
            connectToServer();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public void sorting(String type) {
        String command = "SortBy";
        command = command.concat("!@");
        command = command.concat(type);
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser() {
        String command = "setCurrentUserForProductPage";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkScoreBuyer(long productId) {
        String command = "checkScoreBuyer";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String check = ed.decrypt(dataInputStream.readUTF());
            if (check.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public double getScoreAfterCheckScoreBuyer(long productId) {
        String command = "getScoreAfterCheckScoreBuyer";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        double returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Double.parseDouble(ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public boolean getDoesItHaveDiscount(long productId) {
        String command = "getDoesItHaveDiscount";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String check = ed.decrypt(dataInputStream.readUTF());
            if (check.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public boolean getDoesItHaveOff(long productId) {
        String command = "getDoesItHaveOff";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String check = ed.decrypt(dataInputStream.readUTF());
            if (check.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public int getProductAllSellerById(long productId) {
        String command = "getProductAllSellerById";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        int returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Integer.parseInt(ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public String getSellerOfProductByIdCompanyName(long productId, int index) {
        String command = "getSellerOfProductByIdCompanyName";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(String.valueOf(index));
        String returnValue = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = (ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public int remainingProductForSellerByUserName(long productId, String userName) {
        String command = "getSellerOfProductByIdCompanyName";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(userName);
        int returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Integer.parseInt((ed.decrypt(dataInputStream.readUTF())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public String getProductSellerNameByIndex(long productId, int index) {
        String command = "getProductSellerNameByIndex";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(String.valueOf(index));
        String returnValue = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = (ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public double getProductPriceBySellerUserName(long productId, String userName) {
        String command = "getProductPriceBySellerUserName";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(userName);
        double returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Double.parseDouble((ed.decrypt(dataInputStream.readUTF())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public double getProductDiscountPercentage(long productId) {
        String command = "getProductDiscountPercentage";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        double returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Double.parseDouble((ed.decrypt(dataInputStream.readUTF())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public String getProductParentCategory(long productId) {
        String command = "getProductParentCategory";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        String returnValue = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = (ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public int getProductCommentsSize(long productId) {
        String command = "getProductCommentsSize";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        int returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Integer.parseInt((ed.decrypt(dataInputStream.readUTF())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public int getRemainingItems(long productId) {
        String command = "getRemainingItems";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        int returnValue = 0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue = Integer.parseInt((ed.decrypt(dataInputStream.readUTF())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public ArrayList<String> getProductAllParentCategories(long productId) {
        String command = "getProductAllParentCategories";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        String rawInput = "";
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = (ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            returnValue.add(rawInput.split("!@")[i]);
        }
        return returnValue;
    }

    public ArrayList<Integer> getStrangeInfoForProductPage(long productId) {
        String command = "getStrangeInfoForProductPage";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        String rawInput = "";
        ArrayList<Integer> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = (ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            returnValue.add(Integer.valueOf(rawInput.split("!@")[i]));
        }
        return returnValue;
    }

    public boolean isCurrentUserManagerOrSeller() {
        String command = "isCurrentUserManagerOrSeller";
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String rawInput = (ed.decrypt(dataInputStream.readUTF()));
            if (rawInput.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public boolean isCurrentUserManagerOrGuest() {
        String command = "isCurrentUserManagerOrGuest";
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String rawInput = (ed.decrypt(dataInputStream.readUTF()));
            if (rawInput.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public void addToCart(long productId, String sellerUserName, int count) {
        String command = "addToCart";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(sellerUserName);
        command = command.concat("!@");
        command = command.concat(String.valueOf(count));
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getProductInfoForProductPage() {
        String command = "getProductInfoForProductPage";
        String rawInput = "";
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            returnValue.add(rawInput.split("!@")[i]);
        }
        return returnValue;
    }

    public boolean canRate(long productId) {
        String command = "canRate";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String rawInput = (ed.decrypt(dataInputStream.readUTF()));
            if (rawInput.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public void rateTheProduct(long productId, double score) {
        String command = "rateTheProduct";
        command = command.concat("!@");
        command = command.concat(String.valueOf(productId));
        command = command.concat("!@");
        command = command.concat(String.valueOf(score));
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCurrentUserGuest() {
        String command = "isCurrentUserGuest";
        boolean returnValue = false;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            String rawInput = (ed.decrypt(dataInputStream.readUTF()));
            if (rawInput.equalsIgnoreCase("true")) {
                returnValue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public void addComment(long productId, String title, String comment) {
        String command = "addComment" + "!@" + productId + "!@" + title + "!@" + comment;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCommentsNoted(long productId) {
        String command = "getCommentsNoted" + "!@" + productId;
        String rawInput = "";
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            returnValue.add(rawInput.split("!@")[i]);
        }
        return returnValue;
    }

    public ArrayList<String> getCommentsUserNames(long productId) {
        String command = "getCommentsUserNames" + "!@" + productId;
        String rawInput = "";
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            returnValue.add(rawInput.split("!@")[i]);
        }
        return returnValue;
    }

    public ArrayList<Boolean> getCommentsIsUserBought(long productId) {
        String command = "getCommentsIsUserBought" + "!@" + productId;
        String rawInput = "";
        ArrayList<Boolean> returnValue = new ArrayList<>();
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rawInput.split("!@").length; i++) {
            if (rawInput.split("!@")[i].equalsIgnoreCase("true")) {
                returnValue.add(true);
            } else {
                returnValue.add(false);
            }
        }
        return returnValue;
    }

    public String getCurrentUserUserName() {
        String command = "getCurrentUserUserName";
        String rawInput = "";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawInput = ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawInput;
    }

    public void clickSound() {
        String command = "clickSound";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getReceiptsWithGivenType(String type) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("getReceiptsWithGivenType"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(type + "!@" + bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            return response.split("#\\$");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getReceiptDetailsWithID(String receiptID) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("getReceiptDetailsWithID"));
            dataOutputStream.flush();
            dataOutputStream.writeUTF(ed.encrypt(receiptID + "!@" + bankToken));
            dataOutputStream.flush();
            String response = ed.decrypt(dataInputStream.readUTF());
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    class ED {
        private Cipher ecipher;
        private Cipher dcipher;
        private SecretKey key;

        public ED(String keyString) {
            try {
                byte[] decodedKey = Base64.getDecoder().decode(keyString);
                for (int i = 0; i < decodedKey.length; i++) {
                    if (i % 2 == 0) {
                        decodedKey[i] = (byte) (decodedKey[i] + 1);
                    } else {
                        decodedKey[i] = (byte) (decodedKey[i] - 1);
                    }
                }
                this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
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
    }

    // !@
    // #$   BETWEEN OBJECTS
}
