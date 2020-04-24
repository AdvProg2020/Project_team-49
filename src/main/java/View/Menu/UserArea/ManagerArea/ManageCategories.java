package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;

public class ManageCategories extends Menu {

    public ManageCategories(Menu parentMenu) {
        super("Manage Categories", parentMenu);
    }

    private void doEditCategory(String command) {
        ManagerAreaController.editCategory("");
    }

    private void doAddCategory(String command) {
        ManagerAreaController.addCategory("");
    }

    private void showCategories() {

    }

    @Override
    public void run(String lastCommand) {
        this.showCategories();
        String command = scanner.nextLine().trim();
        if (command.startsWith("remove")) {
            ManagerAreaController.removeCategory("");
            this.run("");
        }
        if (command.startsWith("add")) {
            this.doAddCategory(command);
            this.run("");
        }
        if (command.startsWith("edit")) {
            this.doEditCategory(command);
            this.run("");
        }
        if (lastCommand.equals("logout")) {
            Controller.logout();
            allMenus.get(0).run("");
        }
        if (command.equals("back")){
            this.parentMenu.run("");
        }
    }
}
