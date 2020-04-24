package View.Menu.OffsAndProductsMenu;

import Controller.Filter;
import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.View;

public class Filtering extends Menu {
    public Filtering(Menu parentMenu) {
        super("Filtering", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        String command = scanner.nextLine().trim();
        if (command.equals("show available filters")) {
            View.printAvailableFilters(OffAndProductMenuController.getAllAvailableFilters());

            this.run(lastCommand);
        }
        if (command.equals("filter [an available filter]")) {
            String Type="";
            OffAndProductMenuController.filtering(command.split("\\s")[1],Type);
            //in ye aray liste moratab mide bayad bazesh konam.
            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());

            this.run(lastCommand);
        }
        if (command.equals("current filters")) {
            View.printCurrentFilter(Filter.getCurrentFilter());

            this.run(lastCommand);
        }
        if (command.equals("disable filter [a selected filter]")) {
            OffAndProductMenuController.disableFilter(command.split("\\s")[1]);

            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());
            this.run(lastCommand);
        }

        //bayad toye filtering bashe?albate mitone bashe daste khodemone
        if (command.equals("search for []")){
            String productName=command.split("\\s")[2];
            Filter.findProductByName(productName);
            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
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
