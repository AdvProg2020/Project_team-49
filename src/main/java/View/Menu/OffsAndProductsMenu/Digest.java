package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.Menu.UserArea.GuestArea;
import View.Menu.UserArea.UserArea;
import View.View;

public class Digest extends Menu {

    public Digest(Menu parentMenu) {
        super("Digest", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        long productId = Long.parseLong(lastCommand.split("\\s")[2]);
        String command = scanner.nextLine().trim();
        if (command.equals("add to cart")) {
            if (OffAndProductMenuController.isCurrentUserGuestOrUser()) {
                OffAndProductMenuController.addToCartById(productId,true,null);
                View.printAddToCardSuccessfullyDone();
                this.run(lastCommand);
            }
            View.printOnlyUserOrGuestCanBuyProduct();
            this.run(lastCommand);
        }

        if (command.equals("select seller [seller_username]")) {
            String userName=command.split("\\s")[2];
            if (OffAndProductMenuController.isCurrentUserGuestOrUser()) {
                OffAndProductMenuController.addToCartById(productId,true,userName);
                View.printAddToCardSuccessfullyDone();
                this.run(lastCommand);
            }
            View.printOnlyUserOrGuestCanBuyProduct();
            this.run(lastCommand);
        }

        if (command.equals("log in")){
            new UserArea(this);
            this.run(lastCommand);
        }

        if (command.equals("log out")){
            Controller.logout();
            this.run(lastCommand);
        }

        if (command.equals("back")) {
            this.parentMenu.run(lastCommand);
        }
    }
}
