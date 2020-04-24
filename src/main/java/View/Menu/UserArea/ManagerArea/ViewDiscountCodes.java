package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;

import java.util.HashMap;

public class ViewDiscountCodes extends Menu {

    public ViewDiscountCodes(Menu parentMenu) {
        super("View Discount Codes", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Discount Code", getViewDiscountCode());
        subMenus.put("Edit Discount Code", getEditDiscountCode());
        subMenus.put("Remove Discount Code", getRemoveDiscountCode());
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        return null;
    }

    private Menu getViewDiscountCode() {
        return new Menu("View Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.viewDiscountCode(1);
            }
        };
    }

    private Menu getEditDiscountCode() {
        return new Menu("Edit Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.editDiscountCode(1);
            }
        };
    }

    private Menu getRemoveDiscountCode() {
        return new Menu("Remove Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.removeDiscountCode(1);
            }
        };
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

    private void showDiscountCodes() {

    }

    @Override
    public void run(String lastCommand) {
        showDiscountCodes();
        super.run(lastCommand);
    }
}
