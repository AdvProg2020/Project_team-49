package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class GuestArea extends Menu {

    public GuestArea(Menu parentMenu) {
        super("Guest Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("Login Menu", getLoginMenu());
        subMenus.put("Register Menu", getRegisterMenu());
        this.setSubMenus(subMenus);
    }

    private Menu getLoginMenu() {
        return new Menu("Login Menu", this) {
            @Override
            public void run(String lastCommand) {
                String[] command = lastCommand.split("\\s");
                if (!Controller.hasUserWithUsername(command[1])){
                    View.printString("user not exist");
                } else {
                    while (true) {
                        View.printString("enter password:");
                        String input = scanner.nextLine().trim();
                        if (getMatcher(input, "(?i)back").matches()) {
                            this.parentMenu.run(lastCommand);
                        }
                        if (!Controller.isPasswordCorrect(input)) {
                            break;
                        }
                        View.printString("invalid password");
                    }
                    View.printString(Controller.loginAccount(command[1]));
                    doLogin();
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    //has user with username!
    private Menu getRegisterMenu() {
        return new Menu("Register Menu", this) {
            @Override
            public void run(String lastCommand) {
                String[] command = lastCommand.split("\\s");
                if (Controller.hasUserWithUsername(command[3])) {
                    View.printString("user exist with this username");
                } else if (Controller.hasHeadManager() && command[2].toLowerCase().equals("manager")) {
                    View.printString("cant create manager account");
                } else {
                    View.printString(Controller.createAccount(getAccountInformation(command[3], command[2]), command[2]));
                    // error haye vorodi ha check nashode
                }
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

    private ArrayList<String> getAccountInformation(String username, String type) {
        ArrayList<String> info = new ArrayList<String>();
        info.add(username);
        View.printString("enter password:");
        info.add(scanner.nextLine().trim());
        View.printString("enter first name:");
        info.add(scanner.nextLine().trim());
        View.printString("enter last name");
        info.add(scanner.nextLine().trim());
        View.printString("enter Email:");
        info.add(scanner.nextLine().trim());
        View.printString("enter phone number:");
        info.add(scanner.nextLine().trim());
        if (type.toLowerCase().equals("seller")) {
            View.printString("enter company name:");
            info.add(scanner.nextLine().trim());
        }
        return info;
    }

    private void doLogin() {
        subMenus.get("User Area").run("");
    }
}
