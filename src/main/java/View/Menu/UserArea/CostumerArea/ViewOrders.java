package View.Menu.UserArea.CostumerArea;

import Controller.Controller;
import Controller.DataBase;
import Controller.CostumerAreaController;
import View.Menu.Menu;
import View.View;

import java.util.ArrayList;

public class ViewOrders extends Menu {

    public ViewOrders(Menu parentMenu) {
        super("View Orders", parentMenu);
    }

    private void showOrderPage(long orderId) {
        if (!CostumerAreaController.hasOrderWithId(orderId)) {
            View.printString("order not exist");
            return;
        }
        showOrder(orderId);
        while (true) {
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)rate (\\S+) (\\S)").matches()) {
                rateProduct(command.split("\\s")[1], command.split("\\s")[2], orderId);
                continue;
            }
            if (command.matches("(?i)back")) {
                break;
            }
            View.printString("invalid command");
        }
    }

    private void rateProduct(String productId, String score, long orderId) {
        if (!getMatcher(productId, "\\d+").matches()) {
            View.printString("invalid product Id");
        } else if (getMatcher(score, "\\d").matches()) {
            View.printString("invalid score");
        } else {
            View.printString(CostumerAreaController.rateProduct(Long.parseLong(productId), Integer.parseInt(score), orderId));
        }
    }

    private void showOrders(){
        ArrayList<String> orders = CostumerAreaController.getOrders();
        View.printString("Orders:");
        for (String order : orders) {
            showOrder(Long.parseLong(order.split(",")[0]));
            View.printString("______________________________________");
        }
    }

    @Override
    public void run(String lastCommand) {
        this.showOrders();
        while (true) {
            View.printString(this.getName().toUpperCase() + ":");
            String command = scanner.nextLine().trim();
            if (getMatcher(command, "(?i)show order (\\S+)").matches()) {
                if (!getMatcher(command.split("\\s")[2], "\\d+").matches()) {
                    View.printString("invalid order Id");
                } else {
                    this.showOrderPage(Long.parseLong(command.split("\\s")[2]));
                }
                this.run(lastCommand);
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
            this.run(lastCommand);
        }
    }

    private void showOrder(long orderId) {
        String info = CostumerAreaController.getOrderInfoById(orderId);
        View.printString("order Id: " + info.split(",")[0]);
        View.printString("seller name: " + info.split(",")[1]);
        View.printString("paid amount: " + info.split(",")[2]);
        View.printString("receive status: " + info.split(",")[3]);
        View.printString("bought products: " + info.split(",")[4]);
    }
}
