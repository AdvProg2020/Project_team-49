package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

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
        while (true) {
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)details (\\S+)").matches()) {
                if (!checkRequestId(command.split("\\s")[1])) {
                    continue;
                }
                ManagerAreaController.requestDetails(Long.parseLong(command.split("\\s")[1]));
                continue;
            }
            if (getMatcher(command, "(?i)accept (\\S+)").matches()) {
                if (!checkRequestId(command.split("\\s")[1])) {
                    continue;
                }
                ManagerAreaController.acceptRequest(Long.parseLong(command.split("\\s")[1]));
                continue;
            }
            if (getMatcher(command, "(?i)decline (\\S+)").matches()) {
                if (!checkRequestId(command.split("\\s")[1])) {
                    continue;
                }
                ManagerAreaController.declineRequest(Long.parseLong(command.split("\\s")[1]));
                continue;
            }
            if (lastCommand.equals("logout")) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run("");
                break;
            }
            if (command.equals("back")) {
                break;
            }
            View.printString("invalid command");
        }
        this.parentMenu.run(lastCommand);
    }

    private boolean checkRequestId(String Id) {
        if (!getMatcher(Id, "\\d+").matches()) {
            View.printString("invalid Id");
            return false;
        }
        return true;
    }
}
