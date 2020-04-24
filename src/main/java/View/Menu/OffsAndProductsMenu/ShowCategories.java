package View.Menu.OffsAndProductsMenu;

import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.View;

public class ShowCategories extends Menu {
    public ShowCategories(Menu parentMenu) {
        super("Show Categories", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        View.printCategories(OffAndProductMenuController.getCategoriesName());

        this.parentMenu.run(lastCommand);
    }

}
