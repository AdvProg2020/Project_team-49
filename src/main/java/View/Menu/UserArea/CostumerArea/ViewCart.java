package View.Menu.UserArea.CostumerArea;

import Controller.Controller;
import Controller.CostumerAreaController;
import View.Menu.Menu;
import View.Menu.OffsAndProductsMenu.ShowProduct;
import View.View;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewCart extends Menu {

    public ViewCart(Menu parentMenu) {
        super("View Cart", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("Show Products", getShowProducts());
        subMenus.put("Increase Product", getIncreaseProduct());
        subMenus.put("Decrease Product", getDecreaseProduct());
        subMenus.put("Show Total Price", getShowTotalPrice());
        subMenus.put("Logout", getLogout());
        subMenus.put("Purchase", new Purchase(this));
        subMenus.put("Show Product", new ShowProduct(this));
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        if (getMatcher(command, "(?i)show products").matches()) {
            return "Show Products";
        } else if (getMatcher(command, "(?i)view (\\S+)").matches()) {
            if (!checkProductId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "Show Product";
        } else if (getMatcher(command, "(?i)increase (\\S+)").matches()) {
            if (!checkProductId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "Increase Product";
        } else if (getMatcher(command, "(?i)decrease (\\S+)").matches()) {
            if (!checkProductId(command.split("\\s")[1])) {
                return "invalid";
            }
            return "Decrease Product";
        } else if (getMatcher(command, "(?i)show total price").matches()) {
            return "Show Total Price";
        } else if (getMatcher(command, "(?i)purchase").matches()) {
            return "Purchase";
        } else if (getMatcher(command, "(?i)logout").matches()) {
            return "Logout";
        } else if (getMatcher(command, "(?i)help").matches()) {
            return "help";
        } else if (getMatcher(command, "(?i)back").matches()) {
            return "back";
        } else if (getMatcher(command, "(?i)exit").matches()) {
            return "exit";
        }
        View.printString("invalid command");
        return "invalid";
    }

    private Menu getShowProducts() {
        return new Menu("Show Products", this) {
            @Override
            public void run(String lastCommand) {
                ArrayList<String> products = CostumerAreaController.showProducts();
                View.printString("Products In Cart:");
                for (String product : products) {
                    View.printString("product name:" + product.split(",")[0]);
                    View.printString("product Id:" + product.split(",")[1]);
                    View.printString("product brand:" + product.split(",")[2]);
                    View.printString("product price:" + product.split(",")[3]);
                    View.printString("count:" + product.split(",")[4]);
                    View.printString("product seller:" + product.split(",")[5]);
                    View.printString("________________________________________________");
                }
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getIncreaseProduct() {
        return new Menu("Increase Product", this) {
            @Override
            public void run(String lastCommand) {
                String seller = getSellerUsername();
                View.printString(CostumerAreaController.IncreaseOrDecreaseProduct(Long.parseLong(lastCommand.split("\\s")[1]), 1, seller));
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

    private Menu getDecreaseProduct() {
        return new Menu("Decrease Product", this) {
            @Override
            public void run(String lastCommand) {
                String seller = getSellerUsername();
                View.printString(CostumerAreaController.IncreaseOrDecreaseProduct(Long.parseLong(lastCommand.split("\\s")[1]), -1, seller));
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu getShowTotalPrice() {
        return new Menu("Show Total Price", this) {
            @Override
            public void run(String lastCommand) {
                View.printString("total price: " + CostumerAreaController.getTotalPrice());
                this.parentMenu.run(lastCommand);
            }
        };
    }

    private boolean checkProductId(String Id) {
        if (!getMatcher(Id, "\\d+").matches()) {
            View.printString("invalid product Id");
            return false;
        }
        return true;
    }

    private String getSellerUsername() {
        View.printString("enter username of product's seller");
        return scanner.nextLine().trim();
    }
}
