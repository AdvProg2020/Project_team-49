package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;

public class Comments extends Menu {
    private long productId;

    public Comments(Menu parentMenu, long productId) {
        super("Comments", parentMenu);
        this.productId = productId;
    }

    //

    @Override
    public void run(String lastCommand) {

        while (true) {
            View.printString("\"Comments Menu:\"");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("Add comment")) {
                View.printString("Title:");
                String title = scanner.nextLine();
                View.printString("Content:");
                String content = scanner.nextLine();
                OffAndProductMenuController.addCommentsById(productId, title, content);
                View.printString("Comment Added Successfully.");
                this.parentMenu.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Log In")) {
                new UserArea(this);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Log Out")) {
                Controller.logout();
                allMenus.get(0).run("");
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("back")) {
                this.parentMenu.run(lastCommand);
            }

            if (command.equalsIgnoreCase("help")) {
                View.printCommentsMenu();
                this.run(lastCommand);
            }

            View.printString("invalid command");
            this.run(lastCommand);
        }
    }
}
