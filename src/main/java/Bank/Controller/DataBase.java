package Bank.Controller;

import Bank.Model.Account;
import Bank.Model.Receipt;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class DataBase {
    public static ArrayList<Account> allAccounts = new ArrayList<>();
    public static ArrayList<Receipt> allReceipts = new ArrayList<>();
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static ObjectInputStream objectInputStream;
    private static ObjectOutputStream objectOutputStream;


    public static void bankDataBaseRun() {
        Path resourcesPath = Paths.get("src/main/resources");
        if (!Files.exists(resourcesPath)) {
            try {
                File resourcesDir = new File("src/main/resources");
                resourcesDir.mkdir();
            } catch (Exception e) {
                System.out.println("sorry we can't create resources directory");
                System.exit(0);
            }
        }
        Path dataBasePath = Paths.get("src/main/resources/BankDataBase");
        if (!Files.exists(dataBasePath)) {
            try {
                File dataBaseDir = new File("src/main/resources/BankDataBase");
                dataBaseDir.mkdir();
                File accountFile = new File("src/main/resources/BankDataBase/accounts.txt");
                accountFile.createNewFile();
                File receiptsFile = new File("src/main/resources/BankDataBase/receipts.txt");
                receiptsFile.createNewFile();
                return;
            } catch (Exception e) {
                System.out.println("sorry we can't create DataBase directory");
                System.exit(0);
            }
        }
        loadAllBankData();
    }

    private static void loadAllBankData() {
        loadAllAccounts();
        loadAllReceipts();
    }

    private static void loadAllReceipts() {

        try {
            inputStream = new FileInputStream("src/main/resources/BankDataBase/receipts.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allReceipts = (ArrayList<Receipt>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in bankDataBase/receipts");
        }
    }

    public static void loadAllAccounts() {

        try {
            inputStream = new FileInputStream("src/main/resources/BankDataBase/accounts.txt");
            objectInputStream = new ObjectInputStream(inputStream);
            allAccounts = (ArrayList<Account>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            System.out.println("still no files in bankDataBase/accounts");
        }
    }

    private static void saveAllAccounts(){

        try {
            outputStream = new FileOutputStream("src/main/resources/BankDataBase/accounts.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allAccounts);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save accounts");
        }
    }

    private static void saveAllReceipts(){

        try {
            outputStream = new FileOutputStream("src/main/resources/BankDataBase/receipts.txt");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(allReceipts);
            objectOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("can't save receipts");
        }
    }

    public static void saveAllBankData(){
        saveAllAccounts();
        saveAllReceipts();
    }
}
