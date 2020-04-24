package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;
import View.Menu.UserArea.CostumerArea.CostumerArea;
import View.Menu.UserArea.ManagerArea.ManagerArea;
import View.Menu.UserArea.SellerArea.SellerArea;

import java.util.HashMap;

public class UserArea extends Menu {
    public UserArea(Menu parentMenu) {
        super("User Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("Costumer Area", new CostumerArea(this));
        subMenus.put("Guest Area", new GuestArea(this));
        subMenus.put("Seller Area", new SellerArea(this));
        subMenus.put("Manager Area", new ManagerArea(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (Controller.getCurrentUserType().equals("Guest")) {
            return  "Guest Area";
        }
        if (Controller.getCurrentUserType().equals("Costumer")) {
            return  "Costumer Area";
        }
        if (Controller.getCurrentUserType().equals("Seller")) {
            return  "Seller Area";
        }
        if (Controller.getCurrentUserType().equals("Manager")) {
            return  "Manager Area";
        }
        return null;
    }
}
