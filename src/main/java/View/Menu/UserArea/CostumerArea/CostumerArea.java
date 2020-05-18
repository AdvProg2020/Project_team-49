package View.Menu.UserArea.CostumerArea;

import Controller.Controller;
import Controller.CostumerAreaController;
import View.Menu.Menu;
import View.Menu.UserArea.ViewPersonalInfo;
import View.View;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.ArrayList;
import java.util.HashMap;

public class CostumerArea extends Menu {

    public CostumerArea(Menu parentMenu) {
        super("Costumer Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Balance", getViewBalance());
        subMenus.put("Increase Balance", getIncreaseBalance());
        subMenus.put("View Discount Codes", getViewDiscountCodes());
        subMenus.put("View Personal Info", new ViewPersonalInfo(this));
        subMenus.put("View Cart", new ViewCart(this));
        subMenus.put("View Orders", new ViewOrders(this));
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view personal info").matches()) {
            return "View Personal Info";
        } else if (getMatcher(command, "(?i)view cart").matches()) {
            return "View Cart";
        } else if (getMatcher(command, "(?i)view orders").matches()) {
            return "View Orders";
        } else if (getMatcher(command, "(?i)increase balance").matches()) {
            return "Increase Balance";
        } else if (getMatcher(command, "(?i)view balance").matches()) {
            return "View Balance";
        } else if (getMatcher(command, "(?i)view discount codes").matches()) {
            return "View Discount Codes";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getViewBalance() {
        return new Menu("View Balance", this) {
            @Override
            public void run(String lastCommand) {
                View.printString("current balance: " + Controller.getBalance());
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getIncreaseBalance() {
        return new Menu("Increase Balance", this) {
            @Override
            public void run(String lastCommand) {
                while (true) {
                    View.printString("enter increase  in tomans (back to exit):");
                    String credit = scanner.nextLine().trim();
                    if (getMatcher(credit, "(?i)back").matches()) {
                        break;
                    }
                    if (!getMatcher(credit, "\\d+").matches()) {
                        View.printString("invalid amount");
                        continue;
                    }
                    View.printString(CostumerAreaController.increaseCredit(Long.parseLong(credit)));
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    //usege neveshte nemishe
    private Menu getViewDiscountCodes() {
        return new Menu("View Discount Codes", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> discountCodes = CostumerAreaController.viewCostumerDiscountCodes();
                View.printString("Discount Codes:");
                for (String discountCode : discountCodes) {
                    View.printString("Id: " + discountCode.split(",")[0]);
                    View.printString("start time: " + discountCode.split(",")[1]);
                    View.printString("end time: " + discountCode.split(",")[2]);
                    View.printString("discount percentage: " + discountCode.split(",")[3]);
                    View.printString("maximum amount of discount: " + discountCode.split(",")[4]);
                    View.printString("acceptable using times: " + discountCode.split(",")[5]);
                    View.printString("acceptable using times: " + discountCode.split(",")[6]);
                    View.printString("____________________________________________");
                }
                this.parentMenu.run(lastCommand);
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

    public void showSpecifications() {
        String[] info = Controller.getCurrentUserSpecifications().split(",");
        for (int i = 0; i < info.length - 1; i++) {
            View.printString(info[i]);
        }
    }

    @Override
    public void run(String lastCommand) {
        this.showSpecifications();
        super.run(lastCommand);
    }
}
