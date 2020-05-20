package View.Menu;

import Controller.OffAndProductMenuController;
import View.Menu.OffsAndProductsMenu.OffsPage;
import View.Menu.OffsAndProductsMenu.ProductsPage;
import View.Menu.UserArea.UserArea;
import View.View;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu() {
        super("Main Menu", null);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("User Area", new UserArea(this));
        subMenus.put("Products Page", new ProductsPage(this));
        subMenus.put("Offs", new OffsPage(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)User Area").matches()) {
            return "User Area";
        } else if (getMatcher(command, "(?i)Products").matches()) {
            OffAndProductMenuController.clearAndRestoreProduct();
            return "Products Page";
        } else if (getMatcher(command, "(?i)Offs").matches()) {
            OffAndProductMenuController.clearAndRestoreProduct();
            return "Offs";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }
}
