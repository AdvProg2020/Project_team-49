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

    //field ha kaman
    private void doEditCategory(String category) {
        while (true) {
            View.printString("enter field (available fields: attribute, name):");
            String field = scanner.nextLine().trim();
            if (getMatcher(field, "(?i)back").matches()) {
                break;
            }
            if (!checkField(field.toLowerCase())) {
                View.printString("invalid field");
                continue;
            } else {
                View.printString("enter new content:");
                String newContent = scanner.nextLine().trim();
                if (getMatcher(newContent, "(?i)back").matches()) {
                    break;
                }
                View.printString(ManagerAreaController.editCategory(category, field, newContent));
            }
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

    public static void showCategories() {
        ArrayList<String> categories = ManagerAreaController.showCategories();
        View.printString("Categories:");
        for (String category : categories) {
            View.printString("name: " + category.split("\\s")[0]);
            View.printString("attributes: " + category.split("\\s")[1]);
            View.printString("parent category: " + category.split("\\s")[2]);
            View.printString("________________________________________");
        }
    }

    @Override
    public void showMenu() {
        View.printString(this.getName() + " help:");
        View.printString("remove");
        View.printString("add");
        View.printString("edit");
        View.printString("logout");
        View.printString("help");
        View.printString("back");
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

    private boolean checkField(String field) {
        if (field.equals("name")) {
            return true;
        }
        if (field.equals("attribute")) {
            return true;
        }
        return false;
    }
}
