package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import Controller.DataBase;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;

import java.util.HashMap;

public class ProductsPage extends Menu {

    public ProductsPage(Menu parentMenu) {
        super("Products Page", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("Show All Products", new ShowAllProducts(this));
        subMenus.put("Show Categories", new ShowCategories(this));
        subMenus.put("Filtering", new Filtering(this));
        subMenus.put("Sorting", new Sorting(this));
        subMenus.put("Show Product", new ShowProduct(this));
        subMenus.put("Restore All",restoreAll());
        subMenus.put("Exit",exit());
        subMenus.put("Log In",new UserArea(this));
        subMenus.put("Log Out",getLogout());
        //bara show Product bayad havasam bashe commond ba id pass bedam
        this.setSubMenus(subMenus);


    }

    @Override
    public void run(String lastCommand) {
        View.printProductsPage();
        super.run(lastCommand);
    }

    @Override
    public String getCommandKey(String command) {

        if (command.equalsIgnoreCase("Show All Products")){

            return "Show All Products";
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
        if (command.toLowerCase().startsWith("show product ")&&command.split("\\s").length==3){
            String productIdString = command.split("\\s")[2];
            if (!productIdString.matches("^\\d+$")){
                View.printString("Please Enter Number For Product Id.");
                return "invalid";
            }
            if (!OffAndProductMenuController.isProductWithId(Long.parseLong(productIdString))){
                View.printString("There Is No Product With This Id.");
                return "invalid";
            }
            return "Show Product";
        }

        if (command.equalsIgnoreCase("Restore All")){
            return "Restore All";
        }

        if (command.equalsIgnoreCase("Log In")){
            return "Log In";
        }
        if (command.equalsIgnoreCase("Log Out")){
            return "Log Out";
        }
        if (command.equalsIgnoreCase("Help")){
            return "help";
        }
        if (command.equalsIgnoreCase("Back")){
            return "back";
        }
        if (command.equalsIgnoreCase("Exit")){
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";

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

    private Menu restoreAll(){
        return new Menu("Restore All",this) {
            @Override
            public void run(String lastCommand) {
                OffAndProductMenuController.clearAndRestoreProduct();
                parentMenu.run(lastCommand);
            }
        };
    }

    private Menu exit(){
        return new Menu("Exit",this) {
            @Override
            public void run(String lastCommand) {
                DataBase.endProgram();
            }
        };
    }
}