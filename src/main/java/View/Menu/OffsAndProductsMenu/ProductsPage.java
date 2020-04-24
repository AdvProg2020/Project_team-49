package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;

import java.util.HashMap;

public class ProductsPage extends Menu {

    public ProductsPage(Menu parentMenu) {
        super("Products Page", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("Show All Product", new ShowAllProducts(this));
        subMenus.put("Show Categories", new ShowCategories(this));
        subMenus.put("Filtering", new Filtering(this));
        subMenus.put("Sorting", new Sorting(this));
        subMenus.put("Show Product", new ShowProduct(this));
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
    public String getCommandKey(String command) {
        return super.getCommandKey(command);
    }
}