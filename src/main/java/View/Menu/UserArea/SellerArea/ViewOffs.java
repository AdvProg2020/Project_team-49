package View.Menu.UserArea.SellerArea;

import Controller.Controller;
import Controller.SellerAreaController;
import View.Menu.Menu;

import java.util.HashMap;

public class ViewOffs extends Menu {
    public ViewOffs(Menu parentMenu) {
        super("View Offs", parentMenu);
        HashMap<String, Menu> subMenus = new HashMap<>();
        subMenus.put("View Off", getView());
        subMenus.put("Add Off", getAddOff());
        subMenus.put("Edit Off", getEditOff());
        subMenus.put("Logout", getLogout());
        this.setSubMenus(subMenus);
    }

    @Override
    public String getCommandKey(String command) {
        return super.getCommandKey(command);
    }

    private Menu getView() {
        return new Menu("View Off", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.viewOff(1);
            }
        };
    }

    private Menu getEditOff() {
        return new Menu("Edit Off", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.editOff("", "", 1);
                this.parentMenu.run("");
            }
        };
    }

    private Menu getAddOff() {
        return new Menu("Add Off", this) {
            @Override
            public void run(String lastCommand) {
                SellerAreaController.addOff(new String[3]);
                this.parentMenu.run("");
            }
        };
    }

    private void showOffs(){
        SellerAreaController.showOffs();
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

    @Override
    public void run(String lastCommand) {
        this.showOffs();
        super.run(lastCommand);
    }
}
