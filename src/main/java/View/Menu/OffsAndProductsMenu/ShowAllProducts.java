package View.Menu.OffsAndProductsMenu;

import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.View;

public class ShowAllProducts extends Menu {
    public ShowAllProducts(Menu parentMenu) {
        super("Show All Products", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        View.printAllProduct(OffAndProductMenuController.getCurrentId(),
                OffAndProductMenuController.getCurentName(),
                OffAndProductMenuController.getCurrentPrice(),
                OffAndProductMenuController.getCurrentOffPercentage(),
                OffAndProductMenuController.doesCurrentOff());
        this.parentMenu.run(lastCommand);
    }
}
