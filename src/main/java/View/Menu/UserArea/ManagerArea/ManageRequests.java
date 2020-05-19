package View.Menu.UserArea.ManagerArea;

import Controller.Controller;
import Controller.DataBase;
import Controller.ManagerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ManageRequests extends Menu {

    public ManageRequests(Menu parentMenu) {
        super("Manage Requests", parentMenu);
    }

    private void showRequests() {
        ArrayList<String> requests = ManagerAreaController.showRequests();
        View.printString("Requests:");
        for (String request : requests) {
            View.printString("request Id: " + request.split(",")[0]);
            View.printString("request type: " + request.split(",")[1]);
            View.printString("________________________________________________");
        }
    }

    private void showRequestDetails(String details) {
        View.printString("request Id: " + details.split("\\r?\\n")[0]);
        View.printString("request type: " + details.split("\\r?\\n")[1]);
        if (details.split("\\r?\\n")[1].equals("Add Off")) {
            View.printString("seller username: " + details.split("\\r?\\n")[2]);
            View.printString("off percent: " + details.split("\\r?\\n")[3]);
            View.printString("off start date: " + details.split("\\r?\\n")[4]);
            View.printString("off end date: " + details.split("\\r?\\n")[5]);
            View.printString("products: " + details.split("\\r?\\n")[6]);
        } else if (details.split("\\r?\\n")[1].equals("Add Product")) {
            View.printString("product Id: " + details.split("\\r?\\n")[2]);
            View.printString("product name: " + details.split("\\r?\\n")[3]);
            View.printString("product brand: " + details.split("\\r?\\n")[4]);
            View.printString("product explanation: " + details.split("\\r?\\n")[5]);
            View.printString("product price: " + details.split("\\r?\\n")[6]);
            View.printString("product seller: " + details.split("\\r?\\n")[7]);
        } else if (details.split("\\r?\\n")[1].equals("Add Seller")) {
            View.printString("seller username: " + details.split("\\r?\\n")[2]);
            View.printString("company info: " + details.split("\\r?\\n")[3]);
        } else if (details.split("\\r?\\n")[1].equals("Edit Off")) {
            View.printString("edit field: " + details.split("\\r?\\n")[2]);
            View.printString("old content: " + details.split("\\r?\\n")[3]);
            View.printString("new content: " + details.split("\\r?\\n")[4]);
        } else if (details.split("\\r?\\n")[1].equals("Edit Product")) {
            View.printString("edit field: " + details.split("\\r?\\n")[2]);
            View.printString("old content: " + details.split("\\r?\\n")[3]);
            View.printString("new content: " + details.split("\\r?\\n")[4]);
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
        View.printString("exit");
        View.printString("______________");
    }

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
                String details = ManagerAreaController.requestDetails(Long.parseLong(command.split("\\s")[1]));
                if (details.startsWith("request")) {
                    View.printString(details);
                } else {
                    showRequestDetails(details);
                }
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
            if (getMatcher(command, "(?i)exit").matches()) {
                DataBase.endProgram();
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
