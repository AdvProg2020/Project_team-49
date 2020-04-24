package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;
import View.Menu.UserArea.ViewPersonalInfo;
import View.View;

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
        return null;
    }

    private Menu getViewCompanyInformation() {
        return new Menu("View Company Information", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.viewCompanyInfo();
                this.parentMenu.run("");
            }
        };
    }

    private Menu getViewSalesHistory() {
        return new Menu("View Sales History", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.viewSalesHistory();
                View.printString("");
                this.parentMenu.run("");
            }
        };
    }

    private Menu getAddProduct() {
        return new Menu("Add Product", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.addProduct(new String[3]);
                this.parentMenu.run("");
            }
        };
    }

    private Menu getRemoveProduct() {
        return new Menu("Remove Product", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.removeProduct(1);
                this.parentMenu.run("");
            }
        };
    }

    private Menu getShowCategories() {
        return new Menu("Show Categories", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.showCategories();
                this.parentMenu.run("");
            }
        };
    }

    private Menu getViewBalance() {
        return new Menu("View Balance", this) {
            @Override
            public void run(String lastCommand) {
                Controller.getBalance();
                this.parentMenu.run("");
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

    public void showSpecifications() {
        Controller.getCurrentUserSpecifications();
    }

    @Override
    public void run(String lastCommand) {
        this.showSpecifications();
        super.run(lastCommand);
    }
}
