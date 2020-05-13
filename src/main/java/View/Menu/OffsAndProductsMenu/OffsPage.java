package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;

import java.util.HashMap;

public class OffsPage extends Menu {

    public OffsPage(Menu parentMenu) {
        super("Offs", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("Show All Off Products", new ShowOffProducts(this));
        subMenus.put("Show Categories", new ShowCategories(this));
        subMenus.put("Filtering", new Filtering(this));
        subMenus.put("Sorting", new Sorting(this));
        subMenus.put("Show Product", new ShowProduct(this));
//        new ShowOffProducts(this).run("");
        subMenus.put("Log In",new UserArea(this));
        subMenus.put("Log Out",getLogout());
        //bara show Product bayad havasam bashe commond ba id pass bedam
        this.setSubMenus(subMenus);
    }

    private Menu getLogout() {
        return new Menu("Logout", this) {
            @Override
            public void run(String lastCommand) {
                Controller.logout();
                allMenus.get(0).run("");
            }
        };
    }

    @Override
    public void run(String lastCommand) {
        View.printOffsPage();
        super.run(lastCommand);
    }

    @Override
    public String getCommandKey(String command) {
        View.printOffsPage();

        if (command.equalsIgnoreCase("Show All Product")){
            return "Show All Product";
        }
        if (command.equalsIgnoreCase("Show Categories")){
            return "Show Categories";
        }
        if (command.equalsIgnoreCase("Filtering")){
            return "Filtering";
        }
        if (command.equalsIgnoreCase("Sorting")){
            return "Sorting";
        }
        if (command.equalsIgnoreCase("Show Product")){
            return "Show Product";
        }
        if (command.equalsIgnoreCase("Log In")){
            return "Log In";
        }
        if (command.equalsIgnoreCase("Log Out")){
            return "Log Out";
        }
        View.printString("invalid command");
        return "invalid";
    }
}
