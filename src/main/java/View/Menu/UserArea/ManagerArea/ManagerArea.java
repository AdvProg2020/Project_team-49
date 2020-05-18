package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.Menu.UserArea.ViewPersonalInfo;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerArea extends Menu {

    public ManagerArea(Menu parentMenu) {
        super("Manager Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("Create Discount Code", getCreateDiscountCode());
        subMenus.put("Logout", getLogout());
        subMenus.put("Manage All Products", new ManageAllProducts(this));
        subMenus.put("Manage Categories", new ManageCategories(this));
        subMenus.put("Manage Requests", new ManageRequests(this));
        subMenus.put("Manage Users", new ManageUsers(this));
        subMenus.put("View Discount Codes", new ViewDiscountCodes(this));
        subMenus.put("View Personal Info", new ViewPersonalInfo(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)create discount code").matches()) {
            return "Create Discount Code";
        } else if (getMatcher(command, "(?i)view personal info").matches()) {
            return "View Personal Info";
        } else if (getMatcher(command, "(?i)manage users").matches()) {
            return "Manage Users";
        } else if (getMatcher(command, "(?i)manage all products").matches()) {
            return "Manage All Products";
        } else if (getMatcher(command, "(?i)view discount codes").matches()) {
            return "View Discount Codes";
        } else if (getMatcher(command, "(?i)manage Requests").matches()) {
            return "Manage Requests";
        } else if (getMatcher(command, "(?i)manage categories").matches()) {
            return "Manage Categories";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    //kamel nist
    private Menu getCreateDiscountCode() {
        return new Menu("Create Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> info = getDiscountCodeInfo();
                if (!getMatcher(info.get(0), "\\w+").matches()) {
                    View.printString("invalid Id");
                } else if (!getMatcher(info.get(1), "dd/MM/yyyy HH:mm:ss").matches()) {
                    View.printString("invalid start time");
                } else if (!getMatcher(info.get(2), "dd/MM/yyyy HH:mm:ss").matches()) {
                    View.printString("invalid end time");
                } else if (!getMatcher(info.get(3), "\\d+").matches()) {
                    View.printString("invalid percentage");
                } else if (!getMatcher(info.get(4), "\\d+").matches()) {
                    View.printString("invalid maximum amount");
                } else if (!getMatcher(info.get(5), "\\d+").matches()) {
                    View.printString("invalid repeat times");
                } else {
                    View.printString(ManagerAreaController.createDiscountCode(info));
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getLogout() {
        return new Menu("Logout", this) {
            @Override
            public void run(String lastCommand) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run(lastCommand);
            }
        };
    }

    public void showSpecifications() {
        String[] info = Controller.getCurrentUserSpecifications().split("\\s");
        for (int i = 0; i < info.length - 1; i++) {
            View.printString(info[i]);
        }
    }

    @Override
    public void run(String lastCommand) {
        this.showSpecifications();
        super.run(lastCommand);
    }

    private ArrayList<String> getDiscountCodeInfo() {
        ArrayList<String> info = new ArrayList<>();
        View.printString("enter discount Id:");
        info.add(scanner.nextLine().trim());
        View.printString("enter start time(dd/MM/yyyy HH:mm:ss):");
        info.add(scanner.nextLine().trim());
        View.printString("enter end time(dd/MM/yyyy HH:mm:ss):");
        info.add(scanner.nextLine().trim());
        View.printString("enter discount percentage:");
        info.add(scanner.nextLine().trim());
        View.printString("enter maximum discount:");
        info.add(scanner.nextLine().trim());
        View.printString("enter repeated times for discount code:");
        info.add(scanner.nextLine().trim());
        View.printString("enter allowed costumers(inter them in one line and separated with space):");
        info.add(scanner.nextLine().trim());
        return info;
    }
}