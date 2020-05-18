package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.OffAndProductMenuController;
import Controller.DataBase;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
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
        View.printString("\"Sorting Menu:\"");
        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("Show Available Sort")) {
                View.printAvailableSorting(OffAndProductMenuController.getAllAvailableSorting());
                this.run(lastCommand);
            }

            // to current nemire
            if (command.equalsIgnoreCase("Sort")) {
                View.printString("Enter Your Sort:");
                String sort;
                while (true){
                    sort=scanner.nextLine().trim();
                    if (OffAndProductMenuController.checkSortingInput(sort)){
                        break;
                    }
                    if (sort.equalsIgnoreCase("Back")){
                        this.run(lastCommand);
                    }
                    View.printString("Please Insert Valid Sorting\n" +
                            "You Can Check Available Sorts By Go Back By Typing (Back) And Typing (Show Available Sorts)");
                }
                OffAndProductMenuController.sorting(sort);

                View.printSortedProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("current sort")) {
                View.printCurrentSort(OffAndProductMenuController.getCurrentSort());
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("disable sort")) {
                OffAndProductMenuController.disableSort();

                View.printSortedProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log in")) {
                new UserArea(this).run(lastCommand);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log Out")) {
                Controller.logout();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Help")) {
                View.printSortingMenu();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("back")) {
                this.parentMenu.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Exit")){
                DataBase.endProgram();
            }

            View.printString("Please Insert Valid Instruction\n" +
                    "You Can Check Sorting Instruction By Typing Help.");
        }
    }
}
