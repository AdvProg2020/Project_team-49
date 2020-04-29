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
        return null;
    }

    private Menu getCreateDiscountCode() {
        return new Menu("Create Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.createDiscountCode(new String[3]);
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
        View.printString("inter start time(instruction):");
        info.add(scanner.nextLine().trim());
        View.printString("inter end time(instruction):");
        info.add(scanner.nextLine().trim());
        View.printString("inter discount percentage:");
        info.add(scanner.nextLine().trim());
        View.printString("inter maximum discount:");
        info.add(scanner.nextLine().trim());
        View.printString("inter repeated times for discount code:");
        info.add(scanner.nextLine().trim());
        View.printString("inter allowed costumers(inter them in one line and separated with space):");
        info.add(scanner.nextLine().trim());
        return info;
    }
}