package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.Menu.UserArea.GuestArea;
import View.Menu.UserArea.UserArea;

public class Digest extends Menu {

    public Digest(Menu parentMenu) {
        super("Digest", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        long productId = Long.parseLong(lastCommand.split("\\s")[2]);
        String command = scanner.nextLine().trim();
        if (command.equals("add to cart")) {
            String type= Controller.getCurrentUserType();
            if (type.equals("Geust")){
                new GuestArea(this);
            }
            //havasam bashe ke Guest ham mitone sabad dashte bashe.
            OffAndProductMenuController.addToCartById(productId);
            this.run(lastCommand);
        }
        if (command.equals("select seller [seller_username]")) {
            //che konam?
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
