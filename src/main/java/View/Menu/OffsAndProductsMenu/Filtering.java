package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.Filter;
import Controller.OffAndProductMenuController;
import Controller.DataBase;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;

public class Filtering extends Menu {
    public Filtering(Menu parentMenu) {
        super("Filtering", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        View.printString("\"Filtering Menu:\"");
        while (true) {
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("Show Available Filters")) {
                View.printAvailableFilters(OffAndProductMenuController.getAllAvailableFilters());
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Filter")) {
                while (true) {
                    View.printFilterSthMenu();
                    String type = scanner.nextLine().trim();
                    if (type.equalsIgnoreCase("Name")) {
                        filterByName().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Price")) {
                        filterByPrice().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Brand")) {
                        filterByBrand().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Availability")) {
                        filterByAvailability().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Off")) {
                        filterByOff().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Categories")) {
                        filterByCategory().run(lastCommand);
                        break;
                    } else if (type.equalsIgnoreCase("Back")) {
                        this.run(lastCommand);
                    } else
                        View.printString("Please Enter Valid Filter\n" +
                                "You Can Go Back Or Check All Available Filter Down Here");
                }

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Current Filter")) {
                View.printCurrentFilter(Filter.showCurrentFilters());
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Disable Filter")) {
                View.printCurrentFilter(Filter.showCurrentFilters());
                while (true) {
                    View.printString("Please Enter The Filter You Want to Disable");
                    String type = scanner.nextLine().trim();

                    if (type.equalsIgnoreCase("Name")) {
                        Filter.disableNameFilter();
                        break;
                    }
                    if (type.equalsIgnoreCase("Price")) {
                        Filter.disablePriceFilter();
                        break;
                    }
                    if (type.equalsIgnoreCase("Brand")) {
                        disableFilterByBrand();
                        break;
                    }
                    if (type.equalsIgnoreCase("Availability")) {
                        Filter.disableAvailabilityFilter();
                        break;
                    }
                    if (type.equalsIgnoreCase("Off")) {
                        Filter.disableOffsFilter();
                        break;
                    }
                    if (type.equalsIgnoreCase("Categories")) {
                        Filter.disableCategoryFilter();
                        break;
                    }
                    if (type.equalsIgnoreCase("AgainPlease")){
                        View.printCurrentFilter(Filter.showCurrentFilters());
                        continue;
                    }
                    if (type.equalsIgnoreCase("Back")){
                        this.run(lastCommand);
                    }
                    View.printString("Please Enter Valid Filter\n" +
                            "You Can Go Back By Typing (Back) Or Check Current Filter By Typing (AgainPlease)");
                }
                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());
                this.run(lastCommand);
            }

            //Ignore case ino che konam?
            if (command.toLowerCase().startsWith("search for")) {
                String productName = command.split("\\s")[2];
                Filter.filterByName(productName);
                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log in")) {
                new UserArea(this);
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("log out")) {
                Controller.logout();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Help")) {
                View.printFilteringMenu();
                this.run(lastCommand);
            }

            if (command.equalsIgnoreCase("back")) {
                this.parentMenu.run(lastCommand);
            }

            if (command.equalsIgnoreCase("Exit")){
                DataBase.endProgram();
            }

            View.printString("invalid command");
        }
    }

    //che lozomi dare ke menu bashe?
    private Menu filterByName() {
        return new Menu("FilterByName", this) {
            @Override
            public void run(String lastCommand) {
                View.printString("Type Your Name For Filter");
                String nameForFilter = scanner.nextLine().trim();
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

    private Menu filterByPrice() {
        return new Menu("FilterByPrice", this) {
            @Override
            public void run(String lastCommand) {
                double minPrice;
                double maxPrice;
                while (true) {
                    View.printString("Type Min Price:");
                    String minPriceString = scanner.nextLine();
                    if (minPriceString.trim().equalsIgnoreCase("Back")){
                        parentMenu.run(lastCommand);
                    }
                    if (minPriceString.matches("^(-|)\\d+$")){
                        if (Double.parseDouble(minPriceString)<0){
                            View.printString("HA HA That Was SOO FUNNY\n" +
                                    "Now Enter Sth More Than Zero!\n" +
                                    "You Can even Go Back By Typing (Back)");
                            continue;
                        }
                        minPrice=Double.parseDouble(minPriceString);
                        break;
                    }
                    View.printString("Really?!\n" +
                            "please Insert Number.You Can even Go Back By Typing (Back)!");
                }
                while (true) {
                    View.printString("Type Max Price:");
                    String maxPriceString = scanner.nextLine();
                    if (maxPriceString.trim().equalsIgnoreCase("Back")){
                        parentMenu.run(lastCommand);
                    }
                    if (maxPriceString.trim().equalsIgnoreCase("MinPrice")){
                        this.run(lastCommand);
                    }
                    if (maxPriceString.matches("^(-|)\\d+$")){
                        if (Double.parseDouble(maxPriceString)<0){
                            View.printString("HA HA That Was SOO FUNNY\n" +
                                    "Now Enter Sth More Than Zero!\n" +
                                    "Or You Can Type (MinPrice) To Enter MinPrice Again.\n" +
                                    "You Can Even Go Back By Typing (Back)");
                            continue;
                        }
                        maxPrice=Double.parseDouble(maxPriceString);
                        if (maxPrice<minPrice){
                            View.printString("Oh What Just Happen?! Your Max Price Is Smaller Than Min Price!\n" +
                                    "Please Enter Max Price Again Or You Can Type (MinPrice) To Enter MinPrice Again.\n" +
                                    "You Can Even Go Back By Typing (Back)");
                            continue;
                        }
                        break;
                    }
                    View.printString("Really?!\n" +
                            "please Insert Number.You Can even Go Back By Typing (Back)\n" +
                            "You Can Type (MinPrice) To Enter MinPrice Again!");
                }
                Filter.filterByPrice(minPrice, maxPrice);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu filterByBrand() {
        return new Menu("FilterByBrand", this) {
            @Override
            public void run(String lastCommand) {
                View.printAvailableBrand(Filter.getAvailableBrands());
                String brandForFilter;
                while (true){
                    brandForFilter = scanner.nextLine();
                    if (brandForFilter.trim().equalsIgnoreCase("back")){
                        parentMenu.run(lastCommand);
                    }
                    if (brandForFilter.trim().equalsIgnoreCase("AgainPlease")){
                        View.printAvailableBrand(Filter.getAvailableBrands());
                        continue;
                    }
                    if (!OffAndProductMenuController.checkFilteringByBrand(brandForFilter)){
                        View.printString("Please Enter Valid Brand\n" +
                                "You Can even Go Back By Type (Back) Or Check Available Brand By Type (AgainPlease)");
                        continue;
                    }
                    break;
                }
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

    private Menu filterByAvailability() {
        return new Menu("FilterByAvailability", this) {
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

    private Menu filterByOff() {
        return new Menu("FilterByOff", this) {
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
    }

    private Menu filterByCategory() {
        return new Menu("FilterByCategory", this) {
            @Override
            public void run(String lastCommand) {
                View.printCategories(Filter.showSubCategories());
                String categoryForFilter;
                while (true){
                    View.printString("Please Type One Of Category");
                    categoryForFilter = scanner.nextLine();
                    if (categoryForFilter.trim().equalsIgnoreCase("Back")){
                        parentMenu.run(lastCommand);
                    }
                    if (categoryForFilter.trim().equalsIgnoreCase("AgainPlease")){
                        View.printCategories(Filter.showSubCategories());
                        continue;
                    }
                    if (!OffAndProductMenuController.checkFilteringByCategory(categoryForFilter)){
                        View.printString("Please Enter Valid Category\n" +
                                "You Can even Go Back By Type (Back) Or Check Available Brand By Type (AgainPlease)");
                        continue;
                    }
                    break;
                }
                Filter.filterByCategory(categoryForFilter);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

    private Menu disableFilterByBrand() {
        return new Menu("DisableFilterByBrand", this) {
            @Override
            public void run(String lastCommand) {
                //getAvailableBrands check beshe.
                String brandToDisable = scanner.nextLine();
                // bayad check beshe ke chi ro disable mikone.
                Filter.disableBrandFilter(brandToDisable);

                View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                        OffAndProductMenuController.getCurrentName(),
                        OffAndProductMenuController.getCurrentPrice(),
                        OffAndProductMenuController.getCurrentOffPercentage(),
                        OffAndProductMenuController.doesCurrentOff());

                this.parentMenu.run(lastCommand);
            }
        };
    }

}
