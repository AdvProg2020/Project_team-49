package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;
import View.View;

import java.util.HashMap;

public class GuestArea extends Menu {

    public GuestArea(Menu parentMenu) {
        super("Guest Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("Login Menu", getLoginMenu());
        subMenus.put("Register Menu", getRegisterMenu());
        this.setSubMenus(subMenus);
    }

    private Menu getLoginMenu() {
        return new Menu("Login Menu", this) {
            @Override
            public void run(String lastCommand) {
                Controller.hasUserWithUsername("");
                Controller.isPasswordCorrect("");
                View.printString(Controller.loginAccount(""));
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getRegisterMenu() {
        return new Menu("Sign Up Menu", this) {
            @Override
            public void run(String lastCommand) {
                Controller.hasUserWithUsername("");
                View.printString(Controller.createAccount(new String[3]));
                this.parentMenu.run(lastCommand);
            }
        };
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)create account (\\S+) (\\S+)").matches()) {
            if (!checkCreateAccountCommand(command)) {
                return "invalid";
            }
            return "Register Menu";
        } else if (getMatcher(command, "(?i)login (\\S+)").matches()) {
            if (!checkLoginCommand(command)) {
                return "invalid";
            }
            return "Login Menu";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private boolean checkCreateAccountCommand(String command) {
        if (!getMatcher(command, "(?i)create account (manager|seller|costumer) (\\S+)").matches()) {
            View.printString("invalid account type");
            return false;
        }
        if (!getMatcher(command, "(?i)create account (\\S+) (\\w+)").matches()) {
            View.printString("invalid username");
            return false;
        }
        return true;
    }

    private boolean checkLoginCommand(String command) {
        if (!getMatcher(command, "(?i)login (\\w+)").matches()) {
            View.printString("invalid username");
            return false;
        }
        return true;
    }
}
