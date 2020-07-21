package Bank.Controller;

import Bank.Model.Token;
import Bank.View.BankServer;

public class TokenController {


    public static String isTokenExpired(String bankToken) {
        Token token = getTokenByTokenID(bankToken);
        if(token.isAlive()){
            return "false";
        }else{
            return "true";
        }
    }

    private static Token getTokenByTokenID(String bankToken) {
        for (Token token : BankServer.onlineUsers.keySet()) {
            if(token.getTokenId().equals(bankToken)) return token;
        }
        return null;
    }
}
