package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;

public class ManageRequests extends Menu {

    public ManageRequests(Menu parentMenu) {
        super("Manage Requests", parentMenu);
    }

    private void showRequests() {
        ManagerAreaController.showRequests();
    }

    @Override
    public void run(String lastCommand) {
        this.showRequests();
        String command = scanner.nextLine().trim();
        if (command.startsWith("details")) {
            ManagerAreaController.requestDetails(1);
            this.run("");
        }
        if (command.startsWith("accept")) {
            ManagerAreaController.acceptRequest(1);
            this.run("");
        }
        if (command.startsWith("decline")) {
            ManagerAreaController.declineRequest(1);
            this.run("");
        }
        if (lastCommand.equals("logout")) {
            Controller.logout();
            allMenus.get(0).run("");
        }
        if (command.equals("back")) {
            this.parentMenu.run("");
        }
    }
}
