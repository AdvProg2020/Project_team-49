package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;
import View.View;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.VolatileImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

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
                        if (Controller.isPasswordCorrect(input , command[1])) {
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
                } else if (Controller.getHasHeadManager() && command[2].toLowerCase().equals("manager")) {
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
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
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
        ArrayList<String> info = new ArrayList<>();
        info.add(username);
        System.out.println("enter password:");
        info.add(scanner.nextLine().trim());
        System.out.println("enter first name:");
        info.add(scanner.nextLine().trim());
        System.out.println("enter last name:");
        info.add(scanner.nextLine().trim());
        System.out.println("enter email:");
        info.add(scanner.nextLine().trim());
        System.out.println("enter phone number:");
        info.add(scanner.nextLine().trim());
        if (type.equalsIgnoreCase("costumer")) {
            System.out.println("enter initial credit:");
            info.add(scanner.nextLine().trim());
        }
        if (type.equalsIgnoreCase("seller")) {
            System.out.println("enter company name:");
            info.add(scanner.nextLine().trim());
        }
        return info;
    }

    private void doLogin() {
        this.parentMenu.run("back");
    }
}
