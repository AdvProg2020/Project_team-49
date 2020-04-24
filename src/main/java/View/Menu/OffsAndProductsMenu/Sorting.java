package View.Menu.OffsAndProductsMenu;

import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.View;

public class Sorting extends Menu {

    public Sorting(Menu parentMenu) {
        super("Sorting", parentMenu);
    }

    @Override
    public String getCommandKey(String command) {
        return null;
    }

    @Override
    public void run(String lastCommand) {
        String command = scanner.nextLine().trim();
        if (command.equals("show available sorts")) {
            View.printAvailableSorting(OffAndProductMenuController.getAllAvailableSorting());
            this.run(lastCommand);
        }
        if (command.equals("sort [an available sort]")) {
            OffAndProductMenuController.sorting(command.split("\\s")[1]);

            View.printSortedProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());

            this.run(lastCommand);
        }
        if (command.equals("current sort")) {
            View.printCurrentSort(OffAndProductMenuController.getCurrentSort());

            this.run(lastCommand);
        }
        if (command.equals("disable sort")) {
            OffAndProductMenuController.disableSort(command.split("\\s")[2]);

            View.printSortedProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());

            this.run(lastCommand);
        }
        if (command.equals("back")) {
            this.parentMenu.run(lastCommand);
        }
    }
}
