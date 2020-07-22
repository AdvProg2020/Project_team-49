package Bank.Controller;

import Bank.Model.Account;
import Bank.Model.Receipt;
import Bank.Model.Token;
import Bank.View.BankServer;

public class Controller {
    public static String giveTokenToAccount(String username) {
        Account account = AccountController.getAccountWithUserName(username);
        Token tokenPrime = null;
        for (Token token : BankServer.onlineUsers.keySet()) {
            if (BankServer.onlineUsers.get(token).equals(account)) {
                tokenPrime = token;
            }
        }
        if (tokenPrime != null) {
            BankServer.onlineUsers.remove(tokenPrime, account);
        }
        Token token = new Token(account);
        BankServer.onlineUsers.put(token, account);
        return token.getTokenId();
    }

    public static String generateToken() {
        return RandomString.getAlphaNumericString(20);
    }

    public static void payThisReceipt(String receiptID, String bankToken) {
        Account account = BankServer.onlineUsers.get(bankToken);
        Receipt receipt = ReceiptController.getReceiptWithID(receiptID, bankToken);
        receipt.setDone(true);
        account.setBalance(account.getBalance() - receipt.getMoney());
    }
}

class RandomString {

    // function to generate a random string of length n
    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
