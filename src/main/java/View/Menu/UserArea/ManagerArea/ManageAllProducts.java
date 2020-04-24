package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;

public class ManageAllProducts extends Menu {

    public ManageAllProducts(Menu parentMenu) {
        super("Manage All Products", parentMenu);
    }

    private void showAllProducts() {
        ManagerAreaController.showAllProducts();
    }

    @Override
    public void run(String lastCommand) {
        this.showAllProducts();
        String command = scanner.nextLine().trim();
        if (command.startsWith("delete")) {
            ManagerAreaController.deleteProduct(1);
            this.run("");
        }
        if (lastCommand.equals("logout")) {
            Controller.logout();
            allMenus.get(0).run("");
        }
        if (command.equals("back")) {
            this.parentMenu.run("");
        }
    }
}
