package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.awt.image.VolatileImage;
import java.util.ArrayList;

public class ManageAllProducts extends Menu {

    public ManageAllProducts(Menu parentMenu) {
        super("Manage All Products", parentMenu);
    }

    private void showAllProducts() {
        ArrayList<String> products = ManagerAreaController.showAllProducts();
        View.printString("Products:");
        for (String product : products) {
            View.printString("product name: " + product.split("\\s")[0]);
            View.printString("product Id: " + product.split("\\s")[1]);
            View.printString("product brand: " + product.split("\\s")[2]);
            View.printString("product price: " + product.split("\\s")[3]);
            View.printString("product average score: " + product.split("\\s")[4]);
            View.printString("product explanation: " + product.split("\\s")[5]);
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
                break;
            }
            View.printString("invalid command");
        }
        this.parentMenu.run(lastCommand);
    }
}
