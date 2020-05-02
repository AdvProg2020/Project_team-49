package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.HashMap;

public class ViewDiscountCodes extends Menu {

    public ViewDiscountCodes(Menu parentMenu) {
        super("View Discount Codes", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<String, Menu>();
        subMenus.put("View Discount Code", getViewDiscountCode());
        subMenus.put("Edit Discount Code", getEditDiscountCode());
        subMenus.put("Remove Discount Code", getRemoveDiscountCode());
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view discount code (\\S+)").matches()) {
            if (!checkCode(command.split("\\s")[3])) {
                return "invalid";
            }
            return "View Discount Code";
        } else if (getMatcher(command, "(?i)edit discount code (\\S+)").matches()) {
            if (!checkCode(command.split("\\s")[3])) {
                return "invalid";
            }
            return "Edit Discount Code";
        } else if (getMatcher(command, "(?i)remove discount code (\\S+)").matches()) {
            if (!checkCode(command.split("\\s")[3])) {
                return "invalid";
            }
            return "Remove Discount Code";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getViewDiscountCode() {
        return new Menu("View Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.viewDiscountCode(lastCommand.split("\\s")[3]);
            }
        };
    }

    //kamel nist
    private Menu getEditDiscountCode() {
        return new Menu("Edit Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.editDiscountCode(lastCommand.split("\\s")[3]);
            }
        };
    }

    private Menu getRemoveDiscountCode() {
        return new Menu("Remove Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ManagerAreaController.removeDiscountCode(lastCommand.split("\\s")[3]);
            }
        };
    }

    private Menu getLogout() {
        return new Menu("Logout", this) {
            @Override
            public void run(String lastCommand) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run(lastCommand);
            }
        };
    }

    //kamel nist
    private void showDiscountCodes() {

    }

    @Override
    public void run(String lastCommand) {
        showDiscountCodes();
        super.run(lastCommand);
    }

    private boolean checkCode(String code) {
        if (!getMatcher(code, "\\w+").matches()) {
            View.printString("invalid code");
            return false;
        }
        return true;
    }
}
