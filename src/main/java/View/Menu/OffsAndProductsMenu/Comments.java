package View.Menu.OffsAndProductsMenu;

import Controller.OffAndProductMenuController;
import View.Menu.Menu;

public class Comments extends Menu {
    private long productId;

    public Comments(Menu parentMenu, long productId) {
        super("Comments", parentMenu);
        this.productId=productId;
    }

    @Override
    public void run(String lastCommand) {
        String command = scanner.nextLine().trim();
        String title;
        String content;
        if (command.equals("Add comment")) {
            System.out.println("Title:");
            title = scanner.nextLine();
            System.out.println("Content:");
            content = scanner.nextLine();
            OffAndProductMenuController.addCommentsById(productId, title, content);
            this.parentMenu.run(lastCommand);
        }
        if (command.equals("back")) {
            this.parentMenu.run(lastCommand);
        }
        //agar invalid bood chi?
    }
}
