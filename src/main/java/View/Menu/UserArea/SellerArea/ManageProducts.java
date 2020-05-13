package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.HashMap;

public class ManageProducts extends Menu {

    public ManageProducts(Menu parentMenu) {
        super("Manage Products", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Product", getView());
        subMenus.put("View Buyers", getViewBuyers());
        subMenus.put("Edit Product", getEdit());
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view (\\S+)").matches()) {
            if (!checkId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "View Product";
        } else if (getMatcher(command, "(?i)view buyers (\\S+)").matches()) {
            if (!checkId(command.split("\\s")[2])) {
                return "invalid";
            }
            return "View Buyers";
        } else if (getMatcher(command, "(?i)edit (\\S+)").matches()) {
            if (!checkId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "Edit Product";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getView() {
        return new Menu("view",this) {
            @Override
            public void run(String lastCommand) {
                String info = SellerAreaController.viewProduct(Long.parseLong(lastCommand.split("\\s")[1]));
                if (info.equals("product not exist")) {
                    View.printString(info);
                } else {
                    View.printString("product name: " + info.split("\\s")[0]);
                    View.printString("product Id: " + info.split("\\s")[1]);
                    View.printString("product brand: " + info.split("\\s")[2]);
                    View.printString("product explanation: " + info.split("\\s")[3]);
                    View.printString("_____________________________________________");
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getViewBuyers() {
        return new Menu("View Buyers", this) {
            @Override
            public void run(String lastCommand) {
                String answer = SellerAreaController.viewProductBuyers(Long.parseLong(lastCommand.split("\\s")[2]));
                if (answer.equals("product not exist")) {
                    View.printString(answer);
                } else {
                    View.printString("buyers: " + answer);
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getEdit() {
        return new Menu("Edit", this) {
            @Override
            public void run(String lastCommand) {
                while (true) {
                    View.printString("enter field:");
                    String field = scanner.nextLine().trim();
                    if (field.matches("(?i)back")) {
                        break;
                    }
                    View.printString("enter new content:");
                    String content = scanner.nextLine().trim();
                    if (content.matches("(?i)back")) {
                        break;
                    }
                    View.printString(SellerAreaController.editProduct(field, content, Long.parseLong(lastCommand.split("\\s")[1])));
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

    //kamel nist
    private void showProducts(){
        SellerAreaController.viewSellerProducts();
    }

    @Override
    public void run(String lastCommand) {
        this.showProducts();
        super.run(lastCommand);
    }

    private boolean checkId(String Id) {
        if (!getMatcher(Id, "\\d+").matches()) {
            View.printString("invalid product Id");
            return false;
        }
        return true;
    }
}
