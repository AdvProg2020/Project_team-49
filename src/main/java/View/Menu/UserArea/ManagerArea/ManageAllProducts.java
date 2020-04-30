package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

public class ManageAllProducts extends Menu {

    public ManageAllProducts(Menu parentMenu) {
        super("Manage All Products", parentMenu);
    }

    //kamel nist
    private void showAllProducts() {
        ManagerAreaController.showAllProducts();
    }

    @Override
    public void run(String lastCommand) {
        this.showAllProducts();
        while (true) {
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)remove (\\S+)").matches()) {
                if (!getMatcher(command.split("\\s")[1], "\\d+").matches()) {
                    View.printString("invalid product Id");
                } else {
                    View.printString(ManagerAreaController.deleteProduct(Long.parseLong(command.split("\\s")[1])));
                }
                continue;
            }
            if (lastCommand.equals("logout")) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run(lastCommand);
            }
            if (getMatcher(command, "(?i)back").matches()) {
                break;
            }
        }
        this.parentMenu.run(lastCommand);
    }
}
