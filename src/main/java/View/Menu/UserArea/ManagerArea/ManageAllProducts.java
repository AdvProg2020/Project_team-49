package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.DataBase;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ManageAllProducts extends Menu {

    public ManageAllProducts(Menu parentMenu) {
        super("Manage All Products", parentMenu);
    }

    private void showAllProducts() {
        ArrayList<String> products = ManagerAreaController.showAllProducts();
        View.printString("Products:");
        for (String product : products) {
            View.printString("product name: " + product.split(",")[0]);
            View.printString("product Id: " + product.split(",")[1]);
            View.printString("product brand: " + product.split(",")[2]);
            View.printString("product price: " + product.split(",")[3]);
            View.printString("product average score: " + product.split(",")[4]);
            View.printString("product explanation: " + product.split(",")[5]);
            View.printString("_____________________________________________________");
        }
    }

    @Override
    public void showMenu() {
        View.printString(this.getName() + ":");
        View.printString("Remove Product");
        View.printString("Logout");
        View.printString("help");
        View.printString("back");
        View.printString("exit");
        View.printString("______________");
    }

    @Override
    public void run(String lastCommand) {
        this.showAllProducts();
        while (true) {
            View.printString(this.getName().toUpperCase() + ":");
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)remove (\\S+)").matches()) {
                if (!getMatcher(command.split("\\s")[1], "\\d+").matches()) {
                    View.printString("invalid product Id");
                } else {
                    View.printString(ManagerAreaController.deleteProduct(Long.parseLong(command.split("\\s")[1])));
                }
                continue;
            }
            if (getMatcher(command, "(?i)logout").matches()) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run(lastCommand);
                break;
            }
            if (getMatcher(command, "(?i)help").matches()) {
                this.showMenu();
                continue;
            }
            if (getMatcher(command, "(?i)back").matches()) {
                this.parentMenu.run(lastCommand);
                break;
            }
            if (getMatcher(command, "(?i)exit").matches()) {
                DataBase.endProgram();
            }
            View.printString("invalid command");
        }
        this.parentMenu.run(lastCommand);
    }
}
