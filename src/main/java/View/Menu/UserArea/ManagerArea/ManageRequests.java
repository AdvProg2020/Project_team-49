package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class ManageRequests extends Menu {

    public ManageRequests(Menu parentMenu) {
        super("Manage Requests", parentMenu);
    }

    private void showRequests() {
        ArrayList<String> requests = ManagerAreaController.showRequests();
        View.printString("Requests:");
        for (String request : requests) {
            View.printString("request Id: " + request.split("\\s")[0]);
            View.printString("request type: " + request.split("\\s")[1]);
            View.printString("________________________________________________");
        }
    }

    @Override
    public void showMenu() {
        View.printString(this.getName() + " help:");
        View.printString("details");
        View.printString("accept");
        View.printString("decline");
        View.printString("logout");
        View.printString("help");
        View.printString("back");
    }

    //??????
    @Override
    public void run(String lastCommand) {
        this.showRequests();
        while (true) {
            View.printString(this.getName().toUpperCase() + ":");
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
                View.printString(ManagerAreaController.acceptRequest(Long.parseLong(command.split("\\s")[1])));
                continue;
            }
            if (getMatcher(command, "(?i)decline (\\S+)").matches()) {
                if (!checkRequestId(command.split("\\s")[1])) {
                    continue;
                }
                View.printString(ManagerAreaController.declineRequest(Long.parseLong(command.split("\\s")[1])));
                continue;
            }
            if (getMatcher(command, "(?i)logout").matches()) {
                Controller.logout();
                View.printString("logout successful");
                allMenus.get(0).run(lastCommand);
                break;
            }
            if (getMatcher(command, "(?i)help").matches()) {
                this.showMenu();
                continue;
            }
            if (getMatcher(command, "(?i)back").matches()) {
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
