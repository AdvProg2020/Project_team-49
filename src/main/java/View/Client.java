package View;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

public class Client {

    private String token;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ED ed;
    private String type;

    public Client() {
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
            this.socket = new Socket("127.0.0.1", 8888);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            String key = dataInputStream.readUTF();
            System.out.println(this.type);
            ed = new ED(key);
            View.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setCategoriesInMainBar() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("setCategoriesInMainBar"));
            dataOutputStream.flush();
            return ed.decrypt(dataInputStream.readUTF());
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
            dataOutputStream.writeUTF(ed.encrypt("loginAccount!@" + username));
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

    public ArrayList<String> getSubCategories(){
        String rawCategories="";
        try {
            dataOutputStream.writeUTF("getSubCategories");
            dataOutputStream.flush();
            rawCategories=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String > returnValue=new ArrayList<String>();
        for (int i = 0; i < rawCategories.split("!@").length; i++) {
            returnValue.add(rawCategories.split("!@")[i]);
        }
        return returnValue;
    }

    public void startSong(String path){
        cancelSong();
        String command="startSong";
        command.concat("!@");
        command.concat(path);
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            System.out.println("song Cant Start");
            e.printStackTrace();
        }
    }

    public void cancelSong(){
        String command="cancelSong";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            System.out.println("song cant Canceled");
            e.printStackTrace();
        }
    }

    public boolean getIsDoesItOffPage(){
        String command="getIsDoesItOffPage";
        String rawGetIsItOffPage="";
        try {
            dataOutputStream.writeUTF(ed.encrypt("getIsDoesItOffPage"));
            dataOutputStream.flush();
            rawGetIsItOffPage=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            System.out.println("getIsDoesItOffPage problem");
            e.printStackTrace();
        }
        if (rawGetIsItOffPage.equalsIgnoreCase("true")){
            return true;
        }else {
            return false;
        }

    }

    public int getHowMuchLeftForThisPage(long counter){
        String command="getHowMuchLeftForThisPage";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        int returnValue=0;
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            returnValue=Integer.parseInt(ed.decrypt(dataInputStream.readUTF()));
        } catch (IOException e) {
            System.out.println("getHowMuchLeftForThisPage problem");
            e.printStackTrace();
        }
        return returnValue;
    }

    public ArrayList<String> getProductImageForFxml(long counter){
        String command="getProductImageForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(s);
        }
        return returnValue;
    }

    public ArrayList<Double> getProductPriceForFxml(long counter){
        String command="getProductPriceForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Double.parseDouble(s));
        }
        return returnValue;
    }

    public ArrayList<String> getProductNameForFxml(long counter){
        String command="getProductNameForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(s);
        }
        return returnValue;
    }

    public ArrayList<Double> getProductScoreForFxml(long counter){
        String command="getProductScoreForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Double.parseDouble(s));
        }
        return returnValue;
    }

    public ArrayList<Double> getProductOffRemainForFxml(long counter){
        String command="getProductOffRemainForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Double> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Double.parseDouble(s));
        }
        return returnValue;
    }

    public ArrayList<Long> getProductIdForFxml(long counter){
        String command="getProductIdForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Long> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Long.parseLong(s));
        }
        return returnValue;
    }

    public ArrayList<Boolean> getOffForFxml(long counter){
        String command="getOffForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Boolean> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            if (s.equalsIgnoreCase("true")){
                returnValue.add(true);
            }else {
                returnValue.add(false);
            }
        }
        return returnValue;
    }

    public ArrayList<Integer> getProductOffPercentForFxml(long counter){
        String command="getProductOffPercentForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Integer.parseInt(s));
        }
        return returnValue;
    }

    public ArrayList<Integer> getProductRemainForFxml(long counter){
        String command="getProductRemainForFxml";
        command.concat("!@");
        command.concat(String.valueOf(counter));
        String rawOutput="";
        try {
            dataOutputStream.writeUTF(ed.encrypt(command));
            dataOutputStream.flush();
            rawOutput=ed.decrypt(dataInputStream.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> returnValue=new ArrayList<>();
        for (String s : rawOutput.split("!@")) {
            returnValue.add(Integer.parseInt(s));
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
        sellLogs.addAll(Arrays.asList(answer.split("#\\$")));
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
        offs.addAll(Arrays.asList(answer.split("#\\$")));
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
        return new ArrayList<>(Arrays.asList(answer.split("#\\$")));
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
        buyLogs.addAll(Arrays.asList(answer.split("#\\$")));
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
        categories.addAll(Arrays.asList(answer.split("#\\$")));
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

    public void addCategory(String name, String attribute, String parent) {
        try {
            dataOutputStream.writeUTF(ed.encrypt("addCategory!@" + name + "!@" + attribute + "!@" + parent));
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

    public void logout() {
        try {
            dataOutputStream.writeUTF(ed.encrypt("logout!@" + token));
            dataOutputStream.flush();
            dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        type = "guest";
        token = "";
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
