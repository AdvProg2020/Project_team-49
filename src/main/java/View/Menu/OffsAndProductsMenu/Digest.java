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

        View.printString("Digest Menu:");

        long productId = Long.parseLong(lastCommand.split("\\s")[2]);
        String command = scanner.nextLine().trim();

        if (command.equalsIgnoreCase("add to cart")) {

            if (OffAndProductMenuController.isCurrentUserGuestOrUser()) {
                View.printString("How Many/Much Do You Want?");
                String count="";
                while (true) {
                    count = scanner.nextLine().trim();
                    if (count.matches("^\\d+$")){
                        if (Integer.parseInt(count)==0){
                            View.printString("Really?!\nEnter Something More Than ZERO");
                            continue;
                        }
                        if (!OffAndProductMenuController.checkRemainCountForBuy(productId,null,Integer.parseInt(count))){
                            View.printString("Sorry You Want To Pic More Than Remain!:(\n" +
                                    "If still You Want To Add This Item To Your Cart Please Try Again.");
                            this.run(lastCommand);
                        }
                        break;
                    }
                    View.printString("HA HA HA That Was SOO FUNNY :|\nNow Enter Valid Number :/");
                }
                OffAndProductMenuController.addToCartById(productId,true,null,Integer.parseInt(count));
                View.printAddToCardSuccessfullyDone();
                this.run(lastCommand);
            }
            View.printOnlyUserOrGuestCanBuyProduct();
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("select seller [seller_username]")) {
            String userName=command.split("\\s")[2];
            if (OffAndProductMenuController.isCurrentUserGuestOrUser()) {
                View.printString("How Many/Much Do You Want?");
                String count="";
                while (true) {
                    count = scanner.nextLine().trim();
                    if (count.matches("^\\d+$")){
                        if (Integer.parseInt(count)==0){
                            View.printString("Really?!\nEnter Something More Than ZERO");
                            continue;
                        }
                        if (!OffAndProductMenuController.checkRemainCountForBuy(productId,userName,Integer.parseInt(count))){
                            View.printString("Sorry You Want To Pic More Than Remain!:(\n" +
                                    "If still You Want To Add This Item To Your Cart Please Try Again.");
                            this.run(lastCommand);
                        }
                        break;
                    }
                    View.printString("HA HA HA That Was SOO FUNNY :|\nNow Enter Valid Number :/");
                }
                OffAndProductMenuController.addToCartById(productId,true,userName,Integer.parseInt(count));
                View.printAddToCardSuccessfullyDone();
                this.run(lastCommand);
            }
            View.printOnlyUserOrGuestCanBuyProduct();
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("log in")){
            new UserArea(this);
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("log out")){
            Controller.logout();
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("Help")){
            View.printDigestMenu();
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("back")) {
            this.parentMenu.run(lastCommand);
        }
    }
}
