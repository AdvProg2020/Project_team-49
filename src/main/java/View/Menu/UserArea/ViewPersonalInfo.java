package View.Menu.UserArea;

import Controller.Controller;
import View.Menu.Menu;

public class ViewPersonalInfo extends Menu {

    public ViewPersonalInfo(Menu parentMenu) {
        super("View Personal Info", parentMenu);
    }

    private void showPersonalInfo() {

    }

    @Override
    public void run(String lastCommand) {
        this.showPersonalInfo();
        if (lastCommand.startsWith("edit")) {
            Controller.editField("", "");
            this.run("");
        }
        if (lastCommand.equals("logout")) {
            Controller.logout();
            allMenus.get(0).run("");
        }
        if (lastCommand.equals("back")) {
            this.parentMenu.run("");
        }
    }
}
