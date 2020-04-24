package View.Menu.UserArea.CostumerArea;

import Controller.Controller;
import Controller.CostumerAreaController;
import View.Menu.Menu;

public class ViewOrders extends Menu {

    public ViewOrders(Menu parentMenu) {
        super("View Orders", parentMenu);
    }

    private void showOrderPage(long orderId) {
        String command = scanner.nextLine().trim();
        if (command.startsWith("rate")) {
            rateProduct(1, 1);
            return;
        }
        if (command.equals("back")) {
            return;
        }
        showOrderPage(orderId);
    }

    private void rateProduct(long productId, int score) {
        CostumerAreaController.rateProduct(productId, score);
    }

    private void showOrders(){

    }

    @Override
    public void run(String lastCommand) {
        this.showOrders();
        String command = scanner.nextLine().trim();
        if (command.startsWith("show order")) {
            this.showOrderPage(1);
        }
        if (lastCommand.equals("logout")) {
            Controller.logout();
            allMenus.get(0).run("");
        }
        if (command.equals("back")) {
            this.parentMenu.run("");
        }
        this.run("");
    }
}
