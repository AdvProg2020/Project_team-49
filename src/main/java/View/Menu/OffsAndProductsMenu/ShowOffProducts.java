package View.Menu.OffsAndProductsMenu;

import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.View;

public class ShowOffProducts extends Menu {
    public ShowOffProducts(Menu parentMenu) {
        super("Show Off Products", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        View.printAllOffProduct(OffAndProductMenuController.getCurrentId(),
                                OffAndProductMenuController.getCurentName(),
                                OffAndProductMenuController.getCurrentPrice(),
                                OffAndProductMenuController.getCurrentOffPercentage());
        this.parentMenu.run(lastCommand);
    }
}
