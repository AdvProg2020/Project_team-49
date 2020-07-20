package Bank.Controller;
import static Bank.Controller.DataBase.*;
import Bank.Model.Account;

public class AccountController {
    public static Account getAccountWithUserName(String username){
        for (Account account : allAccounts) {
            if(account.getUsername().equalsIgnoreCase(username)) return account;
        }
        return null;
    }

    public static boolean isThereAnyAccountsWithUsername(String username){
        for (Account account : allAccounts) {
            if(account.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public static double getAccountBalanceWithUsername(String username){
        Account account = getAccountWithUserName(username);
        return account.getBalance();
    }
}
