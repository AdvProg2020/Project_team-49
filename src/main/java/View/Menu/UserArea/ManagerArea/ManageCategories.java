package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ManageCategories extends Menu {

    public ManageCategories(Menu parentMenu) {
        super("Manage Categories", parentMenu);
    }

    private void doEditCategory(String category) {
        while (true) {
            View.printString("enter field for edit (edit [field]):");
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)edit (\\S+)").matches()) {
                View.printString("enter new content:");
                String newContent = scanner.nextLine().trim();
                ManagerAreaController.editCategory(category, command.split("\\s")[1], newContent);
                continue;
            }
            if (getMatcher(command, "(?i)back").matches()) {
                break;
            }
            View.printString("invalid command");
        }
    }

    private void doAddCategory(String category) {
        ArrayList<String> info = new ArrayList<>();
        View.printString("enter name:");
        info.add(scanner.nextLine().trim());
        if (!getMatcher(info.get(0), "\\w+").matches()) {
            View.printString("invalid name");
            return;
        }
        View.printString("enter special attributes:");
        info.add(scanner.nextLine().trim());
        View.printString("enter parent category (if doesnt have enter null word):");
        info.add(scanner.nextLine().trim());
        ManagerAreaController.addCategory(info);
    }

    //kamel nist
    private void showCategories() {

    }

    @Override
    public void run(String lastCommand) {
        this.showCategories();
        while (true) {
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)remove (\\S+)").matches()) {
                if (!checkCategory(command.split("\\s")[1])) {
                    continue;
                }
                ManagerAreaController.removeCategory(command.split("\\s")[1]);
                continue;
            }
            if (getMatcher(command, "(?i)add (\\S+)").matches()) {
                if (!checkCategory(command.split("\\s")[1])) {
                    continue;
                }
                this.doAddCategory(command.split("\\s")[1]);
                continue;
            }
            if (getMatcher(command, "(?i)edit (\\S+)").matches()) {
                if (!checkCategory(command.split("\\s")[1])) {
                    continue;
                }
                this.doEditCategory(command.split("\\s")[1]);
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

    private boolean checkCategory(String category) {
        if (!getMatcher(category, "\\w+").matches()) {
            View.printString("invalid category name");
            return false;
        }
        return true;
    }
}
