package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;
import View.Menu.UserArea.ManagerArea.ManageCategories;
import View.Menu.UserArea.ViewPersonalInfo;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerArea extends Menu {

    public SellerArea(Menu parentMenu) {
        super("Seller Area", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Balance", getViewBalance());
        subMenus.put("View Company Information", getViewCompanyInformation());
        subMenus.put("View Sales History", getViewSalesHistory());
        subMenus.put("Add Product", getAddProduct());
        subMenus.put("Remove Product", getRemoveProduct());
        subMenus.put("Show Categories", getShowCategories());
        subMenus.put("Logout", getLogout());
        subMenus.put("View Personal Info", new ViewPersonalInfo(this));
        subMenus.put("Manage Products", new ManageProducts(this));
        subMenus.put("View Offs", new ViewOffs(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)view company information").matches()) {
            return "View Company Information";
        } else if (getMatcher(command, "(?i)view sales history").matches()) {
            return "View Sales History";
        } else if (getMatcher(command, "(?i)manage products").matches()) {
            return "Manage Products";
        } else if (getMatcher(command, "(?i)add product").matches()) {
            return "Add Product";
        } else if (getMatcher(command, "(?i)remove Product (\\S+)").matches()) {
            if (!getMatcher(command.split("\\s")[2], "\\d+").matches()) {
                View.printString("invalid Id");
                return "invalid";
            }
            return "Remove Product";
        } else if (getMatcher(command, "(?i)show categories").matches()) {
            return "Show Categories";
        } else if (getMatcher(command, "(?i)view offs").matches()) {
            return "View Offs";
        } else if (getMatcher(command, "(?i)view balance").matches()) {
            return "View Balance";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getViewCompanyInformation() {
        return new Menu("View Company Information", this) {
            @Override
            public void run(String lastCommand) {
                View .printString(SellerAreaController.viewCompanyInfo());
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getViewSalesHistory() {
        return new Menu("View Sales History", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> logs = SellerAreaController.viewSalesHistory();
                View.printString("Sales History:");
                View.printString("____________________");
                for (String log : logs) {
                    View.printString("log Id:");
                    View.printString(log.split(",")[0]);
                    View.printString("log Date:");
                    View.printString(log.split(",")[1]);
                    View.printString("buyer name:");
                    View.printString(log.split(",")[2]);
                    View.printString("received amount:");
                    View.printString(log.split(",")[3]);
                    View.printString("off amount:");
                    View.printString(log.split(",")[4]);
                    View.printString("____________________");
                }
                this.parentMenu.run("");
            }
        };
    }

    private Menu getAddProduct() {
        return new Menu("Add Product", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> productInfo = new ArrayList<>();
                View.printString("enter name:");
                productInfo.add(scanner.nextLine().trim());
                View.printString("enter brand:");
                productInfo.add(scanner.nextLine().trim());
                View.printString("enter price:");
                productInfo.add(scanner.nextLine().trim());
                View.printString("enter product explanations:");
                productInfo.add(scanner.nextLine().trim());
                View.printString("enter parent category:");
                productInfo.add(scanner.nextLine().trim());
                View.printString("enter available items for sale:");
                productInfo.add(scanner.nextLine().trim());
                View.printString(SellerAreaController.addProduct(productInfo));
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getRemoveProduct() {
        return new Menu("Remove Product", this) {
            @Override
            public void run(String lastCommand) {
                View.printString(SellerAreaController.removeProduct(Long.parseLong(lastCommand.split("\\s")[2])));
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getShowCategories() {
        return new Menu("Show Categories", this) {
            @Override
            public void run(String lastCommand) {
                ManageCategories.showCategories();
                this.parentMenu.run(lastCommand);
            }
        };
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
        String[] info = Controller.getCurrentUserSpecifications().split("\\s");
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
