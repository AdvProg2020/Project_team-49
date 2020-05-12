package View.Menu.OffsAndProductsMenu;

import Controller.Controller;
import Controller.Filter;
import Controller.OffAndProductMenuController;
import View.Menu.Menu;
import View.Menu.UserArea.UserArea;
import View.View;

public class Filtering extends Menu {
    public Filtering(Menu parentMenu) {
        super("Filtering", parentMenu);
    }

    @Override
    public void run(String lastCommand) {
        String command = scanner.nextLine().trim();
        View.printString("Filtering Menu:");

        if (command.equalsIgnoreCase("Show Available Filters")) {
            View.printAvailableFilters(OffAndProductMenuController.getAllAvailableFilters());
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("Filter")) {
            while (true) {
                String type = command.split("\\s")[1];

                if (type.equalsIgnoreCase("Name")) {
                    filterByName();
                    break;
                } else if (type.equalsIgnoreCase("Price")) {
                    filterByPrice();
                    break;
                } else if (type.equalsIgnoreCase("Brand")) {
                    filterByBrand();
                    break;
                } else if (type.equalsIgnoreCase("Availability")) {
                    filterByAvailability();
                    break;
                } else if (type.equalsIgnoreCase("Off")) {
                    filterByOff();
                    break;
                } else if (type.equalsIgnoreCase("Categories")) {
                    filterByCategory();
                    break;
                } else if (type.equalsIgnoreCase("Back")) {
                    this.run(lastCommand);
                } else
                    View.printString("Please Enter Valid Filter\n" +
                            "You Can Go Back And Check All Available Filter By Typing The (Show Available Filters)");
            }

            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("Current Filters")) {
            View.printCurrentFilter(Filter.showCurrentFilters());
            this.run(lastCommand);
        }

        if (command.equalsIgnoreCase("Disable Filter")) {
            while (true) {
                String type = command.split("\\s")[2];

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
                View.printString("Please Enter Valid Filter\n" +
                        "You Can Go Back And Check All Available Filter By Typing The (Show Available Filters)");
            }
            View.printFilteredProduct(OffAndProductMenuController.getCurrentId(),
                    OffAndProductMenuController.getCurrentName(),
                    OffAndProductMenuController.getCurrentPrice(),
                    OffAndProductMenuController.getCurrentOffPercentage(),
                    OffAndProductMenuController.doesCurrentOff());
            this.run(lastCommand);
        }

        //Ignore case ino che konam?
        if (command.startsWith("Search For")) {
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

    }

    //che lozomi dare ke menu bashe?
    private Menu filterByName() {
        return new Menu("FilterByName", this) {
            @Override
            public void run(String lastCommand) {
                String nameForFilter = scanner.nextLine();
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
                double minPrice = scanner.nextDouble();
                double maxPrice = scanner.nextDouble();
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
                String brandForFilter = scanner.nextLine();
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
                Filter.showSubCategories();
                String categoryForFilter = scanner.nextLine();
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
                String brandToDisable = scanner.nextLine();
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
