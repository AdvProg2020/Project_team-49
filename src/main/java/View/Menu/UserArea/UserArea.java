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
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("Costumer Area", new CostumerArea(this));
        subMenus.put("Guest Area", new GuestArea(this));
        subMenus.put("Seller Area", new SellerArea(this));
        subMenus.put("Manager Area", new ManagerArea(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public void run(String lastCommand) {
        if (getMatcher(lastCommand, "(?i)back").matches()) {
            this.parentMenu.run(lastCommand);
        }
        if (Controller.getCurrentUserType().equals("Guest")) {
            subMenus.get("Guest Area").run(lastCommand);
        }
        if (Controller.getCurrentUserType().equals("Costumer")) {
            subMenus.get("Costumer Area").run(lastCommand);
        }
        if (Controller.getCurrentUserType().equals("Seller")) {
            subMenus.get("Seller Area").run(lastCommand);
        }
        if (Controller.getCurrentUserType().equals("Manager")) {
            subMenus.get("Manager Area").run(lastCommand);
        }
    }
}
