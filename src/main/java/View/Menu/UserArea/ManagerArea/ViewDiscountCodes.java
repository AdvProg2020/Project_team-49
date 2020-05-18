package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;
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
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getViewDiscountCode() {
        return new Menu("View Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> info =  ManagerAreaController.viewDiscountCode(lastCommand.split("\\s")[3]);
                if (info.size() == 1) {
                    View.printString(info.get(0));
                } else {
                    View.printString("discount Id: " + info.get(0));
                    View.printString("start date: " + info.get(1));
                    View.printString("end date: " + info.get(2));
                    View.printString("discount percent: " + info.get(3));
                    View.printString("maximum amount: " + info.get(4));
                    View.printString("accepted use times: " + info.get(5));
                    View.printString("allowed costumers: " + info.get(6));
                    View.printString("_______________________________________________");
                }
            }
        };
    }

    //kamel nist (allowed costumers)
    private Menu getEditDiscountCode() {
        return new Menu("Edit Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                while (true) {
                    View.printString("enter field (available fields: percent, maximum amount):");
                    String field = scanner.nextLine().trim();
                    if (getMatcher(field, "(?i)back").matches()) {
                        break;
                    }
                    if (!checkField(field.toLowerCase())) {
                        View.printString("invalid field");
                        continue;
                    } else {
                        View.printString("enter new content:");
                        String content = scanner.nextLine().trim();
                        if (getMatcher(content, "(?i)back").matches()) {
                            break;
                        }
                        View.printString(ManagerAreaController.editDiscountCode(lastCommand.split("\\s")[3], field, content));
                    }
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getRemoveDiscountCode() {
        return new Menu("Remove Discount Code", this) {
            @Override
            public void run(String lastCommand) {
                View.printString(ManagerAreaController.removeDiscountCode(lastCommand.split("\\s")[3]));
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

    //kamel nist(allowed costumers)
    private void showDiscountCodes() {
        ArrayList<String> discountCodes = ManagerAreaController.showDiscountCodes();
        View.printString("Discount Codes:");
        for (String discountCode : discountCodes) {
            View.printString("discount Id: " + discountCode.split(",")[0]);
            View.printString("start time: " + discountCode.split(",")[1]);
            View.printString("end time: " + discountCode.split(",")[2]);
            View.printString("discount percent: " + discountCode.split(",")[3]);
            View.printString("maximum discount amount: " + discountCode.split(",")[4]);
            View.printString("acceptable using times: " + discountCode.split(",")[5]);
            View.printString("______________________________________");
        }
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

    private boolean checkField(String field) {
        if (field.equals("percent")) {
            return true;
        }
        if (field.equals("maximum amount")) {
            return true;
        }
        return false;
    }
}
