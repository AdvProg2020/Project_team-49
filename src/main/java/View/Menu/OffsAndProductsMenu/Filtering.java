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

        if (command.equals("filter [an available filter format]")) {
            String type=command.split("\\s")[1];

            if (type.equalsIgnoreCase("Name")){
                filterByName();
            }
            if (type.equalsIgnoreCase("Price")){
                filterByPrice();
            }
            if (type.equalsIgnoreCase("Brand")){
                filterByBrand();
            }
            if (type.equalsIgnoreCase("Availability")){
                filterByAvailability();
            }
            if (type.equalsIgnoreCase("Off")){
                filterByOff();
            }
            if (type.equalsIgnoreCase("Categories")){
                //in alan bayad categories har category ro neshon bede.

            }

            //
            OffAndProductMenuController.filtering(command.split("\\s")[1],type);
            //in ye aray liste moratab mide bayad bazesh konam.
            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurrentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());

            this.run(lastCommand);
        }

        if (command.equals("current filters")) {
            View.printCurrentFilter(Filter.showCurrentFilters());

            this.run(lastCommand);
        }

        if (command.equals("disable filter [a selected filter]")) {
            OffAndProductMenuController.disableFilter(command.split("\\s")[1]);

            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurrentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());
            this.run(lastCommand);
        }

        if (command.equals("search for []")){
            String productName=command.split("\\s")[2];
            Filter.filterByName(productName);
            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurrentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());

            this.run(lastCommand);
        }

        if (command.equals("back")) {
            this.parentMenu.run(lastCommand);
        }
    }

    //che lozomi dare ke menu bashe?
    private Menu filterByName(){
        return new Menu("filterByName",this) {
            @Override
            public void run(String lastCommand) {
                String nameForFilter=scanner.nextLine();
                Filter.filterByName(nameForFilter);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu filterByPrice(){
        return new Menu("filterByPrice",this) {
            @Override
            public void run(String lastCommand) {
                double minPrice=scanner.nextDouble();
                double maxPrice=scanner.nextDouble();
                Filter.filterByPrice(minPrice,maxPrice);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu filterByBrand(){
        return new Menu("filterByBrand",this) {
            @Override
            public void run(String lastCommand) {
                View.printAvailableBrand(Filter.getAvailableBrands());
                String brandForFilter=scanner.nextLine();
                Filter.filterByBrand(brandForFilter);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu filterByAvailability(){
        return new Menu("filterByAvailability",this) {
            @Override
            public void run(String lastCommand) {
                Filter.filterByAvailability();
                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu filterByOff(){
        return new Menu("filterByOff",this) {
            @Override
            public void run(String lastCommand) {
                Filter.filterByOffs();
                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }}
