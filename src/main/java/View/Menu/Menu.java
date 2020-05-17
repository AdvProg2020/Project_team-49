package View.Menu;

import Controller.DataBase;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    private String name;
    protected HashMap<String, Menu> subMenus;
    protected Menu parentMenu;
    public static Scanner scanner;
    protected static ArrayList<Menu> allMenus = new ArrayList<Menu>();

    public Menu(String name, Menu parentMenu) {
        this.name = name;
        this.parentMenu = parentMenu;
        this.subMenus = new HashMap<String, Menu>();
        allMenus.add(this);
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public static Matcher getMatcher(String command, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(command);
    }

    public String getName() {
        return name;
    }

    public void showMenu() {
        String show = this.name + " help:\n";
        for (String key : this.subMenus.keySet()) {
            show += subMenus.get(key).getName() + "\n";
        }
        show += "help\n";
        if (this.parentMenu == null) {
            show += "exit";
        } else {
            show += "back";
        }
        View.printString(show);
    }

    public void setSubMenus(HashMap<String, Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getCommandKey(String command) {
        getMatcher("", "");
        return "";
    }

    public void run(String lastCommand) {
        View.printString(this.name.toUpperCase() + ":");
        String command = scanner.nextLine().trim();
        Menu nextMenu = null;
        String key = getCommandKey(command);
        if (key.equals("invalid")) {
            nextMenu = this;
        } else if (key.equalsIgnoreCase("back")) {
            if (this.parentMenu == null) {
                DataBase.endProgram();
            } else {
                nextMenu = this.parentMenu;
            }
        } else if (key.equalsIgnoreCase("help")) {
            this.showMenu();
            nextMenu = this;
        } else {
            nextMenu = this.subMenus.get(key);
        }
        nextMenu.run(command);
    }
}
